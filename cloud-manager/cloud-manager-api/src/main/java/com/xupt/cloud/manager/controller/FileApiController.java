package com.xupt.cloud.manager.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.xupt.cloud.common.util.JSONUtils;
import com.xupt.cloud.manager.common.ManagerApiConstants;
import com.xupt.cloud.manager.domain.MyFile;
import com.xupt.cloud.manager.domain.query.FileQuery;
import com.xupt.cloud.manager.domain.vo.FileShare;
import com.xupt.cloud.manager.service.FileServiceApi;
import com.xupt.cloud.manager.service.ShareServiceApi;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by baihuaiyu on 2018/5/6
 */
@Controller
@RequestMapping(ManagerApiConstants.USER_API_VERSION)
public class FileApiController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileApiController.class);

    @Autowired
    private FileServiceApi fileServiceApi;

    @Autowired
    private ShareServiceApi shareServiceApi;


    /**
     * 上传资源
     * @param request
     * @param file
     */
    @RequestMapping(value = "file", method = RequestMethod.POST)
    public void addFile(HttpServletRequest request, MyFile file){
        LOGGER.info("myFile:"+file);
        try {

            LOGGER.info("success into file upload controller");
            fileServiceApi.addFile(file.getMultipartFile(), (String)request.getSession().getAttribute("username"));
        }catch (Exception e){
            LOGGER.info("upload file fail", e);
        }
    }

    /**
     * 资源列表查看
     * @param pn
     * @param ps
     * @param userName
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/file/{userName}/list", method = RequestMethod.GET)
    public String list(@PathVariable String userName,
                       @RequestParam(value = "pn", required = false, defaultValue = "1") int pn,
                       @RequestParam(value = "ps", required = false, defaultValue = "10") int ps,
                       @RequestParam(value = "fileType", required = false, defaultValue = "all") String fileType,
                       Model model, HttpServletRequest request){
        try {
            if(Objects.isNull(request.getSession().getAttribute("username"))){
                return "index";
            }
            //todo
            FileQuery fileQuery = new FileQuery();

            fileQuery.setPn(pn);
            fileQuery.setPageSize(ps);
            fileQuery.setNeedPage(true);
//            fileQuery.setUsername((String)request.getSession().getAttribute("username"));
            fileQuery.setUsername(userName);
            String response = fileServiceApi.getFilePageByQuery(JSONUtils.toJSON(fileQuery));
            CopyOnWriteArrayList<com.xupt.cloud.manager.domain.vo.File> fileList = JSONUtils.fromJson(response,new TypeReference<CopyOnWriteArrayList<com.xupt.cloud.manager.domain.vo.File>>() {});

            LOGGER.info("size before:"+fileList.size());
            if(StringUtils.isNotBlank(fileType) && !"all".equals(fileType)){
                for(com.xupt.cloud.manager.domain.vo.File fbb : fileList){
                    if (!fbb.getFileType().startsWith(fileType)) {
                        LOGGER.info(fbb.getFileType()+"---"+fileType);
                        fileList.remove(fbb);
                    }
                }
            }
            LOGGER.info("size after:"+fileList.size());

            model.addAttribute("fileList", fileList);
            if(StringUtils.isNotBlank((String) request.getSession().getAttribute("managerName"))){
                return "managerFileList";
            }
            return "fileList";
        }catch(Exception e){
            LOGGER.error("get file list error", e);
            return "fileList";
        }
    }

    /**
     * 获取/下载资源
     * @param fileName
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/file", method = RequestMethod.GET)
    public void getFile(@RequestParam(value = "fileName") String fileName, HttpServletRequest request, HttpServletResponse response) {
        try {
            if(Objects.isNull(request.getSession().getAttribute("username"))){
                return;
            }
            OutputStream out = response.getOutputStream();
            String pathGet = "\\";
            String pathBase = "D:\\usr";
            String path = pathBase + pathGet + fileName;
            response.setContentType("text/html;charset=UTF-8");
            java.io.File file = new java.io.File(path);
            LOGGER.info("fileName:" + fileName);
            LOGGER.info("path:" + path);
            if(!(file.exists())){
                out.write("文件不存在或已经被分享者删除！".getBytes("UTF-8"));
                return;
            }

            InputStream in = new FileInputStream(file);
            response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileName,"UTF-8"));
            byte b[] = new byte[1024];
            int len = -1;
            while((len=in.read(b))!=-1){
                out.write(b,0,len);
            }
            in.close();
//            out.write("下载成功".getBytes("UTF-8"));
        }catch(Exception e){
            LOGGER.error("download file error", e);
            return ;
        }
    }


    /**
     * 资源分享
     * @param fileName
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/file/{fileName}/share", method = RequestMethod.POST)
    public String fileShare(@PathVariable String fileName, HttpServletRequest request, Model model){
        try {
            if (Objects.isNull(request.getSession().getAttribute("username"))) {
                return "index";
            }
            LOGGER.info("request for file share");
            String username = (String) request.getSession().getAttribute("username");
            FileShare fileShare = new FileShare();
            fileShare.setFileName(fileName);
            fileShare.setUsername(username);
            LOGGER.info(JSONUtils.toJSON(fileShare));
            String fileShareJSON = shareServiceApi.shareFile(JSONUtils.toJSON(fileShare));
            LOGGER.info(fileShareJSON);
            return fileShareJSON;
        }catch(Exception e){
            LOGGER.error("file share manager fail", e);
            return "";
        }
    }

    /**
     * 分享资源下载
     * @return
     */
    @RequestMapping(value = "/file/{userName}/{fileName}/share", method = RequestMethod.GET)
    public void fileShareDownload(HttpServletResponse response,
                                    @PathVariable String userName,
                                    @PathVariable String fileName,
                                    @RequestParam(value = "code") String code){
        try {
            LOGGER.info("success into file share controller!");
            //1.检测code是否正确
            FileShare fileShare = new FileShare();
            fileShare.setUsername(userName);
            fileShare.setFileName(fileName);
            FileShare fileShareQuery = JSONUtils.fromJson(shareServiceApi.findShareFile(JSONUtils.toJSON(fileShare)), FileShare.class);
            if (Objects.isNull(fileShareQuery)) {
                LOGGER.info("file not find!");
                return;
            }
            if (StringUtils.contains(code, fileShareQuery.getCode())) {
                LOGGER.info("into download");
                //2.进行下载
                OutputStream out = response.getOutputStream();
                response.setContentType("text/html;charset=UTF-8");
                String pathGet = "\\";
                String pathBase = "D:\\usr";
                String path = pathBase + pathGet + fileName;
                LOGGER.info("path"+path);
                File file = new File(path);
                if (!(file.exists())) {
                    LOGGER.info("file not exists"+ path);
                    return;
                }
                InputStream in = new FileInputStream(file);
                response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileShareQuery.getFileName(), "UTF-8"));
                byte b[] = new byte[1024];
                int len = -1;
                while ((len = in.read(b)) != -1) {
                    out.write(b, 0, len);
                }
                in.close();
                LOGGER.info("file download success");
            } else {
                return;
            }
        }catch(Exception e){
            LOGGER.error("share download fail");
            return;
        }
    }

    /**
     * 资源删除
     * @param fileName
     * @param request
     * @return
     */
    @RequestMapping(value = "/file/{fileName}/del", method = RequestMethod.GET)
    @ResponseBody
    public String delFile(@PathVariable String fileName, HttpServletRequest request){
        try {
            LOGGER.info(fileName);
            if (Objects.isNull(request.getSession().getAttribute("username"))) {
                return "index";
            }
            //1.确认此文件是本人的
            //2.删除文件
            String result = fileServiceApi.delFile((String)request.getSession().getAttribute("username") ,fileName);
            return result;
        }catch(Exception e){
            LOGGER.error("file del fail", e);
            return "";
        }
    }

    @RequestMapping(value = "/{username}/shares", method = RequestMethod.GET)
    public String shareList(@PathVariable String username, HttpServletRequest request, Model model) {
        try{
            LOGGER.info("success into share list");
            String fileShareBean = shareServiceApi.listShare(username);
            List<FileShare> fileShares = JSONUtils.fromJson(fileShareBean, new TypeReference<List<FileShare>>() {});
            model.addAttribute("fileShares", fileShares);
            return "shareList";
        }catch( Exception e){
            LOGGER.info("share list get error");
            return "index";
        }
    }

}
