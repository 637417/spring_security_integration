package com.spring.security.integration.base.util;

import java.security.MessageDigest;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import sun.misc.BASE64Encoder;

/**
 * 
 * @vesion: V1.0
 * @description:(MD5加密)
 *
 */
@SuppressWarnings("all")
public class MD5Utils {
    
    /**
     * @Title: MD5
     * @Description: TODO(MD5加密)
     * @param s
     * @return String 返回类型
     */
    public final static String MD5(String s) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g'};
        try {
            byte[] strTemp = s.getBytes();
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(strTemp);
            byte[] md = mdTemp.digest();
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
    
    public static String EncoderByMd5(String str) {
        String newstr = "";
        try {
            // 确定计算方法
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            BASE64Encoder base64en = new BASE64Encoder();
            // 加密后的字符串
            newstr = base64en.encode(md5.digest(str.getBytes("utf-8")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newstr;
    }
    
    /**
     * 
     * @title：encodePasswordByMD5
     * @author：蔡波
     * @email：caibo@zhichenhaixin.com
     * @description：密码加密（使用Md5PasswordEncoder）
     * @param str
     * @return
     */
    public static String encodePasswordByMD5(String str) {
        Md5PasswordEncoder md5 = new Md5PasswordEncoder();
        String md5Password = md5.encodePassword(str, null);
        return md5Password;
    }
    
}
