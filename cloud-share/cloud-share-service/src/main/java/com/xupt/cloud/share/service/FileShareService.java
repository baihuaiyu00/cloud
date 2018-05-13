package com.xupt.cloud.share.service;

import com.xupt.cloud.share.dao.FileShareDao;
import com.xupt.cloud.share.domain.entity.FileShare;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by baihuaiyu on 2018/5/8
 */
public class FileShareService {

    @Autowired
    private FileShareDao fileShareDao;

    public FileShare addShare(final FileShare fileShare) {
        fileShareDao.insert(fileShare);
        return fileShare;
    }

    public FileShare findShare(FileShare fileShare) {
        return fileShareDao.findByName(fileShare);

    }

    public List<FileShare> listShare(String username) {
        return fileShareDao.findAll(username);
    }
}
