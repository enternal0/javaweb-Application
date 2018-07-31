package com.dan.java.web.servlet.session.httpSession.login;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter writer= resp.getWriter();
        writer.append("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>用户登录</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "  <form method=\"post\" action=\"/login\">\n" +
                "       <label for=\"username\">用户名</label>\n" +
                "      <input  id=\"username\" name=\"username\" type=\"text\" placeholder=\"请输入用户名 \" value=\"\">\n" +
                        "<br>"+
                "      <label for=\"password\">密码</label>\n" +"  "+
                "      <input id=\"password\" name=\"password\" value=\"\" type=\"text\" placeholder=\"请输入用户密码\">\n" +
                "      <input type=\"submit\" value=\"登录\">\n" +
                "  </form>\n" +
                "\n" +
                "</body>\n" +
                "</html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         String userName=req.getParameter("username");
         String passWord=req.getParameter("password");
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        if("root".equals(userName) && "123456".equals(passWord)){
            HttpSession httpSession=req.getSession();
            httpSession.setAttribute("current_name",userName);
            writer.append("登录成功").append("<a href='/index'>").append("返回主页")
                    .append("</a>");
        }else{
             writer.append("登录失败，请重新登录").append("<a href='/login'>").append("重新登录").append("</a>");
        }
    }
}
