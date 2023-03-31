<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>로그인페이지</title>
</head>
<body>
<h1>로그인 페이지</h1>
<hr/>
<form action="/user/login.do" method="post">
    <input type="text" name="username" placeholder="Enter User Name" /><br/>
    <input type="text" name="password" placeholder="Enter Password" /><br/>
    <button type="submit">로그인</button>
</form>
</body>
</html>