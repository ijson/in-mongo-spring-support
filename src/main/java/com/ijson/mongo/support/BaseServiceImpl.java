package com.ijson.mongo.support;

import com.google.common.collect.Lists;
import com.ijson.mongo.AbstractDao;
import com.ijson.mongo.BaseService;
import com.ijson.mongo.support.entity.BaseEntity;
import com.ijson.mongo.support.entity.BaseQuery;
import com.ijson.mongo.support.entity.page.Page;
import com.ijson.mongo.support.entity.page.PageResult;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.mongodb.morphia.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * desc:
 * author: cuiyongxu
 * create_time: 2021/7/12-10:52 下午
 **/
@Data
@Slf4j
public abstract class BaseServiceImpl<T extends BaseEntity, Q extends BaseQuery> implements BaseService<T, Q> {

    @Autowired
    private AbstractDao<T, Q> abstractDao;


    @Override
    public T findInternalById(String id) {
        return abstractDao.findInternalById(id);
    }


    @Override
    public T findById(String id) {
        return abstractDao.findById(id);
    }


    @Override
    public T findOne(String field, Object value) {
        return abstractDao.findOne(field, value);
    }


    @Override
    public List<T> findMany(String field, Object value) {
        return abstractDao.findMany(field, value);
    }


    //TODO 缓存 @RefreshCache(cache = "headers")
    @Override
    public T create(T entity, String userId) {
        entity.setCreatedBy(userId);
        entity.setLastModifiedBy(userId);
        return abstractDao.create(entity);
    }


    //TODO 缓存  @RefreshCache(cache = "headers")
    @Override
    public void enable(String id, boolean enable, String userId) {
        abstractDao.enable(id, enable, userId);
    }


    //TODO 缓存 @RefreshCache(cache = "headers")
    @Override
    public void delete(String id) {
        abstractDao.delete(id);
    }


    @Override
    public void deleteById(String id, String userId) {
        abstractDao.delete(id, userId);
    }


    @Override
    public void inc(String field, String commentId) {
        abstractDao.inc(field, commentId);
    }

    @Override
    public void dec(String field, String commentId) {
        abstractDao.dec(field, commentId);
    }


    @Override
    public PageResult<T> find(Q query, Page pageEntity) {
        return abstractDao.find(query, pageEntity);
    }

    @Override
    public PageResult<T> findInternal(Q query, Page pageEntity) {
        return abstractDao.findInternal(query, pageEntity);
    }


    @Override
    public PageResult<T> find(Query<T> query, Page pageEntity) {
        return abstractDao.find(query, pageEntity);
    }


    //TODO 缓存
//    @Cacheable(value = "blogroll")
    //TODO entity 转 info
    @Override
    public List<T> findAll() {
        return abstractDao.findAll();
    }


    @Override
    public List<T> findByIds(List<String> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return Lists.newArrayList();
        }
        return abstractDao.findByIds(ids);
    }

    @Override
    public long count(String field, Object data) {
        return abstractDao.count(field, data);
    }

}
