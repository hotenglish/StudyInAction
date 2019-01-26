<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>

<!DOCTYPE HTML>
<html>
<head>
    <title>combo自定义下拉框</title>
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
<h2>Basic Combo</h2>
<p>Click the right arrow button to show drop down panel that can be filled with any content.</p>
<div style="margin:20px 0"></div>
<select id="cc" style="width:150px"></select>
<div id="sp">
    <div style="color:#99BBE8;background:#fafafa;padding:5px;">Select a language</div>
    <div style="padding:10px">
        <input type="radio" name="lang" value="01"><span>Java</span><br/>
        <input type="radio" name="lang" value="02"><span>C#</span><br/>
        <input type="radio" name="lang" value="03"><span>Ruby</span><br/>
        <input type="radio" name="lang" value="04"><span>Basic</span><br/>
        <input type="radio" name="lang" value="05"><span>Fortran</span>
    </div>
</div>
<script type="text/javascript">
    $(function () {
        $('#cc').combo({
            required: true,
            editable: false
        });
        $('#sp').appendTo($('#cc').combo('panel'));
        $('#sp input').click(function () {
            var v = $(this).val();
            var s = $(this).next('span').text();
            alert(s);
            $('#cc').combo('setValue', v).combo('setText', s).combo('hidePanel');
        });
    });
</script>

</body>
</html>
