<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String path = request.getContextPath();
  String basePath = request.getServerName() + ":" + request.getServerPort() + path + "/";
  String baseUrlPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>用户管理</title>
  <link rel="stylesheet" href="/layui/css/layui.css" media="all">
</head>
<body>

<table id="demo" lay-filter="test"></table>
<div id="page"></div>

<script src="/layui/layui.js"></script>
<script>
    layui.use(['table','laypage', 'layer'], function(){
        var table = layui.table,laypage = layui.laypage;

        //第一个实例
        table.render({
            elem: '#demo'
            ,height: 312
            ,url: 'LogionJsonAction/findAllUser' //数据接口
            ,page: true //开启分页
            ,cols: [[ //表头
                {field: 'userId', title: 'ID', sort: true, fixed: 'left'}
                ,{field: 'loginName', title: '用户名'}
                ,{field: 'roleCode', title: '角色', sort: true}
            ]]
        });


    });
</script>
</body>
</html>