package com.spring.security.integration.base.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.PropertyResourceConfigurer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.support.PropertiesLoaderSupport;
import org.springframework.web.context.ContextLoader;


@SuppressWarnings("all")
public class SpringBeanUtil implements ApplicationContextAware {

	private static ApplicationContext applicationContext;

	private SpringBeanUtil() {}

	public  void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		if (this.applicationContext == null) {
			synchronized (SpringBeanUtil.class) {
				if (this.applicationContext == null) {
					this.applicationContext = applicationContext;
				}
			}
		}
	}
	
	/**
	 * 
	 * @title: getBean
	 * @category: 根据名称获取唯一Bean
	 * @author: hejiang@zhichenhaixin.com 2016年3月25日
	 * @param beanName
	 * @return
	 */
	public static Object getBean(String beanName) {
		return applicationContext.getBean(beanName);
	}

	/**
	 * 
	 * @title: getBean
	 * @category: 根据类型获取唯一Bean，若该类型存在多个Bean，则抛出异常
	 * @author: hejiang@zhichenhaixin.com 2016年3月25日
	 * @param beanType
	 * @return
	 */
	public static <T> T getBean(Class<T> beanType) {
		return (T) applicationContext.getBean(beanType);
	}

	/**
	 * 
	 * @title: getBean
	 * @category: 根据名称获取唯一Bean，若该名称对应Bean不存在，则根据类型获取Bean，若该类型存在多个Bean，则抛出异常
	 * @author: hejiang@zhichenhaixin.com 2016年3月25日
	 * @param beanName
	 * @param beanType
	 * @return
	 */
	public static <T> T getBean(String beanName, Class<T> beanType) {
		Object obj = getBean(beanName);
		if (obj == null) {
			return (T) getBean(beanType);
		}
		return (T) obj;
	}

	/**
	 * 
	 * @title: existsBean
	 * @category: 指定名称的Bean是否存在
	 * @author: hejiang@zhichenhaixin.com 2016年3月25日
	 * @param beanName
	 * @return
	 */
	public static boolean existsBean(String beanName) {
		return applicationContext.containsBean(beanName);
	}

	/**
	 * 
	 * @title: existsBeanDefinition
	 * @category: 指定名称的Bean的定义是否存在
	 * @author: hejiang@zhichenhaixin.com 2016年3月25日
	 * @param beanName
	 * @return
	 */
	public static boolean existsBeanDefinition(String beanName) {
		return applicationContext.containsBeanDefinition(beanName);
	}

	/**
	 * Return whether the local bean factory contains a bean of the given name,
	 * ignoring beans defined in ancestor contexts.
	 * <p>
	 * This is an alternative to {@code containsBean}, ignoring a bean of the
	 * given name from an ancestor bean factory.
	 * 
	 * @param name
	 *            the name of the bean to query
	 * @return whether a bean with the given name is defined in the local
	 *         factory
	 * @see BeanFactory#containsBean
	 */
	public static boolean existsLocalBean(String beanName) {
		return applicationContext.containsLocalBean(beanName);
	}

	/**
	 * 
	 * @title: existsBean
	 * @category: 是否存在指定类型的Bean 
	 * @author: hejiang@zhichenhaixin.com 2016年3月25日
	 * @param beanType
	 * @return
	 */
	public static boolean existsBean(Class<?> beanType) {
		return applicationContext.getBeanNamesForType(beanType).length > 0;
	}

	/**
	 * 
	 * @title: getApplicationContext
	 * @category: 得到Spring ApplicationContext容器
	 * @author: hejiang@zhichenhaixin.com 2016年3月25日
	 * @return
	 */
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	
}
