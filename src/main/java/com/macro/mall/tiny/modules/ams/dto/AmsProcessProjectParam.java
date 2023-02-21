package com.macro.mall.tiny.modules.ams.dto;

import com.macro.mall.tiny.modules.ams.model.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AmsProcessProjectParam extends AmsProcess {
	
	@ApiModelProperty("关联流程表ID")
	private Long proId;
	
	@ApiModelProperty("所属部门")
	private String department;
	
	@ApiModelProperty("收款单位名称")
	private String collectionCompnay;
	
	@ApiModelProperty("开户行名称")
	private String bankName;
	
	@ApiModelProperty("开户行帐号")
	private String bankAccount;
	
	@ApiModelProperty("合同名称")
	private String contractName;
	
	@ApiModelProperty("合同编号")
	private String contractCode;
	
	@ApiModelProperty("合同金额")
	private BigDecimal contractMoney;
	
	@ApiModelProperty("实际结算金额")
	private BigDecimal actualMoney;
	
	@ApiModelProperty("付款事由说明")
	private String payReason;
	
	@ApiModelProperty("备注")
	private String remark;
	
	@ApiModelProperty("累计已开发票金额")
	private BigDecimal cumulInvoice;
	
	@ApiModelProperty("累计已付款金额")
	private BigDecimal cumulPay;
	
	@ApiModelProperty("应付款金额")
	private BigDecimal shuldPay;
	
	@ApiModelProperty("本次开票金额")
	private BigDecimal thsTime;
	
	@ApiModelProperty("本次申请付款金额")
	private BigDecimal thsTimeWant;
	
	@ApiModelProperty("金额大写")
	private String uppercase;
	
	@ApiModelProperty("报账类型")
	private Long typeId;
}