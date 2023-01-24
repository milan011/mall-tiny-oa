package com.macro.mall.tiny.modules.ams.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 报名用户表
 * </p>
 *
 * @author macro
 * @since 2022-08-13
 */
@Getter
@Setter
@TableName("ams_potential_customer")
@ApiModel(value = "AmsPotentialCustomer对象", description = "报名用户表")
public class AmsPotentialCustomer implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("客户姓名")
    private String customerName;

    @ApiModelProperty("客户电话")
    private String customerPhone;

    @ApiModelProperty("活动状态：1->启用；2->禁用；")
    private Integer status;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("修改时间")
    private Date modifyTime;


}
