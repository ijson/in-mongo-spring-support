package com.ijson.mongo.generator.template;

import com.ijson.mongo.generator.model.GenConfig;
import com.ijson.mongo.generator.model.ParamsVo;
import com.ijson.mongo.support.BaseEntity;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

@Slf4j
public class TemplateEntityImplBuilder implements TemplateHanlder {

    public void execute(ParamsVo vo, GenConfig config) {
        List<Class<? extends BaseEntity>> tables = vo.getEntities();

    }



}
