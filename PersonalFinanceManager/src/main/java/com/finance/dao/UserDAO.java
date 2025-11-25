package com.finance.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.finance.db.DBConnection;
import com.finance.model.User;

public class UserDAO {

    // INSERT User → Register
    public boolean registerUser(User user) {
        boolean result = false;

        try {
            Connection conn = DBConnection.getConnection();
            String sql = "INSERT INTO users(name, email, password, role) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getRole());

            int rows = ps.executeUpdate();
            if (rows > 0) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    // LOGIN → Validate email + password
    public User login(String email, String password) {
        User user = null;

        try {
            Connection conn = DBConnection.getConnection();
            String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user = new User(
                        rs.getInt("user_id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("role")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    // GET USER BY EMAIL  → Useful for Admin / display profile etc.
    public User getUserByEmail(String email) {
        User user = null;

        try {
            Connection conn = DBConnection.getConnection();
            String sql = "SELECT * FROM users WHERE email = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user = new User(
                        rs.getInt("user_id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("role")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }
}
