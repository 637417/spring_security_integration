package com.spring.security.integration.base.util;

import java.util.List;
import java.util.Map;


@SuppressWarnings("all")
public class StringUtil {
    
    public static String toSelectStr(List resultList, String key, String value) {
        StringBuilder selectStr = new StringBuilder();
        for (Object object : resultList) {
            Map _obj = (Map)object;
            selectStr.append("<option value='" + _obj.get(key) + "'>" + _obj.get(value) + "</option>");
        }
        return selectStr.toString();
    }
    
    public static String toString(Object object) {
        if (object != null) {
            return object.toString().trim();
        }
        return null;
    }
    
    /**
     * 首字母变小写
     */
    public static String firstCharToLowerCase(String str) {
        Character firstChar = str.charAt(0);
        String tail = str.substring(1);
        str = Character.toLowerCase(firstChar) + tail;
        return str;
    }
    
    /**
     * 首字母变大写
     */
    public static String firstCharToUpperCase(String str) {
        Character firstChar = str.charAt(0);
        String tail = str.substring(1);
        str = Character.toUpperCase(firstChar) + tail;
        return str;
    }
    
    public static boolean notBlank(String... strings) {
        if (strings == null)
            return false;
        for (String str : strings)
            if (str == null || "".equals(str.trim()))
                return false;
        return true;
    }
    
    public static boolean notNull(Object... paras) {
        if (paras == null)
            return false;
        for (Object obj : paras)
            if (obj == null)
                return false;
        return true;
    }
    
    public static String nvl(String string) {
        return nvl(string, "");
    }
    
    public static String nvl(Object o) {
        return nvl(StringUtil.toString(o), "");
    }
    
    public static String nvl(String string, String string2) {
        if (StringUtil.isBlank(string)) {
            return string2;
        } else {
            return string;
        }
    }
    
    public static String defaultString(String str) {
        return str == null ? "" : str;
    }
    
    public static Object minusOne(String str) {
        if (str != null && !"".equals(str)) {
            str = str.substring(0, str.length() - 1);
        }
        return str;
    }
    
    /**
     * 
     * @title：notBlank
     * @author：蔡波
     * @email：caibo@zhichenhaixin.com
     * @description：若obj为null，直接返回false。若不为null，一种是string类型的，不为""，则返回true，反之返回false；一种是Integer或Long，不为0，则返回true，反之返回false；一种是Double，不为0.0，则返回true，反之返回false；一种是Float，不为0.0F，则返回true，反之返回false
     * @param obj
     * @return
     */
    public static boolean notBlank(Object obj) {
        if (obj != null) {
            if (obj instanceof String) {
                String temp = (String)obj;
                return "".equals(temp.trim()) ? false : true;
            } else if (obj instanceof Integer) {
                Integer temp = (Integer)obj;
                return temp.equals(0) ? false : true;
            } else if (obj instanceof Long) {
                Long temp = (Long)obj;
                return temp.equals(0) ? false : true;
            } else if (obj instanceof Double) {
                Double temp = (Double)obj;
                return temp.equals(0.0) ? false : true;
                
            } else if (obj instanceof Float) {
                Float temp = (Float)obj;
                return temp.equals(0.0F) ? false : true;
            }
        }
        return false;
    }
    
    /**
     * 
     * @title：isBlank
     * @author：蔡波
     * @email：caibo@zhichenhaixin.com
     * @description：若obj为null，直接返回true。若不为null，一种是string类型的，不为“”，则返回false，反之返回true；一种是Integer或Long，不为0，则返回false，反之返回true；一种是Double，不为0.0，则返回false，反之返回true；一种是Float，不为0.0F，则返回false，反之返回true
     * @param str
     * @return
     */
    public static boolean isBlank(Object obj) {
        return !notBlank(obj);
    }
    
}
