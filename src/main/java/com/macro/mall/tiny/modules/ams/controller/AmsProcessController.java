package com.macro.mall.tiny.modules.ams.controller;


import com.macro.mall.tiny.common.api.CommonResult;
import com.macro.mall.tiny.modules.ams.dto.AmsProcessReimbursementParam;
import com.macro.mall.tiny.modules.ams.model.AmsReimbursement;
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
	public CommonResult create(@RequestBody AmsProcessReimbursementParam processReimbursementParam) {
		boolean success = amsProcessService.createReimbursement(processReimbursementParam);
		if (success) {
			return CommonResult.success(null);
		}
		return CommonResult.failed();
	}
}

