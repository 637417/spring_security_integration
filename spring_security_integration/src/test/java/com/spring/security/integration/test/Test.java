package com.spring.security.integration.test;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

public class Test {
    /**
     * @param args
     * @throws UnsupportedEncodingException
     */
    public static void main(String[] args) throws UnsupportedEncodingException {
        Md5PasswordEncoder md5 = new Md5PasswordEncoder();
        String md5Password = md5.encodePassword("a", null);
        System.out.println(md5Password);
        
        md5Password = md5.encodePassword("a", null);
        System.out.println(md5Password);
        boolean b = md5.isPasswordValid("9af7268244164521c43624a92ea963ac", "a", null);
        System.out.println(b);
        Pattern pattern = Pattern.compile("[a-zA-Z0-9_]{4,18}");
        Matcher matcher = pattern.matcher("uvgddfdfdfdfdfdfdfd");
        System.out.println(matcher.matches());
        
        System.out.println(Integer.parseInt("671d", 16));
        System.out.println(Integer.toHexString(26397).toLowerCase());
        
        // 获得字符的uncoide编码
        char c = '朝';
        String uncoide = Integer.toHexString(c);
        System.out.println(c + "的uncoide编码:\t" + uncoide);
        String s = "004a00490020004c00490020005300480045004e0047";
        StringBuffer sbf = new StringBuffer();
        for (int j = 3; j < s.length(); j += 4) {
            String f = s.substring(j - 3, j + 1);
            char[] ds = Character.toChars(Integer.parseInt(f, 16));
            sbf.append(ds);
        }
        System.out.println("==============" + sbf.toString());
        
        // 从uncoide编码转换成10进制
        int x = Integer.parseInt(uncoide, 16);
        System.out.println(uncoide + "转成10进制:\t" + x);
        
        // 从10进制转成uncoide编码
        int y = 39118;
        System.out.println(y + "转成uncoide编码:\t" + Integer.toHexString(y));
        
        System.out.println(decode("E69C9DE997BBE98193EFBC8CE5A495E6ADBBE58FAFE79FA3"));
        System.out.println(encode("朝闻道，夕死可矣"));
        
    }
    
    /**
     * 字符串转换成十六进制字符串
     * 
     * @param String str 待转换的ASCII字符串
     * @return String 每个Byte之间空格分隔，如: [61 6C 6B]
     */
    public static String str2HexStr(String str) {
        
        char[] chars = "0123456789ABCDEF".toCharArray();
        StringBuilder sb = new StringBuilder("");
        byte[] bs = str.getBytes();
        int bit;
        
        for (int i = 0; i < bs.length; i++) {
            bit = (bs[i] & 0x0f0) >> 4;
            sb.append(chars[bit]);
            bit = bs[i] & 0x0f;
            sb.append(chars[bit]);
        }
        return sb.toString().trim();
    }
    
    /**
     * 十六进制转换字符串
     * 
     * @param String str Byte字符串(Byte之间无分隔符 如:[616C6B])
     * @return String 对应的字符串
     * @throws UnsupportedEncodingException
     */
    public static String hexStr2Str(String hexStr) throws UnsupportedEncodingException {
        String str = "0123456789ABCDEF";
        char[] hexs = hexStr.toCharArray();
        byte[] bytes = new byte[hexStr.length() / 2];
        int n;
        
        for (int i = 0; i < bytes.length; i++) {
            n = str.indexOf(hexs[2 * i]) * 16;
            n += str.indexOf(hexs[2 * i + 1]);
            bytes[i] = (byte)(n & 0xff);
        }
        return new String(bytes);
    }
    
    /**
     * bytes转换成十六进制字符串
     * 
     * @param byte[] b byte数组
     * @return String 每个Byte值之间空格分隔
     */
    public static String byte2HexStr(byte[] b) {
        String stmp = "";
        StringBuilder sb = new StringBuilder("");
        for (int n = 0; n < b.length; n++) {
            stmp = Integer.toHexString(b[n] & 0xFF);
            sb.append((stmp.length() == 1) ? "0" + stmp : stmp);
            sb.append(" ");
        }
        return sb.toString().toUpperCase().trim();
    }
    
