<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>

<!DOCTYPE HTML>
<html>
<head>
    <title>MenuButton菜单按钮</title>
    <link rel="stylesheet" type="text/css" href="../easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="../easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="../css/demo.css">

    <script type="text/javascript" src="../js/jquery-1.8.2.min.js"></script>
    <script type="text/javascript" src="../easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="../easyui/easyloader.js"></script>
    <script type="text/javascript" src="../js/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="../js/JQuery-formui.js"></script>
    <script type="text/javascript" src="../js/common.js"></script>
</head>
<body>
<a href="javascript:void(0)" id="mb" class="easyui-menubutton"
   data-options="menu:'#mm',iconCls:'icon-edit'">Edit</a>
<div id="mm" style="width:150px;">
    <div data-options="iconCls:'icon-undo'">Undo</div>
    <div data-options="iconCls:'icon-redo'">Redo</div>
    <div class="menu-sep"></div>
    <div>Cut</div>
    <div>Copy</div>
    <div>Paste</div>
    <div class="menu-sep"></div>
    <div data-options="iconCls:'icon-remove'">Delete</div>
    <div>Select All</div>
</div>

<a href="javascript:void(0)" id="aa">Edit</a>
<div id="bb" style="width:150px"></div>

<script type="text/javascript">
    $("#aa").menubutton({
        iconCls: 'icon-edit',
        menu: '#bb'
    });
</script>

</body>
</html>
