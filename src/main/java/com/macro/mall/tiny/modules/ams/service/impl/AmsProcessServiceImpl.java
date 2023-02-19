package com.macro.mall.tiny.modules.ams.service.impl;

import com.macro.mall.tiny.modules.ams.dto.AmsProcessReimbursementParam;
import com.macro.mall.tiny.modules.ams.model.AmsProcess;
import com.macro.mall.tiny.modules.ams.mapper.AmsProcessMapper;
import com.macro.mall.tiny.modules.ams.service.AmsProcessService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 审批流程表 服务实现类
 * </p>
 *
 * @author macro
 * @since 2023-01-31
 */
@Service
public class AmsProcessServiceImpl extends ServiceImpl<AmsProcessMapper, AmsProcess> implements AmsProcessService {
	@Override
	public boolean create(AmsProcessReimbursementParam processReimbursementParam) {
		/*reimbursement.setCreateTime(new Date());
		reimbursement.setAdminCount(0);
		reimbursement.setSort(0);
		return save(reimbursement);*/
		return true;
	}
}
