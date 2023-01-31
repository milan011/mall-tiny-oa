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
