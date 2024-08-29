<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>Change Password</title>
</head>
<body>
<h2>Change Password</h2>
<form action="ChangePasswordServlet" method="post">
  Username: <input type="text" name="username" required><br>
  Current Password: <input type="password" name="currentPassword" required><br>
  New Password: <input type="password" name="newPassword" required><br>
  <input type="submit" value="Change Password">
</form>
</body>
</html>
