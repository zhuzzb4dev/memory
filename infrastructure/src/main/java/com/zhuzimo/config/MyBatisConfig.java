package com.zhuzimo.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis 配置
 *
 * @author t3
 * @date 2023/11/28
 */
@Configuration
@MapperScan(basePackages = "com.zhuzimo.mapper")
public class MyBatisConfig {
}
