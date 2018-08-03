<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>upload</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/easyui/themes/icon.css">
    <script type="text/javascript" src="<%=basePath%>easyui/jquery.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>easyui/locale/easyui-lang-zh_CN.js"></script>
</head>

<body>
<div class="easyui-panel" title="New Topic" style="width:400px">
    <div style="padding:10px 60px 20px 60px">
        <form id="fileForm" method="post" enctype="multipart/form-data">
            <table cellpadding="5">
                <tr>
                    <td>image name:</td>
                    <td><input class="easyui-textbox" name="title" id="title" data-options="required:true"/></td>
                </tr>
                <tr>
                    <td>select file:</td>
                    <td><input class="easyui-filebox" name="imageFile" id="imageFile"
                               data-options="required:true,prompt:'Choose a file...'"/></td>
                </tr>
            </table>
        </form>
        <div style="text-align:center;padding:5px">
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">commit</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm('fileForm')">reset</a>
        </div>
        <script>
            function submitForm() {
                $('#fileForm').form('submit', {
                    url: "<%=basePath%>file/uploadVer2.do", onSubmit: function () {
                        return true;
                    }, success: function (result, a, b) {
                        var jsonResult = $.parseJSON(result);
                        alert(jsonResult.info);
                    }
                });

            }

            function clearForm(formId){
                $('#'+formId).form('clear');
            }
        </script>
    </div>
</div>

</body>
</html>