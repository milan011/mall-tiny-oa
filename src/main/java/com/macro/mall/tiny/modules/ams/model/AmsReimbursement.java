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
 * 报销单表
 * </p>
 *
 * @author macro
 * @since 2023-01-31
 */
@Getter
@Setter
@TableName("ams_reimbursement")
@ApiModel(value = "AmsReimbursement对象", description = "报销单表")
public class AmsReimbursement implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("关联流程表ID")
    private Long proId;

    @ApiModelProperty("所属部门")
    private String department;

    @ApiModelProperty("报销事由")
    private String reimReason;

    @ApiModelProperty("金额")
    private BigDecimal reimMoney;

    @ApiModelProperty("金额大写")
    private String uppercase;

    @ApiModelProperty("收款方户名")
    private String payPeople;

    @ApiModelProperty("银行账号")
    private String bankAccount;

    @ApiModelProperty("开户行")
    private String bankName;

    @ApiModelProperty("单据数")
    private Integer billNum;


}
