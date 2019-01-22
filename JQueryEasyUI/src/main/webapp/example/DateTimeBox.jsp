<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>DateTimeBox日期时间输入框</title>
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
<input class="easyui-datetimebox" name="birthday"
       data-options="required:true,showSeconds:false" value="3/4/2010 2:3" style="width:150px"/>
<input id="dt" type="text" name="birthday"/>
<script type="javascript">
    $("#dt").datetimebox({
        value: '3/4/2010 2:3',
        required: true,
        showSeconds: false
    });
</script>
</body>
</html>
