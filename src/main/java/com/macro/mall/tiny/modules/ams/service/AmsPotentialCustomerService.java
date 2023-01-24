package com.macro.mall.tiny.modules.ams.service;

import com.macro.mall.tiny.modules.ams.model.AmsPotentialCustomer;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 报名用户表 服务类
 * </p>
 *
 * @author macro
 * @since 2022-08-13
 */
public interface AmsPotentialCustomerService extends IService<AmsPotentialCustomer> {
	/**
	 * 添加活动客户
	 */
	AmsPotentialCustomer create(AmsPotentialCustomer amsPotentialCustomer);
}
