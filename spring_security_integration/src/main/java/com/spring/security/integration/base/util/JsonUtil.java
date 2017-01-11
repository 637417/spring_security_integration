package com.spring.security.integration.base.util;

import java.util.HashMap;
import java.util.Map;
import com.alibaba.fastjson.JSON;

public class JsonUtil {
    
    /**
     * 成功编码
     */
    private static final int success = 1000;
    
    /**
     * 失败编码
     */
    private static final int fail = 1001;
    
    /**
     * 成功消息
     * 
     * @Title: getSuccessJsonMsg
     * @return Object 返回类型
     */
    public static Object getSuccessJsonMsg(String msg, Object obj) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", success);
        map.put("msg", msg);
        map.put("data", obj);
        return JSON.toJSONString(map);
    }
    
    /**
     * 失败消息
     * 
     * @Title: getSuccessJsonMsg
     * @return Object 返回类型
     */
    public static Object getFailJsonMsg(String msg) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", fail);
        map.put("msg", msg);
        return JSON.toJSONString(map);
    }
}
