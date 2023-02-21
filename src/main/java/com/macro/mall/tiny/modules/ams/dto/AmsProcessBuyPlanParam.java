package com.macro.mall.tiny.modules.ams.dto;

import com.macro.mall.tiny.modules.ams.model.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AmsProcessBuyPlanParam extends AmsProcess {
	
	@ApiModelProperty("报销明细列表")
	private List<AmsBuyplan> planDetailsList;
}