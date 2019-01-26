<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<!DOCTYPE HTML>
<html>
<head>
    <script type="text/javascript">
        var xmlHttp;
        function createXMLHttpRequest() {
            if (window.ActiveXObject) {
                xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
            } else if (window.XMLHttpRequest) {
                xmlHttp = new XMLHttpRequest();
            }
        }

        function send() {
            /* 	createXMLHttpRequest();
                //var url= "AddHandler.ashx?num1="+document.getElementById("num1").value+"&num2="+document.getElementById("num2").value;
                var url = "../other/1.txt?name="+"zhangsan"+"&value="+"one";
                xmlHttp.open("GET", url, true);
                xmlHttp.onreadystatechange=callback;
                xmlHttp.send(null); */

            //POST方式
            createXMLHttpRequest();
            var params = "name=" + "zhangsan" + "&value=" + "one";
            var url = "../other/1.txt";
            xmlHttp.open("POST", url, true);
            xmlHttp.onreadystatechange = callback;
            xmlHttp.send(params)
        }

        function callback() {
            if (xmlHttp.readyState == 4) {
                var str = xmlHttp.responseText;
                var strpin = "<p>" + str + "</p>";
                var strall = document.getElementById("send");
                strall.innerHTML = strpin;
                alert(xmlHttp.responseText);
            }
        }

    </script>
</head>
<body>
<h1>XMLHttpRequest实例</h1>
<input type="button" size="10" value="请求" onClick="send();"/>
<div id="send"></div>
</body>
</html>
