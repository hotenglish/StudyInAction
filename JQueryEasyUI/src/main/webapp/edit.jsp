<%@ page language="java" import="java.net.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>编辑例子</title>
	<link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="css/demo.css">
	<script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
	<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
	<script>
		var products = [
		    {productid:'FI-SW-01',name:'张三'},
		    {productid:'K9-DL-01',name:'李四'},
		    {productid:'RP-SN-01',name:'王五'},
		    {productid:'RP-LI-02',name:'赵六'},
		    {productid:'FL-DSH-01',name:'钱七'},
		    {productid:'FL-DLH-02',name:'黄八'},
		    {productid:'AV-CB-01',name:'郁闷'}
		];
		function productFormatter(value){
			for(var i=0; i<products.length; i++){
				if (products[i].productid == value) return products[i].name;
			}
			return value;
		}
		$(function(){
			var lastIndex;
			$('#tt').datagrid({
				toolbar:[{
					text:'增加',
					iconCls:'icon-add',//增加
					handler:function(){
						$('#tt').datagrid('endEdit', lastIndex);
						$('#tt').datagrid('appendRow',{
							itemid:'',
							productid:'',
							listprice:'',
							unitprice:'',
							attr1:'',
							status:'P'
						});
						lastIndex = $('#tt').datagrid('getRows').length-1;
						$('#tt').datagrid('selectRow', lastIndex);
						$('#tt').datagrid('beginEdit', lastIndex);
					}
				},'-',{
					text:'删除',
					iconCls:'icon-remove',//删除
					handler:function(){
						var row = $('#tt').datagrid('getSelected');
						if (row){
							var index = $('#tt').datagrid('getRowIndex', row);
							$('#tt').datagrid('deleteRow', index);
						}
					}
				},'-',{
					text:'保存',
					iconCls:'icon-save',//保存
					handler:function(){
						$('#tt').datagrid('acceptChanges');
						alert("可变值="+$('#tt').datagrid('productid'));
					}
				},'-',{
					text:'撤销',
					iconCls:'icon-undo',//撤销
					handler:function(){
						$('#tt').datagrid('rejectChanges');
					}
				},'-',{
					text:'拿到',
					iconCls:'icon-search',//拿到
					handler:function(){
						var rows = $('#tt').datagrid('getChanges');
						alert('changed rows: ' + rows.length + ' lines');
					}
				}],
				onBeforeLoad:function(){//载入之前
					$(this).datagrid('rejectChanges');
				},
				onClickRow:function(rowIndex){//单击
					if (lastIndex != rowIndex){
						$('#tt').datagrid('endEdit', lastIndex);
						$('#tt').datagrid('beginEdit', rowIndex);
					}
					lastIndex = rowIndex;
				}
			});
		});
	</script>
</head>
<body>
	<h2>编辑例子</h2>
	<div class="demo-info" style="margin-bottom:10px">
		<div class="demo-tip icon-tip"></div>
		<div>点击数据开始编辑</div>
	</div>
	
	<table id="tt" style="width:700px;height:auto"
			data-options="iconCls:'icon-edit',singleSelect:true,idField:'itemid',url:'datagrid_data2.json'"
			title="这是一个标题">
		<thead>
			<tr>
				<th data-options="field:'itemid',width:80">Item ID</th>
				<th data-options="field:'productid',width:100,formatter:productFormatter,
						editor:{
							type:'combobox',
							options:{
								valueField:'productid',
								textField:'name',
								data:products,
								required:true
							}
						}">订单</th>
				<th data-options="field:'listprice',width:80,align:'right',editor:{type:'numberbox',options:{precision:1}}">价格</th>
				<th data-options="field:'unitcost',width:80,align:'right',editor:'numberbox'">Cost</th>
				<th data-options="field:'attr1',width:250,editor:'text'">属性</th>
				<th data-options="field:'status',width:60,align:'center',editor:{type:'checkbox',options:{on:'P',off:''}}">状态</th>
			</tr>
		</thead>
	</table>
	
</body>
</html>