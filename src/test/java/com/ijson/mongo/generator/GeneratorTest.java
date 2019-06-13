package com.ijson.mongo.generator;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ijson.mongo.generator.entity.User;
import com.ijson.mongo.generator.entity.WorkflowOutlineEntity;
import com.ijson.mongo.generator.manager.CodeGeneratorManager;
import com.ijson.mongo.generator.manager.LoadManagerFactory;
import com.ijson.mongo.generator.model.GenConfig;
import com.ijson.mongo.generator.model.ParamsVo;
import org.junit.Test;

import java.util.Map;

/**
 * desc:
 * version: 6.6
 * Created by cuiyongxu on 2019/6/13 4:35 PM
 */
public class GeneratorTest {

    @Test
    public void gen(){
        CodeGeneratorManager codeGeneratorManager = LoadManagerFactory.getInstance().getCodeGeneratorManager();

        ParamsVo vo = new ParamsVo();
        vo.setEntities(Lists.newArrayList(User.class, WorkflowOutlineEntity.class));

        codeGeneratorManager.execute(vo, GenConfig.builder().packager("com.ijson.mongo.dao").build());

    }
}
