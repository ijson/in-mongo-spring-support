package com.ijson.mongo.util;

import com.google.common.collect.Maps;
import com.ijson.mongo.generator.util.ParseUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.IOException;
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

    @Test
    public void readTempletFile() {
        try {
            String dao = IOUtils.toString(getClass().getClassLoader().getResourceAsStream("template/dao.templete"));

            Map<String, Object> data = Maps.newHashMap();

            data.put("packager","com.ijson" );
            data.put("simpleName","User" );
            System.out.println(ParseUtils.render(dao, data));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
