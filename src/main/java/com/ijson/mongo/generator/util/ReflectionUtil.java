package com.ijson.mongo.generator.util;

import com.google.common.collect.Lists;
import com.ijson.mongo.generator.model.DocDescribe;
import com.ijson.mongo.generator.model.ObjectFiled;
import com.ijson.mongo.generator.model.ObjectInfo;
import com.ijson.mongo.support.entity.BaseEntity;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * desc:
 * version: 6.6
 * Created by cuiyongxu on 2019/6/13 5:00 PM
 */
public class ReflectionUtil {

    public static ObjectInfo getEntityType(Class<? extends BaseEntity> clazz) {
        ObjectInfo objectInfo = ObjectInfo.builder()
                .name(clazz.getName())
                .lowerFirstName(TemplateUtil.toLowerCaseFirstOne(clazz.getSimpleName()))
                .simpleName(clazz.getSimpleName())
                .build();
        try {
            Field[] declaredFields = clazz.getDeclaredFields();

            List<ObjectFiled> fields = Lists.newArrayList();

            for (Field field : declaredFields) {

                ObjectFiled build = ObjectFiled.builder()
                        .name(field.getName())
                        .type(field.getType().getName())
                        .simpleType(field.getType().getSimpleName())
                        .typeClazz(getTypeClazz(field.getType().getName()))
                        .simpleName(getSimpleName(field.getName()))
                        .toSimpleName(TemplateUtil.toUpperCaseFirstOne(getSimpleName(field.getName())))
                        .toUpperName(TemplateUtil.toUpperCase(field.getName()))
                        .build();
                DocDescribe annotation = field.getAnnotation(DocDescribe.class);
                if (annotation != null) {
                    build.setDescribe(annotation.value());
                }

                fields.add(build);

            }
            objectInfo.setFileds(fields);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return objectInfo;
    }

    private static String getSimpleName(String name) {
        return name.substring(name.lastIndexOf(".") + 1); // strip the package name
    }


    public static Class getTypeClazz(String name) throws ClassNotFoundException {

        String type;
        switch (name) {
            case "boolean":
                type = Boolean.class.getName();
                break;
            case "char":
                type = Character.class.getName();
                break;
            case "int":
                type = Integer.class.getName();
                break;
            case "long":
                type = Long.class.getName();
                break;
            case "double":
                type = Double.class.getName();
                break;
            case "byte":
                type = Byte.class.getName();
                break;
            case "short":
                type = Short.class.getName();
                break;
            case "float":
                type = Float.class.getName();
                break;
            case "String":
                type = String.class.getName();
                break;
            case "Map":
                type = Map.class.getName();
                break;
            case "List":
                type = List.class.getName();
                break;
            case "Set":
                type = Set.class.getName();
                break;
            default:
                type = name;
                break;
        }

        return Class.forName(type);
    }
}
