package com.swd.springlearn.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @author swd
 * @ClassName: FileUploadController
 * @ProjectName springlearn
 * @Description: TODO
 * @date 2018/9/613:46
 */
@Controller
@RequestMapping("/uploads")
public class FileUploadController {
    private static final Logger log = LoggerFactory.getLogger(FileUploadController.class);

    @GetMapping
    public String index(){
        return "upload";
    }

    @PostMapping("/singleupload")
    @ResponseBody
    public Map<String,String> singleUpload(@RequestParam("file")MultipartFile file) throws IOException {
        log.info("文件类型：{}",file.getContentType());
        log.info("文件名称：{}",file.getName());
        log.info("文件大小：{}",file.getSize());
        //文件写入本地或者oss等等
        file.transferTo(new File("E:\\springlearn\\"+file.getOriginalFilename()));
        Map<String,String> result = new HashMap<>(16);
        result.put("contentType",file.getContentType());
        result.put("fileName",file.getName());
        result.put("fileSize",file.getSize()+"");
        return result;
    }

    @PostMapping("/multipartupload")
    @ResponseBody
    public List<Map<String, String>> multipartUpload(@RequestParam("file")MultipartFile[] files) throws IOException {
        if(files == null||files.length == 0){
            return null;
        }
        List<Map<String, String>> results = new ArrayList<>();
        for (MultipartFile file: files){
            file.transferTo(new File("E:\\springlearn\\"+file.getOriginalFilename()));
            Map<String,String> map = new HashMap<>(16);
            map.put("contentType",file.getContentType());
            map.put("fileName",file.getName());
            map.put("fileSize",file.getSize()+"");
            results.add(map);
        }
        return results;
    }

    @PostMapping("/baseupload")
    @ResponseBody
    public void baseUpload(String base64) throws IOException {
        final File tempFile = new File("E:\\springlearn\\test.jpg");
        // 防止有的传了 data:image/png;base64, 有的没传的情况
        String[] d = base64.split("base64,");
        final byte[] bytes = Base64Utils.decodeFromString(d.length>1?d[1]:d[0]);
        FileCopyUtils.copy(bytes,tempFile);
    }
}
