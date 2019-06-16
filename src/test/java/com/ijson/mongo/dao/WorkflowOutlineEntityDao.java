package com.ijson.mongo.dao;

import com.ijson.mongo.dao.query.WorkflowOutlineEntityQuery;
import com.ijson.mongo.support.model.Page;
import com.ijson.mongo.support.model.PageResult;

public interface WorkflowOutlineEntityDao {

    WorkflowOutlineEntityEntity createOrUpdate(WorkflowOutlineEntityEntity entity);

    WorkflowOutlineEntityEntity find(String id);

    WorkflowOutlineEntityEntity enable(String id, boolean enable, String userId);

    PageResult<WorkflowOutlineEntityEntity> find(WorkflowOutlineEntityQuery query, Page page);

    WorkflowOutlineEntityEntity delete(String id, String userId);

    void delete(String id);
}

