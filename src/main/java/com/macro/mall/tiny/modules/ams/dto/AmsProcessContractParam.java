package com.macro.mall.tiny.modules.ams.dto;

import com.macro.mall.tiny.modules.ams.model.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AmsProcessContractParam extends AmsProcess {
	@ApiModelProperty("关联流程表ID")
	private Long proId;
	
	@ApiModelProperty("所属部门")
	private String department;
	
	@ApiModelProperty("发起依据")
	private String launchBasis;
	
	@ApiModelProperty("合同编号")
	private String contractCode;
	
	@ApiModelProperty("合同名称")
	private String contractName;
	
	@ApiModelProperty("甲方名称")
	private String firstPart;
	
	@ApiModelProperty("乙方名称")
	private String secondPart;
	
	@ApiModelProperty("合同金额")
	private BigDecimal contractMoney;
	
	@ApiModelProperty("基本内容")
	private String contractContent;
}