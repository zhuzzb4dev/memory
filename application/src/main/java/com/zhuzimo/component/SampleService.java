package com.zhuzimo.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 示例组件
 *
 * @author t3
 * @date 2023/11/28
 */
@Component
@Slf4j
public class SampleService {

    /**
     * 测试
     */
    public void test() {
        log.info("SampleService-test");
    }
}
