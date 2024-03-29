package com.ijson.mongo;

import com.ijson.mongo.support.entity.page.Page;
import com.ijson.mongo.support.entity.page.PageResult;
import org.mongodb.morphia.query.Query;

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

    void dec(String field, String commentId);

    PageResult<T> find(Q query, Page pageEntity);

    /**
     * 不做停用删除校验
     * @param query
     * @param pageEntity
     * @return
     */
    PageResult<T> findInternal(Q query, Page pageEntity);

    PageResult<T> find(Query<T> query, Page pageEntity);

    List<T> findAll();

    List<T> findByIds(List<String> ids);

    long count(String field, Object data);
}
