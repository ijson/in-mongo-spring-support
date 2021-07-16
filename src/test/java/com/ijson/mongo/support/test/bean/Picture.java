package com.ijson.mongo.support.test.bean;

import com.ijson.mongo.support.entity.BaseEntity;
import lombok.Data;

import java.util.List;

/**
 * desc:图片 https://baike.baidu.com/item/%E5%9B%BE%E5%83%8F%E5%9F%BA%E6%9C%AC%E5%B1%9E%E6%80%A7/9431341?fr=aladdin
 * <p>
 * version: 7.3.0
 * Created by cuiyongxu on 2020/10/5 8:19 PM
 */
@Data
public class Picture extends BaseEntity {

    private String cname;
    private String small;
    private String middle;
    private String source;
    private long fileSize;

    private long width;
    private long height;

    private String categoryId;
    private String resolutionId;
    
    private String authorId;
    private List<String> tagIds;
    private long uploadTime;


}
