package com.ijson.mongo;

import com.ijson.mongo.support.entity.page.Page;
import com.ijson.mongo.support.entity.page.PageResult;

import java.util.List;

/**
 * desc:
 * author: cuiyongxu
 * create_time: 2021/7/12-10:52 下午
 **/
public interface BaseService<T, Q> {

    T findInternalById(String id);

    T findById(String id);

    T findOne(String field, Object value);

    List<T> findMany(String field, Object value);

    T create(T entity, String userId);

    void enable(String id, boolean enable, String userId);

    void delete(String id);

    void inc(String field, String commentId);

    void deleteById(String id, String userId);

    PageResult<T> find(Q query, Page pageEntity);

    List<T> findAll();

    List<T> findByIds(List<String> ids);
}
