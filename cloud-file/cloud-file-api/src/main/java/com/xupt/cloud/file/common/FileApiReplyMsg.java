package com.xupt.cloud.file.common;


import com.xupt.cloud.common.util.IReplyMsg;

public enum FileApiReplyMsg implements IReplyMsg {

    FILE_UPLOAD_FAIL("file upload failure"),
    FILE_DB_ERROR("file to mongo error"),
    FILE_UPLOAD_SUCCESS("file upload success"),
    FILE_LIST_ERROR("file list error"),
    FILE_DOWNLOAD_ERROR("file download fail"),
    FILE_DOWNLOAD_SUCCESS("file download success");

    private String msg;

    FileApiReplyMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return this.msg;
    }
}
