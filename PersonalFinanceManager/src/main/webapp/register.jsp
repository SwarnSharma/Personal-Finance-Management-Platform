<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/style.css">
<meta charset="UTF-8">
<title>User Registration</title>
</head>
<body>
<h2>Register</h2>
<form action="register" method="post">
    Name: <input type="text" name="name" required><br><br>
    Email: <input type="email" name="email" required><br><br>
    Password: <input type="password" name="password" required><br><br>
    Role: 
    <select name="role" required>
        <option value="USER">User</option>
        <option value="ADMIN">Admin</option>
    </select><br><br>
    <input type="submit" value="Register">
</form>
<p>Already have an account? <a href="index.jsp">Login here</a></p>
</body>
</html>
