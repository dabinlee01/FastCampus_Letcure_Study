<%@ page import="java.util.List" %>
<%@ page import="shop.mtcoding.mvcapp.model.Board" %>  // 바꿔야함
<%@ page import="shop.mtcoding.mvcapp.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>사용자</title>
</head>
<body>
<h1>사용자 목록</h1>
<hr/>
<table border="1">
    <thead>
    <tr>
        <th>번호</th>
        <th>이름</th>
        <th>e-mail</th>
    </tr>
    </thead>
    <tbody>
    <%
        List<User> userList = (List<User>) request.getAttribute("userList");
        for(int i = 0;i < userList.size();i++) {
    %>
    <tr>
        <td><%=userList.get(i).getId()%></td>
        <td><%=userList.get(i).getUsername()%></td>
        <td><%=userList.get(i).getEmail()%></td>
    </tr>
    <%
        }
    %>

    </tbody>
</table>
</body>
</html>>