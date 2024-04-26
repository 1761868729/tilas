package org.example.tilas.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.tilas.pojo.Result;
import org.example.tilas.utils.AliOSSUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@Slf4j
@RequestMapping("upload")
public class UploadController {
    @Autowired
    private AliOSSUtils aliOSSUtils;
    //本地存储
    /*@PostMapping("/upload")
    public Result upload(String username,Integer age, MultipartFile image) throws IOException {
        log.info("文件上传：｛｝，｛｝，｛｝",username,age,image);
        //获取原始文件名的方法
        String originalFilename = image.getOriginalFilename();

        //构造唯一的文件名 -uuid(通用唯一识别码)
        int index = 0;
        if (originalFilename != null) {
            index = originalFilename.lastIndexOf(".");
        }
        String extname = originalFilename.substring(index);
        String newFileName = UUID.randomUUID().toString() + extname;
        log.info("新的文件名：{}",newFileName);

        //将文件存储在服务器的磁盘目录当中
        image.transferTo(new File("E:\\Project\\images\\"+newFileName));

        return Result.success();
    }*/

    /**
     * 文件上传
     * @param image
     * @return
     * @throws IOException
     */
    @PostMapping
    public Result upload(MultipartFile image) throws IOException {
        log.info("文件上传，文件名：｛｝", image.getOriginalFilename());
        //调用阿里云OSS
        String url = aliOSSUtils.upload(image);
        log.info("文件上传完成，文件访问的url：｛｝",url);
        return Result.success(url);
    }

}