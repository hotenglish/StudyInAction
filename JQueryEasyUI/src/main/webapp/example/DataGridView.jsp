<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>Datagrid 数据网格</title>
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
<table class="easyui-datagrid">
    <thead>
    <tr>
        <th data-options="field:'code'">Code</th>
        <th data-options="field:'name'">Name</th>
        <th data-options="field:'price'">Price</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>001</td>
        <td>name1</td>
        <td>2323</td>
    </tr>
    <tr>
        <td>002</td>
        <td>name2</td>
        <td>4612</td>
    </tr>
    </tbody>
</table>

<table id="bg"></table>

<table id="bc" class="easyui-datagrid" style="width:400px;height:250px"
       data-options="url:'datagrid_data_simple.json',fitColumns:true,singleSelect:true">
    <thead>
    <tr>
        <th data-options="field:'code',width:100">Code</th>
        <th data-options="field:'name',width:100">Name</th>
        <th data-options="field:'price',width:100,align:'right'">Price</th>
    </tr>
    </thead>
</table>

<script type="text/javascript">
    $("#bg").datagrid({
        url: 'datagrid_data.json',
        fitColumns:true,
        columns: [[
            {field: 'productid', title: 'productid', width: 100},
            {field: 'productname', title: 'productname', width: 100},
            {field: 'unitcost', title: 'unitcost', width: 100, align: 'right'},
            {field: 'status', title: 'status', width: 100},
            {field: 'listprice', title: 'listprice', width: 100},
            {field: 'attr1', title: 'attr1', width: 100, align: 'right'},
            {field: 'itemid', title: 'itemid', width: 100, align: 'right'}
        ]]
    });
</script>
</body>
</html>
