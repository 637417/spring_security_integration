package com.spring.security.integration.base.util;

import java.lang.reflect.Method;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.PropertyResourceConfigurer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.support.PropertiesLoaderSupport;


@SuppressWarnings("all")
public class SpringPropertyUtil implements ApplicationContextAware {
    
    private static Log log = LogFactory.getLog(SpringPropertyUtil.class);
    
    private static ApplicationContext applicationContext = null;
    
    private static Properties properties = new Properties();
    
    private static AbstractApplicationContext abstractContext = null;
    
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (this.applicationContext == null) {
            synchronized (SpringBeanUtil.class) {
                if (this.applicationContext == null) {
                    this.applicationContext = applicationContext;
                }
            }
        }
        abstractContext = (AbstractApplicationContext)applicationContext;
        init();
    }
    
    private static void init() {
        try {
            String[] postProcessorNames = abstractContext.getBeanNamesForType(BeanFactoryPostProcessor.class, true, true);
            for (String ppName : postProcessorNames) {
                BeanFactoryPostProcessor beanProcessor = abstractContext.getBean(ppName, BeanFactoryPostProcessor.class);
                if (beanProcessor instanceof PropertyResourceConfigurer) {
                    PropertyResourceConfigurer propertyResourceConfigurer = (PropertyResourceConfigurer)beanProcessor;
                    Method mergeProperties = PropertiesLoaderSupport.class.getDeclaredMethod("mergeProperties");
                    mergeProperties.setAccessible(true);
                    Properties props = (Properties)mergeProperties.invoke(propertyResourceConfigurer);
                    
                    Method convertProperties = PropertyResourceConfigurer.class.getDeclaredMethod("convertProperties", Properties.class);
                    convertProperties.setAccessible(true);
                    convertProperties.invoke(propertyResourceConfigurer, props);
                    
                    properties.putAll(props);
                }
            }
            
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
        }
    }
    
    public static String getProperty(String propertyName) {
        return properties.getProperty(propertyName);
    }
    
}
