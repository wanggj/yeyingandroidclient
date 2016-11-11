package com.baidu.yeyingandroidclient;

import java.security.MessageDigest;

/**
 * Created by baidu on 16/11/8.
 */
public class MD5Util {
    /**
     * md5加密
     */
    public final static String md5Hex(String plaintext) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            byte[] inputBytes = plaintext.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            messageDigest.update(inputBytes);
            // 获得密文
            byte[] md = messageDigest.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }

}
