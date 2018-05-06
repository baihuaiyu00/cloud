package com.xupt.cloud.manager.controller;

import com.xupt.cloud.manager.common.ManagerApiConstants;
import com.xupt.cloud.manager.domain.MyFile;
import com.xupt.cloud.manager.service.FileServiceApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by baihuaiyu on 2018/5/6
 */
@Controller
@RequestMapping(ManagerApiConstants.USER_API_VERSION)
public class FileApiController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileApiController.class);

    @Autowired
    private FileServiceApi fileServiceApi;


    @RequestMapping(value = "file", method = RequestMethod.POST)
    public void addFile(MyFile myFile){
        try {
            LOGGER.info("success into file upload controller");
            fileServiceApi.addFile(myFile);
        }catch (Exception e){
            LOGGER.info("upload file fail", e);
        }
    }

}
