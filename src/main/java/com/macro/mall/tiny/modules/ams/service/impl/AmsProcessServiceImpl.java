package com.macro.mall.tiny.modules.ams.service.impl;

import com.macro.mall.tiny.modules.ams.dto.AmsProcessReimbursementParam;
import com.macro.mall.tiny.modules.ams.model.AmsProcess;
import com.macro.mall.tiny.modules.ams.mapper.AmsProcessMapper;
import com.macro.mall.tiny.modules.ams.service.AmsProcessService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.macro.mall.tiny.modules.ums.model.UmsAdmin;
import com.macro.mall.tiny.modules.ums.service.UmsAdminCacheService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.Principal;

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
	@Autowired
	UmsAdminCacheService adminCacheService;
	@Override
	public boolean createReimbursement(AmsProcessReimbursementParam processReimbursementParam) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		UmsAdmin currentAdmin = adminCacheService.getAdmin(currentPrincipalName);
		/*创建流程*/
		AmsProcess process = new AmsProcess();
		BeanUtils.copyProperties(processReimbursementParam, process);
		process.setId(null);
		//amsProcessMapper.insertSelective(process);
		/*创建报销单*/
		
		/*关联报销单-明细*/
		return true;
	}
}


