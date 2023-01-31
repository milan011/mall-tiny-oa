package com.macro.mall.tiny.modules.ums.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 后台用户部门关系表
 * </p>
 *
 * @author macro
 * @since 2023-01-31
 */
@Getter
@Setter
@TableName("ums_user_department_relation")
@ApiModel(value = "UmsUserDepartmentRelation对象", description = "后台用户部门关系表")
public class UmsUserDepartmentRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("用户ID")
    private Long userId;

    @ApiModelProperty("部门ID")
    private Long departmentId;


}
