package com.samton.platform.framework.util;

import com.samton.platform.common.constant.CommonConstant;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: FileUtils
 * @Description:
 * @Author: wuzhiqiang
 * @Date: 2020-03-16 09:54
 * @Copyright (c) 2020, Samton. All rights reserved
 **/
public class FileUtils {

    /**
     *
     * @Author: wuzhiqiang
     * @Description: 将图片存入本地，并返回图片路径
     * @Date: 2020/3/16
     * @Param file:
     * @Param imgPath: 图片的路径， 区分不同类型的图片，放不同目录，如：study(学习模块)，teacher(老师模块)
     * @return: java.util.Map<java.lang.String,java.lang.String>
     */
    public static Map<String, String> imageTo(MultipartFile file, String imgPath) throws IOException {
        if(StringUtils.isBlank(imgPath)){
            imgPath = "";
        }
        Map<String, String> imgData = new HashMap<>(6);
        //上传到服务器路径
        String filePath  = CommonConstant.baseUploadPath;
        //得到原文件名
        String originalName = file.getOriginalFilename();
        //得到文件后缀名
        String suffixName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        //统一处理后缀名称
        if(StringUtils.isNotEmpty(suffixName)){
            suffixName = suffixName.toLowerCase();
        }

        //新的图片名称
        String newFileName = DateUtil.currentDatetimeNum() + ((int)((Math.random()*9+1)*100000)) + suffixName;

        //得到文件大小 单位为kb
        Long fileSize = file.getSize() / 1024;
        if(fileSize==0){
            fileSize=1L;
        }

        //新文件全路径
        filePath = filePath + imgPath + File.separator + newFileName;
        File newFile = new File(filePath);
        if(!newFile.getParentFile().exists()){
            newFile.getParentFile().mkdirs();
        }

        file.transferTo(newFile);

        String relativePath =  CommonConstant.imgUrl + imgPath + "/" + newFileName;
        imgData.put("attachmentName", originalName);
        imgData.put("attachmentPath", relativePath);
        imgData.put("attachmentSuffix", suffixName);
        imgData.put("attachmentSize", fileSize.toString());
        return imgData;
    }

}