## 本组件基于mongodb 的封装
> 本组件依赖spring,且mongodb为非关系型数据库,请悉知,在使用本组件前,请优先安装mongodb

### 使用指南

1. 组件引入
   ```xml
   <dependency>
       <groupId>com.ijson</groupId>
       <artifactId>in-mongo-spring-support</artifactId>
       <version>1.0.0</version>
   </dependency>
   ```

2. 在resources/autoconf目录下,配置mongodb的相关配置文件,例如

   ```
   mongo.servers = mongodb://localhost
   mongo.dbName = test
   mongo.mapPackage = com.ijson.mongo.support.test.bean
   mongo.connectTimeout = 5000
   mongo.socketTimeout = 10000
   encrypt.pwd=false
   process.server.id=1
   ```

3. 在spring文件中,添加一下配置
   ```xml
   <bean id="mongoDatastore" class="com.ijson.mongo.support.MongoDataStoreFactory" p:configName="mongo-test"/>
   ```
   p:configName 为resources/autoconf目录下的mongodb配置文件

4. 定义实体类,用于存储对象数据,例如:
   ```java
   package com.ijson.mongo.support.test.bean;

   import lombok.Data;
   import org.bson.types.ObjectId;
   import org.mongodb.morphia.annotations.Entity;
   import org.mongodb.morphia.annotations.Id;

   /**
    * Created by cuiyongxu on 17/8/1.
    */
   @Entity(value = "user", noClassnameStored = true)
   @Data
   public class User {
       @Id
       private ObjectId id;
       private String username;
       private String password;

       public User(String username) {
           this.username = username;
       }
   }

   ```
5. 单元测试方式
   ```java
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
       public void addUser() {
           for (int i = 300; i < 500; i++) {
               User user = new User("cuiyongxu22-" + i);
               datastore.save(user);
           }
       }

   }

   ```
