package com.zhuzimo.controller;

import com.zhuzimo.req.SampleReq;
import com.zhuzimo.component.SampleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 样品控制器
 *
 * @author t3
 * @date 2023/11/28
 */
@Slf4j
@RestController
@RequestMapping("sample")
public class SampleController {

    @Resource
    private SampleService sampleService;

    /**
     * 获取
     *
     * @param name 名字
     * @return {@link ResponseEntity}<{@link ?}>
     */
    @GetMapping("get/{name}")
    public ResponseEntity<?> get(@PathVariable String name) {
        return ResponseEntity.ok(name);
    }

    /**
     * 发布
     *
     * @param req 要求
     * @return {@link ResponseEntity}<{@link ?}>
     */
    @PostMapping("post")
    public ResponseEntity<?> post(@RequestBody SampleReq req) {
        sampleService.test();
        return ResponseEntity.ok(req.getName());
    }
}
