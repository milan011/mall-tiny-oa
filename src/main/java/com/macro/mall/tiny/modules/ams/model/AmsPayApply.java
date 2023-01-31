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
 * 付款申请单表
 * </p>
 *
 * @author macro
 * @since 2023-01-31
 */
@Getter
@Setter
@TableName("ams_pay_apply")
@ApiModel(value = "AmsPayApply对象", description = "付款申请单表")
public class AmsPayApply implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("关联流程表ID")
    private Long proId;

    @ApiModelProperty("所属部门")
    private String department;

    @ApiModelProperty("收款单位名称")
    private String collectionCompnay;

    @ApiModelProperty("开户行")
    private String bankName;

    @ApiModelProperty("银行账号")
    private String bankAccount;

    @ApiModelProperty("金额")
    private BigDecimal payMoney;

    @ApiModelProperty("付款方式")
    private String payType;

    @ApiModelProperty("金额大写")
    private String uppercase;

    @ApiModelProperty("用途")
    private String usefull;

    @ApiModelProperty("报账类型")
    private Long typeId;


}
