package com.cm.util;

import java.sql.*;

public class BaseDao {
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    private void getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/booksys";
            conn = DriverManager.getConnection(url, "root", "111111");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }

    // 更新 -- 增删改
    protected int executeUpdate(String sql, Object... objects) {
        try {
            getConnection();

            ps = conn.prepareStatement(sql);
            if (objects != null) {
                for (int i = 0; i < objects.length; i++) {
                    ps.setObject(i + 1, objects[i]);
                }
            }

            return ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }

        return -1;
    }

    // 查询
    protected ResultSet executeQuery(String sql, Object... objects) {
        getConnection();

        try {
            ps = conn.prepareStatement(sql);
            if (objects != null) {
                for (int i = 0; i < objects.length; i++) {
                    ps.setObject(i + 1, objects[i]);
                }
            }

            return rs = ps.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // 关闭
    protected void close() {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
