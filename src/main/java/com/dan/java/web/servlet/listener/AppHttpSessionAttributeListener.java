package com.dan.java.web.servlet.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * HttpSessionAttributeListener ：主要监听HttpSession的属性添加，删除，替换
 * HttpSession相关的监听接口四个之一
 */
@WebListener
public class AppHttpSessionAttributeListener implements HttpSessionAttributeListener {
    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        System.out.println("Add HttpSession Attribute :" + event.getName() + " = " + event
                .getValue());
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        System.out.println("Removed HttpSession Attribute :" + event.getName() + " = " + event
                .getValue());
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        System.out.println("Replaced HttpSession Attribute :" + event.getName() + " = " + event
                .getValue());
    }
}
