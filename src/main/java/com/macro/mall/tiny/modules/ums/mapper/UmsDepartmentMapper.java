package com.macro.mall.tiny.modules.ums.mapper;

import com.macro.mall.tiny.modules.ums.model.UmsDepartment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 后台部门表 Mapper 接口
 * </p>
 *
 * @author macro
 * @since 2023-01-31
 */
@Repository
public interface UmsDepartmentMapper extends BaseMapper<UmsDepartment> {
	/**
	 * 获取用户所属部门
	 */
	List<UmsDepartment> getDepartmentList(@Param("adminId") Long adminId);
}
