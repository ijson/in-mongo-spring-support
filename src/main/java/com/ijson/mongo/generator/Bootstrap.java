package com.ijson.mongo.generator;

import com.ijson.mongo.generator.manager.CodeGeneratorManager;
import com.ijson.mongo.generator.manager.LoadManagerFactory;
import com.ijson.mongo.generator.model.GenConfig;
import com.ijson.mongo.generator.model.ParamsVo;
import com.ijson.mongo.support.model.BaseEntity;

import java.util.List;

/**
 * desc:
 * version: 6.7
 * Created by cuiyongxu on 2019/6/28 5:34 PM
 */
public class Bootstrap {

    public static void generator(
            String packager,
            String genFilePath,
            String projectName,
            List<Class<? extends BaseEntity>> entities) {

        CodeGeneratorManager codeGeneratorManager = LoadManagerFactory.getInstance().getCodeGeneratorManager();
        ParamsVo vo = new ParamsVo();
        vo.setEntities(entities);

        codeGeneratorManager.execute(vo, GenConfig.builder()
                .packager(packager)
                .genPath(genFilePath)
                .projectName(projectName)
                .build());
    }
}
