<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.finance.model.User"%>
<%@ page import="com.finance.dao.ExpenseDAO"%>
<%@ page import="com.finance.model.Expense"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Expenses</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
<%
    if(session == null || session.getAttribute("currentUser") == null){
        response.sendRedirect("index.jsp");
        return;
    }
    User user = (User) session.getAttribute("currentUser");

    ExpenseDAO expenseDAO = new ExpenseDAO();
    List<Expense> expenses = expenseDAO.getExpensesByUser(user.getUserId());
%>

<h2>Expenses</h2>

<!-- Add Expense Form -->
<form action="Expenses" method="post">
    Amount: <input type="number" step="0.01" name="amount" required>
    Category: <input type="text" name="category" required>
    Date: <input type="date" name="expenseDate" required>
    Description: <input type="text" name="description">
    <input type="submit" value="Add Expense">
</form>

<!-- Expenses Table -->
<h3>All Expenses</h3>
<table border="1" width="80%" cellpadding="5" cellspacing="0">
    <tr>
        <th>ID</th>
        <th>Amount (₹)</th>
        <th>Category</th>
        <th>Date</th>
        <th>Description</th>
        <th>Action</th>
    </tr>
<%
    for(Expense e : expenses){
%>
    <tr>
        <td><%= e.getId() %></td>
        <td><%= e.getAmount() %></td>
        <td><%= e.getCategory() %></td>
        <td><%= e.getExpenseDate() %></td>
        <td><%= e.getDescription() %></td>
        <td class="action-cell">
    <form action="DeleteExpense" method="post" style="display:inline;">
        <input type="hidden" name="expenseId" value="<%= e.getId() %>">
        <input type="submit" class="btn-delete" value="Delete"
            onclick="return confirm('Are you sure you want to delete this expense?');">
    </form>
</td>
    </tr>
<%
    }
%>
</table>

<p><a href="dashboard.jsp">Back to Dashboard</a></p>
</body>
</html>
