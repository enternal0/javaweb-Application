package com.dan.java.web.servlet.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 请求计数器，统一拦截请求，将请求的URL路径作为属性名，请求次数作为属性值，并且以key-value的形式保存到属性文件
 */
@WebFilter(filterName = "CountFilter",initParams = {@WebInitParam(name="counterFileName",value = "counterFileName.properties")}
             ,urlPatterns = {"/*"}  )
public class CountFilter implements Filter {

    //避免多线程
    private final Executor executor = Executors.newSingleThreadExecutor();
    private Properties properties;
    private File countFile;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String fileName = filterConfig.getInitParameter("counterFileName");
        /**
         * 此处/log要把内容写到log文件夹中
         */
        String logPath = filterConfig.getServletContext().getRealPath("/log");
        countFile = new File(logPath, fileName);
        if (!countFile.getParentFile().exists()) {
            countFile.getParentFile().mkdirs();
        }
        if (!countFile.exists()) {
            try {
               countFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            properties = new Properties();
            try (FileInputStream inputStream = new FileInputStream(countFile)) {
                properties.load(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String uri = req.getRequestURI();
        //异步处理URL的请求统计
        executor.execute(new Runnable() {
            @Override
            public void run() {
                if (uri == null) {
                    try {
                        chain.doFilter(request, response);
                    } catch (IOException | ServletException e) {
                        e.printStackTrace();
                    }
                } else {
                    Object value = properties.get(uri);
                    if (value == null) {
                        value = 1;
                    } else {
                        value = Integer.parseInt((String) value) + 1;
                    }
                    properties.put(uri, String.valueOf(value));
                    try (FileOutputStream fileOutputStream = new FileOutputStream(countFile)) {
                        properties.store(fileOutputStream, "");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        chain.doFilter(request, response);
    }
    @Override
    public void destroy() {
    }
}
