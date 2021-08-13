package com.ijson.mongo.generator.util;

import com.google.common.base.Strings;
import org.apache.commons.io.IOUtils;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.StringTemplateResourceLoader;

import java.io.IOException;
import java.util.Locale;
import java.util.Map;

/**
 * Created by cuiyongxu on 17/9/25.
 */
public class TemplateUtil {


    public static String getTemplate(String tmpName, Map params) {
        StringTemplateResourceLoader resourceLoader = new StringTemplateResourceLoader();
        Configuration cfg;
        String value = null;
        try {
            cfg = Configuration.defaultConfiguration();
            GroupTemplate gt = new GroupTemplate(resourceLoader, cfg);
            String io = IOUtils.toString(TemplateUtil.class.getClassLoader().getResourceAsStream("template/" + tmpName));
            Template tmp = gt.getTemplate(io);
            tmp.binding(params);
            value = tmp.render();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return value;
    }


    /**
     * 首字母转小写
     *
     * @param s string
     * @return 首字母转小写
     */
    public static String toLowerCaseFirstOne(String s) {
        if (Character.isLowerCase(s.charAt(0))) {
            return s;
        } else {
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }

    /**
     * 首字母转大写
     *
     * @param s string
     * @return 首字母转大写
     */
    public static String toUpperCaseFirstOne(String s) {
        if (Character.isUpperCase(s.charAt(0))) {
            return s;
        } else {
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }


    /**
     * 转大写,如果存在大写字母,则前加_
     *
     * @param str
     * @return
     */
    public static String toUpperCase(String str) {
        if (Strings.isNullOrEmpty(str)) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        for (char c : str.toCharArray()) {
            if (Character.isUpperCase(c) && sb.length() != 0) {
                sb.append("_").append(c);
            } else {
                sb.append(c);
            }
        }
        return sb.toString().toUpperCase(Locale.ROOT);
    }


}
