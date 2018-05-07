package com.xupt.cloud.common.util;


public interface IReplyMsg {
    String getMsg();

    default String toJsonString() {
        return "{\"message\": \"" + getMsg() + "\"}";
    }
}
