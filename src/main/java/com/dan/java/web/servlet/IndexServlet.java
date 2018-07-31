package com.dan.java.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "IndexServlet",urlPatterns = "/index")
public class IndexServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.append("<html>")
                .append("<head>")
                .append("<title>")
                .append("java web")
                .append("</title>")
                .append("</head>")
                .append("<body>")
                .append("<h1>")
                .append("this is a java web").append(" now time is :").append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()))
                .append("</h1>")
                .append("</body>")
                .append("</html>");
    }

}