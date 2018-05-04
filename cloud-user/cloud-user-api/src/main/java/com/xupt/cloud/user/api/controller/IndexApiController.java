package com.xupt.cloud.user.api.controller;

import com.xupt.cloud.user.api.common.UserApiConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by baihuaiyu on 2018/5/4
 */
@Controller
@RequestMapping(UserApiConstants.USER_API_VERSION)
public class IndexApiController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(){
        return "index";
    }
}
