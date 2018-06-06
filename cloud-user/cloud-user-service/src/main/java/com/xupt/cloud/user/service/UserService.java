package com.xupt.cloud.user.service;

import com.xupt.cloud.common.util.MD5Util;
import com.xupt.cloud.user.dao.UserDao;
import com.xupt.cloud.user.dto.UserDTO;
import com.xupt.cloud.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;

/**
 * Created by baihuaiyu on 2018/5/2
 */
public class UserService {

    @Autowired
    private UserDao userDao;

    /**
     * select User by username
     * @param user
     * @return
     */
    public boolean queryByUsername(final User user){
        User userBean = userDao.selectByName(user.getUsername());
        if(Objects.isNull(userBean)){
            return false;
        }
        String passwordBefore = userBean.getPassword();
        String passwordInput = MD5Util.encoder(user.getPassword());
        if(passwordBefore == null || "".equals(passwordBefore)){
            return false;
        }
        if(passwordBefore.equals(passwordInput)) {
            return true;
        }else{
            return false;
        }
    }

    /**
     * add User
     * @param user
     * @return
     */
    public User addUser(final User user) {
        user.setPassword(MD5Util.encoder(user.getPassword()));
        return userDao.insert(user);
    }

    /**
     * list user
     * @return
     */
    public List<User> listUser() {
        return userDao.selectAll();
    }

    public User getUserByName(String username) {
        return userDao.selectByName(username);
    }
}
