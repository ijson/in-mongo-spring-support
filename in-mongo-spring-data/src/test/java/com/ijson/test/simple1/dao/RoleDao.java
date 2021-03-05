package com.ijson.test.simple1.dao;

import com.ijson.test.model.RoleEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
public interface RoleDao extends MongoRepository<RoleEntity, String>{
}