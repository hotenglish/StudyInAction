<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>PropertyGrid 属性风格</title>
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

<table id="pa" style="width:300px"></table>
<hr/>
<table id="pg" style="width:300px"></table>

<script type="text/javascript">
    $('#pa').propertygrid({
        url: 'propertygrid_data.json',
        showGroup: true,
        scrollbarSize: 0
    });

    $('#pg').propertygrid({
        showGroup: true,
        scrollbarSize: 0
    });

    var row1 = {"name": "Name", "value": "Bill Smith", "group": "ID Settings", "editor": "text"};
    var row2 = {"name": "Address", "value": "", "group": "ID Settings", "editor": "text"};
    var row3 = {"name": "SSN", "value": "123-456-7890", "group": "ID Settings", "editor": "text"};
    var row4 = {
        "name": "Email", "value": "bill@gmail.com", "group": "Marketing Settings", "editor": {
            "type": "validatebox",
            "options": {
                "validType": "email"
            }
        }
    };

    $('#pg').propertygrid('appendRow', row1);
    $('#pg').propertygrid('appendRow', row2);
    $('#pg').propertygrid('appendRow', row3);
    $('#pg').propertygrid('appendRow', row4);

</script>
</body>
</html>
