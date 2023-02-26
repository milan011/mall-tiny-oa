package com.macro.mall.tiny.modules.ams.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.macro.mall.tiny.modules.ams.model.AmsReimbursementDetails;
import com.macro.mall.tiny.modules.ams.mapper.AmsReimbursementDetailsMapper;
import com.macro.mall.tiny.modules.ams.service.AmsReimbursementDetailsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 报销单-明细表 服务实现类
 * </p>
 *
 * @author macro
 * @since 2023-02-17
 */
@Service
public class AmsReimbursementDetailsServiceImpl extends ServiceImpl<AmsReimbursementDetailsMapper, AmsReimbursementDetails> implements AmsReimbursementDetailsService {

	@Override
	public List<AmsReimbursementDetails> getBillList(Long id){
		QueryWrapper<AmsReimbursementDetails> wrapper = new QueryWrapper<>();
		LambdaQueryWrapper<AmsReimbursementDetails> lambda = wrapper.lambda();
		lambda.eq(AmsReimbursementDetails::getReimId,id);
		List<AmsReimbursementDetails> billList = list(wrapper);
		return billList;
	}
}
