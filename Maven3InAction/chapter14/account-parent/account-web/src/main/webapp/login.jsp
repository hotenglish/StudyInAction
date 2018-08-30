<%@ page contentType="text/html; charset=gb2312" language="java" %>
<html>
<head>
    <script type="text/javascript" src="scripts/${client.js}"></script>
    <link type="text/css" rel="stylesheet" href="css/${client.theme}"><link>
</head>

<body onload="printMyScript()">
<div class="text-field">
    <h2>Account Login</h2>
    <form name="login" action="login" method="post">
        <label>Account ID:</label><input type="text" name="id"></input><br/>
        <label>Password:</label><input type="password" name="password"></input><br/>
        <button>Done</button>
    </form>
</div>
</body>
</html>