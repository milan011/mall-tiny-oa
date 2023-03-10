package com.macro.mall.tiny.modules.ums.mapper;

import com.macro.mall.tiny.modules.ums.model.UmsAdmin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 后台用户表 Mapper 接口
 * </p>
 *
 * @author macro
 * @since 2020-08-21
 */
@Repository
public interface UmsAdminMapper extends BaseMapper<UmsAdmin> {

	/**
	 * 获取资源相关用户ID列表
	 */
	List<Long> getAdminIdList(@Param("resourceId") Long resourceId);
	
	/**
	 * 获取资源相关用户ID列表
	 */
	List getAdminByRoleAndDepartement(@Param("roleIds") List roleIds, @Param("departmentIds") List departmentIds);
	List getAdminByRole(@Param("roleIds") List roleIds);
	
	
}
