package com.cm.servlet;

import com.cm.dao.BookDao;
import com.cm.entity.Book;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UpdateServlet extends HttpServlet {
    private BookDao bookDao = new BookDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        int id = 0;
        if (req.getParameter("id") != null) {
            id = Integer.parseInt(req.getParameter("id"));
        }

        String bookName = req.getParameter("name");
        double price = Double.parseDouble(req.getParameter("price"));
        String author = req.getParameter("author");
        Date pubDate = null;
        try {
            pubDate = new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("pubDate"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int categoryId = Integer.parseInt(req.getParameter("categoryId"));

        Book b = new Book(id, bookName, price, author, pubDate, categoryId);

        if (bookDao.update(b) > 0) {
            resp.sendRedirect("list");
        } else {
            resp.getWriter().print("修改失败");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
