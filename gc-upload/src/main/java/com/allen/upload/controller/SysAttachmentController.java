package com.allen.upload.controller;

import com.allen.upload.base.Result;
import com.allen.upload.entity.SysAttachment;
import com.allen.upload.service.ISysAttachmentService;
import com.allen.upload.vo.SysAttachmentVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/attachment")
@Api(tags = "附件处理接口")
public class SysAttachmentController {

    //@Autowired
    private ISysAttachmentService sysAttachmentService;

    @PostMapping("updateAttachment")
    @ApiOperation(value = "更新附件的业务id和业务类型")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fileIds" , value = "上传成功的文件id集合，用逗号分隔" , required = true),
            @ApiImplicitParam(name = "businessId" , value = "用户业务Id" , required = true),
            @ApiImplicitParam(name = "businessType" , value = "用户业务类型,字典type=sys_attachment_business_type" , required = true)
    })
    public Result updateAttachment(String fileIds , String businessId , String businessType) throws Exception {
        sysAttachmentService.updateAttachment(fileIds , businessId , businessType);
        return Result.ok("更新成功");
    }

    @PostMapping("getAttachmentList")
    @ApiOperation(value = "根据业务ID和业务类型获取文件列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "businessId" , value = "用户业务Id" , required = true),
            @ApiImplicitParam(name = "businessType" , value = "用户业务类型,字典type=sys_attachment_business_type" , required = true)
    })
    public Result<List<SysAttachmentVo>> getAttachmentList(String businessId , String businessType){
        List<SysAttachmentVo> attachmentList = sysAttachmentService.getAttachmentList(businessId, businessType);
        return Result.ok(attachmentList).setMsg("查询文件列表成功");
    }
    @PostMapping("getMultiAttachmentList")
    @ApiOperation(value = "返回多个业务ID对应的文件列表")
    @ApiImplicitParam(name = "businessIds", value = "用户业务Id", required = true)
    public Result<List<SysAttachmentVo>> getMultiAttachmentList(@RequestParam("businessIds") List<String> businessIds) {
        List<SysAttachmentVo> attachmentList = sysAttachmentService.getMultiAttachmentList(businessIds);
        return Result.ok(attachmentList).setMsg("查询文件列表成功");
    }

    @PostMapping("getMultiAttachmentListByFileIds")
    @ApiOperation(value = "返回多个文件ID对应的文件列表")
    @ApiImplicitParam(name = "ids", value = "文件Id集合", required = true)
    public Result<List<SysAttachmentVo>> getMultiAttachmentListByFileIds(@RequestParam("ids") List<String> ids) {
        List<SysAttachmentVo> attachmentList = sysAttachmentService.getMultiAttachmentListByFileIds(ids);
        return Result.ok(attachmentList).setMsg("查询文件列表成功");
    }

    @PostMapping("deleteAttach")
    @ApiOperation(value = "根据ID删除附件,允许传多个ID")
    @ApiImplicitParam(name = "attachIds" , value = "附件ID" , required = true)
    public Result<String> deleteAttach(String attachIds){
        if (StringUtils.isEmpty(attachIds)){
            return Result.error("ID 不能为空");
        }
        List<String> attachArr = Arrays.asList(attachIds.split(","));
        for (String itemId : attachArr){
            sysAttachmentService.removeById(itemId);
        }
        return Result.ok();
    }

    @ApiOperation("保存附件与业务信息,备用")
    @PostMapping("saveAttach")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "attachId" , value = "附件ID" , required = true),
            @ApiImplicitParam(name = "businessId" , value = "用户业务Id" , required = true),
            @ApiImplicitParam(name = "businessType" , value = "用户业务类型,字典type=sys_attachment_business_type" , required = true)
    })
    public Result<Object> saveAttach(String attachId, String businessId , String type) {
        if (StringUtils.isEmpty(attachId) || StringUtils.isEmpty(businessId)){
            return Result.error("附件ID或业务ID不能为空");
        }
        SysAttachment attachment = new SysAttachment();
        attachment.setId(attachId);
        attachment.setBusinessId(businessId);
        attachment.setBusinessType(type);
        sysAttachmentService.updateById(attachment);
        return Result.ok("保存成功");
    }

}
