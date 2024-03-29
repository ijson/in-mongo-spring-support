package com.ijson.mongo.generator.model;

import com.google.common.collect.Lists;
import com.ijson.mongo.support.entity.BaseEntity;
import lombok.Data;

import java.util.List;

/**
 * desc:
 * version: 6.6
 * Created by cuiyongxu on 2019/6/13 4:15 PM
 */
@Data
public class ParamsVo {

    private List<Class<? extends BaseEntity>> entities;

    private List<ObjectInfo> objectInfos = Lists.newArrayList();

}
