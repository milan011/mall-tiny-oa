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
 * 合同会签表
 * </p>
 *
 * @author macro
 * @since 2023-01-31
 */
@Getter
@Setter
@TableName("ams_contract")
@ApiModel(value = "AmsContract对象", description = "合同会签表")
public class AmsContract implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

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
