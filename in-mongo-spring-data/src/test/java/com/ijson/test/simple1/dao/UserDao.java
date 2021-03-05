package com.ijson.test.simple1.dao;

import com.ijson.test.model.RoleEntity;
import com.ijson.test.model.UserEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UserDao extends MongoRepository<UserEntity, String> {


    UserEntity findByRole(RoleEntity role);

    @Query(value = "{'role.$id': ?0 }")
    UserEntity findByRoleId(ObjectId id);
}