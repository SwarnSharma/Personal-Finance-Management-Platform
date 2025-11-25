package com.finance.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.finance.dao.UserDAO;
import com.finance.model.User;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1️⃣ Get form parameters
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role"); // ADMIN or USER

        // 2️⃣ Create User object
        User user = new User(name, email, password, role);

        // 3️⃣ Save to DB
        UserDAO dao = new UserDAO();
        boolean success = dao.registerUser(user);

        // 4️⃣ Response
        if (success) {
            response.getWriter().println("✔ User registered successfully!");
        } else {
            response.getWriter().println("❌ Registration failed! User may already exist.");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.getWriter().println("RegisterServlet is running.");
    }
}
