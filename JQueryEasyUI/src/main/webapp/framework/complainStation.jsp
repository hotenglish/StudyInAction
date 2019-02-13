<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
 	 <title>投诉异常</title>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/demo.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/fancybox/jquery.fancybox-1.3.4.css" ></link>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/fw.css" ></link>
	
	<script type="text/javascript" src="<%=basePath%>/easyui/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>/js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=basePath%>/easyui/jquery.easyui.min.js"></script>	
	<script type="text/javascript" src="<%=basePath%>/easyui/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="<%=basePath%>/js/common.js"></script>
	<script type="text/javascript" src="<%=basePath%>/js/JQuery-formui.js"></script>
	<script type="text/javascript" src="complainstation.js"></script>
</head>
<body>
	<!-- 查询条件 -->
	<div id="formdata" class="demo-info">
		<div class="demo-tip icon-tip"></div>
		<div style="margin-left:30px;">		
			日期范围：<input id="dateStart" onclick="WdatePicker();" type='text'/>-<input id="dateEnd" onclick="WdatePicker();" type='text'/>
			<br>
			账&nbsp;号&nbsp;名：  <input id="tbUsername" type='text'/>(这个测试模糊查询用户表，比如输入admin或者a都可以查询到数据，什么条件都没有则查询全部)
			
			<a href="#" id="btnQuery" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">查询</a>
			<a href="#" id="btnExport" class="easyui-linkbutton" data-options="iconCls:'icon-redo'">导出到Excel</a>
			
		</div>
	</div>

	<!-- 数据列表 -->
	<table id="dataview" ></table>
	
	<!-- 对话框 -->
	<div id="wedit" class="easyui-window" data-options="title:'领取',iconCls:'icon-save',modal:true,closed:true"
        style="width: 500px; height: 340px; padding: 5px;">
        <div class="easyui-layout" data-options="fit:true">
            <div data-options="region:'center',border:false" style="padding: 10px; background: #fff;
                border: 1px solid #ccc;">
                <iframe id="iframemain" scrolling="yes" frameborder="0" src="#" style="width: 100%;
                    height: 98%;">
                 
                    </iframe>
            </div>
        </div>
    </div>
</body>
</html>