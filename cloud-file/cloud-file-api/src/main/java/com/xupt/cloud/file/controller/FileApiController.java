package com.xupt.cloud.file.controller;

import com.xupt.cloud.common.util.JSONUtils;
import com.xupt.cloud.file.common.FileApiConstants;
import com.xupt.cloud.file.common.FileApiReplyMsg;
import com.xupt.cloud.file.domain.query.FileQuery;
import com.xupt.cloud.file.service.FileService;
import com.xupt.cloud.common.util.Replys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by baihuaiyu on 2018/5/5
 */
@RestController
@RequestMapping(FileApiConstants.FILE_API_VERSION)
public class FileApiController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileApiController.class);

    @Autowired
    private FileService fileService;

    @RequestMapping(value = "/file", method = RequestMethod.POST)
    public ResponseEntity addFile(MultipartFile multipartFile){
        LOGGER.info("success into SERVICE-FILE");
        String pathGet = "\\\\";
        String pathBase = "D:\\usr";
        String path = pathBase+pathGet;
        String fileName = multipartFile.getOriginalFilename();
        try {
            //1.file upload to mongoDB
            fileService.addFile(multipartFile, path);
            //2.file upload to disk
            File file = new File(path+"/"+fileName);
            multipartFile.transferTo(file);
            return Replys.success(FileApiReplyMsg.FILE_UPLOAD_SUCCESS);
        } catch (IOException e) {
            LOGGER.error("file into disk error", e);
            return Replys.error(FileApiReplyMsg.FILE_UPLOAD_FAIL);
        } catch (Exception e){
            LOGGER.error("file into DB error", e);
            return Replys.error(FileApiReplyMsg.FILE_DB_ERROR);
        }
    }

    /**
     * list file
     * @param fileQuery
     * @return
     */
    @RequestMapping(value = "/file/list", method = RequestMethod.GET)
    public ResponseEntity getFileList(@RequestParam(value = "fileQuery") String fileQuery){
        try {
            List<com.xupt.cloud.file.domain.entity.File> fileList = fileService.selectByQuery(JSONUtils.fromJson(fileQuery,FileQuery.class));
            return Replys.success(fileList);
        }catch (Exception e){
            LOGGER.error("file list error", e);
            return Replys.error(FileApiReplyMsg.FILE_LIST_ERROR);
        }
    }

    @RequestMapping(value = "/file", method = RequestMethod.GET)
    public ResponseEntity getFile(@RequestParam String filePath, HttpServletResponse response) {
        try {
            OutputStream out = response.getOutputStream();
            return Replys.success(FileApiReplyMsg.FILE_DOWNLOAD_SUCCESS);
        }catch(Exception e){
            LOGGER.error("download file error", e);
            return Replys.error(FileApiReplyMsg.FILE_DOWNLOAD_ERROR);
        }
    }

}
