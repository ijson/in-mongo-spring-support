package com.ijson.mongo.generator.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * desc:
 * version: 6.6
 * Created by cuiyongxu on 2019/6/13 4:48 PM
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ObjectInfo {

    private String name;

    private String simpleName;

    private String lowerFirstName;

    private List<ObjectFiled> fileds;

}
