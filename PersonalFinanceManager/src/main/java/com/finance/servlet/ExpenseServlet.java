package com.finance.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.finance.dao.ExpenseDAO;
import com.finance.model.Expense;
import com.finance.model.User;

@WebServlet("/Expenses")
public class ExpenseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1️⃣ Get current user from session
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) {
            response.getWriter().println("❌ You must be logged in to add expense.");
            return;
        }

        // 2️⃣ Get expense parameters from form
        double amount = Double.parseDouble(request.getParameter("amount"));
        String category = request.getParameter("category");
        String dateStr = request.getParameter("expenseDate");
        String description = request.getParameter("description");

        try {
            // Convert String to java.util.Date
            java.util.Date expenseDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);

            // 3️⃣ Create Expense object
            Expense expense = new Expense(currentUser.getUserId(), amount, category, expenseDate, description);

            // 4️⃣ Save to DB
            ExpenseDAO dao = new ExpenseDAO();
            boolean success = dao.addExpense(expense);

            // 5️⃣ Response
            if (success) {
                //response.getWriter().println("✔ Expense added successfully!");
                if (success) {
                    response.sendRedirect("expenses.jsp");
                } else {
                    response.getWriter().println("❌ Failed to add expense.");
                }

                
            } else {
                response.getWriter().println("❌ Failed to add expense.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("❌ Invalid date format or input error.");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.getWriter().println("ExpenseServlet is running.");
    }
}
