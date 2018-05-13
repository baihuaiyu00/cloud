package com.xupt.cloud.manager.service;

import com.xupt.cloud.manager.domain.vo.Manager;
import com.xupt.cloud.manager.domain.vo.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by baihuaiyu on 2018/5/5
 */
@FeignClient(name = "service-user")
public interface UserServiceApi {

    @RequestMapping(value = "/v1/login", method = RequestMethod.POST)
    String userLogin(@RequestBody User user);

    @RequestMapping(value = "/v1/register", method = RequestMethod.POST)
    String userRegister(@RequestBody User user);

    @RequestMapping(value = "/v1/manager/login", method = RequestMethod.POST)
    String managerLogin(@RequestBody Manager manager);

    @RequestMapping(value = "/v1/user/list", method = RequestMethod.GET)
    String userList();
}
