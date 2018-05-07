package com.xupt.cloud.user.api.controller;

import com.xupt.cloud.common.util.Replys;
import com.xupt.cloud.user.api.common.UserApiConstants;
import com.xupt.cloud.user.api.common.UserApiReplyMsg;
import com.xupt.cloud.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.xupt.cloud.user.entity.User;
import org.springframework.web.bind.annotation.RestController;
/**
 * Created by baihuaiyu on 2018/5/1
 */
@RestController
@RequestMapping(UserApiConstants.USER_API_VERSION)
public class UserApiController {

    @Autowired
    private UserService userService;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserApiController.class);

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity userRegister(@RequestBody User user){
        try {
            LOGGER.info("success into user register controller!");
            userService.addUser(user);
            return Replys.success(UserApiReplyMsg.USER_REGISTER_SUCCESS);
        }catch(Exception e){
            LOGGER.info("register error",e);
            return Replys.error(UserApiReplyMsg.USER_REGISTER_ERROR);
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity userLogin(@RequestBody User user){
        try {
            Boolean loginResult = userService.queryByUsername(user);
            if (loginResult) {
                return Replys.success(UserApiReplyMsg.USER_LOGIN_SUCCESS);
            } else {
                return Replys.error(UserApiReplyMsg.USER_LOGIN_FAIL);
            }
        }catch (Exception e){
            LOGGER.info("login error",e);
            return Replys.error(UserApiReplyMsg.USER_LOGIN_ERROR);
        }
    }

}
