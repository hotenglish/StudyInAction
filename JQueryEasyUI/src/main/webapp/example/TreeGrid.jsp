<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>EasyUI Treegrid 树形网格</title>
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

<table id="tt" class="easyui-treegrid" style="width:600px;height:400px"
       data-options="url:'get_data.json',idField:'id',treeField:'name'">
    <thead>
        <tr>
            <th data-options="field:'name',width:180">Task Name</th>
            <th data-options="field:'persons',width:60,align:'right'">Persons</th>
            <th data-options="field:'begin',width:80">Begin Date</th>
            <th data-options="field:'end',width:80">End Date</th>
        </tr>
    </thead>
</table>

<table id="tb" style="width:600px;height:400px"></table>

<script type="javascript">

    $("tb").treegrid({
        url: 'get_data.json',
        idField: 'id',
        treeField: 'name',
        colums: [[
            {title: 'Task Name', field: 'name', width: 180},
            {field: 'persons', width: 60, align: 'right'},
            {field: 'begin', width: 80},
            {field: 'begin', width: 80}
        ]]
    });

</script>
</body>
</html>
