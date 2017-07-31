package com.ijson.mongo.support;

import org.mongodb.morphia.AdvancedDatastore;

/**
 * 扩展datastore功能,提供切换db功能
 */
public interface DatastoreExt extends AdvancedDatastore {
  /**
   * 切换使用的db
   *
   * @param dbName db名字
   * @return datastore对象
   */
  DatastoreExt use(String dbName);
}
