package com.xupt.cloud.user.api.controller;

import com.xupt.cloud.common.util.Replys;
import com.xupt.cloud.user.api.common.UserApiConstants;
import com.xupt.cloud.user.api.common.UserApiReplyMsg;
import com.xupt.cloud.user.entity.Manager;
import com.xupt.cloud.user.service.ManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by baihuaiyu on 2018/5/7
 */
@RestController
@RequestMapping(UserApiConstants.USER_API_VERSION)
public class ManagerApiController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ManagerApiController.class);

    @Autowired
    private ManagerService managerService;

    @RequestMapping(value = "/manager/login", method = RequestMethod.POST)
    public ResponseEntity managerLogin(@RequestBody Manager manager){
        try {
            Boolean loginResult = managerService.queryByManagername(manager);
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
