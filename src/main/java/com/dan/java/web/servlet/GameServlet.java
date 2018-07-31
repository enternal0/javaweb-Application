package com.dan.java.web.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class GameServlet extends HttpServlet {

    //Servlet实例会被一个应用程序中的所有用户共享，不建议使用类级变量，会造成线程不安全
    //可通过ServletConfig进行变量初始化传参
    private int count = 10;

    //利用servlet容器获得ServletCinfig
    private ServletContext context;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        count = Integer.parseInt(config.getInitParameter("count"));
        context = config.getServletContext();
    }

    @Override
    /**
     * doGet查询方法
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");

        PrintWriter writer = resp.getWriter();
        writer.append("<html>")
                .append("<head>")
                .append("<title>")
                .append("game")
                .append("</title>")
                .append("</head>")
                .append("<body>");
        //在浏览器中用K-V的值来传值
        String value = req.getParameter("name");
        if (value == null) {
            writer.append("");
        }else {
            Object numberObj = context.getAttribute(value);
            Integer number = 0;
            if (numberObj == null) {
                number=count;
                context.setAttribute(value, number);
            } else {
                number = (Integer) numberObj;
            }
            if (number > 0) {
//                --number;
                number = number - 1;
                context.setAttribute(value, number);
                writer.append("<h1>").append("还剩").append(String.valueOf((number))).append("次机会了..")
                        .append("</h1>");
            } else {
                writer.append("<h1>").append("抱歉，您的机会用完了..").append("</h1>");
            }
            writer.append("</body>")
                    .append("</html>");
        }

    }


    @Override
    public void destroy() {
        super.destroy();
        System.out.println("this is destory");
    }
}
