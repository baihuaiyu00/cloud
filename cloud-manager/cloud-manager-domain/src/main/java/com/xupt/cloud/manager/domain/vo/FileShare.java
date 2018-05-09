package com.xupt.cloud.manager.domain.vo;

/**
 * Created by baihuaiyu on 2018/5/8
 */
public class FileShare {

    private String fileName;
    private String username;
    private String realPath;
    private String code;
    private String shareWebsite;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealPath() {
        return realPath;
    }

    public void setRealPath(String realPath) {
        this.realPath = realPath;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getShareWebsite() {
        return shareWebsite;
    }

    public void setShareWebsite(String shareWebsite) {
        this.shareWebsite = shareWebsite;
    }
}
