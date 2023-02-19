package com.macro.mall.tiny.modules.ams.dto;

import com.macro.mall.tiny.modules.ams.model.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class AmsProcessReimbursementParam extends AmsProcess {
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
	
	@ApiModelProperty("报销明细列表")
	private List<AmsReimbursementDetails> remiDetailsList;
}