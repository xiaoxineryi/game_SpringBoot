package com.kaito.game.Config;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

import javax.websocket.server.ServerEndpointConfig.Configurator;


/**
 * 让websocket中也可以注入实例
 */

@Configuration
public class MyEndpointConfig extends Configurator implements
        ApplicationContextAware {

    private static volatile BeanFactory context;

    @Override
    public <T> T getEndpointInstance(Class<T> clazz)
            throws InstantiationException {
        return context.getBean(clazz);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        System.out.println("auto load" + this.hashCode());
        MyEndpointConfig.context = applicationContext;
    }

}