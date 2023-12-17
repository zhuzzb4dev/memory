package com.zhuzimo.repository;

import com.zhuzimo.po.PhotoDoc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * 照片文档存储库
 *
 * @author t3
 * @date 2023/12/05
 */
public interface PhotoDocRepository extends ElasticsearchRepository<PhotoDoc, String> {

    /**
     * 按 MD5 十六进制和 SHA1 十六进制以及长度和用户 ID 查找
     *
     * @param md5Hex  MD5 六角
     * @param sha1Hex SHA1 十六进制
     * @param length  长度
     * @param userId  用户 ID
     * @return {@link List}<{@link PhotoDoc}>
     */
    List<PhotoDoc> findByMd5HexAndSha1HexAndLengthAndUserId(String md5Hex, String sha1Hex, Long length, Long userId);

    /**
     * 按用户 ID 查找
     *
     * @param pageable 可分页
     * @param userId   用户 ID
     * @return {@link Page}<{@link PhotoDoc}>
     */
    Page<PhotoDoc> findByUserId(Pageable pageable, Long userId);
}
