package com.allen.upload.controller;

import com.allen.upload.base.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

/**
 * @program: Allen-projects
 * @description: OutController
 *
 * 外部路径配置，然后在WebMvcConfig配置
 *  registry.addResourceHandler("/image/**").addResourceLocations("file:" + uploadUrl); // 上传路径映射 会使spring boot的自动配置失效
 *
 * 访问方式：
 * http://localhost:8002/image/test/1443749371675.jpeg
 *
 * @author: allen小哥
 * @Date: 2020-01-04 10:38
 **/
@RestController
@RequestMapping("/out")
@Api(tags = "上传外部接口")
@Slf4j
public class OutController {

    @Value("${out.uploadUrl}")
    private String uploadUrl;

    @PostMapping("upload")
    @ApiOperation(value = "文件上传")
    public Result uploadToProject(MultipartFile file , HttpServletRequest request, HttpServletResponse response) throws Exception {

        log.info("上传的首路径:{}",uploadUrl);

        File targetFile2 = new File(uploadUrl+"test");
        if (!targetFile2.exists()&& !targetFile2.isDirectory()){
            targetFile2.mkdir();
        }
        File targetFile = new File(targetFile2,file.getOriginalFilename());

        log.info("targetFile=="+targetFile);
        //上传到外部目录下
        file.transferTo(targetFile);
        // 绝对路径显示  images/file.getOriginalFilename()
        return Result.ok("上传成功").setData("http://localhost:8002/image/test/"+file.getOriginalFilename());
    }


}
