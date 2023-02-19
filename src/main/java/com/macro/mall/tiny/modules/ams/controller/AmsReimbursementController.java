package com.macro.mall.tiny.modules.ams.controller;


import com.macro.mall.tiny.common.api.CommonResult;
import com.macro.mall.tiny.modules.ams.model.AmsProcess;
import com.macro.mall.tiny.modules.ams.model.AmsReimbursement;
import com.macro.mall.tiny.modules.ams.service.AmsReimbursementService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 报销单表 前端控制器
 * </p>
 *
 * @author macro
 * @since 2023-01-31
 */
@RestController
@RequestMapping("reimbursement")
public class AmsReimbursementController {
	@Autowired
	private AmsReimbursementService reimbursementService;
	
	@ApiOperation("添加报销单")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult create(@RequestBody AmsReimbursement reimbursement) {
		boolean success = reimbursementService.create(reimbursement);
		if (success) {
			return CommonResult.success(null);
		}
		return CommonResult.failed();
	}
}

