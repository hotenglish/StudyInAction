<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>

<!DOCTYPE HTML>
<html>
<head>
    <title>Panel面板</title>
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

<div id="a" class="easyui-panel" title="My Panel"
     style="width:500px;height:150px;padding:10px;background:#fafafa;"
     data-options="iconCls:'icon-save',closable:true,
     collapsible:true,minimizable:true,maximizable:true">
    <p>panel content.</p>
    <p>panel content.</p>
</div>

<div id="b" style="padding:10px;">
    <p>panel content.</p>
    <p>panel content.</p>
</div>

<script type="text/javascript">
    $('#b').panel({
        width: 500,
        height: 150,
        title: 'My Panel',
        tools: [{
            iconCls: 'icon-add',
            handler: function () {
                alert("new");
            }
        }, {
            iconCls: 'icon-save',
            handler: function () {
                alert("save");
            }
        }]
    });
</script>

</body>
</html>
