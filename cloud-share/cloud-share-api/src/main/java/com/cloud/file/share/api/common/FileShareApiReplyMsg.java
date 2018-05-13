package com.cloud.file.share.api.common;


import com.xupt.cloud.common.util.IReplyMsg;

public enum FileShareApiReplyMsg implements IReplyMsg {

    FILE_SHARE_SUCCESS("file share success"),
    FILE_SHARE_FAIL("file share fail"), SHARE_LIST_ERROR("share list error");

    private String msg;

    FileShareApiReplyMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return this.msg;
    }
}
