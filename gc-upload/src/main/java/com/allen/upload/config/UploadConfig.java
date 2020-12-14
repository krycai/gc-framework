package com.allen.upload.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

@Configuration
public class UploadConfig {

    //上传根目录
    @Getter
    @Setter
    //@Value("${upload.upload_root_dir}")
    private String uploadRootDir;

    //上传的大小
    @Getter
    @Setter
    private long uploadSize;

    //图片base url
    @Getter
    @Setter
    private String nginxUrl;

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //// 设置文件大小限制 ,超了，页面会抛出异常信息，这时候就需要进行异常信息的处理了;
        if (uploadSize != 0){
            factory.setMaxFileSize(DataSize.ofBytes(uploadSize));
        }
        return factory.createMultipartConfig();
    }

}
