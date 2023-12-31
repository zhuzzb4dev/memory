package com.zhuzimo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 照片EXIF信息
 *
 * @author t3
 * @date 2023/12/11
 */
@Getter
@Setter
public class PhotoExifInfo {
    private Date time;
    private String make;
    private String model;
    private Double latitude;
    private Double longitude;
}
