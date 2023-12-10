package com.zhuzimo.util;


/**
 * 缓存装饰
 *
 * @author t3
 * @date 2023/11/27
 */
public interface CacheFacade {

    /**
     * 保存缓存
     *
     * @param key     钥匙
     * @param value   价值
     * @param timeout 超时,单位秒
     */
    <T> void saveCache(String key, T value, long timeout);


    /**
     * 保存缓存
     *
     * @param key     钥匙
     * @param value   价值
     */
    <T> void saveCache(String key, T value);

    /**
     * 获取缓存
     *
     * @param <T>   范型
     * @param key   钥匙
     * @param clazz 克拉兹
     * @return {@link T}
     */
    <T> T getCache(String key, Class<T> clazz);

    /**
     * 锁
     *
     * @param key     钥匙
     * @param timeout 超时
     * @return boolean
     */
    boolean lock(String key, long timeout);

    /**
     * 开锁
     *
     * @param key     钥匙
     */
    void unLock(String key);
}
