package com.finance.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.finance.dao.GoalDAO;
import com.finance.model.Goal;
import com.finance.model.User;

@WebServlet("/addGoal")
public class GoalServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1️⃣ Get current user from session
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) {
            response.getWriter().println("❌ You must be logged in to add a goal.");
            return;
        }

        // 2️⃣ Get goal parameters from form
        String description = request.getParameter("description");
        double targetAmount = Double.parseDouble(request.getParameter("targetAmount"));
        String deadlineStr = request.getParameter("deadline");

        try {
            // Convert String to java.util.Date
            java.util.Date deadline = new SimpleDateFormat("yyyy-MM-dd").parse(deadlineStr);

            // 3️⃣ Create Goal object
            Goal goal = new Goal(currentUser.getUserId(), description, targetAmount, deadline, false);

            // 4️⃣ Save to DB
            GoalDAO dao = new GoalDAO();
            boolean success = dao.addGoal(goal);

            // 5️⃣ Response
            if (success) {
                response.getWriter().println("✔ Goal added successfully!");
            } else {
                response.getWriter().println("❌ Failed to add goal.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("❌ Invalid date format or input error.");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.getWriter().println("GoalServlet is running.");
    }
}
