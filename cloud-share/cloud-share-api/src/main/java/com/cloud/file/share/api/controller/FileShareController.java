package com.cloud.file.share.api.controller;

import com.cloud.file.share.api.common.FileShareApiConstants;
import com.cloud.file.share.api.common.FileShareApiReplyMsg;
import com.xupt.cloud.common.util.JSONUtils;
import com.xupt.cloud.common.util.Replys;
import com.xupt.cloud.share.domain.entity.FileShare;
import com.xupt.cloud.share.service.FileShareService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Random;

/**
 * Created by baihuaiyu on 2018/5/8
 */
@RestController
@RequestMapping(FileShareApiConstants.USER_API_VERSION)
public class FileShareController {

    @Autowired
    private FileShareService fileShareService;

    private static final Logger LOGGER = LoggerFactory.getLogger(FileShareController.class);

    /**
     * 资源分享记录
     * @param fileShare
     * @return
     */
    //TODO 管理员用户管理...
    @RequestMapping(value = "/file/share", method = RequestMethod.POST)
    public ResponseEntity shareFile(@RequestParam(value = "fileShare") String fileShare, HttpServletRequest request){
        try {
            FileShare fileShareBean = JSONUtils.fromJson(fileShare, FileShare.class);
            Random r = new Random();
            String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            String code = String.valueOf(r.nextInt(10)) + chars.charAt((int) (Math.random() * 26)) + String.valueOf(r.nextInt(10)) + chars.charAt((int) (Math.random() * 26)) + "";
            fileShareBean.setCode(code);
            fileShareBean.setShareWebsite("http://localhost:8082/v1/"+fileShareBean.getUsername()+"/"+fileShareBean.getFileName()+"/share");

            fileShareService.addShare(fileShareBean);
            return Replys.success(fileShareBean);
        }catch(Exception e){
            LOGGER.error("file share error", e);
            return Replys.error(FileShareApiReplyMsg.FILE_SHARE_FAIL);
        }
    }

    /**
     * 分享资源下载
     * @param fileShareBean
     * @return
     */
    @RequestMapping(value = "/file/share", method = RequestMethod.GET)
    public ResponseEntity findShareFile(@RequestParam(value = "fileShare") String fileShareBean){
        try{
            //在此不做判断，只负责传回数据
            LOGGER.info("success into share-service");
            FileShare fileShare = JSONUtils.fromJson(fileShareBean, FileShare.class);
            FileShare fileShareQuery = fileShareService.findShare(fileShare);
            return Replys.success(fileShareQuery);
        }catch(Exception e){
            LOGGER.error("file share error", e);
            return Replys.error(FileShareApiReplyMsg.FILE_SHARE_FAIL);
        }
    }

    @RequestMapping(value = "/{username}/shares", method = RequestMethod.GET)
    public ResponseEntity shareList(@PathVariable(value = "username") String username){
        try{
            LOGGER.info("success into share list");
            List<FileShare> fileShareList = fileShareService.listShare(username);
            return Replys.success(fileShareList);
        }catch(Exception e){
            LOGGER.info("shares get fail");
            return Replys.error(FileShareApiReplyMsg.SHARE_LIST_ERROR);
        }
    }


}
