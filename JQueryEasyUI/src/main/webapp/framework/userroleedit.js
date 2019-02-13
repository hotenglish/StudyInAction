(function($) {
	$(function() {
		//获取参数 角色id
		var icode = JUDGE.getURLParameter("icode");
			
		//加载这个角色已有权限到树
		$('#datagrid').datagrid({
			width:350,		   
			idField:'ICODE',
			url:'USERROLEFindByUUID.action?maindatauuid='+JUDGE.getURLParameter("icode"),
			columns:[[
			    {field:'checked',checkbox:true},
		        {field:'FULLNAME',title:'名称'}
			]],
			onLoadSuccess:function(data){
				var rows = $(this).datagrid('getRows');
				for ( var i = 0; i < rows.length; i++) {
					if(rows[i].checked==true){						
						$(this).datagrid('selectRow',i);
					}	
				}
			}
		});
		
		//保存
		$("#btnSave").click(function(){
			
			//获取选中的菜单（多个中间用逗号隔开）
			var nodes = $('#datagrid').datagrid('getSelections');
			var roles = '';
			for(var i=0; i<nodes.length; i++){
				if (roles != '') roles += '|';
				roles += nodes[i].ICODE;
			}
			
			//获取参数中的角色icode
			var usericode = JUDGE.getURLParameter("icode");
			
			//拼装数据
			var maindata = "{'USERICODE':'"+usericode+"','ROLES':'"+roles+"'}";
			
			//保存
	     	$.ajax( {
				type : "post",
				url : "USERROLESave.action",
				dataType: "json",
				data : maindata,
				contentType : "text/html",
				error : function(event,request, settings) {
					//请求失败时调用函数。   
					$.messager.alert("提示消息", "请求失败!", "info");
				},
				success : function(data) { 
					$.messager.alert("提示消息", "保存成功!", "info",function(){
						window.parent.$('#dataview').datagrid('reload');	
						window.parent.$('#wedit').window('close');		
					});
				}
			});	
			
		});
		
		//取消
		$("#btnCancel").click(function(){
			window.parent.$('#wedit').window('close');			
		});
		
	});
})(window.jQuery);