package com.ijson.mongo.spring.data;

import com.mongodb.client.MongoClient;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;

public class MongoAdapter extends MongoTemplate {

    private String configName;

    public MongoAdapter(MongoClient mongoClient, String databaseName) {
        super(mongoClient, databaseName);
    }
}
