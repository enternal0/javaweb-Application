package com.dan.java.web.servlet.session;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class HiddenServlet extends HttpServlet {

    protected static Map<String, Person> personMap = new HashMap<>();

    @Override
    public void init() throws ServletException {
        super.init();
        //本个id是内部id，对外不可见
        Person java=new Person("java",20,"1001");
        Person javaEE=new Person("javaEE",21,"1002");
        Person javaSE=new Person("javaSE",22,"1003");

        personMap.put("1001",java);
        personMap.put("1002",javaEE);
        personMap.put("1003",javaSE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter writer=resp.getWriter();

          writer.append("<!DOCTYPE html>\n" +
                  "<html lang=\"en\">\n" +
                  "<head>\n" +
                  "    <meta charset=\"UTF-8\">\n" +
                  "    <title>用户信息</title>\n" +
                  "</head>\n" +
                  "<body>\n" +
                  "<h1>用户信息列表</h1>\n" +
                  "   <table>\n" +
                  "      <thead>\n" +
                  "      <tr>\n" +
                  "          <td>编号</td>\n" +
                  "          <td>用户名</td>\n" +
                  "          <td>年龄</td>\n" +
                  "      </tr>\n" +
                  "      </thead>\n" +
                  "       <tbody>");
          for(Map.Entry<String, Person> temp:personMap.entrySet()){
                Person person= temp.getValue();
                writer.append("<tr>")
                        .append("<td>").append("<a href='/person?id=").append(person.id).append("'>")
                        .append(String.valueOf(person.id))
                        .append("</a>").append("</td>")
                         .append("<td>").append(person.userName).append("</td>")
                        .append("<td>").append(String.valueOf(person.age)).append("</td>")
                        .append("</tr>");
          }
          writer.append("     </tbody>\n" +
                  "   </table>\n" +
                  "\n" +
                  "</body>\n" +
                  "</html>");
    }

    public static class Person{
        private String userName;
        private Integer age;
        private String id;

        public Person(String userName, Integer age, String id) {
            this.userName = userName;
            this.age = age;
            this.id = id;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
