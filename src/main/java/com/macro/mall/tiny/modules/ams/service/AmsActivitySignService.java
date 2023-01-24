package com.macro.mall.tiny.modules.ams.service;

import com.macro.mall.tiny.modules.ams.model.AmsActivitySign;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 活动-报名表 服务类
 * </p>
 *
 * @author macro
 * @since 2022-08-13
 */
public interface AmsActivitySignService extends IService<AmsActivitySign> {
	/**
	 * 添加活动
	 */
	@Transactional
	boolean create(AmsActivitySign activitySign);
}
