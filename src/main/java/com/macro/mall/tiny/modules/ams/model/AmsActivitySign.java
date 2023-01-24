package com.macro.mall.tiny.modules.ams.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 活动-报名表
 * </p>
 *
 * @author macro
 * @since 2022-08-13
 */
@Getter
@Setter
@TableName("ams_activity_sign")
@ApiModel(value = "AmsActivitySign对象", description = "活动-报名表")
public class AmsActivitySign implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("活动ID")
    private Long activityId;

    @ApiModelProperty("活动时间")
    private String activityName;

    @ApiModelProperty("客户电话")
    private String customerName;

    @ApiModelProperty("客户ID")
    private Long customerId;

    @ApiModelProperty("客户电话")
    private String customerPhone;

    @ApiModelProperty("表示码")
    private String signCode;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("修改时间")
    private Date modifyTime;


}
