package com.cm.dao;

import com.cm.entity.Category;
import com.cm.util.BaseDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao extends BaseDao {

    public List<Category> getAllCategories() {

        String sql = "select * from category";
        ResultSet resultSet = executeQuery(sql);

        List<Category> categories = new ArrayList<>();
        try {
            while (resultSet.next()) {
                categories.add(new Category(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3)
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }

        return categories.size() > 0 ? categories : null;
    }
}
