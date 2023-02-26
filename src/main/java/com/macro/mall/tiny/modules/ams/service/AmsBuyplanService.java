package com.macro.mall.tiny.modules.ams.service;

import com.macro.mall.tiny.modules.ams.model.AmsBuyplan;
import com.baomidou.mybatisplus.extension.service.IService;
import com.macro.mall.tiny.modules.ams.model.AmsPayApply;

import java.util.List;

/**
 * <p>
 * 采购计划审批表 服务类
 * </p>
 *
 * @author macro
 * @since 2023-01-31
 */
public interface AmsBuyplanService extends IService<AmsBuyplan> {
	List<AmsBuyplan> getInfo(Long id);
}
