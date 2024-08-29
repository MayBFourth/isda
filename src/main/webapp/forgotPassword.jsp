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
<div class="container">
    <h2>Forgot Password</h2>
    <form action="ForgotPasswordServlet" method="post">
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required><br><br>
        <input type="submit" value="Submit">
    </form>

    <%-- Display error message if any --%>
    <%
        String error = (String) request.getAttribute("error");
        if (error != null) {
            out.println("<p class='error'>" + error + "</p>");
        }
    %>
</div>
</body>
</html>
