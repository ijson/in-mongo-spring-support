package com.ijson.mongo.generator;

import com.google.common.collect.Lists;
import com.ijson.mongo.generator.manager.CodeGeneratorManager;
import com.ijson.mongo.generator.manager.LoadManagerFactory;
import com.ijson.mongo.generator.model.GenConfig;
import com.ijson.mongo.generator.model.ParamsVo;
import com.ijson.mongo.support.test.bean.User;
import org.junit.Test;

/**
 * desc:
 * version: 6.6
 * Created by cuiyongxu on 2019/6/13 4:35 PM
 */
public class GeneratorTest {

    @Test
    public void gen() {
        CodeGeneratorManager codeGeneratorManager = LoadManagerFactory.getInstance().getCodeGeneratorManager();

        ParamsVo vo = new ParamsVo();
        vo.setEntities(Lists.newArrayList(User.class));

        codeGeneratorManager.execute(vo, GenConfig.builder()
                .packager("com.ijson.mongo")
                .genPath("/Users/cuiyongxu/Desktop")
                .projectName("in-demo")
                .build());

    }
}
