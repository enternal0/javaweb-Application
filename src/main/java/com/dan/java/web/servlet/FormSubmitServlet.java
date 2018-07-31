package com.dan.java.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class FormSubmitServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter writer= resp.getWriter();
        writer.append("<html>")
                .append("<head>")
                  .append("<meta charset='UTF-8'>")
                  .append("<title>").append("表单提交").append("</title>")
                .append("</head>")
                  .append("<body>")
                    .append("<form method='POST' action='/form'>")
                    .append("请输入姓名：")
                    .append("<input name='name' type='text' value=''>")
                    .append("<input type='submit' value='提交'>")
                  .append("</body>")
                .append("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        String name=req.getParameter("name");
        PrintWriter writer=resp.getWriter();

        writer.append("<html>")
                .append("<head>")
                .append("<meta charset='UTF-8'>")
                .append("<title>").append("提交响应").append("</title>")
                .append("</head>")
                .append("<body>")
                .append("<h1>").append(new String(name.getBytes("ISO-8859-1"),"UTF-8")).append(" 恭喜您，提交成功").append("</h1>")
                .append("</body>")
                .append("</html>");

    }
}
