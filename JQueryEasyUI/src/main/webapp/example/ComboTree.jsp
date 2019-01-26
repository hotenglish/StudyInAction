<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>ComboTree下拉列表框</title>
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

<select id="cc" class="easyui-combotree" style="width:200px;"
        data-options="url:'combotree_data.json',required:true">
</select>

<input id="dd" value="01">

<script type="text/javascript">

    $('#dd').combotree({
        url: 'combotree_data.json',
        required: true
    });

</script>

</body>
</html>
