package com.macro.mall.tiny.modules.ams.service;

import com.macro.mall.tiny.modules.ams.dto.*;
import com.macro.mall.tiny.modules.ams.model.AmsProcess;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 审批流程表 服务类
 * </p>
 *
 * @author macro
 * @since 2023-01-31
 */
public interface AmsProcessService extends IService<AmsProcess> {
	/*
	* 创建报销单*/
	@Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
	boolean createReimbursement(AmsProcessReimbursementParam processReimbursementParam);
	
	@Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
	boolean createBuyPlan(AmsProcessBuyPlanParam processBuyPlanParam);
	
	@Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
	boolean createPayApply(AmsProcessPayApplyParam processPayApplyParam);
	
	@Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
	boolean createAdvance(AmsProcessAdvanceParam processAdvanceParam);
	
	@Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
	boolean createContract(AmsProcessContractParam processContractParam);
	
	@Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
	boolean createProject(AmsProcessProjectParam processProjectParam);
	
}
