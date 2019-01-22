<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>Slider</title>
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

<input id="aa" class="easyui-slider" value="12" style="width:300px"
       data-options="showTip:true,rule:[0,'|',25,'|',50,'|',75,'|',100]"/>

<div id="bb" class="easyui-slider" data-options="min:10,max:90,step:10" style="width:300px"></div>

<div id="ss" style="height:200px"></div>

<script type="javascript">

    $("#ss").slider({
        mode: 'v',
        tipFormatter: function (value) {
            return value + '%';
        }
    });

</script>
</body>
</html>
