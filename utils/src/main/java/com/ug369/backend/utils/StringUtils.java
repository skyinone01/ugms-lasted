package com.ug369.backend.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Roy on 2017/3/8.
 */
public class StringUtils {
    private static List<String> IMGS = new ArrayList<>();;

    static {
        IMGS.add("bmp");
        IMGS.add("png");
        IMGS.add("jpeg");
        IMGS.add("gif");
        IMGS.add("jpg");
        IMGS.add("tiff");
    }
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

    /**
     * 判断文件是否为图片
     *
     * @param fileName 文件名
     * @return 检查后的结果
     * @throws Exception
     */
    public static boolean isPicture(String  fileName){
        if(org.apache.commons.lang3.StringUtils.isEmpty(fileName)){
            return false;
        }
        String tmpName = fileName.substring(fileName.lastIndexOf(".") + 1,
                fileName.length());
        if (!org.apache.commons.lang3.StringUtils.isEmpty(tmpName) && IMGS.contains(tmpName)){
            return true;
        }
        return false;
    }

    public final static String MD5ToString(byte[] md5) {
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        try {
            int j = md5.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md5[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
