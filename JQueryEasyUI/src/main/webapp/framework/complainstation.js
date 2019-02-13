
(function($) {
	var lastIndex;
	function bindata()
	{
		//获取查询条件
		var condition =$("#formdata").toJsonString();
		condition = escape(encodeURIComponent(condition));
		
		//ajax查询数据			
		var url="T_CARDORDER.action";
		if(condition && condition.length>0)
			url += "?condition="+condition;
		//ajax查询数据			
		$('#dataview').datagrid({
			nowrap : false,
			fitColumns : true,
			pagination:true,
			pageList : [ 20, 50,100 ],
			collapsible : false,//是否可折叠的   
			url : url,				
			frozenColumns : [ [ {
				field : 'ck',
				checkbox : true
			} ] ],			
			columns : [ [ {
				field : 'CARDID',
				title : '卡号',
				width : 100,
				editor:{
					type:'text'
				}
			},{
				field : 'CREATE_DATE',
				title : '订单时间',
				width : 100,
				editor:{
					type:'text'
				}
			},{
				field : 'MSISDN',
				title : '推荐号码',
				width : 100,
				editor:{
					type:'text'
				}
			},{
				field : 'REALEAT_TEL',
				title : '联系人电话',
				width : 100,
				editor:{
					type:'text'
				}
			},{
				field : 'RELEAT_NAME',
				title : '联系人姓名',
				width : 100,
				editor:{
					type:'text'
				}
			},{
				field : 'PEOPLEID',
				title : '身份证',
				width : 100,
				editor:{
					type:'text'
				}
			},{
				field : 'PAGE',
				title : '来源',
				width : 100,
				editor:{
					type:'text'
				}
			},{
				field : 'VERIFIER',
				title : '审核人',
				width : 100,
				editor:{
					type:'text'
				}
			},{
				field : 'VERIFIER_DATE',
				title : '审核完时间',
				width : 100,
				editor:{
					type:'datebox'
				}
			},{
				field : 'OPENCARDER',
				title : '开卡人',
				width : 100,
				editor:{
					type:'text'
				}
			},{
				field : 'OPEN_DATE',
				title : '开卡完成时间',
				width : 100,
				editor:{
					type:'datebox'
				}
			},{
				field : 'SENDCARDER',
				title : '发货人',
				width : 100,
				editor:{
					type:'text'
				}
			},{
				field : 'SENDDATE',
				title : '发货时间',
				width : 100,
				editor:{
					type:'datebox'
				}
			},{
				field : 'STAT',
				title : '状态',
				hidden : true
			},{
				field : '操作',
				title : '操作',
				width : 100,
				// 添加超级链，并将来文号作为参数传入
				formatter : function(value, row, index) {
					var s = '<span  onclick="dataDelete(\''+row.PEOPLEID+'\');" style="color:blue;">  删  除   </span>';
					return s ;
				}} ] ],
			toolbar : [ {
				id : 'btnadd',
				text : '发卡',
				iconCls : 'icon-undo',
				handler : function() {
					$("#iframemain").attr("src", "fileconfirm.jsp");
		            $("#wedit").window('open');
				}
			}, {
				id : 'btncut',
				text : '刷新',
				iconCls : 'icon-reload',
				handler : function() {
					$('#dataview').datagrid('reload');
				}
			} ],
			onBeforeLoad : function() {
				$(this).datagrid('rejectChanges');
			},
			onClickRow : function(rowIndex) {
				if (lastIndex != rowIndex) {
					$('#dataview').datagrid('endEdit', lastIndex);
					$('#dataview').datagrid('beginEdit', rowIndex);
				}
				lastIndex = rowIndex;
			}
		});
	}
	
	$(function() {
	
		//查询按钮
		$("#btnQuery").click(function(){			
			bindata();
		});	
		
		bindata();//默认加载列表
	
		//窗体改变时
		$(window).resize(function(){
			$('#dataview').datagrid('resize');
		});
//		
	});
})(window.jQuery);

function dataPermission(icode){
	$("#iframemain").attr("src", "permissionedit.jsp?icode="+icode);
    $("#wedit").window('open');
}

function dataEdit(icode){
	$("#iframemain").attr("src", "roleedit.jsp?icode="+icode);
    $("#wedit").window('open');
}

function dataDelete(icode){	
	$.messager.confirm('询问','确定要删除吗?',function(r){   
	    if (r){   
	    	$.ajax( {
	    		type : "post",
	    		url : "deleteRow.action?cardid="+icode,					
	    		contentType : "text/html",
	    		error : function(event,request, settings) {
	    			//请求失败时调用函数。   
	    			$.messager.alert("提示消息", "请求失败!", "info");
	    		},
	    		success : function(data) { 
	    			//请求成功后回调函数。
	    			//$.messager.alert('操作提示', '数据删除成功!');
	    			//clearSelect('datagrid');
	    			//flashTable("datagrid");
	    			$('#dataview').datagrid('reload');
	    		}
	    	});
	    }   
	}); 
}

