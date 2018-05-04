package com.xupt.cloud.user.service;

import com.xupt.cloud.user.dao.UserDao;
import com.xupt.cloud.user.dto.UserDTO;
import com.xupt.cloud.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by baihuaiyu on 2018/5/2
 */
public class UserService {

    @Autowired
    private UserDao userDao;

    public User queryByUsername(final User user){
        return userDao.selectByQuery(user);
    }

}
