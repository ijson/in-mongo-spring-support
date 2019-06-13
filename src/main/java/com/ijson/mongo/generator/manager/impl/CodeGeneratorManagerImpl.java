package com.ijson.mongo.generator.manager.impl;

import com.ijson.mongo.generator.manager.CodeGeneratorManager;
import com.ijson.mongo.generator.model.GenConfig;
import com.ijson.mongo.generator.model.ParamsVo;
import com.ijson.mongo.generator.template.TemplateHanlder;
import com.ijson.mongo.generator.util.ReflectionUtil;
import com.ijson.mongo.support.BaseEntity;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

@Slf4j
public class CodeGeneratorManagerImpl implements CodeGeneratorManager {

    private List<TemplateHanlder> hanlders;

    /**
     * @param vo     方法参数
     * @param config 配置文件
     */
    public void execute(ParamsVo vo, GenConfig config) {

        List<Class<? extends BaseEntity>> entities = vo.getEntities();
        if (entities != null && !entities.isEmpty()) {
            entities.forEach(entity -> {
                vo.getObjectInfos().add(ReflectionUtil.getEntityType(entity));
            });
        }

        if (hanlders != null && !hanlders.isEmpty()) {
            for (TemplateHanlder hanlder : hanlders) {
                hanlder.execute(vo, config);
            }
        } else {
            log.error("生成器模板接口没有被注入成功");
        }
    }

    public void setHanlders(List<TemplateHanlder> hanlders) {
        this.hanlders = hanlders;
    }

}
