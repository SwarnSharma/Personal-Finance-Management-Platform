<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.finance.model.User"%>
<%@ page import="com.finance.dao.ExpenseDAO"%>
<%@ page import="com.finance.dao.BudgetDAO"%>
<%@ page import="com.finance.dao.GoalDAO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dashboard</title>
<link rel="stylesheet" href="css/style.css">
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body>
<%
    if(session == null || session.getAttribute("currentUser") == null){
        response.sendRedirect("index.jsp");
        return;
    }
    User user = (User) session.getAttribute("currentUser");

    // Get summary data
    ExpenseDAO expenseDAO = new ExpenseDAO();
    BudgetDAO budgetDAO = new BudgetDAO();
    GoalDAO goalDAO = new GoalDAO();

    double totalExpenses = expenseDAO.getTotalExpensesByUser(user.getUserId());
    double totalBudget = budgetDAO.getTotalBudgetByUser(user.getUserId());
    int totalGoals = goalDAO.getGoalsByUser(user.getUserId()).size();
%>

<h2>Welcome, <%= user.getName() %>!</h2>
<p>Role: <%= user.getRole() %></p>

<div class="summary">
    <h3>Summary</h3>
    <ul>
        <li>Total Expenses: ₹ <%= totalExpenses %></li>
        <li>Total Budget: ₹ <%= totalBudget %></li>
        <li>Total Goals: <%= totalGoals %></li>
    </ul>
</div>

<div class="chart-container">
    <canvas id="financeChart"></canvas>
</div>

<script>
    const ctx = document.getElementById('financeChart').getContext('2d');
    const financeChart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: ['Total Expenses', 'Total Budget'],
            datasets: [{
                label: '₹ Amount',
                data: [<%= totalExpenses %>, <%= totalBudget %>],
                backgroundColor: ['#FF6384', '#36A2EB']
            }]
        },
        options: {
            responsive: true,
            plugins: {
                legend: { display: false },
                title: { display: true, text: 'Finance Overview' }
            }
        }
    });
</script>

<h3>Actions</h3>
<ul>
    <li><a href="expenses.jsp">Add Expense</a></li>
    <li><a href="addBudget.jsp">Add Budget</a></li>
    <li><a href="addGoal.jsp">Add Goal</a></li>
    <li><a href="logout.jsp">Logout</a></li>
</ul>

</body>
</html>
