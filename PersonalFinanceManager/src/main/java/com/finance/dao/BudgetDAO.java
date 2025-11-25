package com.finance.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.finance.db.DBConnection;
import com.finance.model.Budget;

public class BudgetDAO {

    // Add new budget
    public boolean addBudget(Budget budget) {
        boolean result = false;
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "INSERT INTO budgets(user_id, category, amount, start_date, end_date) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, budget.getUserId());
            ps.setString(2, budget.getCategory());
            ps.setDouble(3, budget.getAmount());
            ps.setDate(4, new Date(budget.getStartDate().getTime()));
            ps.setDate(5, new Date(budget.getEndDate().getTime()));

            int rows = ps.executeUpdate();
            if (rows > 0) result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public double getTotalBudgetByUser(int userId) {
        double total = 0;
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "SELECT SUM(amount) as total FROM budgets WHERE user_id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                total = rs.getDouble("total");
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return total;
    }
     

    // Get all budgets of a user
    public List<Budget> getBudgetsByUser(int userId) {
        List<Budget> list = new ArrayList<>();
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "SELECT * FROM budgets WHERE user_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Budget b = new Budget(
                        rs.getInt("budget_id"),
                        rs.getInt("user_id"),
                        rs.getString("category"),
                        rs.getDouble("amount"),
                        rs.getDate("start_date"),
                        rs.getDate("end_date")
                );
                list.add(b);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
