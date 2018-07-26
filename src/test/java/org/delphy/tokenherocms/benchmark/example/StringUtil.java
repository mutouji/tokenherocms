package org.delphy.tokenherocms.benchmark.example;

import java.util.Arrays;

public class StringUtil {
    public static final char UNDERLINE = '_';
    public static String camelToUnderline(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append(UNDERLINE);
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static String underlineToCamel(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (UNDERLINE == c) {
                if (++i < len) {
                    sb.append(Character.toUpperCase(c));
                }
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static String firstToCapture(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        char[] cs = param.toCharArray();
        cs[0] ^= 32;
        return String.valueOf(cs);
    }

    public static String fieldToGetMethodName(String fieldName) {
        return "get" + firstToCapture(fieldName);
    }

    public static String fieldToSetMethodName(String fieldName) {
        return "set" + firstToCapture(fieldName);
    }

    public static String underlineToCapture(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        sb.append(Character.toUpperCase(param.charAt(0)));
        for (int i = 1; i < len; i++) {
            char c = param.charAt(i);
            if (UNDERLINE == c) {
                if (++i < len) {
                    sb.append(Character.toUpperCase(c));
                }
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static String array2String(String[] array) {
        return Arrays.toString(array);
    }

    public static String[] string2Array(String string) {
        String[] strings = string.replace("[", "").replace("]", "").split(",");
        String result[] = new String[strings.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = strings[i].trim();
        }
        return result;
    }
}
