package com.zhuzimo.po;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

import java.util.Date;

/**
 * 照片文档
 *
 * @author t3
 * @date 2023/12/11
 */
@Document(indexName = "photo")
@Getter
@Setter
public class PhotoDoc {
    @Id
    private String id;
    @Field(type = FieldType.Keyword)
    private Date time;
    @Field(type = FieldType.Keyword)
    private String make;
    @Field(type = FieldType.Keyword)
    private String model;
    @Field(type = FieldType.Keyword)
    private GeoPoint location;
    @Field(type = FieldType.Keyword)
    private String md5Hex;
    @Field(type = FieldType.Keyword)
    private String sha1Hex;
    @Field(type = FieldType.Keyword)
    private Long length;
    @Field(type = FieldType.Keyword)
    private String name;
    @Field(type = FieldType.Keyword)
    private Long userId;
}
