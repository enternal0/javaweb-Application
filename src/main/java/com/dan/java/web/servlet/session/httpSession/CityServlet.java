package com.dan.java.web.servlet.session.httpSession;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CityServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession=req.getSession();
        String proName= (String) httpSession.getAttribute("pro");
         String cityName=req.getParameter("city");
        //分为以下几种情况
        if (cityName!= null) {
            //处理市
            resp.setContentType("text/html;charset=UTF-8");
            PrintWriter writer = resp.getWriter();
            Map<String, List<String>> cityDist= (Map<String, List<String>>) req.getServletContext().getAttribute("cityDist");
            List<String> dists = cityDist.get(cityName);
            writer.append("<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>区</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "    <table>\n" +
                    "        <thead>\n" +
                    "          <tr>\n" +
                    "              <td>编号</td>\n" +
                    "              <td>区</td>\n" +
                    "          </tr>\n" +
                    "        </thead>\n" +
                    "        <tbody>");
            int id = 1;
            for (String temp : dists) {
                writer.append("<tr>")
                        .append("<td>").append(String.valueOf(id)).append("</td>")
                        .append("<td>").append(temp).append("</td>")
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
