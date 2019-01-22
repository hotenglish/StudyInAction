<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>SearchBox搜索框</title>
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
<input id="aa" class="easyui-search" style="width:300px"
       data-options="searcher:qq1,prompt:'Please Input Value',menu:'#bb'">
<div id="bb" style="width:120px">
    <div data-options="name:'all',iconCls:'icon-ok'">All News</div>
    <div data-options="name:'sports'">Sports News</div>
</div>

<input id="cc"></input>
<div id="dd" style="width:120px">
    <div data-options="name:'all',iconCls:'icon-ok'">All News</div>
    <div data-options="name:'sports'">Sports News</div>
</div>
<script type="javascript">

    function qql(value, name) {
        alert(value + ":" + name);
    }

    $("#cc").searchbox({
        searcher: function (value, name) {
            alert(value + "," + name)
        },
        menu: "#dd",
        propmt: 'Please Input Value'
    });

</script>
</body>
</html>
