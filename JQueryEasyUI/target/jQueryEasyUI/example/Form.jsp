<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>

<!DOCTYPE HTML>
<html>
<head>
    <title>Form表单</title>
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
<form id="ff" method="post">
    <div>
        <label form="name">Name:</label>
        <input class="easyui-validatebox" type="text" name="name" data-options="required:true">
    </div>
    <div>
        <label form="email">Email:</label>
        <input class="easyui-validatebox" type="text" name="email" data-options="validType:'email'"/>
    </div>
</form>

<script type="text/javascript">
    //提交方式1
    var url = "";
    $('#ff').form({
        url: url,
        onSubmit: function () {
            // do some check
            // return false to prevent submit;
        },
        success: function (data) {
            alert(data);
        }
    });
    // submit the form
    $('#ff').submit();

    //提交方式2
    // call 'submit' method of form plugin to submit the form
    $('#ff').form("submit", {
        url: url,
        onSubmit: function () {
            // do some check
            // return false to prevent submit;
        },
        success: function (data) {
            alert(data);
        }
    });

    //提交额外参数
    $('#ff').form("submit", {
        url: url,
        onSubmit: function (param) {
            param.p1 = 'value1';
            param.p2 = 'value2';
        }
    });
</script>

</body>
</html>
