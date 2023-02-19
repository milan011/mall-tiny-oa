package com.macro.mall.tiny.modules.ams.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 报销单-明细表
 * </p>
 *
 * @author macro
 * @since 2023-02-17
 */
@Getter
@Setter
@TableName("ams_reimbursement_details")
@ApiModel(value = "AmsReimbursementDetails对象", description = "报销单-明细表")
public class AmsReimbursementDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("关联报销表ID")
    private Long reimId;

    @ApiModelProperty("费用日期")
    private Date happenTime;

    @ApiModelProperty("费用科目")
    private String reimCourse;

    @ApiModelProperty("费用说明")
    private String reimExplain;

    @ApiModelProperty("票据")
    private String billList;

    @ApiModelProperty("报销金额")
    private BigDecimal reimMoney;

    @ApiModelProperty("金额大写")
    private String uppercase;
    
}
