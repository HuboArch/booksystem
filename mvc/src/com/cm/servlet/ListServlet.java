package com.cm.servlet;

import com.cm.dao.BookDao;
import com.cm.entity.Book;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ListServlet extends HttpServlet {
    private BookDao bookDao = new BookDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Book> list = bookDao.getAll();

        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        PrintWriter pw = resp.getWriter();

        pw.print("<html>");
        pw.print("<head>");
        pw.print("<title>");
        pw.print("book system");
        pw.print("</title>");
        pw.print("</head>");
        pw.print("<body>");
        pw.print("<table align='center' width=60% border='1' cellspacing='0'>");

        pw.print("<tr>");
        pw.print("<td>");
        pw.print("ID");
        pw.print("</td>");
        pw.print("<td>");
        pw.print("Name");
        pw.print("</td>");
        pw.print("<td>");
        pw.print("Price");
        pw.print("</td>");
        pw.print("<td>");
        pw.print("Author");
        pw.print("</td>");
        pw.print("<td>");
        pw.print("PublishDate");
        pw.print("</td>");
        pw.print("<td>");
        pw.print("操作");
        pw.print("</td>");
        pw.print("</tr>");

        for (Book aList : list) {
            pw.print("<tr>");
            pw.print("<td>");
            pw.print(aList.getId());
            pw.print("</td>");
            pw.print("<td>");
            pw.print(aList.getName());
            pw.print("</td>");
            pw.print("<td>");
            pw.print(aList.getPrice());
            pw.print("</td>");
            pw.print("<td>");
            pw.print(aList.getAuthor());
            pw.print("</td>");
            pw.print("<td>");
            pw.print(aList.getPubDate());
            pw.print("</td>");
            pw.print("<td>");
            pw.print("<a href='selectById?id=" + aList.getId() + "'>修改</a>");
            pw.print("<a href='delete?id=" + aList.getId() + "'>删除</a>");
            pw.print("</td>");
            pw.print("</tr>");
        }

        pw.print("</table>");
        pw.print("</body>");
        pw.print("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
