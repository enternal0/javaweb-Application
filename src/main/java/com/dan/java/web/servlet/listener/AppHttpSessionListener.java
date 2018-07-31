package com.dan.java.web.servlet.listener;


import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 主要监听HttpSession的创建和销毁
 * HttpSession相关的监听接口四个之一
 */
@WebListener
public class AppHttpSessionListener implements HttpSessionListener {
    private final AtomicInteger userCount = new AtomicInteger(0);

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        int value = userCount.incrementAndGet();
        System.out.println("sessionCreated userCount=" + value);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        int value = userCount.decrementAndGet();
        System.out.println("sessionDestroyed userCount=" + value);
    }
}
