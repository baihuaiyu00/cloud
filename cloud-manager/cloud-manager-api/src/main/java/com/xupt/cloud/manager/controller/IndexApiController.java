package com.xupt.cloud.manager.controller;

import com.xupt.cloud.manager.common.ManagerApiConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * Created by baihuaiyu on 2018/5/1
 */

@Controller
@RequestMapping(ManagerApiConstants.USER_API_VERSION)
public class IndexApiController {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndexApiController.class);


    /**
     * INDEX FORWARD
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(){
        LOGGER.info("request for index");
        return "index";
    }

    @RequestMapping(value = "myFile", method = RequestMethod.GET)
    public String filePage(HttpServletRequest request){
        if(Objects.isNull(request.getSession().getAttribute("user"))){
            return "index";
        }
        LOGGER.info("request for file upload");
        return "fileupload";
    }


}
