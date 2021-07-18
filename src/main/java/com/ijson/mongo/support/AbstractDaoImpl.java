package com.ijson.mongo.support;

import com.google.common.base.Strings;
import com.ijson.mongo.AbstractDao;
import com.ijson.mongo.generator.util.ObjectId;
import com.ijson.mongo.support.entity.BaseEntity;
import com.ijson.mongo.support.entity.BaseQuery;
import com.ijson.mongo.support.entity.page.Page;
import com.ijson.mongo.support.entity.page.PageResult;
import com.mongodb.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.bson.BSON;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.annotation.PostConstruct;
import java.lang.reflect.ParameterizedType;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

@Setter
@Getter
@Slf4j
public class AbstractDaoImpl<T extends BaseEntity, Q extends BaseQuery> implements AbstractDao<T, Q> {

    @Autowired
    @Qualifier("datastore")
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

    /**
     * 创建数据查询对象
     *
     * @return
     */
    public Query createQuery() {
        return datastore.createQuery(clazz);
    }

    /**
     * 创建预制数据查询对象
     *
     * @param field
     * @param value
     * @return
     */
    public Query<T> createEqualQuery(String field, Object value) {
        Query<T> query = createQuery();
        query.field(field).equal(value);
        return query;
    }


    public Query<T> createInQuery(String field, Collection value) {
        Query<T> query = createQuery();
        query.field(field).in(value);
        return query;
    }

    /**
     * 创建更新数据对象
     *
     * @return
     */
    public UpdateOperations createUpdate() {
        return datastore.createUpdateOperations(clazz);
    }

    /**
     * 设置字段更新数据值
     *
     * @param operations
     * @param key
     * @param value
     * @return
     */
    public UpdateOperations set(UpdateOperations operations, String key, Object value) {
        if (value == null) {
            return operations;
        }
        if (value instanceof String && !Strings.isNullOrEmpty((String) value)) {
            operations.set(key, value);
            return operations;
        }
        operations.set(key, value);

        return operations;
    }


    /**
     * 数据创建
     *
     * @param entity
     * @return
     */
    @Override
    public T create(T entity) {
        if (Strings.isNullOrEmpty(entity.getId())) {
            entity.setId(ObjectId.getId());
        }
        datastore.save(entity);
        return entity;
    }

    /**
     * 禁用/启用
     *
     * @param id
     * @param enable
     * @param userId
     * @return
     */
    @Override
    public T enable(String id, boolean enable, String userId) {
        Query<T> query = createQuery();
        query.field(BaseEntity.Fields._id).equal(id);
        UpdateOperations<T> updateOperations = createUpdate();
        updateOperations.set(BaseEntity.Fields.enable, enable);
        updateOperations.set(BaseEntity.Fields.lastModifiedBy, userId);
        updateOperations.set(BaseEntity.Fields.lastModifiedTime, System.currentTimeMillis());
        return datastore.findAndModify(query, updateOperations);
    }


    /**
     * 数据更新
     *
     * @param query
     * @param operations
     * @return
     */
    @Override
    public T findAndModify(Query<T> query, UpdateOperations<T> operations) {
        operations.set(BaseEntity.Fields.lastModifiedTime, System.currentTimeMillis());
        return datastore.findAndModify(query, operations);
    }

    /**
     * 数据更新 没有就创建
     *
     * @param query
     * @param operations
     * @param oldVersion
     * @param createIfMissing
     * @return
     */
    @Override
    public T findAndModify(Query<T> query, UpdateOperations<T> operations, boolean oldVersion, boolean createIfMissing) {
        operations.set(BaseEntity.Fields.lastModifiedTime, System.currentTimeMillis());
        return datastore.findAndModify(query, operations, oldVersion, createIfMissing);
    }

    /**
     * 物理删除
     *
     * @param id
     */
    @Override
    public void delete(String id) {
        deleteByField(BaseEntity.Fields._id, id);
    }


    /**
     * 物理删除
     *
     * @param field
     * @param data
     */
    @Override
    public void deleteByField(String field, Object data) {
        datastore.delete(createQuery().field(field).equal(data), WriteConcern.UNACKNOWLEDGED);
    }


