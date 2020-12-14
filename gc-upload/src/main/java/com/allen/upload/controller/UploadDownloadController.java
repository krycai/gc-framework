package com.allen.upload.controller;

import com.allen.upload.base.Result;
import com.allen.upload.service.IUploadDownloadService;
import com.allen.upload.vo.UploadSuccessFileVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/upload")
@Api(tags = "上传下载接口")
public class UploadDownloadController {

    //@Autowired
    IUploadDownloadService uploadDownloadService;

    @PostMapping("multiUpload")
    @ApiOperation(value = "多个文件上传，上传成功返回全部上传的文件信息")
    @ApiImplicitParam(name = "relativeDir" , value = "上传相对目录，如果没有，将会上传到配置的根目录" )
    public Result<List<UploadSuccessFileVo>> multiUpload(@RequestParam(required = false , value = "files") MultipartFile[] files, String relativeDir) throws Exception {
        List<UploadSuccessFileVo> uploadSuccessFileVoList = uploadDownloadService.multiUpload(files, relativeDir);
        return Result.ok(uploadSuccessFileVoList).setMsg("上传成功");
    }

    @PostMapping("upload")
    @ApiOperation(value = "单个文件上传，上传成功返回上传的文件信息")
    @ApiImplicitParam(name = "relativeDir" , value = "上传相对目录，如果没有，将会上传到配置的根目录" )
    public Result<UploadSuccessFileVo> upload(MultipartFile file, String relativeDir) throws Exception {
        UploadSuccessFileVo uploadSuccessFileVo = uploadDownloadService.upload(file, relativeDir);
        return Result.ok(uploadSuccessFileVo).setMsg("上传成功");
    }

    @GetMapping("download")
    @ApiOperation(value = "文件下载")
    @ApiImplicitParam(name = "fileId" , value = "文件id" , required = true)
    public Result download(String fileId , HttpServletRequest request, HttpServletResponse response) throws Exception {
        uploadDownloadService.download(fileId , request , response);
        return Result.ok("下载成功");
    }


}
