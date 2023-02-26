package com.macro.mall.tiny.modules.ams.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.macro.mall.tiny.modules.ams.model.AmsReimbursement;
import com.macro.mall.tiny.modules.ams.mapper.AmsReimbursementMapper;
import com.macro.mall.tiny.modules.ams.service.AmsReimbursementService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 报销单表 服务实现类
 * </p>
 *
 * @author macro
 * @since 2023-01-31
 */
@Service
public class AmsReimbursementServiceImpl extends ServiceImpl<AmsReimbursementMapper, AmsReimbursement> implements AmsReimbursementService {
	@Override
	public boolean create(AmsReimbursement reimbursement) {
		/*reimbursement.setCreateTime(new Date());
		reimbursement.setAdminCount(0);
		reimbursement.setSort(0);
		return save(reimbursement);*/
		return true;
	}
	
	@Override
	public AmsReimbursement getRemibursementInfo(Long id){
		QueryWrapper<AmsReimbursement> wrapper = new QueryWrapper<>();
		LambdaQueryWrapper<AmsReimbursement> lambda = wrapper.lambda();
		lambda.eq(AmsReimbursement::getProId,id);
		AmsReimbursement reimbursement = getOne(wrapper);
		return reimbursement;
	}
}
