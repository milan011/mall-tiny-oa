package com.macro.mall.tiny.modules.ams.controller;

import com.macro.mall.tiny.common.api.CommonResult;
import com.macro.mall.tiny.modules.ams.model.AmsActivitySign;
import com.macro.mall.tiny.modules.ams.service.AmsActivitySignService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * <p>
 * 活动-报名表 前端控制器
 * </p>
 *
 * @author macro
 * @since 2022-08-13
 */
@RestController
@Tag(name = "AmsActivityController",description = "报名管理")
@RequestMapping("/ams/amsActivitySign")
public class AmsActivitySignController {
	@Autowired
	AmsActivitySignService amsActivitySignService;

	@ApiOperation(value = "报名活动")
	@RequestMapping(value = "/sign", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<?> sign(@RequestBody AmsActivitySign amsActivitySign) {
		System.out.println(amsActivitySign);
		boolean success = amsActivitySignService.create(amsActivitySign);
		if (success) {
			return CommonResult.success(amsActivitySign.getSignCode());
		}
		return CommonResult.failed("已经报名该活动le");
	}

}

