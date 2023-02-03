package com.macro.mall.tiny.modules.ums.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.macro.mall.tiny.modules.ums.model.UmsDepartment;
import com.macro.mall.tiny.modules.ums.mapper.UmsDepartmentMapper;
import com.macro.mall.tiny.modules.ums.service.UmsDepartmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 后台部门表 服务实现类
 * </p>
 *
 * @author macro
 * @since 2023-01-31
 */
@Service
public class UmsDepartmentServiceImpl extends ServiceImpl<UmsDepartmentMapper, UmsDepartment> implements UmsDepartmentService {

  @Override
  public boolean create(UmsDepartment department) {
    department.setCreateTime(new Date());
    department.setAdminCount(0);
    department.setSort(0);
    return save(department);
  }

  @Override
  public boolean delete(List<Long> ids) {
    boolean success = removeByIds(ids);
//    adminCacheService.delResourceListByRoleIds(ids);
    return success;
  }

  @Override
  public Page<UmsDepartment> list(String keyword, Integer pageSize, Integer pageNum) {
    Page<UmsDepartment> page = new Page<>(pageNum,pageSize);
    QueryWrapper<UmsDepartment> wrapper = new QueryWrapper<>();
    LambdaQueryWrapper<UmsDepartment> lambda = wrapper.lambda();
    if(StrUtil.isNotEmpty(keyword)){
      lambda.like(UmsDepartment::getDepname,keyword);
    }
    return page(page,wrapper);
  }
}
