package com.xupt.cloud.manager.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by baihuaiyu on 2018/5/9
 */
@FeignClient(name = "service-share")
public interface ShareServiceApi {

    @RequestMapping(value = "/v1/file/share", method = RequestMethod.POST)
    String shareFile(@RequestParam(value = "fileShare") String fileShare);

    @RequestMapping(value = "/v1/file/share", method = RequestMethod.GET)
    String findShareFile(@RequestParam(value = "fileShare") String fileShare);

    @RequestMapping(value = "/v1/{username}/shares", method = RequestMethod.GET)
    String listShare(@PathVariable(value = "username") String username);
}
