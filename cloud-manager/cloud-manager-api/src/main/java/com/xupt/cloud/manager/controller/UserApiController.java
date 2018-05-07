package com.xupt.cloud.manager.controller;

import com.xupt.cloud.manager.common.ManagerApiConstants;
import com.xupt.cloud.manager.domain.vo.User;
import com.xupt.cloud.manager.service.UserServiceApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by baihuaiyu on 2018/5/6
 */
@Controller
@RequestMapping(ManagerApiConstants.USER_API_VERSION)
public class UserApiController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserApiController.class);

    @Autowired
    private UserServiceApi userServiceApi;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpServletRequest request, User user){
        try {
            String result = userServiceApi.userLogin(user);
            LOGGER.info("result:" + result);
            LOGGER.info("username:" + user.getUsername());
            LOGGER.info("password:" + user.getPassword());
            HttpSession session = request.getSession();
            session.setAttribute("user",user);
            return "userHome";
        }catch(Exception e){
            LOGGER.error("login fail",e);
            return "index";
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(User user){
        try{
            String result = userServiceApi.userRegister(user);
            LOGGER.info("success into user register");
            LOGGER.info("result:" + result);
            return "userHome";
        }catch(Exception e){
            LOGGER.info("register error", e);
            return "index";
        }
    }

}
