package com.xupt.cloud.share.dao;

import com.xupt.cloud.share.domain.entity.FileShare;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

/**
 * Created by baihuaiyu on 2018/5/8
 */
public class FileShareDao {


    private MongoTemplate cloudMongoTemplate;
    private String collectionName;

    /**
     * 新增
     * @param fileShare
     * @return
     */
    public FileShare insert(final FileShare fileShare) {
        cloudMongoTemplate.insert(fileShare, collectionName);
        return fileShare;
    }

    /**
     * 通过用户名，文件名查找
     * @param fileShare
     * @return
     */
    public FileShare findByName(final FileShare fileShare) {
        Query mongoQuery = new Query();
        mongoQuery.addCriteria(Criteria.where("username").is(fileShare.getUsername()));
        mongoQuery.addCriteria(Criteria.where("fileName").is(fileShare.getFileName()));
        return cloudMongoTemplate.findOne(mongoQuery, FileShare.class, collectionName);
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public void setCloudMongoTemplate(MongoTemplate cloudMongoTemplate) {
        this.cloudMongoTemplate = cloudMongoTemplate;
    }

}
