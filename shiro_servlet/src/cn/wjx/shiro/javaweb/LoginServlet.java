package cn.wjx.shiro.javaweb;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet：指定访问路径
@WebServlet(name="loginServlet",urlPatterns = "/login.html")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String usename = req.getParameter("username");
        String password = req.getParameter("password");
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(usename,password);
        try {
            currentUser.login(token);
            //登录成功后，跳转到首页
            resp.sendRedirect("/index.html");
        } catch (AuthenticationException e) {
            e.printStackTrace();
            //提示信息
            req.setAttribute("error","用户名或密码错误");
            //登录失败后，依然返回到登录页
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }
}
