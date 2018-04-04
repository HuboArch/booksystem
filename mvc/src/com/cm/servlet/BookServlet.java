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
        resp.setContentType("text/plain;charset=utf-8");

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
        } else if ("update".equals(action)) {
            update(req, resp);
        }
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Book b = createBook(req, true);

        if (bookDao.update(b) > 0) {
            resp.sendRedirect("book?op=list");
        } else {
            resp.getWriter().print("修改失败");
        }
    }

    private static Book createBook(HttpServletRequest req, boolean withId) {

        int id = 0;
        if (withId) {
            id = validateId(req);
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

        return withId ? new Book(id, bookName, price, author, pubDate, categoryId) :
                new Book(bookName, price, author, pubDate, categoryId);
    }

    private static int validateId(HttpServletRequest req) {
        return req.getParameter("id") != null
                ? Integer.parseInt(req.getParameter("id"))
                : 0;
    }

    private void getById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = validateId(req);
        Book book = bookDao.getByID(id);
        List<Category> categoryList = categoryDao.getAllCategories();

        req.setAttribute("book", book);
        req.setAttribute("cList", categoryList);

        req.getRequestDispatcher("update.jsp").forward(req, resp);
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = validateId(req);

        if (bookDao.delete(id) > 0) {
            resp.sendRedirect("book?op=list");
        } else {
            resp.getWriter().print("删除失败");
        }
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        Book b = createBook(req, false);

        if ((bookDao.add(b) > 0)) {
            resp.sendRedirect("book?op=list");
        } else {
            resp.getWriter().print("添加失败");
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
