package com.ijson.mongo.dao;

import com.ijson.mongo.dao.entity.WorkflowOutlineEntity;
import com.ijson.mongo.dao.query.WorkflowOutlineQuery;
import com.ijson.mongo.support.model.Page;
import com.ijson.mongo.support.model.PageResult;

public interface WorkflowOutlineDao {

    WorkflowOutlineEntity createOrUpdate(WorkflowOutlineEntity entity);

    WorkflowOutlineEntity find(String id);

    WorkflowOutlineEntity enable(String id, boolean enable, String userId);

    PageResult<WorkflowOutlineEntity> find(WorkflowOutlineQuery query, Page page);

    WorkflowOutlineEntity delete(String id, String userId);

    void delete(String id);
}

