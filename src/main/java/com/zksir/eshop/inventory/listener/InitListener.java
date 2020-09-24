package com.zksir.eshop.inventory.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 系统初始化的监听器
 */
public class InitListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("=====系统初始化======");
//        ServletContext sc = sce.getServletContext();
//        ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(sc);

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
