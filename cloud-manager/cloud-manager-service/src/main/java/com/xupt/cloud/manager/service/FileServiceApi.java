package com.xupt.cloud.manager.service;

import com.xupt.cloud.manager.domain.MyFile;
import com.xupt.cloud.manager.domain.query.FileQuery;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


/**
 * Created by baihuaiyu on 2018/5/6
 */
@FeignClient(name = "service-file", configuration = FileServiceApi.MultipartSupportConfig.class)
public interface FileServiceApi {

    @RequestMapping(
            value = "/v1/file",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE},
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    String addFile(@RequestPart(value = "file") MultipartFile file);

    @RequestMapping(value = "v1/file/list", method = RequestMethod.GET)
    String getFilePageByQuery(@RequestParam(value = "fileQuery") String fileQuery);

    /**
     * 内部类
      */
    class MultipartSupportConfig {
        @Bean
        public Encoder feignFormEncoder() {
            return new SpringFormEncoder();
        }
    }

}
