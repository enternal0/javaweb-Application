package com.dan.java.web.servlet.filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter(filterName = "LoginFilter",initParams ={@WebInitParam(name = "exclude",value = "/login,/index.html,/,/logout")},
        urlPatterns = {"/*"})
public class LoginFilter implements Filter {

    private String[] urlArray;
    private PrintWriter writer;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String urls = filterConfig.getInitParameter("exclude");
        if (urls == null || urls.length() == 0) {
            urlArray = new String[]{};
        } else {
            urlArray = urls.split(",");
        }
    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String uri = req.getRequestURI();
        boolean exclude = false;
        for (String temp : urlArray) {
            if (uri.equals(temp)) {
                exclude = true;
                break;
            }
        }
        if (exclude) {
            chain.doFilter(request, response);
        } else {
            resp.setContentType("text/html;charset=UTF-8");
            writer = resp.getWriter();
            HttpSession httpSession = req.getSession();
            Object attribute= httpSession.getAttribute("current_name");
            if (attribute == null) {
                writer.append("<a href='/login'>").append("还未登录，请登录...").append("</a>");
            } else {
                chain.doFilter(request, response);
            }
        }
    }
    @Override
    public void destroy() {
        if (writer != null) {
            writer.close();
        }
    }
}
