package com.macro.mall.tiny.modules.ams.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.macro.mall.tiny.modules.ams.model.AmsContract;
import com.macro.mall.tiny.modules.ams.mapper.AmsContractMapper;
import com.macro.mall.tiny.modules.ams.model.AmsPayApply;
import com.macro.mall.tiny.modules.ams.service.AmsContractService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 合同会签表 服务实现类
 * </p>
 *
 * @author macro
 * @since 2023-01-31
 */
@Service
public class AmsContractServiceImpl extends ServiceImpl<AmsContractMapper, AmsContract> implements AmsContractService {
	public AmsContract getInfo(Long id){
		QueryWrapper<AmsContract> wrapper = new QueryWrapper<>();
		LambdaQueryWrapper<AmsContract> lambda = wrapper.lambda();
		lambda.eq(AmsContract::getProId,id);
		AmsContract contractInfo = getOne(wrapper);
		return contractInfo;
	}
}
