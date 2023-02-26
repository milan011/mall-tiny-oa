package com.macro.mall.tiny.modules.ams.service;

import com.macro.mall.tiny.modules.ams.model.AmsPayApply;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 付款申请单表 服务类
 * </p>
 *
 * @author macro
 * @since 2023-01-31
 */
public interface AmsPayApplyService extends IService<AmsPayApply> {
	AmsPayApply getInfo(Long id);
}
