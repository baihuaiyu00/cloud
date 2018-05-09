package com.xupt.cloud.file.dao;

import com.xupt.cloud.file.domain.entity.File;
import com.xupt.cloud.file.domain.query.FileQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

/**
 * Created by baihuaiyu on 2018/5/7
 */
public class FileDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileDao.class);

    private MongoTemplate cloudMongoTemplate;
    private String collectionName;

    public File insert(final File file){
        cloudMongoTemplate.insert(file, collectionName);
        return file;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public void setCloudMongoTemplate(MongoTemplate cloudMongoTemplate) {
        this.cloudMongoTemplate = cloudMongoTemplate;
    }

    public List<File> selectByQuery(FileQuery fileQuery) {
        int skip = (fileQuery.getPn()-1) * fileQuery.getPageSize();
        int limit = fileQuery.getPageSize();
        Query mongoQuery = new Query();
        mongoQuery.addCriteria(Criteria.where("username").is(fileQuery.getUsername()));
        LOGGER.info("username:"+fileQuery.getUsername());
        if(fileQuery.getPn()!=null && fileQuery.getPn() > 0) {
            mongoQuery.skip(skip);
            mongoQuery.limit(limit);
        }
        return cloudMongoTemplate.find(mongoQuery,File.class,collectionName);
    }

    public File delete(FileQuery fileQuery) {
        Query query = new Query();
        query.addCriteria(Criteria.where("username").is(fileQuery.getUsername()));
        query.addCriteria(Criteria.where("fileName").is(fileQuery.getFileName()));
        cloudMongoTemplate.remove(query, collectionName);
        return fileQuery;
    }
}
