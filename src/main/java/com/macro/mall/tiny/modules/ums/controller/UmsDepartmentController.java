package com.macro.mall.tiny.modules.ums.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.macro.mall.tiny.common.api.CommonPage;
import com.macro.mall.tiny.common.api.CommonResult;
import com.macro.mall.tiny.modules.ums.model.UmsDepartment;
import com.macro.mall.tiny.modules.ums.service.UmsDepartmentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 后台部门表 前端控制器
 * </p>
 *
 * @author macro
 * @since 2023-01-31
 */
@RestController
@RequestMapping("/department")
public class UmsDepartmentController {
  @Autowired
  private UmsDepartmentService departmentService;

  @ApiOperation("根据部门名称分页获取角色列表")
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  @ResponseBody
  public CommonResult<CommonPage<UmsDepartment>> list(
      @RequestParam(value = "keyword", required = false) String keyword,
      @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
      @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
    Page<UmsDepartment> roleList = departmentService.list(keyword, pageSize, pageNum);
    return CommonResult.success(CommonPage.restPage(roleList));
  }
}

