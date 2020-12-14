package com.allen.upload.controller;


import com.allen.upload.base.Result;
import com.allen.upload.service.IFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/file")
@Api(tags = "图片文件接口")
public class FileController {

   // @Autowired
    private IFileService fileService;

    @PostMapping("getImageUrl")
    @ApiOperation(value = "获取图片文件URL")
    @ApiImplicitParam(name = "fileId" , value = "文件id" , required = true)
    public Result getImageUrl(String fileId) throws Exception {
        String imageUrl = fileService.getImageUrl(fileId);
        return Result.ok(imageUrl).setMsg("获取图片文件URL成功");
    }

}
