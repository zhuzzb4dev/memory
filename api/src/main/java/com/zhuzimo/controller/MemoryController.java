package com.zhuzimo.controller;

import com.zhuzimo.account.repository.PhotoRepository;
import com.zhuzimo.component.UserComponent;
import com.zhuzimo.dto.CommonResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("memory")
@Slf4j
public class MemoryController {

    @Resource
    private PhotoRepository photoRepository;

    @Resource
    UserComponent userComponent;

    @PostMapping("query-all")
    public CommonResp queryAll() {
        return CommonResp.buildSuccess(photoRepository.findByUserId(userComponent.getLoginUserId()));
    }
}
