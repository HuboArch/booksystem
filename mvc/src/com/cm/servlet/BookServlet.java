package com.cm.servlet;

import com.cm.dao.BookDao;
import com.cm.dao.CategoryDao;
import com.cm.entity.Book;
import com.cm.entity.Category;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/book")
public class BookServlet extends HttpServlet {
    private BookDao bookDao = new BookDao();
    private CategoryDao categoryDao = new CategoryDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        /*
         * op=list    展示全部图书数据
         * op=add     添加数据
         * op=delete  删除数据
         * op=update  更新数据
         * op=getById 查询单行数据
         * op=toAdd   到添加数据界面
         */
        String action = req.getParameter("op");
        if (action == null || "list".equals(action)) {
            list(req, resp);
        } else if ("toAdd".equals(action)) {
            toAdd(req, resp);
        } else if ("add".equals(action)) {
            add(req, resp);
        } else if ("delete".equals(action)) {
            delete(req, resp);
        } else if ("getById".equals(action)) {
            getById(req, resp);
        }
    }

    private void getById(HttpServletRequest req, HttpServletResponse resp) {


    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = req.getParameter("id") == null ? 0 : Integer.parseInt(req.getParameter("id"));

        if (bookDao.delete(id) > 0) {
            resp.sendRedirect("book?op=list");
        } else {
            resp.getWriter().print("删除失败");
        }
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) {

        String name = req.getParameter("name");
        int price = Integer.parseInt(req.getParameter("price"));
        String author = req.getParameter("author");
        int categoryId = Integer.parseInt(req.getParameter("categoryId"));
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("pubDate"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Book book = new Book(name, price, author, date, categoryId);

        if (bookDao.add(book) > 0) {
            try {
                resp.sendRedirect("book?op=list");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                resp.getWriter().print("添加失败");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void toAdd(HttpServletRequest req, HttpServletResponse resp) {

        List<Category> categoryList = categoryDao.getAllCategories();
        req.setAttribute("cList", categoryList);

        try {
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void list(HttpServletRequest req, HttpServletResponse resp) {

        List<Book> list = bookDao.getAll();
        List<Category> categories = categoryDao.getAllCategories();

        req.setAttribute("list", list);
        req.setAttribute("cList", categories);

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
