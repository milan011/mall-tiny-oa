package com.macro.mall.tiny.modules.ams.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.macro.mall.tiny.modules.ams.model.AmsActivity;
import com.macro.mall.tiny.modules.ams.mapper.AmsActivityMapper;
import com.macro.mall.tiny.modules.ams.service.AmsActivityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import java.util.Date;

/**
 * <p>
 * 课程活动表 服务实现类
 * </p>
 *
 * @author macro
 * @since 2022-08-13
 */
@Service
public class AmsActivityServiceImpl extends ServiceImpl<AmsActivityMapper, AmsActivity> implements AmsActivityService {
	@Override
	public boolean create(AmsActivity activity) {
		activity.setCreateTime(new Date());
		activity.setModifyTime(new Date());
		/*role.setCreateTime(new Date());
		role.setAdminCount(0);
		role.setSort(0);
		return save(role);*/
		return save(activity);
	}

	@Override
	public Page<AmsActivity> list(String keyword, Integer pageSize, Integer pageNum) {
		Page<AmsActivity> page = new Page<>(pageNum,pageSize);
		QueryWrapper<AmsActivity> wrapper = new QueryWrapper<>();
		LambdaQueryWrapper<AmsActivity> lambda = wrapper.lambda();
		if(StrUtil.isNotEmpty(keyword)){
			lambda.like(AmsActivity::getActivityName,keyword);
			lambda.orderByDesc(AmsActivity::getId);
		}
		return page(page,wrapper);
	}
}
