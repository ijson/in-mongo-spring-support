package com.ijson.mongo.dao.impl;

import com.google.common.base.Strings;
import com.ijson.mongo.dao.UserDao;
import com.ijson.mongo.dao.entity.UserEntity;
import com.ijson.mongo.dao.query.UserQuery;
import com.ijson.mongo.generator.util.ObjectId;
import com.ijson.mongo.support.AbstractDao;
import com.ijson.mongo.support.model.Page;
import com.ijson.mongo.support.model.PageResult;
import com.mongodb.WriteConcern;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.Objects;

@Component
public class UserDaoImpl extends AbstractDao<UserEntity> implements UserDao {


    @Override
    public UserEntity createOrUpdate(UserEntity entity) {
        if (!Strings.isNullOrEmpty(entity.getId())) {
            entity = findAndModify(entity);
        } else {
            entity.setId(ObjectId.getId());
            datastore.save(entity);
            return entity;
        }
        return entity;
    }

    private UserEntity findAndModify(UserEntity entity) {
        Query<UserEntity> query = createQuery();
        query.field(UserEntity.Fields.id).equal(entity.getId());
        UpdateOperations operations = createUpdateOperations();


		operations.set(UserEntity.Fields.id, entity.getId());
		operations.set(UserEntity.Fields.username, entity.getUsername());
		operations.set(UserEntity.Fields.password, entity.getPassword());

        return datastore.findAndModify(query, operations);
    }

    @Override
    public UserEntity find(String id) {
        Query<UserEntity> query = datastore.createQuery(UserEntity.class);
        query.field(UserEntity.Fields.id).equal(id);
        UserEntity entity = query.get();
        if (!Objects.isNull(entity)) {
            return entity;
        } else {
            throw new RuntimeException("数据不存在或已删除");
        }
    }

    @Override
    public UserEntity enable(String id, boolean enable, String userId) {
        Query<UserEntity> query = datastore.createQuery(UserEntity.class);
        query.field(UserEntity.Fields.id).equal(id);
        UpdateOperations<UserEntity> updateOperations = datastore.createUpdateOperations(UserEntity.class);
        updateOperations.set(UserEntity.Fields.enable, enable);
        updateOperations.set(UserEntity.Fields.lastModifiedBy, userId);
        return datastore.findAndModify(query, updateOperations);
    }

    @Override
    public PageResult<UserEntity> find(UserQuery iquery, Page page) {
        Query<UserEntity> query = datastore.createQuery(UserEntity.class);
        if (!Strings.isNullOrEmpty(iquery.getId())) {
            query.field(UserEntity.Fields.id).equal(iquery.getId());
        }

        query.field(UserEntity.Fields.deleted).equal(false);
        if (page.getOrderBy() != null) {
            query.order("-" + page.getOrderBy());//添加排序
        }
        if (page.getPageNumber() > 0) {
            query.offset((page.getPageNumber() - 1) * page.getPageSize()).limit(page.getPageSize());
        }

        long totalNum = query.countAll();
        List<UserEntity> entities = query.asList();

        PageResult<UserEntity> ret = new PageResult<>();
        ret.setDataList(entities);
        ret.setTotal(totalNum);
        return ret;
    }

    @Override
    public UserEntity delete(String id, String userId) {
        Query<UserEntity> query = datastore.createQuery(UserEntity.class);
        query.field(UserEntity.Fields.createdBy).equal(userId);
        query.field(UserEntity.Fields.id).equal(id);
        UpdateOperations<UserEntity> updateOperations = datastore.createUpdateOperations(UserEntity.class);
        updateOperations.set(UserEntity.Fields.lastModifiedBy, userId);
        updateOperations.set(UserEntity.Fields.deleted, true);
        return datastore.findAndModify(query, updateOperations);
    }

    @Override
    public void delete(String id) {
        datastore.delete(datastore.createQuery(UserEntity.class).field(UserEntity.Fields.id).equal(id), WriteConcern.UNACKNOWLEDGED);
    }
}
