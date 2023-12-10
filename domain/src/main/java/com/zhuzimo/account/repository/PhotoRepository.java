package com.zhuzimo.account.repository;

import com.zhuzimo.account.dp.Photo;

import java.util.List;

/**
 * 照片库
 *
 * @author t3
 * @date 2023/12/05
 */
public interface PhotoRepository {

    void save(Photo photo);

    List<Photo> findAll();

    boolean findExist(Photo photo);

    List<Photo> findByUserId(Long userId);
}
