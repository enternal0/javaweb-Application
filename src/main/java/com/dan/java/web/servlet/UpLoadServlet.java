package com.dan.java.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;

@WebServlet(name = "upLoadServlet",urlPatterns = "/upload")
@MultipartConfig(maxFileSize = 10311680,location = "D:\\picture",maxRequestSize = 257792000)
//@MultipartConfig 为了辅助 Servlet 3.0 中 HttpServletRequest 提供的对上传文件的支持
public class UpLoadServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取上传的文件
        Part part = req.getPart("filename");
        InputStream is = part.getInputStream();
        //获取上传文件的路径
        String uploadPath = req.getServletContext().getRealPath("/upload");
        String filename = part.getSubmittedFileName();

        File file = new File(uploadPath,filename);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        if (file != null) {
            int len = 0;
            byte[] buffer = new byte[1024];
            OutputStream os = new FileOutputStream(file);
            while ((len = is.read(buffer)) != -1) {
                os.write(buffer, 0, len);
            }
            os.close();
            is.close();
            resp.setContentType("text/html; charset=UTF-8");
            writer.append("<html>")
                    .append("<head>")
                    .append("<meta charset='UTF-8'>")
                    .append("<title>File</title>")
                    .append("</head>")
                    .append("<body>")
                    .append("<a href='")
                    .append("/upload/").append(part.getSubmittedFileName())
                    .append("'>")
                    .append("<img src='")
                    .append("/upload/").append(part.getSubmittedFileName())
                    .append("'>")
                    .append("上传的文件")
                    .append("</a>")
                    .append("</body>")
                    .append("</html>");
        } else {
            writer.append("文件不存在，请选择需要上传的文件...");
        }
    }
}
