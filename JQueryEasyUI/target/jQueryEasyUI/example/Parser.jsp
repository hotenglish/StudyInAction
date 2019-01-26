<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>Parser解析器</title>
    <link rel="stylesheet" type="text/css" href="../easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="../easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="../css/demo.css">

    <script type="text/javascript" src="../js/jquery-1.8.2.min.js"></script>
    <script type="text/javascript" src="../easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="../js/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="../js/JQuery-formui.js"></script>
    <script type="text/javascript" src="../js/common.js"></script>
</head>
<body>
<a href="#" id="btnQuery" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>

<script type="text/javascript">

    $.parser.parse($('#btnQuery').parent());
    $.parser.onComplete = function () {
        alert("已成功加载");
    };

</script>
</body>
</html>