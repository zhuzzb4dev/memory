package com.zhuzimo.repository;

import com.zhuzimo.account.dp.Photo;
import com.zhuzimo.account.repository.PhotoRepository;
import com.zhuzimo.po.PhotoDoc;
import org.springframework.beans.BeanUtils;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;

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
    public List<Photo> findAll() {
        ArrayList<Photo> photos = new ArrayList<>();
        Iterable<PhotoDoc> all = photoDocRepository.findAll();
        Iterator<PhotoDoc> iterator = all.iterator();
        while (iterator.hasNext()) {
            PhotoDoc next = iterator.next();
            Photo photo = new Photo();
            BeanUtils.copyProperties(next, photo);
            GeoPoint geoPoint = next.getLocation();
            if (Objects.nonNull(geoPoint)) {
                photo.setLatitude(geoPoint.getLat());
                photo.setLongitude(geoPoint.getLon());
            }
            photos.add(photo);
        }
        return photos;
    }

    @Override
    public boolean findExist(Photo photo) {
        List<PhotoDoc> list = photoDocRepository.findByMd5HexAndSha1HexAndLength(photo.getMd5Hex(), photo.getSha1Hex(), photo.getLength());
        return !CollectionUtils.isEmpty(list);
    }
}
