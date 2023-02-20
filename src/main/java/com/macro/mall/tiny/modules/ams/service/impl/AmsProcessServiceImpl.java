package com.macro.mall.tiny.modules.ams.service.impl;

import com.macro.mall.tiny.modules.ams.dto.AmsProcessReimbursementParam;
import com.macro.mall.tiny.modules.ams.mapper.AmsReimbursementMapper;
import com.macro.mall.tiny.modules.ams.model.AmsProcess;
import com.macro.mall.tiny.modules.ams.mapper.AmsProcessMapper;
import com.macro.mall.tiny.modules.ams.model.AmsReimbursement;
import com.macro.mall.tiny.modules.ams.service.AmsProcessService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.macro.mall.tiny.modules.ums.model.UmsAdmin;
import com.macro.mall.tiny.modules.ums.service.UmsAdminCacheService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Date;

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
	AmsReimbursementMapper reimbursementMapper;
	@Override
	public boolean createReimbursement(AmsProcessReimbursementParam processReimbursementParam) {
		UmsAdmin currentAdmin = adminCacheService.getAdminBySecurity();
		
		/*创建流程*/
		AmsProcess process = new AmsProcess();
		BeanUtils.copyProperties(processReimbursementParam, process);
		process.setId(null);
		process.setApplyUserId(currentAdmin.getId());
		process.setCreateTime(new Date());
		baseMapper.insert(process);
		Long addProcessId = process.getId();
		
		/*创建报销单*/
		AmsReimbursement reimbursement = new AmsReimbursement();
		BeanUtils.copyProperties(processReimbursementParam, reimbursement);
		reimbursement.setId(null);
		reimbursement.setProId(addProcessId);
		reimbursementMapper.insert(reimbursement);
		
		Long addReimbursementId = reimbursement.getId();
		/*关联报销单-明细*/
		
		return true;
	}
}


