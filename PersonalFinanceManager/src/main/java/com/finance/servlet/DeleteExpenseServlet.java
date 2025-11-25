package com.finance.servlet;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.finance.dao.ExpenseDAO;

@WebServlet("/DeleteExpense")
public class DeleteExpenseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int expenseId = Integer.parseInt(request.getParameter("expenseId"));
            ExpenseDAO dao = new ExpenseDAO();
            boolean deleted = dao.deleteExpense(expenseId);

            if (deleted) {
                response.sendRedirect("expenses.jsp"); // refresh page
            } else {
                response.getWriter().println("❌ Failed to delete expense.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("❌ Error deleting expense.");
        }
    }
}
