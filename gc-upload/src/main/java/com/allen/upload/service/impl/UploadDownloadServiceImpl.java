package com.allen.upload.service.impl;

import com.allen.upload.config.UploadConfig;
import com.allen.upload.entity.SysAttachment;
import com.allen.upload.enums.DateStyle;
import com.allen.upload.exception.FileUploadException;
import com.allen.upload.exception.ServiceException;
import com.allen.upload.service.ISysAttachmentService;
import com.allen.upload.service.IUploadDownloadService;
import com.allen.upload.util.DateUtils;
import com.allen.upload.util.DownloadUtil;
import com.allen.upload.util.FileUtils;
import com.allen.upload.util.ImageUtil;
import com.allen.upload.vo.UploadSuccessFileVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class UploadDownloadServiceImpl implements IUploadDownloadService {

    @Autowired
    private ISysAttachmentService sysAttachmentService;

    @Autowired
    private UploadConfig uploadConfig;

    @Override
    public List<UploadSuccessFileVo> multiUpload(MultipartFile[] files, String relativeDir) throws Exception {
        List<SysAttachment> sysAttachmentList = new ArrayList<>();

        if (files != null && files.length > 0){
            for(MultipartFile file : files){
                SysAttachment sysAttachment = parseData(file , relativeDir);
                sysAttachmentList.add(sysAttachment);

                String transferPath = getFileUploadRootPath(sysAttachment.getFileName() , false)  + File.separator +  sysAttachment.getFilePath();
                FileUtils.createDirectory(transferPath, true);
                try {
                    file.transferTo(new File(transferPath));
                } catch (IOException e) {
                    log.error(e.getMessage());
                    throw new FileUploadException(e.getMessage());
                }
            }
        }

        List<UploadSuccessFileVo> uploadSuccessFileVoList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(sysAttachmentList)){
            sysAttachmentService.saveBatch(sysAttachmentList);

            sysAttachmentList.forEach(sysAttachment -> {
                UploadSuccessFileVo uploadSuccessFileVo = new UploadSuccessFileVo();
                uploadSuccessFileVo.setId(sysAttachment.getId());
                uploadSuccessFileVo.setFileName(sysAttachment.getFileName());
                //统一返回全路径
                String filePathVo = getFileUploadRootPath(sysAttachment.getFileName() , true) + File.separator + sysAttachment.getFilePath();
                uploadSuccessFileVo.setFilePath(filePathVo);

                uploadSuccessFileVoList.add(uploadSuccessFileVo);
            });
        }
        return uploadSuccessFileVoList;
    }

    @Override
    public UploadSuccessFileVo upload(MultipartFile file, String relativeDir) throws Exception {
        UploadSuccessFileVo uploadSuccessFileVo = new UploadSuccessFileVo();
        if (file != null){
            SysAttachment sysAttachment = parseData(file , relativeDir);
            String transferPath = getFileUploadRootPath(sysAttachment.getFileName() , false)  + File.separator +  sysAttachment.getFilePath();
            FileUtils.createDirectory(transferPath , true);
            try {
                file.transferTo(new File(transferPath));
                sysAttachmentService.save(sysAttachment);

                uploadSuccessFileVo.setId(sysAttachment.getId());
                uploadSuccessFileVo.setFileName(sysAttachment.getFileName());
                //统一返回全路径
                String filePathVo = getFileUploadRootPath(sysAttachment.getFileName() , true) + File.separator + sysAttachment.getFilePath();
                uploadSuccessFileVo.setFilePath(filePathVo);
            } catch (IOException e) {
                log.error(e.getMessage());
                throw new FileUploadException(e.getMessage());
            }
        }
        return uploadSuccessFileVo;
    }

    @Override
    public void download(String fileId, HttpServletRequest request, HttpServletResponse response) throws Exception {
        SysAttachment sysAttachment = sysAttachmentService.getById(fileId);
        if (sysAttachment == null) throw new FileUploadException("查无此文件");
        String downloadPath = getFileUploadRootPath(sysAttachment.getFileName() , false) + File.separator + sysAttachment.getFilePath();
        String msg = DownloadUtil.downFile(new File(downloadPath) , request , response);
        if (StringUtils.isNotBlank(msg)){
            throw new ServiceException(msg);
        }
    }

    /**
     * 文件上传的根目录
     * @param fileName 文件名
     * @param backVo 是否返回用于做回显的图片文件
     * @return
     */
    private String getFileUploadRootPath(String fileName , boolean backVo){
        String rootDir = uploadConfig.getUploadRootDir();

        if (ImageUtil.isImageByName(fileName) && backVo){
            rootDir = uploadConfig.getNginxUrl();
        }
        rootDir = FileUtils.removeEndMark(rootDir);
        return rootDir;
    }


    private SysAttachment parseData(MultipartFile file, String relativeDir){
        String userId = "";

        String dateStr = DateUtils.formatDate(new Date(), DateStyle.YYYYMMDD);

        String originalName = file.getOriginalFilename();
        String fileExt = originalName.substring(originalName.lastIndexOf(".") + 1);

        relativeDir = FileUtils.generateNormalPath(relativeDir);

        String filePath = "";
        if (StringUtils.isNotEmpty(relativeDir)){
            filePath = relativeDir + File.separator + dateStr + File.separator + UUID.randomUUID().toString() + "." + fileExt;
        } else {
            filePath = dateStr + File.separator + UUID.randomUUID().toString() + "." + fileExt;
        }

        SysAttachment sysAttachment = new SysAttachment();
        sysAttachment.setFileName(originalName);
        sysAttachment.setFilePath(filePath);
        sysAttachment.setCreateTime(LocalDateTime.now());
        sysAttachment.setDelFlag(false);
        sysAttachment.setCreateTime(LocalDateTime.now());
        sysAttachment.setCreateUser(userId);
        sysAttachment.setContentLength(file.getSize());

        return sysAttachment;
    }
}
