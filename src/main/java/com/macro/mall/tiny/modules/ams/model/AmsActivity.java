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
 * 课程活动表
 * </p>
 *
 * @author macro
 * @since 2022-08-13
 */
@Getter
@Setter
@TableName("ams_activity")
@ApiModel(value = "AmsActivity对象", description = "课程活动表")
public class AmsActivity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("活动名称")
    private String activityName;

    @ApiModelProperty("活动负责人")
    private String activityLeader;

    @ApiModelProperty("活动地点")
    private String activityAddress;

    @ApiModelProperty("活动联系电话")
    private String activityPhone;

    @ApiModelProperty("活动内容")
    private String activityContent;

    @ApiModelProperty("活动状态：1->启用；2->禁用；")
    private Integer status;

    @ApiModelProperty("活动备注")
    private String remark;

    @ApiModelProperty("开始时间")
    private Date startTime;

    @ApiModelProperty("结束时间")
    private Date endTime;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("修改时间")
    private Date modifyTime;


}
