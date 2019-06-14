package com.ijson.mongo.support.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * desc:
 * version: 6.6
 * Created by cuiyongxu on 2019/6/14 10:43 AM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Page {
    private int pageSize = 20;
    private int pageNumber = 1;
    private String orderBy;
    private boolean asc;

}
