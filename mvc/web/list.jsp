<%--
  Created by IntelliJ IDEA.
  User: dean
  Date: 2018/4/3
  Time: 10:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Book List</title>
    <link rel="stylesheet" href="assets/semantic.min.css">
    <script src="assets/jquery-3.3.1.min.js"></script>
    <script src="assets/semantic.min.js"></script>
</head>
<body>
<div class="ui stacked segment">

    <table class="ui celled table">
        <thead>
        <tr>
            <th>ID</th>
            <th>书名</th>
            <th>价格</th>
            <th>作者</th>
            <th>出版日期</th>
            <th>所属类别</th>
            <th>数据操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${list}" var="bean">
        <tr>
            <td>${bean.getId()}</td>
            <td>${bean.getName()}</td>
            <td>${bean.getPrice()}</td>
            <td>${bean.getAuthor()}</td>
            <td>${bean.getPubDate()}</td>
            <td>${bean.getCategoryId()}</td>
            <td>
                <div class="ui button">
                    <a href="book?op=getById&id=${bean.getId()}">修改</a>
                </div>
                <div class="ui button">
                    <a class="ui white" href="book?op=delete&id=${bean.getId()}">删除</a>
                </div>
            </td>
        </tr>
        </c:forEach>
    </table>

</div>
</body>
</html>
