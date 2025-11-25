package com.finance.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.finance.db.DBConnection;
import com.finance.model.Expense;

public class ExpenseDAO {

    // Add new expense
    public boolean addExpense(Expense expense) {
        boolean result = false;
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "INSERT INTO expenses(user_id, amount, category, expense_date, description) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, expense.getUserId());
            ps.setDouble(2, expense.getAmount());
            ps.setString(3, expense.getCategory());
            ps.setDate(4, new Date(expense.getExpenseDate().getTime()));
            ps.setString(5, expense.getDescription());

            int rows = ps.executeUpdate();
            if (rows > 0) result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    // Get total expenses of a user
    public double getTotalExpensesByUser(int userId) {
        double total = 0;
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "SELECT SUM(amount) as total FROM expenses WHERE user_id=?";
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
    
    
 // Delete expense by ID
    public boolean deleteExpense(int expenseId) {
        boolean result = false;
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "DELETE FROM expenses WHERE expense_id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, expenseId);

            int rows = ps.executeUpdate();
            if (rows > 0) result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    

    // Get all expenses of a user
    public List<Expense> getExpensesByUser(int userId) {
        List<Expense> list = new ArrayList<>();
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "SELECT * FROM expenses WHERE user_id = ? ORDER BY expense_date DESC";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Expense e = new Expense(
                        rs.getInt("expense_id"),
                        rs.getInt("user_id"),
                        rs.getDouble("amount"),
                        rs.getString("category"),
                        rs.getDate("expense_date"),
                        rs.getString("description")
                );
                list.add(e);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
