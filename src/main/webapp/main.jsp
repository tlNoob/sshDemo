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
    <%--<ul class="layui-nav layui-layout-left">
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
    </ul>--%>
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
  <%--<div id="indixe-tab">
    <div class="layui-tab" lay-allowClose="true" lay-filter="demo1" style="margin:0px;padding:0px;">
      <ul class="layui-tab-title">

      </ul>
      <div class="layui-tab-content" style="margin:0px;padding:0px;">

      </div>
    </div>
  </div>--%>
<%--  <div class="layui-body">
    <!-- 内容主体区域 -->
    <div style="padding: 15px;">
    </div>
  </div>--%>

  <div class="layui-tab" lay-filter="tag" lay-allowclose="true" style="margin-left: 210px;">
    <ul class="layui-tab-title ">

    </ul>

    <ul class="rightmenu" style="display: none;position: absolute;background: #6E6C79;font-family: '微软雅黑';">
      <li id="yue" style="color: red;cursor:pointer;" data-id="" data-type="closethis">
        <a href="#" id="colcse" style="color: white;cursor:pointer;"  data-type="closethis"  >   &nbsp;&nbsp;关闭当前&nbsp;&nbsp;</a>
      </li>
      <li data-type="closeall">
        <a href="#" id="colcse" style="color: white;cursor:pointer;" data-id=""  data-type="closeall">&nbsp;&nbsp;关闭所有&nbsp;&nbsp;</a>
      </li>
    </ul>

    <div class="layui-tab-content">
    </div>
  </div>

  <div class="layui-footer">
    <!-- 底部固定区域 -->
    © layui.com - 底部固定区域
  </div>
