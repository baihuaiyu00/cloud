package com.xupt.cloud.file.controller;

import com.xupt.cloud.file.domain.entity.MyFile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Created by baihuaiyu on 2018/5/5
 */
@Controller
public class FileUploadController {

    @RequestMapping(value = "file", method = RequestMethod.POST)
    public ResponseEntity addFile(MyFile myFile){
        MultipartFile multipartFile = myFile.getMultipartFile();

        String pathGet = "\\\\";
        String pathBase = "E:\\";
        String path = pathBase+pathGet;
        String fileName = multipartFile.getOriginalFilename();
        try {
            File file = new File(path+"/"+fileName);
            multipartFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
