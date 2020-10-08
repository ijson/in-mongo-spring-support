package com.ijson.mongo.generator.manager;

import com.google.common.collect.Lists;
import com.ijson.mongo.generator.manager.impl.CodeGeneratorManagerImpl;
import com.ijson.mongo.generator.template.*;

import java.util.List;


public class LoadManagerFactory {

    private static LoadManagerFactory instance;

    private CodeGeneratorManager codeGeneratorManager;

    private LoadManagerFactory() {

    }

    public static LoadManagerFactory getInstance() {
        if (null == instance) {
            instance = new LoadManagerFactory();
        }
        return instance;
    }

    /**
     * description:  根据模板生成
     *
     * @return value
     * @author cuiyongxu
     */
    public CodeGeneratorManager getCodeGeneratorManager() {
        if (null == codeGeneratorManager) {
            CodeGeneratorManagerImpl codegener = new CodeGeneratorManagerImpl();
            List<TemplateHanlder> hanlders = Lists.newArrayList();
            hanlders.add(new TemplateDaoImplBuilder());//daoimpl
            hanlders.add(new TemplateDaoBuilder());//dao
            hanlders.add(new TemplateEntityBuilder());//entity
            hanlders.add(new TemplateQueryBuilder());//query
            hanlders.add(new TemplateInfoBuilder());//info
            codegener.setHanlders(hanlders);
            codeGeneratorManager = codegener;
        }
        return codeGeneratorManager;
    }

}
