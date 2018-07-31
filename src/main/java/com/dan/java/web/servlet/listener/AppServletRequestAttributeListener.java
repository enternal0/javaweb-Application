package com.dan.java.web.servlet.listener;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.annotation.WebListener;

/**
 *  ServletRequestAttributeListener
 *  当一个ServletRequest范围的属性被添加，删除，或者替换时，ServletRequestAttributeListener接口会被调用。
 * 类似于HttpSessionAttributeListener
 */
@WebListener
public class AppServletRequestAttributeListener implements ServletRequestAttributeListener {
    @Override
    public void attributeAdded(ServletRequestAttributeEvent srae) {
        System.out.println("Add ServletRequest Attribute :" + srae.getName() + " = " + srae
                .getValue());
    }

    @Override
    public void attributeRemoved(ServletRequestAttributeEvent srae) {
        System.out.println("Removed ServletRequest Attribute :" + srae.getName() + " = " + srae
                .getValue());
    }

    @Override
    public void attributeReplaced(ServletRequestAttributeEvent srae) {
        System.out.println("Replaced ServletRequest Attribute :" + srae.getName() + " = " + srae
                .getValue());
    }
}
