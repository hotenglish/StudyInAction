 <%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
 <%
String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>角色管理</title>
	
	<link rel="stylesheet" type="text/css" href="../easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="../easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="../css/demo.css">
	<link rel="stylesheet" type="text/css" href="../css/fw.css" ></link>
	
	<script type="text/javascript" src="../easyui/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="../easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="../easyui/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="../js/common.js"></script>
	<script type="text/javascript" src="../js/JQuery-formui.js"></script>
	<script type="text/javascript" src="fileconfirm.js"></script>
	
</head>
<body>

	<div data-options="fit:true"  >
		<!-- 内容栏 -->
		<div class="editcontent" 
			style="padding:10px;background:#fff;border:1px solid #ccc;height:200px;">

			<div id="maindata" >
				
				<table class="table table-hover table-condensed">
					
					<tr>
						<th>请选择要上传的文件</th>
						<td colspan="3">
						<p>
						<form name="demoForm" id="demoForm" method="post" enctype="multipart/form-data" action="javascript: uploadAndSubmit();">
							<p>文件名称: <input type="file" name="file" /></p>
							<p><input type="submit" value="上传" /></p>
						</form>
						<div>进度 (in Bytes): <span id="bytesRead"></span> / <span id="bytesTotal"></span><label id="lbls"></label></div>
						</p>
						
	  <script type="text/javascript">
	 <!--
		function uploadAndSubmit() {
			var form = document.forms["demoForm"];
			
			if (form["file"].files.length > 0)
			{
				var file = form["file"].files[0];
				
				// try sending
				var reader = new FileReader();
				
				reader.onloadstart = function() {
					console.log("onloadstart");
					
	            document.getElementById("bytesTotal").textContent = file.size;
				}
				
				reader.onprogress = function(p) {
					console.log("onprogress");
					document.getElementById("bytesRead").textContent = p.loaded;
				}
				
				reader.onload = function() {
					console.log("load complete");
				}
				
				reader.onloadend = function() {
					if (reader.error) {
						console.log(reader.error);
					} else {
						document.getElementById("bytesRead").textContent = file.size;
						var xhr = new XMLHttpRequest();
						xhr.open(/* method */ "POST", /* target url */ "<%=path%>/pcard/uploadfile.action?fileName=" + file.name /*, async, default to true */);
                  xhr.overrideMimeType("application/octet-stream");
						xhr.sendAsBinary(reader.result);
						
						xhr.onreadystatechange = function() {
							if (xhr.readyState == 4) {
								if (xhr.status == 200) {
									console.log("upload complete");
									console.log("response: " + xhr.responseText);
									var ff = xhr.getResponseHeader("serpath");
									$('#serpath').val(ff);
									$('#lbls').text("上传成功！");
								}
							}
						}
					}
					
				}
					reader.readAsBinaryString(file);
			}
			else
			{
				alert ("请先选择要上传的文件.");
			}
		}
		
	  // -->
	 </script>
				<input type="hidden" id="serpath"/>
						</td>
					</tr>
				</table>
				
			</div>

		</div>

		<!-- 保存按钮栏 -->
		<div style="text-align:center;padding:5px 0;">
			<a id="btnSave" class="easyui-linkbutton" data-options="iconCls:'icon-ok'"  >确 定</a> 
			&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;
			<a id="btnCancel" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'"  >取 消</a>
		</div>
	</div>

	

</body>
</html>