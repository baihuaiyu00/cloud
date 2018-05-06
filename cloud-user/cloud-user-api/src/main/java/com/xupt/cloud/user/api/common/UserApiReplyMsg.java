package com.xupt.cloud.user.api.common;


import com.xupt.cloud.manager.common.util.IReplyMsg;

public enum UserApiReplyMsg implements IReplyMsg {

    USER_REGISTER_ERROR("user register error"),
    USER_REGISTER_SUCCESS("user register success"),
    USER_LOGIN_SUCCESS("user login success"),
    USER_LOGIN_ERROR("user login error"),
    USER_LOGIN_FAIL("password or username wrong");

    private String msg;

    UserApiReplyMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return this.msg;
    }
}
