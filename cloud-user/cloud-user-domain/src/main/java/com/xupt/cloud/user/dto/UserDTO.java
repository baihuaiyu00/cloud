package com.xupt.cloud.user.dto;

/**
 * Created by baihuaiyu on 2018/5/1
 */
public class UserDTO {

    private Integer id;
    private String username;
    private String pasword;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasword() {
        return pasword;
    }

    public void setPasword(String pasword) {
        this.pasword = pasword;
    }
}
