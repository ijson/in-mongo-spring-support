package com.ijson.mongo.generator.template;


import com.ijson.mongo.generator.model.GenConfig;
import com.ijson.mongo.generator.model.ParamsVo;

import java.util.Map;

public interface TemplateHanlder {

    /**
     * 代码生成器模板接口
     *
     * @param config config
     * @param vo 方法参数
     */
     void execute(ParamsVo vo, GenConfig config);
}
