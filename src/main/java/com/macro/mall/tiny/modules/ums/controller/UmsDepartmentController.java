package com.macro.mall.tiny.modules.ums.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.macro.mall.tiny.common.api.CommonPage;
import com.macro.mall.tiny.common.api.CommonResult;
import com.macro.mall.tiny.modules.ums.model.UmsDepartment;
import com.macro.mall.tiny.modules.ums.service.UmsDepartmentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

  @ApiOperation("添加部门")
  @RequestMapping(value = "/create", method = RequestMethod.POST)
  @ResponseBody
  public CommonResult create(@RequestBody UmsDepartment department) {
    boolean success = departmentService.create(department);
    if (success) {
      return CommonResult.success(null);
    }
    return CommonResult.failed();
  }

  @ApiOperation("修改部门")
  @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
  @ResponseBody
  public CommonResult update(@PathVariable Long id, @RequestBody UmsDepartment department) {
    department.setId(id);
    boolean success = departmentService.updateById(department);
    if (success) {
      return CommonResult.success(null);
    }
    return CommonResult.failed();
  }

  @ApiOperation("批量删除部门")
  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  @ResponseBody
  public CommonResult delete(@RequestParam("ids") List<Long> ids) {
    boolean success = departmentService.delete(ids);
    if (success) {
      return CommonResult.success(null);
    }
    return CommonResult.failed();
  }

  @ApiOperation("修改部门状态")
  @RequestMapping(value = "/updateStatus/{id}", method = RequestMethod.POST)
  @ResponseBody
  public CommonResult updateStatus(@PathVariable Long id, @RequestParam(value = "status") Integer status) {
    UmsDepartment department = new UmsDepartment();
    department.setId(id);
    department.setStatus(status);
    boolean success = departmentService.updateById(department);
    if (success) {
      return CommonResult.success(null);
    }
    return CommonResult.failed();
  }
}

