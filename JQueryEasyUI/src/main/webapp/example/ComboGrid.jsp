<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>ComboGrid</title>
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

<select id="cc" class="easyui-combogrid" name="dept" style="width:250px;"
        data-options="
        panelWidth:450,
        value:'006',
        idField:'code',
        textField:'name',
        url:'datagrid_data.json',
        columns:[[
        {field:'code',title:'Code',width:60},
        {field:'name',title:'Name',width:100},
        {field:'addr',title:'Address',width:120},
        {field:'col4',title:'Col41',width:100}
        ]]
        "></select>

<input id="dd" name="dept" value="01">

<script type="text/javascript">
    $('#dd').combogrid({
        panelWidth: 450,
        value: '006',
        idField: 'code',
        textField: 'name',
        url: 'datagrid_data.json',
        columns: [[
            {field: 'code', title: 'Code', width: 60},
            {field: 'name', title: 'Name', width: 100},
            {field: 'addr', title: 'Address', width: 120},
            {field: 'col4', title: 'Col41', width: 100}
        ]]
    });
</script>

</body>
</html>