    /**
     * 逻辑删除
     *
     * @param id
     * @param userId
     * @return
     */
    @Override
    public T delete(String id, String userId) {
        Query<T> query = createQuery();
        query.field(BaseEntity.Fields._id).equal(id);
        UpdateOperations<T> updateOperations = createUpdate();

        updateOperations.set(BaseEntity.Fields.deleted, true);
        updateOperations.set(BaseEntity.Fields.enable, false);

        updateOperations.set(BaseEntity.Fields.lastModifiedBy, userId);
        updateOperations.set(BaseEntity.Fields.lastModifiedTime, System.currentTimeMillis());

        return datastore.findAndModify(query, updateOperations);
    }

    /**
     * 查询数据 不校验启用停用或删除
     *
     * @param id
     * @return
     */
    @Override
    public T findInternalById(String id) {
        Query<T> query = createQuery();
        query.field(BaseEntity.Fields._id).equal(id);
        return query.get();
    }

    /**
     * 查询正常状态数据
     *
     * @param id
     * @return
     */
    @Override
    public T findById(String id) {
        Query<T> query = createQuery();
        query.field(BaseEntity.Fields._id).equal(id);
        query.field(BaseEntity.Fields.enable).equal(true);
        query.field(BaseEntity.Fields.deleted).equal(false);
        return query.get();
    }

    /**
     * 通过id查询数据(校验启用停用)
     *
     * @param ids
     * @return
     */
    @Override
    public List<T> findByIds(List<String> ids) {
        Query<T> query = createQuery();
        query.field(BaseEntity.Fields.enable).equal(true);
        query.field(BaseEntity.Fields.deleted).equal(false);
        query.field(BaseEntity.Fields._id).hasAnyOf(new HashSet<>(ids));
        return query.asList();
    }

    /**
     * 根据id查询并返回特定字段
     *
     * @param ids
     * @param fields
     * @return
     */
    @Override
    public List<T> findIncludeByIds(List<String> ids, String... fields) {
        Query<T> query = createQuery();
        query.field(BaseEntity.Fields.enable).equal(true);
        query.field(BaseEntity.Fields.deleted).equal(false);
        query.retrievedFields(true, fields);
        query.field(BaseEntity.Fields._id).hasAnyOf(new HashSet<>(ids));
        return query.asList();
    }


    /**
     * 通过id查询数据 不校验启用停用删除
     *
     * @param ids
     * @return
     */
    @Override
    public List<T> findInternalByIds(List<String> ids) {
        Query<T> query = createQuery();
        query.field(BaseEntity.Fields._id).hasAnyOf(new HashSet<>(ids));
        return query.asList();
    }

    /**
     * 自行拼接条件 查询数据
     *
     * @param query
     * @return
     */
    @Override
    public T findOne(Query<T> query) {
        return query.get();
    }


    /**
     * 通过字段及值查询数据
     *
     * @param field
     * @param data  不能为空
     * @return
     */
    @Override
    public T findOne(String field, Object data) {
        Query<T> query = createQuery();
        query.field(field).equal(data);
        query.field(BaseEntity.Fields.enable).equal(true);
        query.field(BaseEntity.Fields.deleted).equal(false);
        return query.get();
    }


    /**
     * 通过属性及值 查询数据 不验证启用/停用 或删除
     *
     * @param field
     * @param data
     * @return
     */
    @Override
    public T findInternalOne(String field, Object data) {
        Query<T> query = createQuery();
        query.field(field).equal(data);
        return query.get();
    }

    /**
     * 查询所有  慎用,会将所有数据加载至内存
     *
     * @return
     */
    @Override
    public List<T> findAll() {
        Query<T> query = createQuery();
        query.order("-" + BaseEntity.Fields._id);
        query.field(BaseEntity.Fields.enable).equal(true);
        query.field(BaseEntity.Fields.deleted).equal(false);
        return query.asList();
    }


    /**
     * 查询所有  慎用,会将所有数据加载至内存  不验证启用/停用 或删除
     *
     * @return
     */
    @Override
    public List<T> findInternalAll() {
        Query<T> query = createQuery();
        query.order("-" + BaseEntity.Fields._id);
        return query.asList();
    }

    /**
     * 通过字段及值查询数据列表
     *
     * @param field
     * @param data
     * @return
     */
    @Override
    public List<T> findMany(String field, Object data) {
        Query<T> query = createQuery();
        query.field(field).equal(data);
        query.field(BaseEntity.Fields.enable).equal(true);
        query.field(BaseEntity.Fields.deleted).equal(false);
        return query.asList();
    }

    @Override
    public List<T> findMany(Query query) {
        if (query == null) {
            query = createQuery();
        }
        query.field(BaseEntity.Fields.enable).equal(true);
        query.field(BaseEntity.Fields.deleted).equal(false);
        return query.asList();
    }

