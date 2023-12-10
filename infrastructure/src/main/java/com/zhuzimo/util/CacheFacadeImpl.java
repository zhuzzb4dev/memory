package com.zhuzimo.util;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * 缓存装饰实现
 *
 * @author t3
 * @date 2023/11/28
 */
@Component
@Slf4j
public class CacheFacadeImpl implements CacheFacade {

    @Resource
    private RedissonClient redissonClient;

    private static final Long DEFAULT_CACHE_TIMEOUT = 60L;

    @Override
    public <T> void saveCache(String key, T value) {
        saveCache(key, value, DEFAULT_CACHE_TIMEOUT);
    }

    @Override
    public <T> void saveCache(String key, T value, long timeout) {
        RBucket<T> bucket = redissonClient.getBucket(key);
        bucket.set(value, Duration.ofSeconds(timeout));
    }

    @Override
    public <T> T getCache(String key, Class<T> clazz) {
        RBucket<T> bucket = redissonClient.getBucket(key);
        return bucket.get();
    }

    @Override
    public boolean lock(String key, long timeout) {
        boolean result = false;
        try {
            result = redissonClient.getLock(key).tryLock(timeout, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            log.error("key为{}获取锁失败", key, e);
        }
        return result;
    }

    @Override
    public void unLock(String key) {
        redissonClient.getLock(key).unlock();
    }
}
