<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.finance.model.User" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="css/style.css">
    <meta charset="UTF-8">
    <title>Add Budget</title>
</head>
<body>
<%
    if (session == null || session.getAttribute("currentUser") == null) {
        response.sendRedirect("index.jsp");
        return;
    }
    User user = (User) session.getAttribute("currentUser");
%>
    <h2>Add Budget</h2>
    <form action="addBudget" method="post">
        Category: <input type="text" name="category" required><br><br>
        Amount: <input type="number" step="0.01" name="amount" required><br><br>
        Start Date: <input type="date" name="startDate" required><br><br>
        End Date: <input type="date" name="endDate" required><br><br>
        <input type="submit" value="Add Budget">
    </form>
    <p><a href="dashboard.jsp">Back to Dashboard</a></p>
</body>
</html>
