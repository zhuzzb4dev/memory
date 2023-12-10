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

    List<PhotoDoc> findByMd5HexAndSha1HexAndLength(String md5Hex, String sha1Hex, Long length);

    Page<PhotoDoc> findByUserId(Pageable pageable, Long userId);
}
