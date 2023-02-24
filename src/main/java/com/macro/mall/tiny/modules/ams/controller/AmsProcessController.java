package com.macro.mall.tiny.modules.ams.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.macro.mall.tiny.common.api.CommonPage;
import com.macro.mall.tiny.common.api.CommonResult;
import com.macro.mall.tiny.modules.ams.dto.*;
import com.macro.mall.tiny.modules.ams.model.AmsProcess;
import com.macro.mall.tiny.modules.ams.service.AmsProcessService;
import com.macro.mall.tiny.modules.ums.model.UmsResource;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
	
	@ApiOperation("分页查询流程")
	@RequestMapping(value = "/handleList", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<IPage<AmsProcess>> handleList(@RequestParam(required = false) Long applyTypeId,
																												 @RequestParam(required = false) String nameKeyword,
																												 @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
																												 @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
		IPage<AmsProcess> processList = amsProcessService.handleList(applyTypeId,nameKeyword,pageNum, pageSize);
		return CommonResult.success(processList);
	}
	
	/*@ApiOperation("分页查询流程")
	@RequestMapping(value = "/handleList", method = RequestMethod.GET)
	@ResponseBody
	public RestResponceBody<AmsProcess> findGpsRecordPage(@RequestParam(required = false) Long applyTypeId,
																												@RequestParam(required = false) String nameKeyword,
																												@RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
																												@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum){
		// 结论就是当你的参数中加了  new Page 之后,他会自动对你这个方法分页很强大的呀
		IPage<GpsRecordModel> gpsEntityIPage = gpsRecordService.findPage(new Page<GpsRecordModel>(pageNo, pageSize).addOrder(OrderItem.desc("a.create_time")),placeName);
		return new RestResponceBody<GpsRecordModel>(gpsEntityIPage) ;
	}*/
	
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

