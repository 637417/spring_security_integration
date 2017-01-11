package com.spring.security.integration.sys.model;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SysLog implements Serializable {
    
    private static final long serialVersionUID = 8208312900873983608L;
    
    /**
     * 记录流水号
     */
    private Long logId;
    
    /**
     * 用户编号
     */
    private Integer userId;
    
    /**
     * 用户名
     */
    private String userName;
    
    /**
     * 所执行操作
     */
    private String operation;
    
    /**
     * 创建时间
     */
    private String createTime;
    
    /**
     * 备注
     */
    private String remarks;
    
    /**
     * 请求访问地址IP
     */
    private String visitIp;
    
    /**
     * 请求参数
     */
    private String requestParm;
    
    /**
     * 响应结果
     */
    private String result;
    
}