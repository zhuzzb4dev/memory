package com.zhuzimo.photo.aggregate;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 相片
 *
 * @author t3
 * @date 2023/12/11
 */
@Getter
@Setter
public class Photo {
    private String id;
    private Date time;
    private String make;
    private String model;
    private Double latitude;
    private Double longitude;
    private String md5Hex;
    private String sha1Hex;
    private String name;
    private Long length;
    private Long userId;
}
