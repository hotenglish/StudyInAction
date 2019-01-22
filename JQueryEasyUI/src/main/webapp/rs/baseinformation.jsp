<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Form - jQuery EasyUI Demo</title>
    <style type="text/css">
        label {
            width: 120px;
            display: block;
        }
    </style>
    <link rel="stylesheet" type="text/css" href="../easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="../easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="../css/demo.css">

    <script type="text/javascript" src="../js/jquery-1.8.2.min.js"></script>
    <script type="text/javascript" src="../easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="../js/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="../js/JQuery-formui.js"></script>
    <script type="text/javascript" src="../js/common.js"></script>
    <script type="javascript">

        function loaddata1() {
            $("#ff").form('load', 'form_data.json');
        }

        function loaddata2() {
            $("#ff").form('load', {
                name: 'name2',
                email: 'mymail@gmail.com',
                subject: 'subject2',
                message: 'message2',
                language: 5
            });
        }

        function clearData() {
            $("#baseinformation").form("clear");
        }
    </script>

<body>
<h2>基本信息</h2>
<div class="demo-info">
    <div class="demo-tip icon-tip"></div>
    <div>从这里录入你的基本信息</div>
</div>

<div style="margin:10px 0;">
    <a href="#" class="easyui-linkbutton" onclick="loaddata1()">Load1</a>
    <a href="#" class="easyui-linkbutton" onclick="loaddata2()">Load2</a>
</div>
<div style="background:#fafafa;padding:10px;width:400px;height:600px;">
    <form id="baseinformation" method="post" action="baseinformation">
        <div>
            <label for="name">姓名:</label>
            <input class="easyui-validatebox" id="name" type="text" name="name" data-options="required:true"/>
        </div>
        <div>
            <label for="sex">性别:</label>
            <input class="easyui-validatebox" type="text" id="sex" name="sex"/>
        </div>
        <div>
            <label for="birth">出生:</label>
            <input class="easyui-validatebox" type="text" id="birth" name="birth"/>
        </div>
        <div>
            <label for="mobile">手机:</label>
            <input class="easyui-validatebox" type="text" id="mobile" name="mobile"/>
        </div>
        <div>
            <label for="communication">QQ:</label>
            <input class="easyui-validatebox" type="text" id="communication" name="communication"/>
        </div>
        <div>
            <label for="message">宣言:</label>
            <textarea id="message" name="message" style="height:60px;"/>
        </div>
        <div>
            <label for="hobby">爱好:</label>
            <textarea id="hobby" name="hobby" style="height:60px;"/>
        </div>
        <div>
            <label for="remark">备注:</label>
            <textarea id="remark" name="remark" style="height:60px;"/>
        </div>
        <div>
            <label for="nationality">国籍:</label>
            <input class="easyui-combobox" id="nationality" name="nationality"
                   data-options="url:'combobox_data.json',
                                 idField:'id',
                                 textField:'text',
                                 panelHeight:'auto'"/>
        </div>
        <div>
            <input type="submit" value="提交">
            <input type="submit" value="重置" onclick="clearData()">
        </div>
    </form>
</div>
<h1><s:property value="#session.test1"/></h1>
${applicationScope.app}<br/>

${sessionScope.sess}<br/>

${requestScope.req}<br/>

${applicationScope.app2}<br/>

${sessionScope.sess2}<br/>

${requestScope.req2}<br/>
</body>
</html>

<script type="javascript">
    var testend = "${applicationScope.app}";
    alert("testend===" + testend);
</script>