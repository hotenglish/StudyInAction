<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>地市管理</title>

    <link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="css/demo.css">
    <link rel="stylesheet" type="text/css" href="css/fw.css">

    <script type="text/javascript" src="easyui/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="easyui/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="js/common.js"></script>
    <script type="text/javascript" src="js/JQuery-formui.js"></script>
    <script type="text/javascript" src="areaedit.js"></script>

</head>
<body>
<div data-options="fit:true">
    <!-- 内容栏 -->
    <div class="editcontent"
         style="padding:10px;background:#fff;border:1px solid #ccc;height:200px;">
        <div id="maindata">
            <!-- 不需要显示的字段 -->
            <div style="display: none">
                <input id="LEVEL" type="text">
                <input id="ICODE" type="text">
            </div>
        </div>
        <table class="table table-hover table-condensed">
            <tr>
                <th>编码</th>
                <td><input id="NO" type="text" class="easyui-validate span2" data-options="required:true"/></td>
                <th>上级</th>
                <td><input id="PARENTICODE" type="text" class="easyui-validate span2" data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <th>名称</th>
                <td><input id="NAME" type="text" class="easyui-validate span2" data-options="required:true"/></td>
                <th>拼音码</th>
                <td>
                    <input id="SPELLNO" type="text" class="span2" readonly="readonly"/>
                </td>
            </tr>
            <tr>
                <th>自定义码</th>
                <td>
                    <input id="CUSTOMNO" type="text" class="span2"/>
                </td>
                <th>邮编</th>
                <td>
                    <input id="ZIP" type="text" class="span2"/>
                </td>
            </tr>
            <tr>
                <th>联系电话</th>
                <td colspan="3">
                    <input id="TEL" type="text" class="span2"/>
                </td>
            </tr>
            <tr>
                <th>地址</th>
                <td colspan="3">
                    <input id="ADDRESS" type="text" class="span5" style="width:322px;"/>
                </td>
            </tr>
        </table>
    </div>

    <!-- 保存按钮栏 -->
    <div style="text-align:center;padding:5px 0;">
        <a id="btnSave" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">保 存</a>
        &nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;
        <a id="btnCancel" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取 消</a>
    </div>
</div>
</body>
</html>