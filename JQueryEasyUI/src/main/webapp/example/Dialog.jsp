<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>Dialog</title>
    <link rel="stylesheet" type="text/css" href="../easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="../easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="../css/demo.css">

    <script type="text/javascript" src="../js/jquery-1.8.2.min.js"></script>
    <script type="text/javascript" src="../easyui/jquery.easyui.min.js"></script>
    <script styp="text/javascript" src="../easyui/easyloader.js"></script>
    <script type="text/javascript" src="../js/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="../js/JQuery-formui.js"></script>
    <script type="text/javascript" src="../js/common.js"></script>
</head>
<body>
<div id="dd" class="easyui-dialog" title="My Dialog"
     data-options="iconCls:'icon-save',resizable:true, modal:true" style="width:400px;height:200px;">
    Dialog Content.
</div>

<div id="ff">Dialog Content.</div>
<script type="javascript">
    $("#ff").dialog({
        title: 'My Dialog',
        width: 400,
        height: 200,
        closed: false,
        cache: false,
    });
</script>
</body>
</html>
