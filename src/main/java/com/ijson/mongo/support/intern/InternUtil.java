package com.ijson.mongo.support.intern;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableSet;

import java.lang.reflect.Array;
import java.util.*;

/**
 * 内部工具类
 */
public final class InternUtil {
    private static final TimeZone CHINA_ZONE = TimeZone.getTimeZone("GMT+08:00");
    private static final Locale CHINA_LOCALE = Locale.CHINA;
    private static final Set<String> NAMES =
            ImmutableSet.of("Boolean", "Character", "Byte", "Short", "Long", "Integer", "Byte", "Float", "Double", "Void", "String");

    private InternUtil() {
    }

    public static String getFirstNotNullMessage(final Throwable e) {
        Throwable t = e;
        while (t != null) {
            String msg = t.getMessage();
            if (!Strings.isNullOrEmpty(msg)) {
                return msg;
            }
            t = t.getCause();
        }
        return null;
    }

    /**
     * 函数参数信息
     *
     * @param args 参数列表
     * @return 格式化输出
     */
    public static String getParameters(Object[] args) {
        if (args == null || args.length == 0) {
            return "";
        }
        StringBuilder sbd = new StringBuilder();
        for (Object i : args) {
            if (i == null) {
                sbd.append("null");
            } else {
                Class clz = i.getClass();
                if (isPrimitive(clz)) {
                    sbd.append(evalPrimitive(i));
                } else if (clz.isArray()) {
                    evalArray(i, sbd);
                } else if (Collection.class.isAssignableFrom(clz)) {
                    Object[] arr = ((Collection<?>) i).toArray();
                    evalArray(arr, sbd);
                } else if (i instanceof Date) {
                    sbd.append('"').append(formatYmdHis((Date) i)).append('"');
                } else if (i instanceof Map) {
                    sbd.append("map{sz=").append(((Map) i).size()).append('}');
                } else {
                    sbd.append(clz.getSimpleName()).append(":OBJ");
                }
            }
            sbd.append(',');
        }
        sbd.setLength(sbd.length() - 1);
        return sbd.toString();
    }

    private static boolean isPrimitive(Class clz) {
        return clz.isPrimitive() || NAMES.contains(clz.getSimpleName());
    }

    private static String evalPrimitive(Object obj) {
        String s = String.valueOf(obj);
        if (s.length() > 32) {
            return s.substring(0, 32) + "...";
        }
        return s;
    }

    private static void evalArray(Object arr, StringBuilder sbd) {
        int sz = Array.getLength(arr);
        if (sz == 0) {
            sbd.append("[]");
            return;
        }
        Class<?> clz = Array.get(arr, 0).getClass();
        if (clz == Byte.class) {
            sbd.append("Byte[").append(sz).append(']');
            return;
        }
        if (isPrimitive(clz)) {
            sbd.append('[');
            int len = Math.min(sz, 10);
            for (int i = 0; i < len; i++) {
                Object obj = Array.get(arr, i);
                if (obj == null) {
                    sbd.append("null");
                } else if (isPrimitive(obj.getClass())) {
                    sbd.append(evalPrimitive(obj));
                } else {
                    sbd.append(obj.getClass().getSimpleName()).append(":OBJ");
                }
                sbd.append(',');
            }
            if (sz > 10) {
                sbd.append(",...,len=").append(sz);
            }
            if (sbd.charAt(sbd.length() - 1) == ',') {
                sbd.setCharAt(sbd.length() - 1, ']');
            } else {
                sbd.append(']');
            }
        } else {
            sbd.append("[len=").append(sz).append(']');
        }
    }

    /**
     * 构造时间的显示，带上时分秒的信息，如 2013-06-11 03:14:25
     *
     * @param date 时间
     * @return 字符串表示
     */
    private static String formatYmdHis(Date date) {
        Calendar ca = Calendar.getInstance(CHINA_ZONE, CHINA_LOCALE);
        ca.setTimeInMillis(date.getTime());
        StringBuilder sbd = new StringBuilder();
        sbd.append(ca.get(Calendar.YEAR)).append('-');
        int month = 1 + ca.get(Calendar.MONTH);
        if (month < 10) {
            sbd.append('0');
        }
        sbd.append(month).append('-');
        int day = ca.get(Calendar.DAY_OF_MONTH);
        if (day < 10) {
            sbd.append('0');
        }
        sbd.append(day).append(' ');
        int hour = ca.get(Calendar.HOUR_OF_DAY);
        if (hour < 10) {
            sbd.append('0');
        }
        sbd.append(hour).append(':');
        int minute = ca.get(Calendar.MINUTE);
        if (minute < 10) {
            sbd.append('0');
        }
        sbd.append(minute).append(':');
        int second = ca.get(Calendar.SECOND);
        if (second < 10) {
            sbd.append('0');
        }
        sbd.append(second);
        return sbd.toString();
    }
}
