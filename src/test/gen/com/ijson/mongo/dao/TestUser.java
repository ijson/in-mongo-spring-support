package com.ijson.mongo.dao;

import com.ijson.mongo.dao.entity.UserEntity;
import com.ijson.mongo.support.test.bean.User;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * desc:
 * version: 6.7
 * Created by cuiyongxu on 2019/7/22 1:32 PM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-mongo-biz.xml")
public class TestUser {

    @Autowired
    private UserDao userDao;

    @org.junit.Test
    public void add(){
        UserEntity user = new UserEntity();
        userDao.createOrUpdate(user);
    }
}
