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
 * 审批流程表
 * </p>
 *
 * @author macro
 * @since 2023-01-31
 */
@Getter
@Setter
@TableName("ams_process")
@ApiModel(value = "AmsProcess对象", description = "审批流程表")
public class AmsProcess implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("申请用户ID")
    private Long applyUserId;

    @ApiModelProperty("审核用户ID")
    private Long examineUserId;

    @ApiModelProperty("申请类型ID")
    private Long applyTypeId;

    @ApiModelProperty("优先级")
    private String priority;

    @ApiModelProperty("申请类型名称")
    private String applyTypeName;

    @ApiModelProperty("审核步骤详情 JSON对象格式")
    private String stepsConcent;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("结束时间")
    private Date endTime;

    @ApiModelProperty("描述")
    private String remark;

    @ApiModelProperty("审核状态：1->审核中；2->审核结束;3->审核驳回;4->审核撤销")
    private Integer status;


}