    /**
     * 通过字段及值查询数据列表 不验证启用/停用 或删除
     *
     * @param field
     * @param data
     * @return
     */
    @Override
    public List<T> findInternalMany(String field, Object data) {
        Query<T> query = createQuery();
        query.field(field).equal(data);
        return query.asList();
    }

    @Override
    public PageResult<T> find(Q iquery, Page page) {
        Query<T> query = createQuery();

        if (!Strings.isNullOrEmpty(iquery.getId())) {
            query.field(BaseEntity.Fields._id).equal(iquery.getId());
        }

        if (!Strings.isNullOrEmpty(page.getOrderBy())) {
            query.order("-" + page.getOrderBy());//添加排序
        } else {
            query.order("-" + BaseEntity.Fields._id);
        }
        if (page.getPageNumber() > 0) {
            query.offset((page.getPageNumber() - 1) * page.getPageSize()).limit(page.getPageSize());
        }

        query.field(BaseEntity.Fields.deleted).equal(Objects.nonNull(iquery.getDeleted()) ? iquery.getDeleted() : false);
        query.field(BaseEntity.Fields.enable).equal(Objects.nonNull(iquery.getEnable()) ? iquery.getEnable() : true);

        long totalNum = count();
        List<T> entities = query.asList();


        PageResult<T> ret = new PageResult<>();
        ret.setDataList(entities);
        ret.setTotal(totalNum);
        return ret;
    }


    /**
     * 数据查询 分页  自行控制数据
     *
     * @param query
     * @param page
     * @return
     */
    @Override
    public PageResult<T> find(Query<T> query, Page page) {
        if (query == null) {
            query = createQuery();
        }

        if (!Strings.isNullOrEmpty(page.getOrderBy())) {
            query.order("-" + page.getOrderBy());//添加排序
        } else {
            query.order("-" + BaseEntity.Fields._id);
        }
        if (page.getPageNumber() > 0) {
            query.offset((page.getPageNumber() - 1) * page.getPageSize()).limit(page.getPageSize());
        }


        long totalNum = count();
        List<T> entities = query.asList();

        PageResult<T> ret = new PageResult<>();
        ret.setDataList(entities);
        ret.setTotal(totalNum);
        return ret;
    }


    /**
     * 常用与点赞 +1
     *
     * @param id
     * @param field
     */
    @Override
    public void inc(String field, String id) {
        Query<T> query = createQuery();
        query.field(BaseEntity.Fields._id).equal(id);
        query.field(BaseEntity.Fields.enable).equal(true);
        query.field(BaseEntity.Fields.deleted).equal(false);
        UpdateOperations<T> operations = createUpdate();
        operations.inc(field);
        datastore.findAndModify(query, operations);
    }


    @Override
    public void dec(String field, String id) {
        Query<T> query = createQuery();
        query.field(BaseEntity.Fields._id).equal(id);
        query.field(BaseEntity.Fields.enable).equal(true);
        query.field(BaseEntity.Fields.deleted).equal(false);
        UpdateOperations<T> operations = createUpdate();
        operations.dec(field);
        datastore.findAndModify(query, operations);
    }

    /**
     * 查询总数
     *
     * @return
     */
    @Override
    public long count() {
        Query<T> query = createQuery();
        query.field(BaseEntity.Fields.enable).equal(true);
        query.field(BaseEntity.Fields.deleted).equal(false);
        return query.countAll();
    }


    /**
     * 通过条件 查询总数
     *
     * @param field
     * @param data
     * @return
     */
    @Override
    public long count(String field, Object data) {
        Query<T> query = createQuery();
        query.field(field).equal(data);
        query.field(BaseEntity.Fields.enable).equal(true);
        query.field(BaseEntity.Fields.deleted).equal(false);
        return query.countAll();
    }


    /**
     * 查询小于某时间的数据列表
     *
     * @param field
     * @param beforTime
     * @return
     */
    @Override
    public List<T> lessThan(String field, long beforTime) {
        Query<T> query = createQuery();
        query.field(field).lessThan(beforTime);
        query.field(BaseEntity.Fields.enable).equal(true);
        query.field(BaseEntity.Fields.deleted).equal(false);
        return query.asList();
    }

    @Override
    public DBCursor cursor() {
        DBCollection collection = datastore.getCollection(clazz);
        DBObject filter = new BasicDBObject();
        return collection.find(filter).batchSize(10);
    }

}