package com.zhuzimo.photo.repository;

import com.zhuzimo.photo.aggregate.Photo;
import com.zhuzimo.common.CommonPaged;
import com.zhuzimo.common.CommonPagedAble;

/**
 * 照片库
 *
 * @author t3
 * @date 2023/12/05
 */
public interface PhotoRepository {

    /**
     * 保存
     *
     * @param photo 相片
     */
    void save(Photo photo);

    /**
     * 查找存在
     *
     * @param photo 相片
     * @return boolean
     */
    boolean findExist(Photo photo);

    /**
     * 按用户 ID 分页查询
     *
     * @param commonPagedAble 通用分页能力
     * @param loginUserId     登录用户 ID
     * @return {@link CommonPaged}<{@link Photo}>
     */
    CommonPaged<Photo> queryPagedByUserId(CommonPagedAble commonPagedAble, Long loginUserId);
}
