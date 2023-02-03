package com.macro.mall.tiny.modules.ums.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.macro.mall.tiny.modules.ums.model.UmsDepartment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 后台部门表 服务类
 * </p>
 *
 * @author macro
 * @since 2023-01-31
 */
public interface UmsDepartmentService extends IService<UmsDepartment> {
  /**
   * 添加部门
   */
  boolean create(UmsDepartment department);

  /**
   * 批量删除部门
   */
  boolean delete(List<Long> ids);
  /**
   * 分页获取角色列表
   */
  Page<UmsDepartment> list(String keyword, Integer pageSize, Integer pageNum);
}
