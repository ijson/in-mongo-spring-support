package com.ijson.mongo.support.entity.page;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

@Data
public class PageResult<E> {

    private long total;
    private List<E> dataList = Lists.newArrayList();


    public List<E> getDataList() {
        return dataList == null ? Lists.newArrayList() : dataList;
    }

    public static PageResult of(long total, List dataList) {
        PageResult pageResult = new PageResult();
        pageResult.setDataList(dataList);
        pageResult.setTotal(total);
        return pageResult;
    }
}