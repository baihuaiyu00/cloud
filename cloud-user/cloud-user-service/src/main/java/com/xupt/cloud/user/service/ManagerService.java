package com.xupt.cloud.user.service;

import com.xupt.cloud.user.dao.ManagerDao;
import com.xupt.cloud.user.entity.Manager;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

/**
 * Created by baihuaiyu on 2018/5/2
 */
public class ManagerService {

    @Autowired
    private ManagerDao managerDao;

    /**
     * select Manager by managername
     * @param manager
     * @return
     */
    public boolean queryByManagername(final Manager manager){
        Manager managerBean = managerDao.selectByName(manager.getManagerName());
        if(Objects.isNull(managerBean)){
            return false;
        }
        String passwordBefore = managerBean.getPassword();
        if(passwordBefore == null || "".equals(passwordBefore)){
            return false;
        }
        if(passwordBefore.equals(manager.getPassword())) {
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
