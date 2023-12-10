package com.zhuzimo.util;

import com.drew.imaging.ImageMetadataReader;
import com.drew.lang.GeoLocation;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifIFD0Directory;
import com.drew.metadata.exif.GpsDirectory;
import com.zhuzimo.dto.PhotoExifInfo;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.Date;
import java.util.Objects;

/**
 * 照片EXIF阅读器
 *
 * @author t3
 * @date 2023/12/05
 */
public class PhotoExifReader {

    /**
     * 获取照片信息
     *
     * @param file 文件
     * @return {@link PhotoExifInfo}
     */
    public static PhotoExifInfo getPhotoExifInfo(File file) {
        PhotoExifInfo photoExifInfo = new PhotoExifInfo();
        try {
            Metadata metadata = ImageMetadataReader.readMetadata(file);

            ExifIFD0Directory directory = metadata.getFirstDirectoryOfType(ExifIFD0Directory.class);
            if (directory != null) {
                // 获取拍摄时间
                Date date = directory.getDate(ExifIFD0Directory.TAG_DATETIME_ORIGINAL);
                if (Objects.isNull(date)) {
                    date = directory.getDate(ExifIFD0Directory.TAG_DATETIME);
                }
                photoExifInfo.setTime(date);

                // 获取相机型号
                String make = directory.getString(ExifIFD0Directory.TAG_MAKE);
                photoExifInfo.setMake(make);

                // 获取相机型号
                String model = directory.getString(ExifIFD0Directory.TAG_MODEL);
                photoExifInfo.setModel(model);
            }

            GpsDirectory firstDirectoryOfType = metadata.getFirstDirectoryOfType(GpsDirectory.class);
            if (Objects.nonNull(firstDirectoryOfType)) {
                GeoLocation geoLocation = firstDirectoryOfType.getGeoLocation();
                if (Objects.nonNull(geoLocation)) {
                    photoExifInfo.setLatitude(geoLocation.getLatitude());
                    photoExifInfo.setLongitude(geoLocation.getLongitude());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return photoExifInfo;
    }
}
