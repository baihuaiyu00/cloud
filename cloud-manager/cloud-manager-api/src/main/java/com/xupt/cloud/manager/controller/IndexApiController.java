package com.xupt.cloud.manager.controller;

import com.xupt.cloud.manager.common.ManagerApiConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    @RequestMapping(value = "/myFile", method = RequestMethod.GET)
    public String filePage(HttpServletRequest request){
        if(Objects.isNull(request.getSession().getAttribute("username"))){
            return "index";
        }
        LOGGER.info("request for file upload");
        return "fileupload";
//        return "fileupload2";
    }

    /**
     * SHARE FILE DOWNLOAD FORWARD
     * @param userName
     * @param fileName
     * @param request
     * @return
     */
    @RequestMapping(value = "/{userName}/{fileName}/share", method = RequestMethod.GET)
    public String shareFileDownload(@PathVariable String userName, @PathVariable String fileName, HttpServletRequest request){
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
    public String managerIndex(HttpServletRequest request){
        LOGGER.info("request for userHome");
        return "manager";
    }

    @RequestMapping(value = "/user/register" ,method = RequestMethod.GET)
    public String userRegister(){
        return "register";
    }

    @RequestMapping(value = "/pwdForget" ,method = RequestMethod.GET)
    public String pwdForget(){
        return "pwdForget";
    }

    @ResponseBody
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(){
        return "<h4>cloud-manager-A</h4>";
    }
}
