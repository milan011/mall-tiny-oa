package com.macro.mall.tiny.modules.ams.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 采购计划审批表
 * </p>
 *
 * @author macro
 * @since 2023-01-31
 */
@Getter
@Setter
@TableName("ams_buyplan")
@ApiModel(value = "AmsBuyplan对象", description = "采购计划审批表")
public class AmsBuyplan implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("关联流程表ID")
    private Long proId;

    @ApiModelProperty("所属部门")
    private String department;

    @ApiModelProperty("物资名称")
    private String goodsName;

    @ApiModelProperty("使用部门或使用人")
    private String depPerson;

    @ApiModelProperty("商品单位")
    private String goodsUnit;

    @ApiModelProperty("商品数量")
    private Integer goodsNums;

    @ApiModelProperty("预计单价")
    private BigDecimal onesMoney;

    @ApiModelProperty("计划金额")
    private BigDecimal goodsMoney;


}
