package com.ijson.mongo.generator;

import com.google.common.collect.Lists;
import com.ijson.mongo.support.test.PictureLevelDownload;
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
                "com.ijson.pipic",
                "/Users/cuiyongxu/Desktop",
                "in-demo",
                Lists.newArrayList( ShortUrl.class));
    }
}
