package com.ijson.mongo.generator.template;

import com.google.common.collect.Maps;
import com.ijson.mongo.generator.model.GenConfig;
import com.ijson.mongo.generator.model.ObjectInfo;
import com.ijson.mongo.generator.model.ParamsVo;
import com.ijson.mongo.generator.util.FileOperate;
import com.ijson.mongo.generator.util.TemplateUtil;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * desc:
 * version: 6.6
 * Created by cuiyongxu on 2019/6/15 7:07 PM
 */
public class TemplateSaveHtmlBuilder implements TemplateHanlder {

    @Override
    public void execute(ParamsVo vo, GenConfig config) {
        String prefix = "src/main/";
        List<ObjectInfo> objectInfos = vo.getObjectInfos();
        createdInfo(prefix, objectInfos, config);
    }

    private void createdInfo(String prefix, List<ObjectInfo> objectInfos, GenConfig config) {
        String projectName = config.getProjectName();
        String daoPath = config.getGenPath() + "/" + projectName + "/" + prefix + "java/" + config.getPackager().replace(".", "/") + "/html/";
        FileOperate.getInstance().newCreateFolder(daoPath);
        getTemplateStr(objectInfos, daoPath, config);
    }

    private void getTemplateStr(List<ObjectInfo> objectInfos, String daoPath, GenConfig config) {
        if (!CollectionUtils.isEmpty(objectInfos)) {
            for (ObjectInfo objectInfo : objectInfos) {
                Map<String, Object> maps = Maps.newHashMap();
                maps.put("name", objectInfo.getSimpleName());
                maps.put("packager", config.getPackager());
                maps.put("columns", objectInfo.getFileds());
                maps.put("lowerFirstName", objectInfo.getLowerFirstName());
                FileOperate.getInstance().newCreateFile(
                        daoPath + "save-" + objectInfo.getLowerFirstName() + ".html",
                        TemplateUtil.getTemplate("save-html.templete", maps)

                );
            }
        }
    }
}
