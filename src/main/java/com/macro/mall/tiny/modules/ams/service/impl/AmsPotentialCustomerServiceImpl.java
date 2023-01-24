package com.macro.mall.tiny.modules.ams.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.macro.mall.tiny.modules.ams.model.AmsPotentialCustomer;
import com.macro.mall.tiny.modules.ams.mapper.AmsPotentialCustomerMapper;
import com.macro.mall.tiny.modules.ams.service.AmsPotentialCustomerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 报名用户表 服务实现类
 * </p>
 *
 * @author macro
 * @since 2022-08-13
 */
@Service
public class AmsPotentialCustomerServiceImpl extends ServiceImpl<AmsPotentialCustomerMapper, AmsPotentialCustomer> implements AmsPotentialCustomerService {
	@Override
	public AmsPotentialCustomer create(AmsPotentialCustomer amsPotentialCustomer) {
		//查询是否有相同电话的客户
		QueryWrapper<AmsPotentialCustomer> wrapper = new QueryWrapper<>();
		wrapper.lambda().eq(
			AmsPotentialCustomer::getCustomerPhone,
			amsPotentialCustomer.getCustomerPhone());
		List<AmsPotentialCustomer> umsCustomerList = list(wrapper);
		if (umsCustomerList.size() > 0) {
			return umsCustomerList.get(0);
		}
		amsPotentialCustomer.setCreateTime(new Date());
		amsPotentialCustomer.setModifyTime(new Date());
		save(amsPotentialCustomer);
		return amsPotentialCustomer;
	}
}
