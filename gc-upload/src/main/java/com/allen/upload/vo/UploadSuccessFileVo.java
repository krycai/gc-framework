package com.allen.upload.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="UploadSuccessFile对象", description="上传成功的文件")
public class UploadSuccessFileVo implements Serializable {

    @ApiModelProperty(value = "文件id")
    private String id;

    @ApiModelProperty(value = "文件名")
    private String fileName;

    @ApiModelProperty(value = "文件路径")
    private String filePath;

}
