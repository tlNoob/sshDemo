package com.demo.servletDemo;

import com.demo.utils.JDBCUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.ResultSet;

@WebServlet(name="login",value = {"/loginServlet"})
public class LoginServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String userName=req.getParameter("userName");
        String passWord=req.getParameter("passWord");
        RequestDispatcher rd;
        String errMsg="";

        String sql="select *  from tl_user_info where userId= ?";
        ResultSet resultSet=JDBCUtils.query(sql,userName);
        try{
            if(resultSet!=null && resultSet.next()){
                if(resultSet.getString("password").equals(passWord)){
                    HttpSession session=req.getSession(true);
                    session.setAttribute("name",userName);
                    //rd=req.getRequestDispatcher("/main.jsp");
                    //rd.forward(req,resp);
                    resp.sendRedirect("/main.jsp");
                }else{
                    errMsg="您输入的用户名密码不符，请重新输入";
                }
            }else {
                errMsg="用户不存在！";
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        if(errMsg!=null && !errMsg.equals("")){
            rd=req.getRequestDispatcher("/index.jsp");
            req.setAttribute("err",errMsg);
            rd.forward(req,resp);

        }
    }
}
