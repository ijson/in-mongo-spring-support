package com.ijson.mongo.support.model;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

@Data
public class PageResult<E> {
    private long total;
    private List<E> dataList = Lists.newArrayList();
}