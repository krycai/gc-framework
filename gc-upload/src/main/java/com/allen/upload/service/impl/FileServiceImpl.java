package com.allen.upload.service.impl;

import com.allen.upload.config.UploadConfig;
import com.allen.upload.entity.SysAttachment;
import com.allen.upload.service.IFileService;
import com.allen.upload.service.ISysAttachmentService;
import com.allen.upload.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class FileServiceImpl implements IFileService {

    @Autowired
    private ISysAttachmentService sysAttachmentService;

    @Autowired
    private UploadConfig uploadConfig;

    @Override
    public String getImageUrl(String fileId) throws Exception {
        SysAttachment sysAttachment = sysAttachmentService.getById(fileId);
        if (sysAttachment != null){
            String fileName = sysAttachment.getFileName();
            if (ImageUtil.isImageByName(fileName)){
                String imageUrl = getFileUrlBasePath() + File.separator + sysAttachment.getFilePath();
                return imageUrl;
            }
            return null;
        }
        return null;
    }

    //文件的url , base目录
    private String getFileUrlBasePath(){
        String ngxin_url = uploadConfig.getNginxUrl();

        if (ngxin_url.endsWith("//")){
            ngxin_url = ngxin_url.substring(0 , ngxin_url.lastIndexOf("//"));
        } else if (ngxin_url.endsWith("/")){
            ngxin_url = ngxin_url.substring(0 , ngxin_url.lastIndexOf("/"));
        } else if (ngxin_url.endsWith("\\")){
            ngxin_url = ngxin_url.substring(0 , ngxin_url.lastIndexOf("\\"));
        }
        return ngxin_url;
    }

}
