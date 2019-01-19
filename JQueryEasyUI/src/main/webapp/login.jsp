<%@ page import="com.fore.util.PublicinformationUtil" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html style="width:100%;height:100%;overflow:hidden">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>登录</title>
    <link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
    <link ref="stylesheet" type="text/css" href="css/main.css">

    <script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
    <script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="js/JQuery-formui.js"></script>
    <script type="text/javascript" src="js/common.js"></script>
</head>
<body style="height:100%;width:100%;overflow:hidden;border:none;visibility:visible;">
<div id="mainwindow" class="easyui-window" style="width:500px;height:300px;background:#fafafa;overflow:hidden"
     title="登录" border="false" resizale="false" draggable="false" minimizable="false" maximizable="false">
    <div class="header" style="height: 35px;">
        <div class="toptitle" style="margin-top: 25px; font-size:20px; margin-left:60px;">
            <%=PublicinformationUtil.GetProperties("projectname")%>
        </div>
    </div>
    <div style="padding:60px 0;">
        <div id="loginForm">
            <div style="padding-left:150px">
                <table cellpadding="0" cellspacing="3">
                    <tr>
                        <td>登录帐号</td>
                        <td><input id="LOGINNAME" style="width:114px;"></input>
                        </td>
                    </tr>
                    <tr>
                        <td>登录密码</td>
                        <td><input id="PASSWORD" type="password" style="width:114px;"></input>
                        </td>
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td></td>
                        <td>
                            <a id="btnLogin" class="easyui-linkbutton">登 录</a>
                            <a class="easyui-linkbutton" onclick="clearAll()">重 置</a>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    function clearAll() {
        document.getElementById('LOGINNAME').value = "";
        document.getElementById('PASSWORD').value = "";
    }

    $("#password").keydown(function (event) {
        if (event.keyCode == 13) {
            $("#btnLogin").click();
        }
    });

    $("#btnLogin").click(function () {
        var LOGINNAME = $("#LOGINNAME").val();
        var PASSWORD = $("#PASSWORD").val();
        if (JUDGE.isNull(LOGINNAME) || JUDGE.isNull(PASSWORD)) {
            $.messager.alert("提示消息", "用户名、密码都不能为空!", "info");
            return;
        }

        var condition = {"LOGINNAME": LOGINNAME, "PASSWORD": PASSWORD};
        condition = JSON.stringify(condition);
        condition = escape(encodeURIComponent(condition));
        var url = 'USERLogin.action?condition=' + condition;

        $.ajax({
            type: "post",
            url: url,
            contentType: "text/html",
            error: function (event, request, settings) {
                $.messager.alert("提示消息", "请求失败!", "info");
            },
            success: function (data) {
                if (data.total > 0) {
                    window.location.href = "main.jsp";
                } else {
                    $.messager.alert("提示消息", "用户名或密码错误!", "info");
                }
            }
        });
    });

</script>
</body>
</html>
