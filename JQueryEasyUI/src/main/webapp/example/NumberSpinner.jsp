<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>Numberspinner 数值微调器</title>
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
<input id="ss" class="easyui-numberspinner" style="width:80px;"
       required="true" data-options="min:0,max:100,editable:false"/>

<input id="st" style="width:80px;">

<input class="easyui-numberspinner" value="1234567890" style="width:150px;"
       data-options="required:true,precision:2,groupSeparator:',',decimalSeparator:'.',prefix:'$'">

<script type="javascript">
    $("#st").numberspinner({
        min: 10,
        max: 100,
        editable: false
    });
</script>

</body>
</html>