</div>
<script src="layui/layui.js"></script>
<script>


    //JavaScript代码区域
    layui.use(['element', 'layer', 'jquery'], function(){
        var $ = layui.jquery
            ,element = layui.element;

        /*element.on('nav(demo)', function(elem){
            debugger;
            var data = $(this);
            if(true){
                element.tabAdd('demo1', {
                    title: data.attr("data-title") //用于演示
                    ,content: '<iframe src="'+data.attr("data-url")+'" style="width:100%;height:491px;" scrolling="auto" frameborder="0"></iframe>'
                    ,id: data.attr("data-id")//实际使用一般是规定好的id，这里以时间戳模拟下z
                })
                element.tabChange('demo1', data.attr("data-id"));
            }
        });*/
        //获取所有的菜单
        $.ajax({
            type:"GET",
            url:"LogionJsonAction/initMenu",
            dataType:"json",
            success:function(data){
                //debugger;
                //先添加所有的主材单
                var list=data.list;
                $.each(list,function(i,obj){
                    //debugger;
                    var content='<li class="layui-nav-item">';
                    content+='<a href="javascript:;" class="menu" data-id="'+obj.id+'" data-title="'+obj.name+'" data-url="'+obj.link+'" data-ispage="'+obj.isPage+'" >'+obj.name+'</a>';
                    //这里是添加所有的子菜单
                    content+=loadchild(obj);
                    content+='</li>';
                    $(".layui-nav-tree").append(content);
                });
                element.init();
            },
            error:function(jqXHR){
                alert("发生错误："+ jqXHR.status);
            }
        });

        //组装子菜单的方法
        function loadchild(obj){
            //debugger;
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
                    content+='<a href="javascript:;"  class="menu" data-id="'+note.id+'" data-title="'+note.name+'" data-url="'+note.link+'" data-ispage="'+note.isPage+'" >'+note.name+'</a>';
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


        //触发事件
        var active = {
            //在这里给active绑定几项事件，后面可通过active调用这些事件
            tabAdd: function(url,id,name) {
                //新增一个Tab项 传入三个参数，分别对应其标题，tab页面的地址，还有一个规定的id，是标签中data-id的属性值
                //关于tabAdd的方法所传入的参数可看layui的开发文档中基础方法部分
                element.tabAdd('tag', {
                    title: name,
                    content: '<iframe data-frameid="'+id+'" scrolling="auto" frameborder="0" src="'+url+'" style="width:100%;height:99%;"></iframe>',
                    id: id //规定好的id
                })
                CustomRightClick(id); //给tab绑定右击事件
                FrameWH();  //计算ifram层的大小
            },
            tabChange: function(id) {
                //切换到指定Tab项
                element.tabChange('tag', id); //根据传入的id传入到指定的tab项
            },
            tabDelete: function (id) {
                element.tabDelete("tag", id);//删除
            }
            , tabDeleteAll: function (ids) {//删除所有
                $.each(ids, function (i,item) {
                    element.tabDelete("tag", item); //ids是一个数组，里面存放了多个id，调用tabDelete方法分别删除
                })
            }
        };

        element.on('nav(demo)', function(elem){
            var dataid = $(this);
            if(dataid.attr("data-ispage")=='Y'){
                //这时会判断右侧.layui-tab-title属性下的有lay-id属性的li的数目，即已经打开的tab项数目
                if ($(".layui-tab-title li[lay-id]").length <= 0) {
                    //如果比零小，则直接打开新的tab项
                    active.tabAdd(dataid.attr("data-url"), dataid.attr("data-id"),dataid.attr("data-title"));
                } else {
                    //否则判断该tab项是否以及存在

                    var isData = false; //初始化一个标志，为false说明未打开该tab项 为true则说明已有
                    $.each($(".layui-tab-title li[lay-id]"), function () {
                        //如果点击左侧菜单栏所传入的id 在右侧tab项中的lay-id属性可以找到，则说明该tab项已经打开
                        if ($(this).attr("lay-id") == dataid.attr("data-id")) {
                            isData = true;
                        }
                    })
                    if (isData == false) {
                        //标志为false 新增一个tab项
                        active.tabAdd(dataid.attr("data-url"), dataid.attr("data-id"),dataid.attr("data-title"));
                    }
                }
                //最后不管是否新增tab，最后都转到要打开的选项页面上
                active.tabChange(dataid.attr("data-id"));
            }
        });

        function CustomRightClick(id) {
            //取消右键  rightmenu属性开始是隐藏的 ，当右击的时候显示，左击的时候隐藏
            $('.layui-tab-title li').on('contextmenu', function () { return false; })
            $('.layui-tab-title,.layui-tab-title li').click(function () {
                $('.rightmenu').hide();
            });
            //桌面点击右击
            $('.layui-tab-title li').on('contextmenu', function (e) {
                var popupmenu = $(".rightmenu");
                popupmenu.find("li").attr("data-id",id); //在右键菜单中的标签绑定id属性

                //判断右侧菜单的位置
                l = ($(document).width() - e.clientX) < popupmenu.width() ? (e.clientX - popupmenu.width()) : e.clientX;
                t = ($(document).height() - e.clientY) < popupmenu.height() ? (e.clientY - popupmenu.height()) : e.clientY;
                popupmenu.css({ left: l, top: t }).show(); //进行绝对定位
                //alert("右键菜单")
                return false;
            });
        }

        $(".rightmenu li").click(function () {

            //右键菜单中的选项被点击之后，判断type的类型，决定关闭所有还是关闭当前。
            if ($(this).attr("data-type") == "closethis") {
                //如果关闭当前，即根据显示右键菜单时所绑定的id，执行tabDelete
                active.tabDelete($(this).attr("data-id"))
            } else if ($(this).attr("data-type") == "closeall") {
                var tabtitle = $(".layui-tab-title li");
                var ids = new Array();
                $.each(tabtitle, function (i) {
                    ids[i] = $(this).attr("lay-id");
                })
                //如果关闭所有 ，即将所有的lay-id放进数组，执行tabDeleteAll
                active.tabDeleteAll(ids);
            }

            $('.rightmenu').hide(); //最后再隐藏右键菜单
        })
        function FrameWH() {
            var h = $(window).height() -41- 10 - 60 -10-44 -10;
            $("iframe").css("height",h+"px");
        }

        $(window).resize(function () {
            FrameWH();
        })
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