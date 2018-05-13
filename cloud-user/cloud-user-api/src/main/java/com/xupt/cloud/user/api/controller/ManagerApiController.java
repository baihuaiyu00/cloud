package com.xupt.cloud.user.api.controller;

import com.xupt.cloud.common.util.Replys;
import com.xupt.cloud.user.api.common.UserApiConstants;
import com.xupt.cloud.user.api.common.UserApiReplyMsg;
import com.xupt.cloud.user.entity.Manager;
import com.xupt.cloud.user.entity.User;
import com.xupt.cloud.user.service.ManagerService;
import com.xupt.cloud.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by baihuaiyu on 2018/5/7
 */
@RestController
@RequestMapping(UserApiConstants.USER_API_VERSION)
public class ManagerApiController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ManagerApiController.class);

    @Autowired
    private ManagerService managerService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/manager/login", method = RequestMethod.POST)
    public ResponseEntity managerLogin(@RequestBody Manager manager){
        try {
            Boolean loginResult = managerService.queryByManagername(manager);
            if (loginResult) {
                return Replys.success(UserApiReplyMsg.MANAGER_LOGIN_SUCCESS);
            } else {
                return Replys.error(UserApiReplyMsg.MANAGER_LOGIN_FAIL);
            }
        }catch (Exception e){
            LOGGER.info("manager login error",e);
            return Replys.error(UserApiReplyMsg.MANAGER_LOGIN_ERROR);
        }
    }

    @RequestMapping(value = "/user/list", method = RequestMethod.GET)
    public ResponseEntity userListGet(){
        try{
            List<User> users = userService.listUser();
            return Replys.success(users);
        }catch(Exception e){
            LOGGER.info("get user List error", e);
            return Replys.error(UserApiReplyMsg.USER_LIST_ERROR);
        }
    }

}
