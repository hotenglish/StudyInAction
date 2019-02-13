(function($) {

	function getPath(obj) {
		if (obj) {
			if (window.navigator.userAgent.indexOf("MSIE") >= 1) {
				obj.select();
				return document.selection.createRange().text;
			}
			else if (window.navigator.userAgent.indexOf("Firefox") >= 1) {
				var ip = document.getElementById("filename");  
				if (ip.files) {  
				//ffx3 - try to have access to full path  
					return obj.files.item(0).getAsDataURL(); 
				};  
				alert(ip.value);
				return ip.value;  
			}
			return obj.value;
		}
	}
	
	
	
	function readFileFirefox(fileBrowser) { //FF浏览器
	    try { 
	        netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect"); 
	    }  
	    catch (e) { 
	        alert('路径错误!'); 
	        return; 
	    }

	    var fileName=fileBrowser.value; 
	    var file = Components.classes["@mozilla.org/file/local;1"] 
	        .createInstance(Components.interfaces.nsILocalFile); 
	    try { 
	        file.initWithPath( fileName.replace(/\//g, "\\\\") ); 
	    } 
	    catch(e) { 
	        if (e.result!=Components.results.NS_ERROR_FILE_UNRECOGNIZED_PATH) throw e; 
	        return; 
	    }

	    if ( file.exists() == false ) { 
	        alert("File '" + fileName + "' not found."); 
	        return; 
	    } 
	    alert(file.path); 
	}


	

	$(function() {

		//获取参数
		var state = JUDGE.getURLParameter("state");
		var updatetype = JUDGE.getURLParameter("updatetype");
		$("#btnCancel").click(function() {
			window.parent.$('#wedit').window('close');
		});

		$("#btnSave").click(function() {
			var filepath = $('#serpath').val();
			///alert(filepath);
			if (filepath== null || filepath == "") {
				$.messager.alert("提示消息", "文件正在上传，等待上传成功后在进行此操作!", "info");
				return;
			}

			var filename = escape(encodeURIComponent(filepath));
			$.ajax({
				type : "post",
				url : "../pcard/readPcardCsv.action?csvpath=" + filename + "&state=" + state+"&updatetype="+updatetype,
				dataType : "json",
				//data : maindata,
				contentType : "text/html",
				error : function(event, request, settings) {
					//请求失败时调用函数。   
					$.messager.alert("提示消息", "请求失败!", "info");
				},
				success : function(data) {
					//if (data.status=="F") {
						//$.messager.alert("提示消息", "数据读取成功，没有匹配更新任何数据，请核查数据后在进行此操作!", "info");

					//} else {
						$.messager.alert("提示消息", "数据读取成功，成功更新["+data.returncount+"]条记录!，状态：["+data.stat+"]", "info", function() {
							window.parent.$('#dataview').datagrid('reload');
							window.parent.$('#wedit').window('close');
						});
					//}
				}
			});

		});

	});
})(window.jQuery);