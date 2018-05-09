package com.xupt.cloud.file.service;

import com.xupt.cloud.file.dao.FileDao;
import com.xupt.cloud.file.domain.entity.File;
import com.xupt.cloud.file.domain.query.FileQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by baihuaiyu on 2018/5/7
 */
public class FileService {

    @Autowired
    private FileDao fileDao;

    private static final Logger LOGGER = LoggerFactory.getLogger(FileService.class);

    /**
     * add file/file upload
     * @param multipartFile
     * @param path
     * @return
     */
    public File addFile(MultipartFile multipartFile, String path, String username) {
        //1.form file object
        File file = new File();
        file.setFileType(multipartFile.getContentType());
        file.setFileSize(multipartFile.getSize()/1024);
        file.setFileName(multipartFile.getOriginalFilename());
        file.setUsername(username);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        file.setUpdateTime(sdf.format(new Date()));
        file.setFilePath(path);
        //2.save object to db
        return fileDao.insert(file);
    }

    /**
     * select file by Query
     * @param fileQuery
     * @return
     */
    public List<File> selectByQuery(FileQuery fileQuery) {
        return fileDao.selectByQuery(fileQuery);
    }

    /**
     * delete file
     * @param fileQuery
     * @return
     */
    public File delFile(FileQuery fileQuery) {
        return fileDao.delete(fileQuery);
    }
}
