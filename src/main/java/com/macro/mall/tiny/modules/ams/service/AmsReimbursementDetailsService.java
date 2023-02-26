package com.macro.mall.tiny.modules.ams.service;

import com.macro.mall.tiny.modules.ams.model.AmsReimbursementDetails;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 报销单-明细表 服务类
 * </p>
 *
 * @author macro
 * @since 2023-02-17
 */
public interface AmsReimbursementDetailsService extends IService<AmsReimbursementDetails> {
	List<AmsReimbursementDetails> getBillList(Long id);
}
