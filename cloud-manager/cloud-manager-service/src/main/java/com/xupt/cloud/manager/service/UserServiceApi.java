package com.xupt.cloud.manager.service;

import com.xupt.cloud.manager.domain.User;
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

}
