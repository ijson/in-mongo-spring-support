package com.ijson.mongo.generator.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * desc:
 * version: 6.6
 * Created by cuiyongxu on 2019/6/13 6:20 PM
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ObjectFiled {

    private String name;

    private String simpleName;

    private String type;

    private String simpleType;

    private Class typeClazz;

}
