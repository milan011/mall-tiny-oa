package com.macro.mall.tiny.modules.ams.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.macro.mall.tiny.modules.ams.model.AmsPayApply;
import com.macro.mall.tiny.modules.ams.model.AmsProject;
import com.macro.mall.tiny.modules.ams.mapper.AmsProjectMapper;
import com.macro.mall.tiny.modules.ams.service.AmsProjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 工程项目付款审批单表 服务实现类
 * </p>
 *
 * @author macro
 * @since 2023-01-31
 */
@Service
public class AmsProjectServiceImpl extends ServiceImpl<AmsProjectMapper, AmsProject> implements AmsProjectService {
	public AmsProject getInfo(Long id){
		QueryWrapper<AmsProject> wrapper = new QueryWrapper<>();
		LambdaQueryWrapper<AmsProject> lambda = wrapper.lambda();
		lambda.eq(AmsProject::getProId,id);
		AmsProject projectInfo = getOne(wrapper);
		return projectInfo;
	}
}
