package com.finance.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.finance.dao.BudgetDAO;
import com.finance.model.Budget;
import com.finance.model.User;

@WebServlet("/addBudget")
public class BudgetServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1️⃣ Get current user from session
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) {
            response.getWriter().println("❌ You must be logged in to add a budget.");
            return;
        }

        // 2️⃣ Get budget parameters from form
        String category = request.getParameter("category");
        double amount = Double.parseDouble(request.getParameter("amount"));
        String startDateStr = request.getParameter("startDate");
        String endDateStr = request.getParameter("endDate");

        try {
            // Convert String to java.util.Date
            java.util.Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(startDateStr);
            java.util.Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(endDateStr);

            // 3️⃣ Create Budget object
            Budget budget = new Budget(currentUser.getUserId(), category, amount, startDate, endDate);

            // 4️⃣ Save to DB
            BudgetDAO dao = new BudgetDAO();
            boolean success = dao.addBudget(budget);

            // 5️⃣ Response
            if (success) {
                response.getWriter().println("✔ Budget added successfully!");
            } else {
                response.getWriter().println("❌ Failed to add budget.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("❌ Invalid date format or input error.");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.getWriter().println("BudgetServlet is running.");
    }
}
