package com.ijson.mongo.generator;

import com.google.common.collect.Lists;
import com.ijson.mongo.generator.util.TemplateUtil;
import com.ijson.mongo.support.test.bean.*;
import org.junit.Test;

/**
 * desc:
 * version: 6.6
 * Created by cuiyongxu on 2019/6/13 4:35 PM
 */
public class GeneratorTest {

    @Test
    public void gen() {
        Bootstrap.generator(
                "com.ijson.blog",
                "/Users/cuiyongxu/Desktop",
                "in-demo",
                Lists.newArrayList(DataItem.class,DataCate.class));
    }

    @Test
    public void print(){
        System.out.println(TemplateUtil.toUpperCase("MyData"));
    }
}
