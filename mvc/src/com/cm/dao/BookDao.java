package com.cm.dao;

import com.cm.entity.Book;
import com.cm.util.BaseDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class BookDao extends BaseDao {

    // 根据 ID 返回记录
    public Book getByID(int id) {

        String sql = "select * from book where id=?";

        try {
            ResultSet rs = executeQuery(sql, id);
            if (rs.next()) {
                return new Book(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getString(4),
                        rs.getDate(5),
                        rs.getInt(6)
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }

        return null;
    }

    // 查询所有书籍
    public List<Book> getAll() {

        List<Book> list = new ArrayList<>();

        String sql = "select * from book";
        ResultSet rs = executeQuery(sql);

        try {
            while (rs.next()) {
                list.add(new Book(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getString(4),
                        rs.getDate(5),
                        rs.getInt(6)
                ));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // 添加书籍
    public int add(Book b) {

        String sql = "insert into book(name,price,author,pubDate,categoryId) values (?,?,?,?,?)";
        String pubDate = new SimpleDateFormat("yyyy-MM-dd").format(b.getPubDate());

        return executeUpdate(sql, b.getName(), b.getPrice(), b.getAuthor(), pubDate, b.getCategoryId());
    }

    // 修改书籍
    public int update(Book b) {

        String sql = "update book set name=?,price=?,author=?,pubDate=?,categoryId=? where id=" + b.getId();

        return executeUpdate(sql,
                b.getName(),
                b.getPrice(),
                b.getAuthor(),
                new SimpleDateFormat("yyyy-MM-dd").format(b.getPubDate()),
                b.getCategoryId()
        );
    }

    // 删除单行数据
    public int delete(int id) {
        String sql = "delete from book where id=?";

        return executeUpdate(sql, id);
    }
}
