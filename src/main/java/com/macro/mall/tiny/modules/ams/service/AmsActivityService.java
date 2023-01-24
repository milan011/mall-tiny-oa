package com.macro.mall.tiny.modules.ams.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.macro.mall.tiny.modules.ams.model.AmsActivity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程活动表 服务类
 * </p>
 *
 * @author macro
 * @since 2022-08-13
 */
public interface AmsActivityService extends IService<AmsActivity> {
	/**
	 * 添加活动
	 */
	boolean create(AmsActivity activity);

	/**
	 * 活动列表
	 */
	Page<AmsActivity> list(String keyword, Integer pageSize, Integer pageNum);

}
