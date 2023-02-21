package com.macro.mall.tiny.modules.ams.dto;

import com.macro.mall.tiny.modules.ams.model.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AmsProcessPayApplyParam extends AmsProcess {
	
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