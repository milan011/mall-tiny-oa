package com.macro.mall.tiny.modules.ams.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.macro.mall.tiny.modules.ams.model.AmsPayApply;
import com.macro.mall.tiny.modules.ams.mapper.AmsPayApplyMapper;
import com.macro.mall.tiny.modules.ams.model.AmsReimbursement;
import com.macro.mall.tiny.modules.ams.service.AmsPayApplyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 付款申请单表 服务实现类
 * </p>
 *
 * @author macro
 * @since 2023-01-31
 */
@Service
public class AmsPayApplyServiceImpl extends ServiceImpl<AmsPayApplyMapper, AmsPayApply> implements AmsPayApplyService {
	@Override
	public AmsPayApply getInfo(Long id){
		QueryWrapper<AmsPayApply> wrapper = new QueryWrapper<>();
		LambdaQueryWrapper<AmsPayApply> lambda = wrapper.lambda();
		lambda.eq(AmsPayApply::getProId,id);
		AmsPayApply payApplyInfo = getOne(wrapper);
		return payApplyInfo;
	}
}
