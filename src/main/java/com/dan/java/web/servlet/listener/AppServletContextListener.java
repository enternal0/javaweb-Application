package com.dan.java.web.servlet.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 够响应ServletContext生命周期事件,提供了ServletContext容器
 * 创建之后和关闭之前的调用方法
 */
@WebListener
public class AppServletContextListener implements ServletContextListener {
    /**
     * 我们可以在此处初始化一些我们需要使用的数据
     *
     * @param sce
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {

        System.out.println("---contextInitialized---");

        Map<String, List<String>> proCity = new HashMap<>();
        Map<String, List<String>> cityDist = new HashMap<>();
        List<String> citys = new ArrayList<>();
        citys.add("西安市");
        citys.add("咸阳市");
        citys.add("渭南市");
        citys.add("宝鸡市");
        proCity.put("陕西省", citys);

        List<String> xianDist = new ArrayList<>();
        xianDist.add("莲湖区");
        xianDist.add("长安区");
        xianDist.add("未央区");
        xianDist.add("临潼区");
        cityDist.put("西安市", xianDist);

        sce.getServletContext().setAttribute("proCity",proCity);
        sce.getServletContext().setAttribute("cityDist",cityDist);


    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("---contextDestroyed---");
                 sce.getServletContext().removeAttribute("proCity");
                 sce.getServletContext().removeAttribute("cityDist");
    }
}
