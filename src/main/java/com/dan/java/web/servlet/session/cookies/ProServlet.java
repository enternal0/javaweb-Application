package com.dan.java.web.servlet.session.cookies;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String proName = req.getParameter("pro");

        //创建Cookie
        Cookie cookie = new Cookie("pro", proName);
        resp.addCookie(cookie);

        //分为以下几种情况
        if (proName!=null) {
            //处理省
            resp.setContentType("text/html;charset=UTF-8");
            PrintWriter writer = resp.getWriter();
            Map<String, List<String>> proCity = (Map<String, List<String>>) req.getServletContext().getAttribute("proCity");
            List<String> citys = proCity.get(proName);
            writer.append("<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>市</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "    <table>\n" +
                    "        <thead>\n" +
                    "          <tr>\n" +
                    "              <td>编号</td>\n" +
                    "              <td>市</td>\n" +
                    "          </tr>\n" +
                    "        </thead>\n" +
                    "        <tbody>");
            int id = 1;
            for (String temp : citys) {
                writer.append("<tr>")
                        .append("<td>").append(String.valueOf(id)).append("</td>")
                        .append("<td>")
                        .append("<a href='").append("/city?").append("city=").append(temp).append("'>")
                        .append(temp)
                        .append("</a>")
                        .append("</td>")
                        .append("</tr>");
                id = id + 1;
            }
            writer.append("   </tbody>\n" +
                    "    </table>\n" +
                    "</body>\n" +
                    "</html>");
        } else {
            resp.setContentType("text/html;charset=UTF-8");
            PrintWriter writer = resp.getWriter();
            writer.append("没有相关数据哦--no data");
        }
    }
}
