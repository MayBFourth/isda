<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Forgot Password</title>
    <style>
        .container {
            width: 300px;
            margin: 0 auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        .error {
            color: red;
        }
    </style>
</head>
<body>
<%
    String email = request.getParameter("email");
%>
<div class="container" style="width: 80%;">
    <h2>Reset Password</h2>
    <form action="ResetPasswordServlet" method="post">
        <label for="email">Email:</label>
        <span id="email"><%= (email != null ? email : "Không tìm thấy email") %></span>
        <br><br>
        <label for="newPass">New Password:</label>
        <input type="password" id="newPass" name="newPass" required><br><br>

        <label for="confirmPass">Confirm Password:</label>
        <input type="password" id="confirmPass" name="confirmPass" required><br><br>

        <input type="submit" value="Submit">
    </form>

    <%-- Display error message if any --%>

</div>
</body>
</html>
