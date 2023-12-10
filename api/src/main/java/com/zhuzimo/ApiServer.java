package com.zhuzimo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * API 服务器
 *
 * @author t3
 * @date 2023/11/28
 */
@SpringBootApplication
@Slf4j
public class ApiServer {

    /**
     * 主要
     *
     * @param args 参数
     */
    public static void main(String[] args) {
        SpringApplication.run(ApiServer.class, args);
        log.info("服务启动成功");
    }

}
