package com.sebrogala.WordPressGenerator.Helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtility {

    public static String findByRegExp(String content, String pattern) {
        String result = "";
        Pattern regex = Pattern.compile(pattern);
        Matcher m = regex.matcher(content);
        if (m.find()) {
            result = m.group(1);
        }

        return result;
    }
}
