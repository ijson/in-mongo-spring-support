package com.ijson.mongo.support.test.bean;

import com.ijson.mongo.support.model.BaseEntity;
import lombok.Data;

/**
 * desc:图片类别
 * <p>
 * version: 7.3.0
 * Created by cuiyongxu on 2020/10/5 8:11 PM
 */
@Data
public class PictureCategory extends BaseEntity {

    private String id;
    private String code;

    private String came;

    private Long count;
}
