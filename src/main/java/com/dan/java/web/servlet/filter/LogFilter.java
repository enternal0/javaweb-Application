package com.dan.java.web.servlet.filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 实现一个filter将request请求的url记录到日志文件中
 * 通过日志，分析出有用的价值，比如：哪些url被访问的更加频繁一点
 * filter会根据你配置的顺序进行拦截（过滤），所以顺序很重要
 */
@WebFilter(filterName = "LogFilter",initParams = {@WebInitParam(name = "prefix",value = "LOG"),
        @WebInitParam(name = "logFilaName",value = "filter_logging.txt")},urlPatterns = {"/*"})
public class LogFilter implements Filter {

    private String prefix;
    private PrintWriter writer;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
       prefix=filterConfig.getInitParameter("prefix");
       String logFileName=filterConfig.getInitParameter("logFilaName");
       String logPath=filterConfig.getServletContext().getRealPath("/log");
       File logFile=new File(logPath,logFileName);
      if(!logFile.getParentFile().exists()){
          logFile.getParentFile().mkdirs();
      }
        try {
            writer=new PrintWriter(logFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if(writer==null){
          throw new RuntimeException("writer can not be null");
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

       HttpServletRequest req= (HttpServletRequest) request;
        String uri=req.getRequestURI();
        String log=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"-->"+this.prefix+"-->"+uri+"\n";
        writer.write(log);
        writer.flush();

        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {
          if(writer!=null){
              writer.close();
          }
    }
}
