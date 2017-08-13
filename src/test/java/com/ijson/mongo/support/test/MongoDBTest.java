package com.ijson.mongo.support.test;

import com.ijson.mongo.support.DatastoreExt;
import com.ijson.mongo.support.test.bean.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by cuiyongxu on 17/8/1.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-mongo-datastore.xml")
public class MongoDBTest {

    @Resource(name = "mongoDatastore")
    private DatastoreExt datastore;


    @Test
    public void testUserTwoOperate2() throws Exception {
        for (int i = 0; i < 200; i++) {
            User user = new User("twoeftrr22222" + i);
            datastore.save(user);
            //Thread.sleep(1000L);
        }
    }

    @Test
    public void addUser() {
        for (int i = 100; i < 200; i++) {
            User user = new User("cuiyongxu22-" + i);
            datastore.save(user);
        }
    }

}
