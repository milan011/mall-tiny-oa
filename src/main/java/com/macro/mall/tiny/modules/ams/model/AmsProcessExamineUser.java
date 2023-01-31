package com.macro.mall.tiny.modules.ams.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 审核人-流程关系表
 * </p>
 *
 * @author macro
 * @since 2023-01-31
 */
@Getter
@Setter
@TableName("ams_process_examine_user")
@ApiModel(value = "AmsProcessExamineUser对象", description = "审核人-流程关系表")
public class AmsProcessExamineUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("审核人ID")
    private Long examineUserId;

    @ApiModelProperty("审核流程ID")
    private Long processId;


}
