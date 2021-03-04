package com.ijson.mongo.generator.util;

import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
public class ParseUtils {

    private static final String DEF_REGEX = "\\{\\{(.+?)\\}\\}";

    public static String render(String parseText, Map<String, Object> renderData) {
        return render(parseText, renderData, DEF_REGEX);
    }

    private static String render(String parseText, Map<String, Object> renderData, String regex) {
        if (Strings.isNullOrEmpty(parseText)) {
            throw new RuntimeException("模板内容为空");
        }
        if (renderData == null || renderData.size() == 0) {
            return parseText;
        }
        Map<String, Object> data = dataTrasfer(renderData);

        try {
            StringBuffer sb = new StringBuffer();
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(parseText);
            while (matcher.find()) {
                String name = matcher.group(1);// 键名
                String value = String.valueOf(data.get(name));// 键值
                matcher.appendReplacement(sb, value);
            }
            matcher.appendTail(sb);
            return sb.toString();
        } catch (Exception e) {
            log.error("解析变量异常:", e);
            throw new RuntimeException("解析变量异常");
        }

    }


    /**
     * 对数据进行转换
     *
     * @param data
     * @return
     */
    private static Map<String, Object> dataTrasfer(Map<String, Object> data) {
        return data.keySet().stream().collect(Collectors.toMap((key -> key), vkey -> {
            Object value = data.get(vkey);
            if (Objects.isNull(value)) {
                return "";
            }
            return data.get(vkey);
        }));
    }

}
