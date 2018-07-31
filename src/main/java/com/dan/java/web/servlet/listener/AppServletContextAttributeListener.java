package com.dan.java.web.servlet.listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.annotation.WebListener;

/**
 * 当一个ServletContext范围的属性被添加，删除或者替换时，ServletContextAttributeListener接口的实现类会接收到消息。
 */
@WebListener
public class AppServletContextAttributeListener implements ServletContextAttributeListener {
    @Override
    public void attributeAdded(ServletContextAttributeEvent event) {
        System.out.println("Add ServletContext Attribute :" + event.getName() + " = " + event
                .getValue());
    }

    @Override
    public void attributeRemoved(ServletContextAttributeEvent event) {
        System.out.println("remove ServletContext Attribute :" + event.getName() + " = " + event
                .getValue());
    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent event) {
        System.out.println("replace ServletContext Attribute :" + event.getName() + " = " + event
                .getValue());
    }
}
