package com.dan.java.web.servlet.listener;

import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

/**
 * ServletRequestListener监听器会对ServletRequest的创建和销毁事件进行响应
 */
@WebListener
public class AppServletRequestListener implements ServletRequestListener {
    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        ServletRequest servletRequest = sre.getServletRequest();
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        long startTime = System.nanoTime();
        request.setAttribute("start_time", startTime);
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        ServletRequest servletRequest = sre.getServletRequest();
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        long endTime = System.nanoTime();
        request.setAttribute("end_time", endTime);

        long startTime = (long) request.getAttribute("start_time");
        System.out.println("Request " + request.getRequestURI() + " Cast time " +
                (endTime - startTime) / 1000 + " 微秒 ");

        request.removeAttribute("start_time");
        request.removeAttribute("end_time");

    }
}
