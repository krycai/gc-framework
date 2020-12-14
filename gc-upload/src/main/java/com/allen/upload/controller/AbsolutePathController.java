package com.allen.upload.controller;

import com.allen.upload.base.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

/**
 * @program: Allen-projects
 * @description: AbsolutePathController
 *
 * 绝对路径，文件存放在resources的静态目录static下
 *
 * 访问方式：
 * http://localhost:8002/images/test/1443749371675.jpeg
 *
 * 即是static目录下的images下的test下的文件
 *
 * @author: allen小哥
 * @Date: 2020-01-01 11:38
 **/
@RestController
@RequestMapping("/absolute")
@Api(tags = "上传绝对路径接口")
@Slf4j
public class AbsolutePathController {


    @Value("${server.port}")
    private Integer port;

    @PostMapping("uploadToProject")
    @ApiOperation(value = "文件上传")
    public Result uploadToProject(MultipartFile file , HttpServletRequest request, HttpServletResponse response) throws Exception {

        //获取项目下static的路径
        File staticFilePath = ResourceUtils.getFile("classpath:static");
        log.info("stati路径:{}",staticFilePath);
        File targetFile2 = new File(staticFilePath+File.separator+"images");
        if (!targetFile2.exists()&& !targetFile2.isDirectory()){
            targetFile2.mkdir();
        }
        File targetFile = new File(targetFile2,file.getOriginalFilename());

        log.info("targetFile=="+targetFile);
        //上传到项目static路径下
        file.transferTo(targetFile);
        // 绝对路径显示  images/file.getOriginalFilename()
        return Result.ok("上传成功").setData("http://localhost:"+port+"/images/"+file.getOriginalFilename());
    }


}
