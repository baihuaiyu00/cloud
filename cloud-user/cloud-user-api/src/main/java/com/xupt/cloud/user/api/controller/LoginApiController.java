package com.xupt.cloud.user.api.controller;

import com.xupt.cloud.user.api.common.UserApiConstants;
import com.xupt.cloud.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.xupt.cloud.user.entity.User;
/**
 * Created by baihuaiyu on 2018/5/1
 */
@Controller
@RequestMapping(UserApiConstants.USER_API_VERSION)
public class LoginApiController {

    @Autowired
    private UserService userService;

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginApiController.class);

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String userLogin(@RequestBody User user){
        String username = user.getUsername();

        return "userHome";

    }

}
