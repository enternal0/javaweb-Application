package com.dan.java.web.servlet.session;
import com.dan.java.web.servlet.session.HiddenServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static com.dan.java.web.servlet.session.HiddenServlet.personMap;

public class PersonServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter writer=resp.getWriter();
        Object attribute= req.getParameter("id");
        if(attribute==null){
            writer.append("参数不能为空");
        }else{
            String id=(String)attribute;
            HiddenServlet.Person person = personMap.get(id);
            writer.append("<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>用户信息修改</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<h1>用户信息列表</h1>\n" +
                    " <form action=\"/person\" method=\"post\">\n" +
                    "     <input name=\"id\" type=\"text\" hidden=\"hidden\" value=\""+person.getId()+"\">\n" +
                    "  姓名: <input name=\"name\" type=\"text\" value=\""+person.getUserName()+"\" placeholder=\"请输入姓名..\">\n" +
                    "  年龄: <input name=\"age\" type=\"text\" value=\""+person.getAge()+"\" placeholder=\"请输入年龄\">\n" +
                    "     <input type=\"submit\" value=\"提交更新\">\n" +
                    " </form>\n" +
                    "\n" +
                    "</body>\n" +
                    "</html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        String id = req.getParameter("id");
        if (id == null) {
            //新建用户
            writer.append("暂时不支持");
        } else {
            //修改用户
            String name = req.getParameter("name");
            String ageStr = req.getParameter("age");
            HiddenServlet.Person person = personMap.get(id);
            person.setAge(Integer.parseInt(ageStr));
            person.setUserName(name);
            writer.append("<a href='/hidden'>").append("回到列表").append("</a>");
        }
    }
}
