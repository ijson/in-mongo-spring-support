package com.ijson.mongo.generator.manager;



import com.ijson.mongo.generator.model.GenConfig;
import com.ijson.mongo.generator.model.ParamsVo;

import java.util.Map;

/**
 * description: 代码
 *
 * @author cuiyongxu 创建时间：Nov 17, 2015
 */
public interface CodeGeneratorManager {

    /**
     * 代码统一生成器入口方法
     *
     * @param config config
     * @param vo 方法参数
     */
    void execute(ParamsVo vo, GenConfig config);
}
