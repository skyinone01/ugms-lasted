package com.ug369.backend.utils;

/**
 * Created by Roy on 2017/3/8.
 */
public class StringUtils {
    public static String[] splitBy(String a, String b) {
        int pos = a.indexOf(b);
        String [] r = new String[2];
        r[0] = a.substring(0, pos);
        r[1] = a.substring(pos + 1);
        return r;
    }
	public static String[] split(String str, String separator) {
		return org.apache.commons.lang3.StringUtils.split(str, separator);
	}
}
