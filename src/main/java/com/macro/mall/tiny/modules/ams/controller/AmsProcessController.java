package com.macro.mall.tiny.modules.ams.controller;


import com.macro.mall.tiny.common.api.CommonResult;
import com.macro.mall.tiny.modules.ams.dto.*;
import com.macro.mall.tiny.modules.ams.service.AmsProcessService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 审批流程表 前端控制器
 * </p>
 *
 * @author macro
 * @since 2023-01-31
 */
@RestController
@RequestMapping("process")
public class AmsProcessController {
	@Autowired
	AmsProcessService amsProcessService;
	@ApiOperation("添加报销单")
	@RequestMapping(value = "/createForReimbursement", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult createForReimbursement(@RequestBody AmsProcessReimbursementParam processReimbursementParam) {
		boolean success = amsProcessService.createReimbursement(processReimbursementParam);
		if (success) {
			return CommonResult.success(null);
		}
		return CommonResult.failed();
	}
	
	@ApiOperation("添加采购计划审批单")
	@RequestMapping(value = "/createForBuyPlan", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult createForBuyPlan(@RequestBody AmsProcessBuyPlanParam processBuyPlanParam) {
		boolean success = amsProcessService.createBuyPlan(processBuyPlanParam);
		if (success) {
			return CommonResult.success(null);
		}
		return CommonResult.failed();
	}
	
	@ApiOperation("添加付款审批单")
	@RequestMapping(value = "/createForPayApply", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult createForPayApply(@RequestBody AmsProcessPayApplyParam processPayApplyParam) {
		boolean success = amsProcessService.createPayApply(processPayApplyParam);
		if (success) {
			return CommonResult.success(null);
		}
		return CommonResult.failed();
	}
	
	@ApiOperation("添加预付款审批单")
	@RequestMapping(value = "/createForAdvance", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult createForAdvance(@RequestBody AmsProcessAdvanceParam processAdvanceParam) {
		boolean success = amsProcessService.createAdvance(processAdvanceParam);
		if (success) {
			return CommonResult.success(null);
		}
		return CommonResult.failed();
	}
	
	@ApiOperation("添加合同审批单")
	@RequestMapping(value = "/createForContract", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult createForContract(@RequestBody AmsProcessContractParam processContractParam) {
		boolean success = amsProcessService.createContract(processContractParam);
		if (success) {
			return CommonResult.success(null);
		}
		return CommonResult.failed();
	}
	
	@ApiOperation("添加工程项目审批单")
	@RequestMapping(value = "/createForProject", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult createForProject(@RequestBody AmsProcessProjectParam processProjectParam) {
		boolean success = amsProcessService.createProject(processProjectParam);
		if (success) {
			return CommonResult.success(null);
		}
		return CommonResult.failed();
	}
}

