package com.allen.upload.service;

import com.allen.upload.vo.UploadSuccessFileVo;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface IUploadDownloadService {
    List<UploadSuccessFileVo> multiUpload(@RequestParam("files") MultipartFile[] files, String relativeDir) throws Exception;
    UploadSuccessFileVo upload(MultipartFile file, String relativeDir) throws Exception;
    void download(String fileId, HttpServletRequest request, HttpServletResponse response) throws Exception;
}
