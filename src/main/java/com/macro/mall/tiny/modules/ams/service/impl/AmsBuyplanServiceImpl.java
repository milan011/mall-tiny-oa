package com.macro.mall.tiny.modules.ams.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.macro.mall.tiny.modules.ams.model.AmsBuyplan;
import com.macro.mall.tiny.modules.ams.mapper.AmsBuyplanMapper;
import com.macro.mall.tiny.modules.ams.model.AmsPayApply;
import com.macro.mall.tiny.modules.ams.service.AmsBuyplanService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 采购计划审批表 服务实现类
 * </p>
 *
 * @author macro
 * @since 2023-01-31
 */
@Service
public class AmsBuyplanServiceImpl extends ServiceImpl<AmsBuyplanMapper, AmsBuyplan> implements AmsBuyplanService {
	public List<AmsBuyplan> getInfo(Long id){
		QueryWrapper<AmsBuyplan> wrapper = new QueryWrapper<>();
		LambdaQueryWrapper<AmsBuyplan> lambda = wrapper.lambda();
		lambda.eq(AmsBuyplan::getProId,id);
		List<AmsBuyplan> buyPlanInfo = list(wrapper);
		return buyPlanInfo;
	}
}
