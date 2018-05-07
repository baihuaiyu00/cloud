package com.xupt.cloud.manager.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.xupt.cloud.common.util.JSONUtils;
import com.xupt.cloud.common.util.Replys;
import com.xupt.cloud.manager.common.ManagerApiConstants;
import com.xupt.cloud.manager.domain.MyFile;
import com.xupt.cloud.manager.domain.query.FileQuery;
import com.xupt.cloud.manager.domain.vo.File;
import com.xupt.cloud.manager.service.FileServiceApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Objects;

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
    public void addFile(HttpServletRequest request, MyFile file){
        LOGGER.info("myFile:"+file);
        try {

            LOGGER.info("success into file upload controller");
            fileServiceApi.addFile(file.getMultipartFile());
        }catch (Exception e){
            LOGGER.info("upload file fail", e);
        }
    }

    @RequestMapping(value = "file/list", method = RequestMethod.GET)
    public String list(@RequestParam(value = "pn", required = false, defaultValue = "1") int pn,
                       @RequestParam(value = "ps", required = false, defaultValue = "10") int ps,
                       @RequestParam(value = "userName") String useraname,
                       Model model, HttpServletRequest request){
        try {
            if(Objects.isNull(request.getSession().getAttribute("user"))){
                return "index";
            }
            FileQuery fileQuery = new FileQuery();
            fileQuery.setPn(pn);
            fileQuery.setPageSize(ps);
            fileQuery.setNeedPage(true);
            String response = fileServiceApi.getFilePageByQuery(JSONUtils.toJSON(fileQuery));
//            List<File> fileList = JSONUtils.fromJson(response,File.class)
            model.addAttribute("filePage", response);
            return "fileList";
        }catch(Exception e){
            LOGGER.error("get file list error", e);
            return "fileList";
        }
    }

    @RequestMapping(value = "/file", method = RequestMethod.GET)
    public String getFile(@RequestParam String fileName, HttpServletRequest request, HttpServletResponse response) {
        try {
            if(Objects.isNull(request.getSession().getAttribute("user"))){
                return "index";
            }
            OutputStream out = response.getOutputStream();
            String pathGet = "\\\\";
            String pathBase = "D:\\usr";
            String path = pathBase + pathGet + fileName;
            response.setContentType("text/html;charset=UTF-8");
            java.io.File file = new java.io.File(path);
            LOGGER.info("path:" + path);
            if(!(file.exists())){
                out.write("文件不存在或已经被分享者删除！".getBytes("UTF-8"));
                return "";
            }

            InputStream in = new FileInputStream(file);
            response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileName,"UTF-8"));
            byte b[] = new byte[1024];
            int len = -1;
            while((len=in.read(b))!=-1){
                out.write(b,0,len);
            }
            in.close();
            out.write("下载成功".getBytes("UTF-8"));
        }catch(Exception e){
            LOGGER.error("download file error", e);
            return "";
        }
        return "";
    }

}
