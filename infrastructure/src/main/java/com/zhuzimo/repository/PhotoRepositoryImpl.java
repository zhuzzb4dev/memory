package com.zhuzimo.repository;

import com.zhuzimo.photo.aggregate.Photo;
import com.zhuzimo.photo.repository.PhotoRepository;
import com.zhuzimo.common.CommonPaged;
import com.zhuzimo.common.CommonPagedAble;
import com.zhuzimo.po.PhotoDoc;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 照片存储库 IMPL
 *
 * @author t3
 * @date 2023/12/05
 */
@Component
public class PhotoRepositoryImpl implements PhotoRepository {

    @Resource
    private PhotoDocRepository photoDocRepository;

    @Override
    public void save(Photo photo) {
        PhotoDoc photoDoc = new PhotoDoc();
        photoDoc.setTime(photo.getTime());
        photoDoc.setId(photo.getId());
        photoDoc.setMake(photo.getMake());
        photoDoc.setModel(photo.getModel());
        if (Objects.nonNull(photo.getLatitude()) && Objects.nonNull(photo.getLongitude())) {
            photoDoc.setLocation(new GeoPoint(photo.getLatitude(), photo.getLongitude()));
        }
        photoDoc.setMd5Hex(photo.getMd5Hex());
        photoDoc.setSha1Hex(photo.getSha1Hex());
        photoDoc.setLength(photo.getLength());
        photoDoc.setName(photo.getName());
        photoDoc.setUserId(photo.getUserId());
        photoDocRepository.save(photoDoc);
    }

    @Override
    public boolean findExist(Photo photo) {
        List<PhotoDoc> list =
                photoDocRepository.findByMd5HexAndSha1HexAndLengthAndUserId(
                        photo.getMd5Hex(),
                        photo.getSha1Hex(),
                        photo.getLength(),
                        photo.getUserId());
        return !CollectionUtils.isEmpty(list);
    }

    @Override
    public CommonPaged<Photo> queryPagedByUserId(CommonPagedAble commonPagedAble, Long userId) {
        ArrayList<Photo> photos = new ArrayList<>();
        Pageable pageable = PageRequest.of(commonPagedAble.getPageNumber() - 1, commonPagedAble.getPageSize());
        Page<PhotoDoc> all = photoDocRepository.findByUserId(pageable, userId);
        if (!all.isEmpty()) {
            List<PhotoDoc> iterator = all.getContent();
            for (PhotoDoc next : iterator) {
                Photo photo = new Photo();
                BeanUtils.copyProperties(next, photo);
                GeoPoint geoPoint = next.getLocation();
                if (Objects.nonNull(geoPoint)) {
                    photo.setLatitude(geoPoint.getLat());
                    photo.setLongitude(geoPoint.getLon());
                }
                photos.add(photo);
            }
        }
        return CommonPaged.build(commonPagedAble.getPageNumber(), commonPagedAble.getPageSize(), all.getTotalElements(), all.getTotalPages(), photos);
    }
}
