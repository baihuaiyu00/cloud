package com.xupt.cloud.manager.common;


import com.xupt.cloud.manager.common.util.IReplyMsg;

public enum ManagerApiReplyMsg implements IReplyMsg {

    USER_LOGIN_FAIL("password or username wrong");

    private String msg;

    ManagerApiReplyMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return this.msg;
    }
}
