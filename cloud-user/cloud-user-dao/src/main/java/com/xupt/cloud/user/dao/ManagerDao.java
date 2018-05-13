package com.xupt.cloud.user.dao;

import com.xupt.cloud.user.entity.Manager;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

/**
 * Created by baihuaiyu on 2018/5/2
 */
public class ManagerDao {

    private MongoTemplate cloudMongoTemplate;
    private String collectionName;

    /**
     * insert manager
     * @param manager
     * @return
     */
    public Manager insert(final Manager manager) {
        cloudMongoTemplate.insert(manager, collectionName);
        return manager;
    }


    /**
     *
     * @param managerName
     * @return
     */
    public Manager selectByName(final String managerName){
        Query query = new Query(Criteria.where("managerName").is(managerName));
        return cloudMongoTemplate.findOne(query, Manager.class, collectionName);
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public void setCloudMongoTemplate(MongoTemplate cloudMongoTemplate) {
        this.cloudMongoTemplate = cloudMongoTemplate;
    }
}
