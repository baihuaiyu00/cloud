package com.xupt.cloud.manager.common.util;


public interface IReplyMsg {
    String getMsg();

    default String toJsonString() {
        return "{\"message\": \"" + getMsg() + "\"}";
    }
}
