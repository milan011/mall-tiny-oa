package com.macro.mall.tiny.modules.ams.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.macro.mall.tiny.modules.ams.model.AmsProcess;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 审批流程表 Mapper 接口
 * </p>
 *
 * @author macro
 * @since 2023-01-31
 */
public interface AmsProcessMapper extends BaseMapper<AmsProcess> {
	IPage<AmsProcess> getHandleProcess(Page<AmsProcess> page, Long applyTypeId, String nameKeyword);
}
