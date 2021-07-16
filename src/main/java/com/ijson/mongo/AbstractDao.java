package com.ijson.mongo;

import com.ijson.mongo.support.entity.BaseEntity;
import com.ijson.mongo.support.entity.BaseQuery;
import org.mongodb.morphia.query.Query;

import java.util.List;

/**
 * desc:
 * author: cuiyongxu
 * create_time: 2021/7/17-2:51 上午
 **/
public interface AbstractDao<T extends BaseEntity,Q extends BaseQuery> {

    T create(T entity);

    T enable(String id, boolean enable, String userId);

    void delete(String id);

    T delete(String id, String userId);

    T findInternalById(String id);

    T findById(String id);

    List<T> findByIds(List<String> ids);

    T findOne(Query<T> query);

    T findOne(String field, Object data);

    List<T> findAll();

    List<T> findMany(String field, Object data);

    void inc(String id, String field);

    long count();

    long count(String field, Object data);

    List<T> lessThan(String field, long beforTime);
}
