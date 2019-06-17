package com.ijson.mongo.dao;

import com.ijson.mongo.dao.entity.UserEntity;
import com.ijson.mongo.dao.query.UserQuery;
import com.ijson.mongo.support.model.Page;
import com.ijson.mongo.support.model.PageResult;

public interface UserDao {

    UserEntity createOrUpdate(UserEntity entity);

    UserEntity find(String id);

    UserEntity enable(String id, boolean enable, String userId);

    PageResult<UserEntity> find(UserQuery query, Page page);

    UserEntity delete(String id, String userId);

    void delete(String id);
}

