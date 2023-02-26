package com.macro.mall.tiny.modules.ams.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.macro.mall.tiny.modules.ams.model.AmsAdvancepay;
import com.macro.mall.tiny.modules.ams.mapper.AmsAdvancepayMapper;
import com.macro.mall.tiny.modules.ams.model.AmsPayApply;
import com.macro.mall.tiny.modules.ams.service.AmsAdvancepayService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 预付款项报账单表 服务实现类
 * </p>
 *
 * @author macro
 * @since 2023-01-31
 */
@Service
public class AmsAdvancepayServiceImpl extends ServiceImpl<AmsAdvancepayMapper, AmsAdvancepay> implements AmsAdvancepayService {
	public AmsAdvancepay getInfo(Long id){
		QueryWrapper<AmsAdvancepay> wrapper = new QueryWrapper<>();
		LambdaQueryWrapper<AmsAdvancepay> lambda = wrapper.lambda();
		lambda.eq(AmsAdvancepay::getProId,id);
		AmsAdvancepay advanceInfo = getOne(wrapper);
		return advanceInfo;
	}
}
