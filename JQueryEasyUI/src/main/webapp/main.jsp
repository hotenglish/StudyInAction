<%@ page import="com.fore.util.PublicinformationUtil" %>
<%@ page import="com.manage.platform.entity.MANAGE_USEREntity" %>
<%@ page import="com.manage.platform.entity.MANAGE_AREAEntity" %>
<%@ page import="java.util.*" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title><%=PublicinformationUtil.GetProperties("projectname")%></title>

    <link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="css/main.css">

    <script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
    <script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="js/common.js"></script>
    <script type="text/javascript" src="main.js"></script>
</head>
<body style="border:none;visibility:visible;width:100%;height:100%;" onload="showTime();">
<div id="cc" class="easyui-layout" style="width:100%;height:100%;">
    <!-- 页面顶部top及菜单栏 -->
    <div region="north" style="height:96px;width: 100%;">
        <div class="header" style="background: #fff url('css/images/banner.jpg');">
            <div style="text-align: right; padding-right: 20px; padding-top: 30px; padding-bottom: 14px;">
                <span style="color:#FDFDFD" id="loginuserInfo">欢迎你，<%=((MANAGE_USEREntity)session.getAttribute("user")).getNAME()%></span>
                <span style="color:#FDFDFD" id="loginuserArea">,<%=((MANAGE_AREAEntity)session.getAttribute("area")).getNAME()%></span>
                <span style="color:#FDFDFD" id="timeInfo"></span>
                <a href="login.jsp" style="color:#FDFDFD;text-decoration:none;">退出</a>
            </div>
            <div class="maintitle" style="top:12px;font-size:20px;color:#FDFDFD; margin-left:5px;">WEB商务管理系统</div>
        </div>
        <div id="topmenu" class="topmenu" style="height:34px;background:url('css/images/maintop.png');color:#fff;font-size:14px;font-weight:bold;">
            &nbsp;&nbsp;&nbsp;&nbsp;
            <a href="javascript:addTab('首页','welcome.htm')">首&nbsp;&nbsp;页</a>
        </div>
    </div>
    <!-- 页面底部信息 -->
    <div region="south" style="height:18px">
        <center></center>
    </div>
    <!-- 左侧导航菜单 -->
    <div region="west" title="导航菜单栏" style="width:180px;">
        <ul id="tt1" class="easyui-tree" data-options="animate:true,dnd:true"></ul>
    </div>
    <!-- 主显示区域选项卡界面 -->
    <div region="center">
        <div class="easyui-tabs" fit="true" id="tt">
            <div  title="首页" data-options="closable:true">
                <iframe width="100%" height="100%" id="iframe" name='iframe' frameborder='0' scrolling="auto" src="welcome.jsp"/>
            </div>
        </div>
    </div>
    <div id="dd"></div>
</div>
</body>
</html>
