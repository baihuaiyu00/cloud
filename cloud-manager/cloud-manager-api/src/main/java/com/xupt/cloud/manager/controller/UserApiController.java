package com.xupt.cloud.manager.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.xupt.cloud.common.util.JSONUtils;
import com.xupt.cloud.manager.common.MailUtil;
import com.xupt.cloud.manager.common.ManagerApiConstants;
import com.xupt.cloud.manager.domain.vo.Manager;
import com.xupt.cloud.manager.domain.vo.User;
import com.xupt.cloud.manager.service.UserServiceApi;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Properties;
import java.util.Random;

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
    public String login(HttpServletRequest request, User user, Model model){
        try {
            String result = userServiceApi.userLogin(user);
            LOGGER.info("result:" + result);
            LOGGER.info("username:" + user.getUsername());
            LOGGER.info("password:" + user.getPassword());
            HttpSession session = request.getSession();
            session.setAttribute("username",user.getUsername());
            return "userHome";
        }catch(Exception e){
            LOGGER.error("login fail",e);
            model.addAttribute("info","密码输入错误！请重试");
            return "index";
        }
    }

    @RequestMapping(value = "/manager/login", method = RequestMethod.POST)
    public String managerLogin(HttpServletRequest request, Manager manager){
        try {
            String result = userServiceApi.managerLogin(manager);
            HttpSession session = request.getSession();
            session.setAttribute("username",manager.getManagerName());
            session.setAttribute("managerName",manager.getManagerName());
            return "managerHome";
        }catch(Exception e){
            LOGGER.error("manager login fail",e);
            return "manager";
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(HttpServletRequest request, User user){
        try{
            HttpSession session = request.getSession();
            session.setAttribute("username",user.getUsername());
            String result = userServiceApi.userRegister(user);
            LOGGER.info("success into user register");
            LOGGER.info("result:" + result);
            //mail begin
            MailUtil.sendMail(user, "感谢注册资源共享云平台", user.getUsername()+"恭喜您注册成功");
            //mail end
            return "userHome";
        }catch(Exception e){
            LOGGER.info("register error", e);
            return "index";
        }
    }

    @RequestMapping(value = "/user/list", method = RequestMethod.GET)
    public String userList(HttpServletRequest request, Model model){
        try {
            HttpSession session = request.getSession();
            if(StringUtils.isBlank((String)session.getAttribute("managerName"))){
                return "index";
            }else{
                String usersJson = userServiceApi.userList();
                List<User> userList = JSONUtils.fromJson(usersJson, new TypeReference<List<User>>() {});
                model.addAttribute("userList", userList);
                return "userList";
            }
        }catch(Exception e){
            LOGGER.error("manager user list error", e);
            return "managerHome";
        }
    }

    @RequestMapping(value = "/user/password", method = RequestMethod.POST)
    public String userPwdChange(HttpServletRequest request, @RequestParam(value = "forgetName") String forgetName){
        try {
//            String username = (String) request.getSession().getAttribute("username");
            User user = JSONUtils.fromJson(userServiceApi.getUser(forgetName), User.class);
            Random r = new Random();
            String code = String.valueOf(r.nextInt(10))
                    + String.valueOf(r.nextInt(10))
                    + String.valueOf(r.nextInt(10))
                    + String.valueOf(r.nextInt(10));
            MailUtil.sendMail(user, "密码修改验证码", "您的密码修改验证码为" + code);
            request.setAttribute("code",code);
            return "pwdChange";
        }catch (Exception e){
            e.printStackTrace();
            return "pwdChange";
        }
    }



}
