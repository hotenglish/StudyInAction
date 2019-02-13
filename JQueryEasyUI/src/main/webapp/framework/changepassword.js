(function($) {
	$(function() {
	
		$('#btnSave').click(function(){

			//表单验证
			if(!$('#maindata').form('validate')){
				$.messager.alert("提示消息", "信息填写不完整!", "info");
				return;
			}
			
			//新密码是否正确
			var newpassword = $('#NEWPASSWORD').val();
			var newpassword1 = $('#NEWPASSWORD1').val();
			if(newpassword!=newpassword1){
				$.messager.alert("提示消息", "两次填写的新密码不同，请重新填写!", "info");
				return;
			}
			
			//主表
	     	var maindata =$('#maindata').toJsonString();
	     	
	     	$.ajax({
				type : "post",
				url : "USERChangepassword.action",
				dataType: "json",
				data : maindata,
				contentType : "text/html",
				error : function(event,request, settings) {
					//请求失败时调用函数。   
					$.messager.alert("提示消息", "请求失败!", "info");
				},
				success : function(data) {
					$.messager.alert("提示消息", data.msg, "info");
				}
			});	
			
		});
		
	});
})(window.jQuery);