package com.allen.upload.service;

import com.allen.upload.entity.SysAttachment;
import com.allen.upload.vo.SysAttachmentVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface ISysAttachmentService extends IService<SysAttachment> {
    void updateAttachment(String fileIds, String businessId, String businessType);

    List<SysAttachmentVo> getAttachmentList(String businessId, String businessType);

    List<SysAttachmentVo> getMultiAttachmentList(List<String> businessIds);

    List<SysAttachmentVo> getMultiAttachmentListByFileIds(List<String> ids);
}
