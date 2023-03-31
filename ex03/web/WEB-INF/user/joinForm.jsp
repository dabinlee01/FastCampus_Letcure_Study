<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>회원가입페이지</title>
</head>
<body>
<h1>회원가입 페이지</h1>
<hr/>
<form action="/user/join.do" method="post">
    <input type="text" name="username" placeholder="Enter UserName" /><br/>
    <%--모두 구현 시 <input type="password" id="password" name="password">--%>
    <%--으로 다시 해보기--%>
    <input type="text" name="password" placeholder="Enter Password" /><br/>
    <input type="text" name="email" placeholder="Enter E-mail" /><br/>
    <button type="submit">회원가입</button>
</form>
</body>
</html>