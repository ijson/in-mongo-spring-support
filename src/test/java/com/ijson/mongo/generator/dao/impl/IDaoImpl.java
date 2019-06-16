package com.ijson.mongo.generator.dao.impl;

import com.google.common.base.Strings;
import com.ijson.mongo.generator.dao.IDao;
import com.ijson.mongo.generator.entity.WorkflowOutline;
import com.ijson.mongo.generator.query.WorkflowOutlineQuery;
import com.ijson.mongo.generator.util.ObjectId;
import com.ijson.mongo.support.AbstractDao;
import com.ijson.mongo.support.model.Page;
import com.ijson.mongo.support.model.PageResult;
import com.mongodb.WriteConcern;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

import java.util.List;
import java.util.Objects;

/**
 * desc:
 * version: 6.6
 * Created by cuiyongxu on 2019/6/14 11:41 AM
 */
public class IDaoImpl extends AbstractDao<WorkflowOutline> implements IDao {


    @Override
    public WorkflowOutline createOrUpdate(WorkflowOutline entity) {
        if (!Strings.isNullOrEmpty(entity.getId())) {
            entity = findAndModify(entity);
        } else {
            entity.setId(ObjectId.getId());
            datastore.save(entity);
            return entity;
        }
        return entity;
    }

    private WorkflowOutline findAndModify(WorkflowOutline workflowOutline) {
        Query<WorkflowOutline> query = createQuery();
        query.field(WorkflowOutline.Fields.id).equal(workflowOutline.getId());
        query.field(WorkflowOutline.Fields.tenantId).equal(workflowOutline.getTenantId());
        UpdateOperations operations = createUpdateOperations();
        operations.set(WorkflowOutline.Fields.workflowId, workflowOutline.getWorkflowId());
        operations.set(WorkflowOutline.Fields.name, workflowOutline.getName());
        operations.set(WorkflowOutline.Fields.description, workflowOutline.getDescription());
        operations.set(WorkflowOutline.Fields.rangeEmployeeIds, workflowOutline.getRangeEmployeeIds());
        operations.set(WorkflowOutline.Fields.rangeCircleIds, workflowOutline.getRangeCircleIds());
        operations.set(WorkflowOutline.Fields.rangeGroupIds, workflowOutline.getRangeGroupIds());
        return datastore.findAndModify(query, operations);
    }

    @Override
    public WorkflowOutline find(String id) {
        Query<WorkflowOutline> query = datastore.createQuery(WorkflowOutline.class);
        query.field(WorkflowOutline.Fields.id).equal(id);
        WorkflowOutline entity = query.get();
        if (!Objects.isNull(entity)) {
            return entity;
        } else {
            throw new RuntimeException("数据不存在或已删除");
        }
    }

    @Override
    public WorkflowOutline enable(String id, boolean enable, String userId) {
        Query<WorkflowOutline> query = datastore.createQuery(WorkflowOutline.class);
        query.field(WorkflowOutline.Fields.id).equal(id);
        UpdateOperations<WorkflowOutline> updateOperations = datastore.createUpdateOperations(WorkflowOutline.class);
        updateOperations.set(WorkflowOutline.Fields.enable, enable);
        updateOperations.set(WorkflowOutline.Fields.lastModifiedBy, userId);
        return datastore.findAndModify(query, updateOperations);
    }

    @Override
    public PageResult<WorkflowOutline> find(WorkflowOutlineQuery iquery, Page page) {
        Query<WorkflowOutline> query = datastore.createQuery(WorkflowOutline.class);
        if (!Strings.isNullOrEmpty(iquery.getId())) {
            query.field(WorkflowOutline.Fields.id).equal(iquery.getId());
        }

        query.field(WorkflowOutline.Fields.isDeleted).equal(false);
        if (page.getOrderBy() != null) {
            query.order("-" + page.getOrderBy());//添加排序
        }
        if (page.getPageNumber() > 0) {
            query.offset((page.getPageNumber() - 1) * page.getPageSize()).limit(page.getPageSize());
        }

        long totalNum = query.countAll();
        List<WorkflowOutline> entities = query.asList();

        PageResult<WorkflowOutline> ret = new PageResult<>();
        ret.setDataList(entities);
        ret.setTotal(totalNum);
        return ret;
    }

    @Override
    public WorkflowOutline delete(String id, String userId) {
        Query<WorkflowOutline> query = datastore.createQuery(WorkflowOutline.class);
        query.field(WorkflowOutline.Fields.userId).equal(userId);
        query.field(WorkflowOutline.Fields.id).equal(id);
        UpdateOperations<WorkflowOutline> updateOperations = datastore.createUpdateOperations(WorkflowOutline.class);
        updateOperations.set(WorkflowOutline.Fields.lastModifiedBy, userId);
        updateOperations.set(WorkflowOutline.Fields.isDeleted, true);
        return datastore.findAndModify(query, updateOperations);
    }

    @Override
    public void delete(String id) {
        datastore.delete(datastore.createQuery(WorkflowOutline.class).field(WorkflowOutline.Fields.id).equal(id), WriteConcern.UNACKNOWLEDGED);
    }
}