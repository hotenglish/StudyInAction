<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>EasyLoader简单加载</title>
    <link rel="stylesheet" type="text/css" href="../easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="../easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="../css/demo.css">

    <script type="text/javascript" src="../js/jquery-1.8.2.min.js"></script>
    <script type="text/javascript" src="../easyui/easyloader.js"></script>
    <!-- <script type="text/javascript" src="../easyui/jquery.easyui.min.js"></script> -->
    <script type="text/javascript" src="../easyui/easyloader.js"></script>
    <script type="text/javascript" src="../js/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="../js/JQuery-formui.js"></script>
    <script type="text/javascript" src="../js/common.js"></script>
</head>
<body>

<a id="btnInfor" class="easyui-linkbutton">手动加载控件</a>

<script type="javascript">
    easyloader.locale = "zh_CN";
    using("messager", function () {
        alert("初始化控件!");
        $("#btninfo").click(function () {
            $.messager.alert("提示", "手动加载控件成功!");
        });
    });
</script>

</body>
</html>
