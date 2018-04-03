package com.cm.servlet;

import com.cm.dao.BookDao;
import com.cm.entity.Book;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class SelectByIdServlet extends HttpServlet {
    private BookDao bookDao = new BookDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取 id
        int id = 0;
        if (req.getParameter("id") != null) {
            id = Integer.parseInt(req.getParameter("id"));
        }

        Book book = bookDao.getByID(id);

        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        PrintWriter pw = resp.getWriter();

        pw.print("<html>");
        pw.print("<head>");
        pw.print("<meta charset='utf-8'>");
        pw.print("<title>");
        pw.print("修改页面");
        pw.print("</title>");
        pw.print("</head>");
        pw.print("<body>");

        pw.print("<form action='update' method='post'>");
        pw.print("<table width='60%' cellspacing='0' border='1'>");

        pw.print("<tr>");
        pw.print("<td>书名：</td");
        pw.print("<td><input style='float:right' type='text' name='name' value=" + book.getName() + " /></td");
        pw.print("<td><input type='hidden' name='id' value='" + book.getId() + "'/></td");
        pw.print("</tr>");

        pw.print("<tr>");
        pw.print("<td>价格：</td");
        pw.print("<td><input style='float:right' type='text' name='price' value=" + book.getPrice() + " /></td");
        pw.print("</tr>");

        pw.print("</tr>");
        pw.print("<td>作者：</td");
        pw.print("<td><input style='float:right' type='text' name='author' value=" + book.getAuthor() + " /></td");
        pw.print("</tr>");

        pw.print("<tr>");
        pw.print("<td>出版日期：</td");
        pw.print("<td><input style='float:right' type='text' name='pubDate' value=" + book.getPubDate() + " /></td");
        pw.print("</tr>");

        pw.print("<tr colspan='2'>");
        pw.print("<td>");
        pw.print("<input style='float:right' type='submit' value='提交'>");
        pw.print("</td>");
        pw.print("</tr>");

        pw.print("</table>");
        pw.print("</form>");

        pw.print("</body>");
        pw.print("</html>");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
