package com.spring.security.integration.base.util;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.log4j.Logger;

import com.spring.security.integration.sys.model.MyMail;


public class SendMailUtil {

	public static Logger log = Logger.getLogger(SendMailUtil.class);
	
	public static boolean send(MyMail mail){
		HtmlEmail email = new HtmlEmail();
		
        try {
        	// 这里是SMTP发送服务器的名字：163的如下："smtp.163.com"
        	email.setHostName(mail.getHost());
        	// 字符编码集的设置
        	email.setCharset(MyMail.ENCODEING);
        	// 收件人的邮箱
			email.addTo(mail.getReceiver());
	        // 发送人的邮箱
	        email.setFrom(mail.getSender(), mail.getName());
	        // 如果需要认证信息的话，设置认证：用户名-密码。分别为发件人在邮件服务器上的注册名称和密码
	        email.setAuthentication(mail.getUsername(), mail.getPassword());
	        // 要发送的邮件主题
	        email.setSubject(mail.getSubject());
	        // 要发送的信息，由于使用了HtmlEmail，可以在邮件内容中使用HTML标签
	        email.setMsg(mail.getMessage());
	        // 发送
	        email.send();
	        log.info(mail.getSender() + " 发送邮件到 " + mail.getReceiver());
	        return true;
        } catch (EmailException e) {
			log.error("邮件发送异常" + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
	public static boolean sendList(MyMail mail){
		HtmlEmail email = new HtmlEmail();
        try {
        	// 这里是SMTP发送服务器的名字：163的如下："smtp.163.com"
        	email.setHostName(mail.getHost());
        	// 字符编码集的设置
        	email.setCharset(MyMail.ENCODEING);
        	// 收件人的邮箱列表
			email.setTo(mail.getCollectionList());
	        // 发送人的邮箱
	        email.setFrom(mail.getSender(), mail.getName());
	        // 如果需要认证信息的话，设置认证：用户名-密码。分别为发件人在邮件服务器上的注册名称和密码
	        email.setAuthentication(mail.getUsername(), mail.getPassword());
	        // 要发送的邮件主题
	        email.setSubject(mail.getSubject());
	        // 要发送的信息，由于使用了HtmlEmail，可以在邮件内容中使用HTML标签
	        email.setMsg(mail.getMessage());
	        // 发送
	        email.send();
	        log.info(mail.getSender() + " 发送邮件到 " + mail.getCollectionList().toString());
	        return true;
        } catch (EmailException e) {
			log.error("邮件发送异常" + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
}
