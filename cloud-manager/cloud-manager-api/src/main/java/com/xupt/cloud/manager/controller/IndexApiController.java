package com.xupt.cloud.manager.controller;

import com.xupt.cloud.manager.common.ManagerApiConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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

    /**
     * FILE UPLOAD FORWARD
     * @param request
     * @return
     */
    @RequestMapping(value = "myFile", method = RequestMethod.GET)
    public String filePage(HttpServletRequest request){
        if(Objects.isNull(request.getSession().getAttribute("username"))){
            return "index";
        }
        LOGGER.info("request for file upload");
        return "fileupload";
    }

    /**
     * SHARE FILE DOWNLOAD FORWARD
     * @param userName
     * @param fileName
     * @param request
     * @return
     */
    @RequestMapping(value = "share/{userName}/{fileName}", method = RequestMethod.GET)
    public String shareFileDownload(@PathVariable String userName, @PathVariable String fileName, HttpServletRequest request){
        if(Objects.isNull(request.getSession().getAttribute("username"))){
            return "index";
        }
        request.setAttribute("userName", userName);
        request.setAttribute("fileName", fileName);
        LOGGER.info("request fore share-file download");
        return "fileShare";
    }

    /**
     * FILE USERHOME FORWARD
     * @param request
     * @return
     */
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String userHome(HttpServletRequest request){
        if(Objects.isNull(request.getSession().getAttribute("username"))){
            return "index";
        }
        LOGGER.info("request for userHome");
        return "userHome";
    }

    @RequestMapping(value = "/manager", method = RequestMethod.GET)
    public String managerIndex(){
        LOGGER.info("request for manager login index");
        return "manager";
    }

    @RequestMapping(value = "/user/register", method = RequestMethod.GET)
    public String registerIndex(){
        LOGGER.info("request for user register index");
        return "register";
    }


}
