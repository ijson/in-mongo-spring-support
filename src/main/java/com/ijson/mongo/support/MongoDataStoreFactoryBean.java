package com.ijson.mongo.support;


import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.google.common.net.UrlEscapers;
import com.google.common.reflect.Reflection;
import com.ijson.rest.proxy.config.ConfigFactory;
import com.ijson.rest.proxy.config.ExtMap;
import com.mongodb.*;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.mapping.MapperOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MongoDataStoreFactoryBean implements InitializingBean, DisposableBean, FactoryBean<DatastoreExt> {

    private final Logger log = LoggerFactory.getLogger(MongoDataStoreFactoryBean.class);
    private String configName;
    private String mapName;
    private String mongoServer;
    private MongoClientOptions.Builder builder;
    private boolean ignoreInvalidClasses = false;
    private boolean storeEmpties = false;
    private boolean storeNulls = false;
    /**
     * 每个db对应一个proxy示例,避免多线程切换db问题
     */
    private Map<String, MyHandler> stores = Maps.newConcurrentMap();
    private DatastoreExt current;

    public void setConfigName(String configName) {
        this.configName = configName;
    }

    private DatastoreExt getOrCreate(String name) {
        MyHandler handler = stores.get(name);
        if (handler == null) {
            synchronized (this) {
                handler = stores.get(name);
                if (handler == null) {
                    handler = new MyHandler(name);
                    stores.put(name, handler);
                }
            }
        }
        return Reflection.newProxy(DatastoreExt.class, handler);
    }

    private void loadConfig(ExtMap<String, Object> config) {
        String dbName = config.getString("mongo.dbName");
        mapName = config.getString("mongo.mapPackage");
        ignoreInvalidClasses = config.getBool("mongo.ignoreInvalidClasses");
        storeEmpties = config.getBool("mongo.storeEmpties");
        storeNulls = config.getBool("mongo.storeNulls");
        String readPreference = config.getString("mongo.readPreference", "primary");
        Integer maxWaitTime = config.getInt("mongo.maxWaitTime", 120000);
        Integer maxConnectionsPerHost = config.getInt("mongo.maxConnectionsPerHost", 100);
        Integer connectTimeout = config.getInt("mongo.connectTimeout", 5000);
        Integer socketTimeout = config.getInt("mongo.socketTimeout", 60000);
        String mongoServer = config.getString("mongo.servers");
        Preconditions.checkNotNull(mongoServer, "The servers configuration is incorrect!");


        if (config.getBool("encrypt.pwd")) {
            Pattern p = Pattern.compile("mongodb://((.+):(.*)@)");
            Matcher m = p.matcher(mongoServer);
            if (m.find()) {
                try {
                    String pwd = UrlEscapers.urlFormParameterEscaper().escape(m.group(3));
                    mongoServer = mongoServer.substring(0, m.end(2) + 1) + pwd + mongoServer.substring(m.end(1) - 1);
                } catch (Exception e) {
                    log.error("cannot decode " + m.group(3), e);
                }
            }
        }


        MongoClientOptions.Builder builder = new MongoClientOptions.Builder();
        builder.socketKeepAlive(true)
                .readPreference(ReadPreference.valueOf(readPreference))
                .maxWaitTime(maxWaitTime)
                .connectionsPerHost(maxConnectionsPerHost)
                .connectTimeout(connectTimeout)
                .socketTimeout(socketTimeout);

        this.mongoServer = mongoServer;
        this.builder = builder;
        //mongodb://
        //mongodb://[username:password@]host1[:port1][,host2[:port2],…[,hostN[:portN]]][/[database][?options]]
        MongoClientURI uri = new MongoClientURI(this.mongoServer, this.builder);
        if (!Strings.isNullOrEmpty(dbName)) {
            current = getOrCreate(dbName);
        } else {
            String db = uri.getDatabase() != null ? uri.getDatabase() : "local";
            current = getOrCreate(db);
        }
        stores.values().forEach(MyHandler::refresh);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        loadConfig(ConfigFactory.getConfig(configName));
    }

    @Override
    public void destroy() throws Exception {
        for (MyHandler handler : stores.values()) {
            handler.close();
        }
        stores.clear();
    }

    @Override
    public Class<DatastoreExt> getObjectType() {
        return DatastoreExt.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    @Override
    public DatastoreExt getObject() {
        return current;
    }

    private class MyHandler implements InvocationHandler, AutoCloseable {
        private final String db;
        private MongoClient mongo;
        private Datastore delegate;
        private String hosts;

        MyHandler(String db) {
            this.db = db;
            refresh();
        }

        void refresh() {
            MongoClientURI uri = null;
            // 如果配置的dbName和要使用的不一致,这里要做切换,否则会报权限认证失败
            if (!mongoServer.endsWith('/' + db)) {
                int pos = mongoServer.lastIndexOf('/');
                if (pos > 0 && mongoServer.charAt(pos - 1) != '/') {
                    uri = new MongoClientURI(mongoServer.substring(0, pos + 1) + db, builder);
                }
            }
            if (uri == null) {
                uri = new MongoClientURI(mongoServer, builder);
            }
            hosts = Joiner.on(',').join(uri.getHosts());
            Morphia morphia = new Morphia();
            MapperOptions options = morphia.mapPackage(mapName, ignoreInvalidClasses).getMapper().getOptions();
            options.setStoreEmpties(storeEmpties);
            options.setStoreNulls(storeNulls);
            if (mongo != null) {
                mongo.close();
            }
            mongo = new MongoClient(uri);
            delegate = morphia.createDatastore(mongo, db);
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            String methodName = method.getName();
            if (methodName.equals("use")) {
                String name = (String) args[0];
                return getOrCreate(name);
            } else {
                return handle(method, args);
            }
        }

        private Object handle(Method method, Object[] args) throws Exception {
            try {
                return method.invoke(delegate, args);
            } catch (Exception e) {
                throw e;
            }
        }

        @Override
        public void close() throws Exception {
            if (mongo != null) {
                mongo.close();
            }
        }
    }
}
