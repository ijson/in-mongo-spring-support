package com.ijson.mongo.generator.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * desc:
 * version: 6.6
 * Created by cuiyongxu on 2019/6/13 7:20 PM
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GenConfig {

    private String packager;

    private String genPath;

    private String projectName;
}
