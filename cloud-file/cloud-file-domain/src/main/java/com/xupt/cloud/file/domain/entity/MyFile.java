package com.xupt.cloud.file.domain.entity;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * Created by baihuaiyu on 2018/5/5
 */
public class MyFile implements Serializable{

    private MultipartFile multipartFile;

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }
}
