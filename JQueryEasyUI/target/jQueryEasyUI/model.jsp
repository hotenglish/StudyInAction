<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>地市管理</title>

    <link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="css/demo.css">
    <link rel="stylesheet" type="text/css" href="fancybox/jquery.fancybox-1.3.4.css"></link>
    <link rel="stylesheet" type="text/css" href="css/fw.css"></link>

    <script type="text/javascript" src="easyui/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="easyui/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="js/common.js"></script>
    <script type="text/javascript" src="js/JQuery-formui.js"></script>
    <script type="text/javascript" src="model.js"></script>

</head>
<body>
<!-- 数据列表 -->
<table id="dataview"></table>
<!-- 对话框 -->
<div id="wedit" class="easyui-window" data-options="title:'菜单管理',iconCls:'icon-save',modal:true,closed:true"
     style="width: 500px; height: 340px; padding: 5px;">
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'center',border:false" style="padding: 10px; background: #fff;
                border: 1px solid #ccc;">
            <iframe id="iframemain" scrolling="yes" frameborder="0" src="#" style="width: 100%;
                    height: 98%;"></iframe>
        </div>
    </div>
</div>

</body>
</html>