    /**
     * bytes字符串转换为Byte值
     * 
     * @param String src Byte字符串，每个Byte之间没有分隔符
     * @return byte[]
     */
    public static byte[] hexStr2Bytes(String src) {
        int m = 0, n = 0;
        int l = src.length() / 2;
        System.out.println(l);
        byte[] ret = new byte[l];
        for (int i = 0; i < l; i++) {
            m = i * 2 + 1;
            n = m + 1;
            ret[i] = Byte.decode("0x" + src.substring(i * 2, m) + src.substring(m, n));
        }
        return ret;
    }
    
    /**
     * String的字符串转换成unicode的String
     * 
     * @param String strText 全角字符串
     * @return String 每个unicode之间无分隔符
     * @throws Exception
     */
    public static String strToUnicode(String strText) throws Exception {
        char c;
        StringBuilder str = new StringBuilder();
        int intAsc;
        String strHex;
        for (int i = 0; i < strText.length(); i++) {
            c = strText.charAt(i);
            intAsc = (int)c;
            strHex = Integer.toHexString(intAsc);
            if (intAsc > 128)
                str.append("\\u" + strHex);
            else
                // 低位在前面补00
                str.append("\\u00" + strHex);
        }
        return str.toString();
    }
    
    /**
     * unicode的String转换成String的字符串
     * 
     * @param String hex 16进制值字符串 （一个unicode为2byte）
     * @return String 全角字符串
     */
    public static String unicodeToString(String hex) {
        int t = hex.length() / 6;
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < t; i++) {
            String s = hex.substring(i * 6, (i + 1) * 6);
            // 高位需要补上00再转
            String s1 = s.substring(2, 4) + "00";
            // 低位直接转
            String s2 = s.substring(4);
            // 将16进制的string转为int
            int n = Integer.valueOf(s1, 16) + Integer.valueOf(s2, 16);
            // 将int转换为字符
            char[] chars = Character.toChars(n);
            str.append(new String(chars));
        }
        return str.toString();
    }
    
    /**
     * 16进制数字字符集
     */
    private static String hexString = "0123456789ABCDEF";
    
    /**
     * 转化字符串为十六进制编码
     */
    public static String toHexString(String s) {
        String str = "";
        for (int i = 0; i < s.length(); i++) {
            int ch = (int)s.charAt(i);
            String s4 = Integer.toHexString(ch);
            str = str + s4;
        }
        return str;
    }
    
    /**
     * 转化十六进制编码为字符串
     */
    public static String toStringHex(String s) {
        byte[] baKeyword = new byte[s.length() / 2];
        for (int i = 0; i < baKeyword.length; i++) {
            try {
                baKeyword[i] = (byte)(0xff & Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            s = new String(baKeyword, "utf-8");// UTF-16le:Not
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return s;
    }
    
    /**
     * 将字符串编码成16进制数字,适用于所有字符（包括中文）
     */
    public static String encode(String str) {
        // 根据默认编码获取字节数组
        byte[] bytes = str.getBytes();
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        // 将字节数组中每个字节拆解成2位16进制整数
        for (int i = 0; i < bytes.length; i++) {
            sb.append(hexString.charAt((bytes[i] & 0xf0) >> 4));
            sb.append(hexString.charAt((bytes[i] & 0x0f) >> 0));
        }
        return sb.toString();
    }
    
    /**
     * 将16进制数字解码成字符串,适用于所有字符（包括中文）
     * 
     * @throws UnsupportedEncodingException
     */
    public static String decode(String bytes) throws UnsupportedEncodingException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream(bytes.length() / 2);
        // 将每2位16进制整数组装成一个字节
        for (int i = 0; i < bytes.length(); i += 2)
            baos.write((hexString.indexOf(bytes.charAt(i)) << 4 | hexString.indexOf(bytes.charAt(i + 1))));
        return new String(baos.toByteArray(), "UTF-8");
    }
    
}