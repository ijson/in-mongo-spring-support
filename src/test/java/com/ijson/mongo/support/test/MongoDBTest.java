package com.ijson.mongo.support.test;

import com.google.common.collect.Lists;
import com.ijson.mongo.support.DatastoreExt;
import com.ijson.mongo.support.test.bean.Pojo;
import com.ijson.mongo.support.test.bean.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by cuiyongxu on 17/8/1.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-mongo-datastore.xml")
public class MongoDBTest {

    @Resource(name = "mongoDatastore")
    private DatastoreExt datastore;

    @Test
    public void addUser() {
        List<Pojo> pojoList = Lists.newArrayList();
        pojoList.add(new Pojo("function", "0", null));
        pojoList.add(new Pojo("send_qixin", "0", null));
        pojoList.add(new Pojo("send_email", "0", null));
        User user = new User("cuiyongxu", "33", pojoList);
        datastore.save(user);
    }


    @Test
    public void updateList() {
        Query<User> query = datastore.createQuery(User.class).disableValidation();
        query.criteria("_id").equal("5d1b073c14cc8f0b89f0b3e3");
        UpdateOperations<User> updateOperations = datastore.createUpdateOperations(User.class);
        updateOperations.set("pojoList", "");
        datastore.update(query, updateOperations);
    }


}
