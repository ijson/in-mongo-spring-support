package com.ijson.mongo.util;

import com.google.common.collect.Maps;
import com.ijson.mongo.generator.util.ParseUtils;
import org.junit.Test;

import java.util.Map;

/**
 * desc:
 * version: 6.6
 * Created by cuiyongxu on 2019/6/14 10:35 AM
 */
public class ParseUtilsTest {

    @Test
    public void genTemplete() {
        String templete = "class {{name}}{" +
                "" +
                "}";

        Map<String, Object> data = Maps.newHashMap();
        data.put("name", "User");
        System.out.println(ParseUtils.render(templete, data));
    }
}
