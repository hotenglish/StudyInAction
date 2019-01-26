<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>EasyUI Splitbutton 时间微调器</title>
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
<a href="javascript:void(0)" id="sb" class="easyui-splitbutton"
   data-options="menu:'#mm',iconCls:'icon-ok'" onclick="javascript:alert('ok')">OK</a>
<div id="mm" style="width:100px">
    <div data-options="iconCls:'icon-ok'">Ok</div>
    <div data-options="iconCls:'icon-cancel'">Cancel</div>
</div>

<a href="javascript:void(0)" id="sa" class="easyui-splitbutton">OK</a>
<div id="ma" style="width:100px">
    <div data-options="iconCls:'icon-ok'">Ok</div>
    <div data-options="iconCls:'icon-cancel'">Cancel</div>
</div>

<script type="text/javascript">
    $('#sa').splitbutton({
        iconCls: 'icon-ok',
        menu: '#ma'
    });
</script>

</body>
</html>
