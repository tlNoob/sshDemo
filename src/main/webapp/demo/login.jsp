<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/11/26
  Time: 21:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://127.0.0.1/frist-taglib" prefix="tl"%>
<html>
  <head>
    <title>登陆</title>
    <style>
       .main{
         position: absolute;
         left: 50%;
         top: 50%;
         transform: translate(-50%,-50%);
       }
    </style>
  </head>
  <body>
  <form method="post" action="login">

    <div class="main">
      用户名：<input type="text" ><br>
      密码：<input type="text"><br>
      <input type="submit" value="登陆">

        <tl:helloWorld/>
    </div>


  </form>
  </body>
</html>
