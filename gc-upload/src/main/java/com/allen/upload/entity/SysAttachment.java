package com.allen.upload.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_attachment")
@ApiModel(value="SysAttachment对象", description="上传附件表")
public class SysAttachment implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "文件名称")
    private String fileName;

    @ApiModelProperty(value = "文件路径")
    private String filePath;

    @ApiModelProperty(value = "文件内容长度，为下载进度所用")
    private Long contentLength;

    @ApiModelProperty(value = "关联业务ID")
    private String businessId;

    @ApiModelProperty(value = "业务类型,字典type=sys_attachment_business_type")
    private String businessType;

    @ApiModelProperty(value = "创建用户ID")
    @TableField(fill = FieldFill.INSERT)
    private String createUser;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新用户ID")
    @TableField(fill = FieldFill.UPDATE)
    private String updateUser;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "删除标记",hidden = true)
    @TableField(fill = FieldFill.INSERT)
    @JsonIgnore
    @TableLogic
    private Boolean delFlag;
}
