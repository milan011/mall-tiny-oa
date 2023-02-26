package com.macro.mall.tiny.modules.ams.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.macro.mall.tiny.modules.ams.dto.*;
import com.macro.mall.tiny.modules.ams.mapper.*;
import com.macro.mall.tiny.modules.ams.model.*;
import com.macro.mall.tiny.modules.ams.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.macro.mall.tiny.modules.ums.model.UmsAdmin;
import com.macro.mall.tiny.modules.ums.service.UmsAdminCacheService;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 * 审批流程表 服务实现类
 * </p>
 *
 * @author macro
 * @since 2023-01-31
 */
@Service
public class AmsProcessServiceImpl extends ServiceImpl<AmsProcessMapper, AmsProcess> implements AmsProcessService {
	
	@Autowired
	UmsAdminCacheService adminCacheService;
	@Autowired
	AmsReimbursementService reimbursementService;
	@Autowired
	AmsReimbursementDetailsService reimbursementDetailsService;
	@Autowired
	AmsBuyplanService buyplanService;
	@Autowired
	AmsProjectService projectService;
	@Autowired
	AmsContractService contractService;
	@Autowired
	AmsPayApplyService payApplyService;
	@Autowired
	AmsAdvancepayService advancepayService;
	@Autowired
	AmsReimbursementMapper reimbursementMapper;
	
	@Autowired
	AmsPayApplyMapper amsPayApplyMapper;
	
	@Autowired
	AmsAdvancepayMapper advancepayMapper;
	
	@Autowired
	AmsContractMapper contractMapper;
	
	@Autowired
	AmsProjectMapper projectMapper;
	
	@Override
	public IPage<AmsProcess> handleList(Long applyTypeId, String nameKeyword, Integer pageNum, Integer pageSize) {
		/*Page<AmsProcess> page = new Page<>(pageNum,pageSize);
		QueryWrapper<AmsProcess> wrapper = new QueryWrapper<>();
		LambdaQueryWrapper<AmsProcess> lambda = wrapper.lambda();
		lambda.eq(AmsProcess::getStatus, 1);
		if(applyTypeId!=null){
			lambda.eq(AmsProcess::getApplyTypeId,applyTypeId);
		}
		if(StrUtil.isNotEmpty(nameKeyword)){
			lambda.like(AmsProcess::getName,nameKeyword);
		}
		return page(page,wrapper);*/
		UmsAdmin currentAdmin = adminCacheService.getAdminBySecurity();
		Long currentAdminId = currentAdmin.getId();
		Page<AmsProcess> page = new Page<>(pageNum, pageSize);
		return baseMapper.getHandleProcess(page, currentAdminId,applyTypeId, nameKeyword);
	}
	public Boolean handleProcess(HashMap<String, Object> map){
		/*修改审核内容相关*/
		Integer processId = (Integer) map.get("id");
		AmsProcess process = baseMapper.selectById(processId);
		if(StringUtil.isNullOrEmpty(process.getStepsConcent())){ //第一步
		
		}
		HashMap<String, Object> concentMap = new HashMap<>();
		List<HashMap<String, Object>> concentListMap = new ArrayList<>();
		concentMap.put("description", map.get("description"));
		concentMap.put("examineHandle", map.get("examineHandle"));
		concentMap.put("examineUser", map.get("examineUser"));
		concentMap.put("examineUserRole", map.get("examineUserRole"));
		concentMap.put("examineTime", new Date());
		
		concentListMap.add(concentMap);
		
		String json = JSONUtil.toJsonStr(concentListMap);
		List<HashMap> list = JSONUtil.toList(json, HashMap.class);
		//JSONObject jsonObject = JSONUtil.parseObj(process.getStepsConcent());
		/*修改审核状态*/
		/*关联审核-流程记录关系表*/
		return true;
	}
	@Override
	public HashMap<String, Object> getProcessDetail(Long id){
		HashMap<String, Object> data = new HashMap<>();
		/*审批基本信息*/
		HashMap<String, Object> process = baseMapper.getProcessInfoById(id);
		//String[] arr = {"name"};
		//Map<Object, Object> baseInfo = MapUtil.getAny(process, arr);
		/*各审批单相关信息*/
		//HashMap<String, Object> concreteInfo =  getConcreteByType(id, (Long) process.get("apply_type_id"));
		getConcreteByType(data, id, (Long) process.get("apply_type_id"));
		data.put("baseInfo", process);
		//data.put("concreteInfo", concreteInfo);
	
		return data;
	}
	
