package com.ijson.mongo.spring.data.impl;

import com.ijson.mongo.spring.data.IDao;
import com.ijson.mongo.support.model.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;

public abstract class IDaoImpl<T extends BaseEntity> implements IDao<T> {

    @Autowired
    public MongoTemplate mongoTemplate;

    /**
     * 数据插入  主键存在 报错:org.springframework.dao.DuplicateKeyException
     *
     * @param entity
     * @param <T>
     * @return
     */
    @Override
    public <T> T insert(T entity) {
        return mongoTemplate.insert(entity);
    }

    /**
     * 数据更新
     *
     * @param entity
     * @param <T>
     * @return
     */
    @Override
    public <T> T update(T entity) {
        return mongoTemplate.save(entity);
    }

    /**
     * 批量插入
     * @param entity
     */
    @Override
    public void batchInsert(List<T> entity) {
        mongoTemplate.insert(entity);
    }

    @Override
    public void delete(String... ids) {
        mongoTemplate.remove(ids);

    }

    @Override
    public T find(String id) {
        return null;
    }
}
