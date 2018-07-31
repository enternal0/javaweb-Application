package com.dan.java.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "QueryServlet",urlPatterns = "/query")
public class QueryServlet extends HttpServlet {
    private Map<String, String> typeName = new HashMap<>();
    private Map<String, List<String>> thingsName = new HashMap<>();

    @Override
    public void init() throws ServletException {
        super.init();
        List<String> fruit = new ArrayList<>();
        fruit.add("香蕉");
        fruit.add("葡萄");
        fruit.add("西瓜");
        fruit.add("苹果");
        typeName.put("fruit", "水果");
        thingsName.put("fruit", fruit);


        List<String> veg = new ArrayList<>();
        veg.add("黄瓜");
        veg.add("西红柿");
        veg.add("南瓜");
        veg.add("茄子");
        typeName.put("veg", "蔬菜");
        thingsName.put("veg", veg);


        List<String> bool = new ArrayList<>();
        bool.add("铅笔");
        bool.add("文具盒");
        bool.add("作业本");
        bool.add("圆珠笔");
        typeName.put("bool", "文具");
        thingsName.put("bool", bool);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        处理参数
        String type = req.getParameter("type");
//        准备数据
        List<TypeDao> typeDaoList = new ArrayList<>();
        if (type == null || type.length() == 0) {
            //所有信息全部输出
            for (Map.Entry<String, List<String>> temp : thingsName.entrySet()) {
                List<String> thingName = temp.getValue();
                for (String temp1 : thingName) {
                    TypeDao typeDao = new TypeDao();
                    typeDao.setThingsName(temp1);
                    typeDao.setTypeName(typeName.get(temp.getKey()));
                    typeDaoList.add(typeDao);
                }
            }
        } else {
//            类型名字
            List<String> list = thingsName.get(type);
            if (list == null) {
                list = new ArrayList<>();
            } else {
                for (String temp : list) {
                    TypeDao typeDao = new TypeDao();
                    typeDao.setTypeName(typeName.get(type));
                    typeDao.setThingsName(temp);
                    typeDaoList.add(typeDao);
                }
            }
        }
//            响应数据
            resp.setContentType("text/html;charset=UTF-8");
            PrintWriter writer = resp.getWriter();
            writer.append("<html>")
                    .append("<head>")
                    .append("<meta charset='UTF-8'>")
                    .append("<title>").append("各种类型表单").append("</title>")
                    .append("</head>")
                    .append("<body>")
                    .append("<table>")
                    .append("<thead>")
                    .append("<tr>")
                    .append("<td>").append("编号").append("</td>")
                    .append("<td>").append("类型").append("</td>")
                    .append("<td>").append("名字").append("</td>")
                    .append("</tr>")
                    .append("</thead>")
                    .append("<tbody>");
            int id = 1;
            for (TypeDao temp : typeDaoList) {
                writer.append("<tr>")
                        .append("<td>").append(String.valueOf(id)).append("</td>")
                        .append("<td>").append(temp.getTypeName()).append("</td>")
                        .append("<td>").append(temp.getThingsName()).append("</td>")
                        .append("</tr>");
                id = id + 1;
            }
            writer.append("</tbody>")
                    .append("</table>")
                    .append("</body>")
                    .append("</html>");
    }

    public static class TypeDao {
        private String typeName;
        private String thingsName;

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }

        public String getThingsName() {
            return thingsName;
        }

        public void setThingsName(String thingsName) {
            this.thingsName = thingsName;
        }
    }
}