	private Boolean getConcreteByType(HashMap<String, Object> data, Long id, Long apply_type_id){
		//HashMap<String, Object> dataConcrete = new HashMap<>();
		if (apply_type_id.equals(1L)){ //报销单详情
			AmsReimbursement remiInfo = reimbursementService.getRemibursementInfo(id);
			List<AmsReimbursementDetails> reimbursementDetails = reimbursementDetailsService.getBillList(remiInfo.getId());
			data.put("concreteInfo", remiInfo);
			data.put("billList", reimbursementDetails);
		}
		if (apply_type_id.equals(2L)){ //付款申请单详情
			AmsPayApply payApplyInfo = payApplyService.getInfo(id);
			data.put("concreteInfo", payApplyInfo);
		}
		if (apply_type_id.equals(3L)){ //预付款项报账单详情
			AmsAdvancepay info = advancepayService.getInfo(id);
			data.put("concreteInfo", info);
		}
		if (apply_type_id.equals(4L)){ //采购计划审批单详情
			List<AmsBuyplan> info = buyplanService.getInfo(id);
			data.put("planList", info);
		}
		if (apply_type_id.equals(5L)){ //合同会签详情
			AmsContract info = contractService.getInfo(id);
			data.put("concreteInfo", info);
		}
		if (apply_type_id.equals(6L)){ //工程项目付款审批单详情
			AmsProject info = projectService.getInfo(id);
			data.put("concreteInfo", info);
		}
		return true;
	}
	@Override
	public boolean createReimbursement(AmsProcessReimbursementParam processReimbursementParam) {
		//UmsAdmin currentAdmin = adminCacheService.getAdminBySecurity();
		
		/*创建流程*/
		AmsProcess process = new AmsProcess();
		BeanUtils.copyProperties(processReimbursementParam, process);
		/*process.setId(null);
		process.setApplyUserId(currentAdmin.getId());
		process.setCreateTime(new Date());
		baseMapper.insert(process);
		Long addProcessId = process.getId();*/
		
		Long addProcessId = createPross(process);
		
		/*创建报销单*/
		AmsReimbursement reimbursement = new AmsReimbursement();
		BeanUtils.copyProperties(processReimbursementParam, reimbursement);
		reimbursement.setId(null);
		reimbursement.setProId(addProcessId);
		reimbursementMapper.insert(reimbursement);
		
		Long addReimbursementId = reimbursement.getId();
		/*关联报销单-明细*/
		
		List<AmsReimbursementDetails> detailsList = processReimbursementParam.getRemiDetailsList();
		if (detailsList != null && detailsList.size() > 0) {
			for (AmsReimbursementDetails details : detailsList) {
				details.setReimId(addReimbursementId);
			}
			return reimbursementDetailsService.saveBatch(detailsList);
			/*for (Long roleId : roleIds) {
				UmsAdminRoleRelation roleRelation = new UmsAdminRoleRelation();
				roleRelation.setAdminId(adminId);
				roleRelation.setRoleId(roleId);
				list.add(roleRelation);
			}
			adminRoleRelationService.saveBatch(list);*/
			
		}else{
			return addReimbursementId > 0;
		}
	}
	
	@Override
	public boolean createBuyPlan(AmsProcessBuyPlanParam processBuyPlanParam) {
		/*创建流程*/
		AmsProcess process = new AmsProcess();
		BeanUtils.copyProperties(processBuyPlanParam, process);
		Long addProcessId = createPross(process);
		
		/*关联物资明细*/
		List<AmsBuyplan> planDetailsList = processBuyPlanParam.getPlanDetailsList();
		if(planDetailsList != null && planDetailsList.size() > 0){
			for(AmsBuyplan plan : planDetailsList){
				plan.setProId(addProcessId);
			}
			return buyplanService.saveBatch(planDetailsList);
		}else{
			return false;
		}
	}
	
	@Override
	public boolean createPayApply(AmsProcessPayApplyParam processPayApplyParam) {
		/*创建流程*/
		AmsProcess process = new AmsProcess();
		BeanUtils.copyProperties(processPayApplyParam, process);
		Long addProcessId = createPross(process);
		
		/*创建付款审批单*/
		AmsPayApply amsPayApply = new AmsPayApply();
		BeanUtils.copyProperties(processPayApplyParam, amsPayApply);
		amsPayApply.setId(null);
		amsPayApply.setProId(addProcessId);
		amsPayApplyMapper.insert(amsPayApply);
		
		return amsPayApply.getId() > 0;
	}
	
	public boolean createAdvance(AmsProcessAdvanceParam processAdvanceParam) {
		/*创建流程*/
		AmsProcess process = new AmsProcess();
		BeanUtils.copyProperties(processAdvanceParam, process);
		Long addProcessId = createPross(process);
		/*创建预付款审批单*/
		AmsAdvancepay advancepay = new AmsAdvancepay();
		BeanUtils.copyProperties(processAdvanceParam, advancepay);
		advancepay.setId(null);
		advancepay.setProId(addProcessId);
		advancepayMapper.insert(advancepay);
		
		return advancepay.getId() > 0;
	}
	
	public boolean createContract(AmsProcessContractParam processContractParam) {
		/*创建流程*/
		AmsProcess process = new AmsProcess();
		BeanUtils.copyProperties(processContractParam, process);
		Long addProcessId = createPross(process);
		/*创建合同审批单*/
		AmsContract contract = new AmsContract();
		BeanUtils.copyProperties(processContractParam, contract);
		contract.setId(null);
		contract.setProId(addProcessId);
		contractMapper.insert(contract);
		
		return contract.getId() > 0;
	}
	
	public boolean createProject(AmsProcessProjectParam processProjectParam) {
		/*创建流程*/
		AmsProcess process = new AmsProcess();
		BeanUtils.copyProperties(processProjectParam, process);
		Long addProcessId = createPross(process);
		
		/*创建工程项目审批单*/
		AmsProject project = new AmsProject();
		BeanUtils.copyProperties(processProjectParam, project);
		project.setId(null);
		project.setProId(addProcessId);
		projectMapper.insert(project);
		
		return project.getId() > 0;
	}
	
	private Long createPross(AmsProcess process){
		UmsAdmin currentAdmin = adminCacheService.getAdminBySecurity();
		
		process.setId(null);
		process.setApplyUserId(currentAdmin.getId());
		process.setCreateTime(new Date());
		baseMapper.insert(process);
		Long addProcessId = process.getId();
		
		return addProcessId;
	}
}


