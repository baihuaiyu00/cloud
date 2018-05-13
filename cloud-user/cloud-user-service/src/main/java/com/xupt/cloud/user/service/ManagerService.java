package com.xupt.cloud.user.service;

import ch.qos.logback.classic.spi.LoggerRemoteView;
import com.xupt.cloud.user.dao.ManagerDao;
import com.xupt.cloud.user.entity.Manager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

/**
 * Created by baihuaiyu on 2018/5/2
 */
public class ManagerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ManagerService.class);

    @Autowired
    private ManagerDao managerDao;

    /**
     * select Manager by managername
     * @param manager
     * @return
     */
    public boolean queryByManagername(final Manager manager){
        Manager managerQuery = managerDao.selectByName(manager.getManagerName());
        String passwordQuery = managerQuery.getPassword();
        if(passwordQuery == null || "".equals(passwordQuery)){
            return false;
        }
        if(passwordQuery.equals(manager.getPassword())) {
            return true;
        }else{
            return false;
        }
    }

    /**
     * add Manager
     * @param manager
     * @return
     */
    public Manager addManager(final Manager manager) {
        return managerDao.insert(manager);
    }

}
