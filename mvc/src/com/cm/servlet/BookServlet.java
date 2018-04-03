package com.cm.servlet;

import com.cm.dao.BookDao;
import com.cm.entity.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/book")
public class BookServlet extends HttpServlet {
    private BookDao bookDao = new BookDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("op");

        if (action == null || "list".equals(action)) {
            list(req, resp);
        }
    }

    private void list(HttpServletRequest req, HttpServletResponse resp) {

        List<Book> list = bookDao.getAll();
        req.setAttribute("list", list);

        try {
            req.getRequestDispatcher("list.jsp").forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
