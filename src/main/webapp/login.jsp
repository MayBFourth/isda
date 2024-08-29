<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>Login</title>
</head>
<body>
<h2>Login</h2>
<form action="login" method="post">
  Username: <input type="text" name="username" required><br>
  Password: <input type="password" name="password" required><br>
  <input type="submit" value="Login">
</form>
<a href="forgotPassword.jsp">
  <button type="button">Change Password</button>
</a>
<%
  String error = (String) request.getAttribute("error");
  if (error != null) {
    out.print("<p style='color:red;'>" + error + "</p>");
  }
%>
</body>
</html>
