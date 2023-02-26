package com.macro.mall.tiny.modules.ams.service;

import com.macro.mall.tiny.modules.ams.model.AmsAdvancepay;
import com.baomidou.mybatisplus.extension.service.IService;
import com.macro.mall.tiny.modules.ams.model.AmsPayApply;

/**
 * <p>
 * 预付款项报账单表 服务类
 * </p>
 *
 * @author macro
 * @since 2023-01-31
 */
public interface AmsAdvancepayService extends IService<AmsAdvancepay> {
	AmsAdvancepay getInfo(Long id);
}
