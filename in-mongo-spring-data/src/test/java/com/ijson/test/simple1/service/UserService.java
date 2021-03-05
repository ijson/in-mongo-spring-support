package com.ijson.test.simple1.service;


import com.ijson.test.model.RoleEntity;
import com.ijson.test.model.UserEntity;
import com.ijson.test.simple1.dao.UserDao;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public UserEntity save(UserEntity user) {
        return userDao.save(user);
    }

    public UserEntity findById(String id) {
        return userDao.findById(id).get();
    }

    public List<UserEntity> findAll() {
        return userDao.findAll();
    }

    public UserEntity findByRole(RoleEntity role) {
        return userDao.findByRoleId(new ObjectId(role.getId()));
    }
}
