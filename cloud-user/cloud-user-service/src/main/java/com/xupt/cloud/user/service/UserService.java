package com.xupt.cloud.user.service;

import com.xupt.cloud.user.dao.UserDao;
import com.xupt.cloud.user.dto.UserDTO;
import com.xupt.cloud.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

/**
 * Created by baihuaiyu on 2018/5/2
 */
public class UserService {

    @Autowired
    private UserDao userDao;

    public boolean queryByUsername(final User user){
        User userBean = userDao.selectByQuery(user);
        if(Objects.isNull(userBean)){
            return false;
        }
        String passwordBefore = userBean.getPassword();
        if(passwordBefore == null || "".equals(passwordBefore)){
            return false;
        }
        if(passwordBefore.equals(user.getPassword())) {
            return true;
        }else{
            return false;
        }
    }

    public UserDTO addUser(User user) {

        //TODO 决定是否进行转换
        UserDTO userDTO = new UserDTO();
        User userBean = userDao.insert(user);
        return userDTO;

    }

}
