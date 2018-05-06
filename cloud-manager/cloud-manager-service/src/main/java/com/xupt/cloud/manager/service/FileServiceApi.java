package com.xupt.cloud.manager.service;

import com.xupt.cloud.manager.domain.MyFile;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by baihuaiyu on 2018/5/6
 */
@FeignClient(name = "service-file")
public interface FileServiceApi {

    @RequestMapping(value = "/v1/file", method = RequestMethod.POST)
    String addFile(MyFile myFile);

}
