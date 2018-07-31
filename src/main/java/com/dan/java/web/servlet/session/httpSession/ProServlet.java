package com.dan.java.web.servlet.session.httpSession;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProServlet extends HttpServlet {
    private Map<String, List<String>> proCity = new HashMap<>();
    private Map<String, List<String>> cityDist = new HashMap<>();

    @Override
    public void init() throws ServletException {
        super.init();
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
        cityDist.put("西安市",xianDist);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String proName = req.getParameter("pro");

        //创建HttpSession
        HttpSession session = req.getSession();
        session.setAttribute("pro",proName);

        //分为以下几种情况
        if (proName!=null) {
            //处理省
            resp.setContentType("text/html;charset=UTF-8");
            PrintWriter writer = resp.getWriter();
            Map<String, List<String>> proCity= (Map<String, List<String>>) req.getServletContext().getAttribute("proCity");
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
                        .append("<a href='").append("/city1?").append("city=").append(temp).append("'>")
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
