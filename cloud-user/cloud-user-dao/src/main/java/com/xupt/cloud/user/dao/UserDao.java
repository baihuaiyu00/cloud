package com.xupt.cloud.user.dao;

import com.xupt.cloud.user.entity.User;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

/**
 * Created by baihuaiyu on 2018/5/2
 */
public class UserDao {

    private MongoTemplate cloudMongoTemplate;
    private String collectionName;

    /**
     * insert user
     * @param user
     * @return
     */
    public User insert(final User user) {
        cloudMongoTemplate.insert(user, collectionName);
        return user;
    }


    /**
     *
     * @param username
     * @return
     */
    public User selectByName(final String username){
        Query query = new Query(Criteria.where("username").is(username));
        return cloudMongoTemplate.findOne(query, User.class, collectionName);
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public void setCloudMongoTemplate(MongoTemplate cloudMongoTemplate) {
        this.cloudMongoTemplate = cloudMongoTemplate;
    }
}
