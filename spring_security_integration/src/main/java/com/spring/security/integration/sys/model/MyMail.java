package com.spring.security.integration.sys.model;

import java.io.Serializable;
import java.util.Collection;
import javax.mail.internet.InternetAddress;
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
public class MyMail implements Serializable {
	private static final long serialVersionUID = 1661400571900519983L;

	public static final String ENCODEING = "UTF-8";  
	  
    private String host; // 服务器地址  
  
    private String sender; // 发件人的邮箱  
  
    private String receiver; // 收件人的邮箱  
    
    private Collection<InternetAddress> collectionList;
  
    private String name; // 发件人昵称  
  
    private String username; // 账号  
  
    private String password; // 密码  
  
    private String subject; // 主题  
  
    private String message; // 信息(支持HTML)

}
