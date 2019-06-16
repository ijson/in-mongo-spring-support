package com.ijson.mongo.generator.dao;

import com.ijson.mongo.generator.entity.WorkflowOutline;
import com.ijson.mongo.generator.query.WorkflowOutlineQuery;
import com.ijson.mongo.support.model.Page;
import com.ijson.mongo.support.model.PageResult;

/**
 * desc:
 * version: 6.6
 * Created by cuiyongxu on 2019/6/14 3:36 PM
 */
public interface IDao {
    WorkflowOutline createOrUpdate(WorkflowOutline entity);

    WorkflowOutline find(String id);

    WorkflowOutline enable(String id, boolean enable, String userId);

    PageResult<WorkflowOutline> find(WorkflowOutlineQuery iquery, Page page);

    WorkflowOutline delete(String id, String userId);

    void delete(String id);
}
