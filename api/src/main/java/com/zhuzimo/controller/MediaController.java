package com.zhuzimo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.IOUtils;

import java.io.FileInputStream;


@RequestMapping("media")
@RestController
public class MediaController {

    @GetMapping("image/{id}")
    public byte[] image(@PathVariable String id) throws Exception{
        return IOUtils.readAllBytes(new FileInputStream("/Users/zhuzhibin/Desktop/upload/zzb/2023/11/1702219593317.jpg"));
    }
}
