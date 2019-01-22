<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>Draggable拖动</title>
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

<div id="aa" class="easyui-draggable"
     data-options="handle:'#title1'" style="width:100px;height:100px;">
    <div id="title1" style="background:#ccc;">title1</div>
</div>

<div id="bb" style="width:100px;height:100px;">
    <div id="title2" style="background:#ccc;">title1</div>
</div>
<script type="javascript">
    $("#bb").draggable({
        handle: '#title2'
    });
</script>
</body>
</html>
