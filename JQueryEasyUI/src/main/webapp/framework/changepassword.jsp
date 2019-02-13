<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>修改密码</title>
	
	<link rel="stylesheet" type="text/css" href="../easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="../easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="../css/demo.css">
	<link rel="stylesheet" type="text/css" href="../css/fw.css" ></link>
	
	<script type="text/javascript" src="../easyui/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="../easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="../easyui/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="../js/common.js"></script>
	<script type="text/javascript" src="../js/JQuery-formui.js"></script>
	<script type="text/javascript" src="changepassword.js"></script>
	
</head>
<body>

	<div data-options="fit:true"  >
		<!-- 内容栏 -->
		<div class="editcontent" 
			style="padding:10px;background:#fff;border:1px solid #ccc;height:200px;">

			<div id="maindata" >
			
				<!-- 不需要显示的字段 -->
				<div style="display:none;">
					<input id="ICODE" type="text" >
				</div>
				
				<table class="table table-hover table-condensed" style="margin:50px auto;">
					<tr>
						<th>原密码</th>
						<td><input id="OLDPASSWORD" type="text" class="easyui-validatebox span5" data-options="required:true">
						</td>
					</tr>
					<tr>
						<th>新密码</th>
						<td><input id="NEWPASSWORD" type="text" class="easyui-validatebox span5" data-options="required:true">
						</td>
					</tr>
					<tr>
						<th>确认新密码</th>
						<td><input id="NEWPASSWORD1" type="text" class="easyui-validatebox span5" data-options="required:true">
						</td>
					</tr>
					
				</table>
				
			</div>

		</div>

		<!-- 保存按钮栏 -->
		<div style="text-align:center;padding:5px 0;">
			&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;
			<a id="btnSave" class="easyui-linkbutton" data-options="iconCls:'icon-ok'"  >保 存</a> 
			
		</div>
	</div>

	

</body>
</html>