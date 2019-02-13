
(function($) {
	$(function() {

		$(function() {
			//ajax查询数据			
			var url="ROLEFind.action";
			$('#dataview').datagrid({
				nowrap : false,
				fitColumns : true,
				rownumbers : true,
				pageList : [ 20, 50,100 ],
				singleSelect : true,//是否单选  
				collapsible : false,//是否可折叠的   
				url : url,				
				columns : [ [ {
					field : 'FULLNAME',
					title : '名称',
					width : 200
				},{
					field : '操作',
					title : '操作',
					width : 50,
					// 添加超级链，并将来文号作为参数传入
					formatter : function(value, row, index) {
						var s = ' <span  onclick="dataEdit(\''+row.ICODE+'\');" style="color:blue;">编辑</span>'
							+'| <span  onclick="dataDelete(\''+row.ICODE+'\');" style="color:blue;">删除</span>'
							+'| <span  onclick="dataPermission(\''+row.ICODE+'\');" style="color:blue;">分配权限</span>';
						return s ;
					}} ] ],
				toolbar : [ {
					id : 'btnadd',
					text : '新增',
					iconCls : 'icon-add',
					handler : function() {
						$("#iframemain").attr("src", "roleedit.jsp");
			            $("#wedit").window('open');
					}
				}, {
					id : 'btncut',
					text : '刷新',
					iconCls : 'icon-reload',
					handler : function() {
						//$('#btnsave').linkbutton('enable');
						//alert('cut')
						//getSelected();
						$('#dataview').datagrid('reload');
					}
				} ]
			});
		});		
		
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
	    		url : "ROLEDelete.action?maindatauuid="+icode,					
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

