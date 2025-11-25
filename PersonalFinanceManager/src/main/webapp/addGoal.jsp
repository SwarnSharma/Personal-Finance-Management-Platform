<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.finance.model.User" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/style.css">
<meta charset="UTF-8">
<title>Add Goal</title>
</head>
<body>
<%
    if(session == null || session.getAttribute("currentUser") == null){
        response.sendRedirect("index.jsp");
        return;
    }
    User user = (User) session.getAttribute("currentUser");
%>

<h2>Add Financial Goal</h2>
<form action="addGoal" method="post">
    Description: <input type="text" name="description" required><br><br>
    Target Amount: <input type="number" step="0.01" name="targetAmount" required><br><br>
    Deadline: <input type="date" name="deadline" required><br><br>
    <input type="submit" value="Add Goal">
</form>

<p><a href="dashboard.jsp">Back to Dashboard</a></p>
</body>
</html>
