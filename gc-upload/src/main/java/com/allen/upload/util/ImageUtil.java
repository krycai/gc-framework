package com.allen.upload.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

@Slf4j
public class ImageUtil {

    private ImageUtil(){

    }

    /**
     * 判断是否是图片
     * @param fileName
     * @return
     */
    public static boolean isImageByName(String fileName) {
        String extUpp = StringUtils.upperCase(fileName.substring(fileName.lastIndexOf('.') + 1));
        return extUpp.matches("^[(JPEG)|(JPG)|(PNG)|(GIF)]+$");
    }


    /**
     * 判断是否是图片
     * @param path
     * @return
     */
    public static boolean isImage(String path){
        try {
            Image image = ImageIO.read(new File(path));
            if (image != null){
                return true;
            }
        } catch (Exception e){

            return false;
        }
        return false;
    }

    /**
     * 判断是否是图片
     * @param file
     * @return
     */
    public static boolean isImage(MultipartFile file) {
        try {
            Image image = ImageIO.read(file.getInputStream());
            if (image != null) {
                return true;
            }
        } catch (Exception e) {
            log.error("转化图片失败{}", e);
            return false;
        }
        return false;
    }

    /**
     * 判断是否是图片
     * @param file
     * @return
     */
    public static boolean isImage(File file){
        try {
            Image image = ImageIO.read(file);
            if (image != null){
                return true;
            }
        } catch (Exception e){
            log.error("转化图片失败{}", e);
            return false;
        }
        return false;
    }

}
