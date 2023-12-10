package com.zhuzimo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PhotoExifInfo {
    private Date time;
    private String make;
    private String model;
    private Double latitude;
    private Double longitude;
}
