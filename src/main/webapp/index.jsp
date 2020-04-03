<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/11/26
  Time: 21:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta http-equiv="Pragma" content="no-cache">
  <meta http-equiv="Cache-Control" content="no-cache">
  <meta http-equiv="Expires" content="0">
  <title>后台登录</title>
  <link href="css/login.css" type="text/css" rel="stylesheet">
  <script src="js/jquery-3.4.1.js"></script>
  <script src="layui/layui.js"></script>
  <link rel="stylesheet" href="layui/css/layui.css" media="all" />



  <script type="text/javascript">
      $(document).ready(function () {
          var  err='${err}';
          if(CheckIsNullOrEmpty(err)){
              layui.use('layer', function(){ //独立版的layer无需执行这一句
                  layer = layui.layer;
                  layer.open({
                      type: 1
                      ,offset: 't' //具体配置参考：http://www.layui.com/doc/modules/layer.html#offset
                      ,id: 'layerDemo' //防止重复弹出
                      ,content: '<div style="padding: 20px 100px;">'+ err +'</div>'
                      ,btn: '关闭全部'
                      ,btnAlign: 'c' //按钮居中
                      ,shade: 0 //不显示遮罩
                      ,yes: function(){
                          layer.closeAll();
                      }
                  });

              });

          }
      });

      function CheckIsNullOrEmpty(value) {
          //正则表达式用于判斷字符串是否全部由空格或换行符组成
          var reg = /^\s*$/
          //返回值为true表示不是空字符串
          return (value != null && value != undefined && !reg.test(value));
      }

  </script>
</head>
<body>

<div class="login">
  <div class="message">TLDemo</div>
  <div id="darkbannerwrap"></div>

  <form method="post" action="login">
    <input name="userName" placeholder="用户名" required="true" type="text">
    <hr class="hr15" >
    <input name="passWord" placeholder="密码" required="true" type="password">
    <hr class="hr15" >
    <input value="登录" style="width:100%;" type="submit">
    <hr class="hr20">
    <!-- 帮助 <a onClick="alert('请联系管理员')">忘记密码</a> -->
  </form>


</div>


</body>
</html>
