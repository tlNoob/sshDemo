<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String path = request.getContextPath();
  String basePath = request.getServerName() + ":" + request.getServerPort() + path + "/";
  String baseUrlPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <title>tlDemo后台管理</title>
  <link rel="stylesheet" href="layui/css/layui.css">
  <script src="js/jquery-3.4.1.js"></script>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
  <div class="layui-header">
    <div class="layui-logo">tlDemoLogo</div>
    <!-- 头部区域（可配合layui已有的水平导航） -->
    <ul class="layui-nav layui-layout-left">
      <li class="layui-nav-item"><a href="">控制台</a></li>
      <li class="layui-nav-item"><a href="">商品管理</a></li>
      <li class="layui-nav-item"><a href="">用户</a></li>
      <li class="layui-nav-item">
        <a href="javascript:;">其它系统</a>
        <dl class="layui-nav-child">
          <dd><a href="">邮件管理</a></dd>
          <dd><a href="">消息管理</a></dd>
          <dd><a href="">授权管理</a></dd>
        </dl>
      </li>
    </ul>
    <ul class="layui-nav layui-layout-right">
      <li class="layui-nav-item">
        <a href="javascript:;">
          <img src="images/people.jpg" class="layui-nav-img">
          tldemo
        </a>
        <dl class="layui-nav-child">
          <dd><a href="">基本资料</a></dd>
          <dd><a href="">安全设置</a></dd>
        </dl>
      </li>
      <li class="layui-nav-item"><a href="javascript:void(0);" onclick="loginOut()">退了</a></li>
    </ul>
  </div>

  <div class="layui-side layui-bg-black">
    <div class="layui-side-scroll">
      <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
      <ul class="layui-nav layui-nav-tree"  lay-filter="demo">
        <%--<li class="layui-nav-item layui-nav-itemed">
          <a class="" href="javascript:;">所有商品</a>
          <dl class="layui-nav-child">
            <dd><a href="javascript:;">列表一</a></dd>
            <dd><a href="javascript:;">列表二</a></dd>
            <dd><a href="javascript:;">列表三</a></dd>
            <dd><a href="">超链接</a></dd>
          </dl>
        </li>
        <li class="layui-nav-item">
          <a href="javascript:;">解决方案</a>
          <dl class="layui-nav-child">
            <dd><a href="javascript:;">列表一</a></dd>
            <dd><a href="javascript:;">列表二</a></dd>
            <dd><a href="">超链接</a></dd>
          </dl>
        </li>
        <li class="layui-nav-item"><a href="">云市场</a></li>
        <li class="layui-nav-item"><a href="">发布商品</a></li>--%>
      </ul>
    </div>
  </div>
  <!--页面选项卡-->
  <div id="indixe-tab">
    <div class="layui-tab" lay-allowClose="true" lay-filter="demo1" style="margin:0px;padding:0px;">
      <ul class="layui-tab-title">

      </ul>
      <div class="layui-tab-content" style="margin:0px;padding:0px;">

      </div>
    </div>
  </div>

  <div style="clear: both"></div>

  <div class="layui-body">
    <!-- 内容主体区域 -->
    <div style="padding: 15px;">内容主体区域</div>
  </div>

  <div class="layui-footer">
    <!-- 底部固定区域 -->
    © layui.com - 底部固定区域
  </div>
</div>
<script src="layui/layui.js"></script>
<script>


    //JavaScript代码区域
    layui.use('element', function(){
        var $ = layui.jquery
            ,element = layui.element;

        element.on('nav(demo)', function(elem){
            //console.log(elem.getAttribute("meun_id")); //得到当前点击的DOM对象
            var id=$(this).attr("meun_id");
            if(typeof(id)!="undefined"){
                console.log('进入方法');
                element.tabAdd('demo1', {
                    title: '新选项'+ (Math.random()*1000|0) //用于演示
                    ,content: '<iframe src="test.html" style="width:100%;height:491px;" scrolling="auto" frameborder="0"></iframe>'
                    ,id: id//实际使用一般是规定好的id，这里以时间戳模拟下z
                })
                element.tabChange('demo1', id);
            }
        });
        //获取所有的菜单
        $.ajax({
            type:"GET",
            url:"LogionJsonAction/initMenu",
            dataType:"json",
            success:function(data){
                debugger;
                //先添加所有的主材单
                var list=data.list;
                $.each(list,function(i,obj){
                    debugger;
                    var content='<li class="layui-nav-item">';
                    content+='<a href="'+obj.link+'">'+obj.name+'</a>';
                    //这里是添加所有的子菜单
                    content+=loadchild(obj);
                    content+='</li>';
                    $(".layui-nav-tree").append(content);
                });
                element.init();
            },
            error:function(jqXHR){
                aler("发生错误："+ jqXHR.status);
            }
        });

        //组装子菜单的方法
        function loadchild(obj){
            debugger
            if(obj==null){
                return;
            }

            var content='';
            if(obj.childMenu!=null && obj.childMenu.length>0){
                content+='<dl class="layui-nav-child">';
            }else{
                content+='<dl>';
            }

            if(obj.childMenu!=null && obj.childMenu.length>0){
                $.each(obj.childMenu,function(i,note){
                    content+='<dd>';
                    content+='<a href="javascript:;">'+note.name+'</a>';
                    if(note.childMenu==null){
                        return;
                    }
                    content+=loadchild(note);
                    content+='</dd>';
                });

                content+='</dl>';
            }
            console.log(content);
            return content;
        }
    });

    function loginOut() {

        layui.use('layer', function(){ //独立版的layer无需执行这一句
            layer = layui.layer;
            layer.confirm('确定退出吗?', {icon: 3, title:'提示'}, function(index){
                //do something
                window.location.href="/LoginAction/loginOut";
                layer.close(index);
            });
        });
    }
</script>
</body>
</html>