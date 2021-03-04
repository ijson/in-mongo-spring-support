package com.ijson.mongo.spring.data;

import com.ijson.mongo.support.model.BaseEntity;

import java.util.List;

public interface IDao<T extends BaseEntity> {

    <T> T insert(T entity);

    void update(T entity);

    <T> T update(T entity);

    <T> T insert(List<T> entity);

    void batchInsert(List<T> entity);

    void delete(String... ids);

    T find(String id);

}
