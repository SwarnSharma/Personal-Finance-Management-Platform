package com.finance.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.finance.db.DBConnection;
import com.finance.model.Goal;

public class GoalDAO {

    // Add new goal
    public boolean addGoal(Goal goal) {
        boolean result = false;
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "INSERT INTO goals(user_id, description, target_amount, deadline, achieved) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, goal.getUserId());
            ps.setString(2, goal.getDescription());
            ps.setDouble(3, goal.getTargetAmount());
            ps.setDate(4, new Date(goal.getDeadline().getTime()));
            ps.setBoolean(5, goal.isAchieved());

            int rows = ps.executeUpdate();
            if (rows > 0) result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    // Get all goals of a user
    public List<Goal> getGoalsByUser(int userId) {
        List<Goal> list = new ArrayList<>();
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "SELECT * FROM goals WHERE user_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Goal g = new Goal(
                        rs.getInt("goal_id"),
                        rs.getInt("user_id"),
                        rs.getString("description"),
                        rs.getDouble("target_amount"),
                        rs.getDate("deadline"),
                        rs.getBoolean("achieved")
                );
                list.add(g);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
