package com.macro.mall.tiny.modules.ams.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.macro.mall.tiny.modules.ams.dto.*;
import com.macro.mall.tiny.modules.ams.mapper.*;
import com.macro.mall.tiny.modules.ams.model.*;
import com.macro.mall.tiny.modules.ams.service.AmsBuyplanService;
import com.macro.mall.tiny.modules.ams.service.AmsProcessService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.macro.mall.tiny.modules.ams.service.AmsReimbursementDetailsService;
import com.macro.mall.tiny.modules.ums.model.UmsAdmin;
import com.macro.mall.tiny.modules.ums.model.UmsAdminRoleRelation;
import com.macro.mall.tiny.modules.ums.model.UmsResource;
import com.macro.mall.tiny.modules.ums.service.UmsAdminCacheService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
	AmsBuyplanService amsBuyplanService;
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
	
	@Autowired
	AmsReimbursementDetailsService reimbursementDetailsService;
	
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
			return amsBuyplanService.saveBatch(planDetailsList);
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


