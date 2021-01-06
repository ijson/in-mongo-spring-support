package com.ijson.mongo.support.test;

import com.google.common.collect.Lists;
import com.ijson.mongo.generator.util.ObjectId;
import com.ijson.mongo.support.DatastoreExt;
import com.ijson.mongo.support.test.bean.IndexEntity;
import com.ijson.mongo.support.test.bean.Pojo;
import com.ijson.mongo.support.test.bean.User;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
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
        User user = new User("cuiyowwwwwwngxu", "33", pojoList);
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


    @Test
    public void addIndex() {
        IndexEntity entity = new IndexEntity();
        entity.setId(ObjectId.getId());
        entity.setCname("崔永旭");
        entity.setTime(new Date());
        /**
         * 如果是Timestamp 需要将时间转一下
         */
//        entity.setTime(new Timestamp(System.currentTimeMillis()));
        datastore.save(entity);
    }


    @Test
    public void insert() {
        for (int i = 0; i < 50; i++) {
            User user = new User();
            user.setUsername("a" + i);
            datastore.save(user);
        }
    }

    @Test
    public void delete() {
        datastore.find(User.class).getCollection().find().batchSize(10).forEachRemaining(k->{
            Query<User> query = datastore.createQuery(User.class).disableValidation();
            query.criteria("_id").equal(k.get("_id"));
            //datastore.delete(query);
        });
//        iterator.forEachRemaining(k -> {
//
//        });
    }

    @Test
    public void delete2() {

        Iterator<DBObject> iterator = datastore.find(User.class).getCollection().find().iterator();
        iterator.forEachRemaining(k -> {
            Query<User> query = datastore.createQuery(User.class).disableValidation();
            query.criteria("_id").equal(k.get("_id"));
            datastore.delete(query);
        });


    }


}
