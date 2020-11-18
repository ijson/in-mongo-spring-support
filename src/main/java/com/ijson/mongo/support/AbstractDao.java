package com.ijson.mongo.support;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.bson.BSON;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.QueryFactory;
import org.mongodb.morphia.query.UpdateOperations;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.lang.reflect.ParameterizedType;
import java.math.BigDecimal;
import java.util.Objects;

@Setter
@Getter
@Slf4j
public class AbstractDao<T> {

    @Autowired
    protected DatastoreExt datastore;
    private Class<?> clazz;


    static {
        BSON.addEncodingHook(BigDecimal.class, (objectToTransform -> {
            if (Objects.nonNull(objectToTransform)) {
                return ((BigDecimal) objectToTransform).doubleValue();
            }
            return null;
        }));
    }

    @PostConstruct
    public void init() {
        ParameterizedType parameterizedType = (ParameterizedType) this.getClass().getGenericSuperclass();
        try {
            clazz = Class.forName(parameterizedType.getActualTypeArguments()[0].getTypeName());
            datastore.ensureIndexes(clazz, true);
            log.info("ensureIndex {}", clazz.getName());
        } catch (ClassNotFoundException e) {
            log.error("ensureIndex", e);
        }
    }

    public Query createQuery() {
        return datastore.createQuery(clazz);
    }

    public UpdateOperations createUpdateOperations() {
        return datastore.createUpdateOperations(clazz);
    }

    public DB getDB() {
        return datastore.getDB();
    }

    public DBCollection getCollection() {
        return datastore.getCollection(clazz);
    }

    public MongoClient getMongo() {
        return datastore.getMongo();
    }

    public QueryFactory getQueryFactory() {
        return datastore.getQueryFactory();
    }
}