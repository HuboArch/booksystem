<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: dean
  Date: 2018/4/3
  Time: 11:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>图书录入界面</title>
    <style>
        #card {
            margin-top: 20px;
            width: 600px;
        }
    </style>
    <link rel="stylesheet" href="assets/semantic.min.css">
    <script src="assets/jquery-3.3.1.min.js"></script>
    <script src="assets/semantic.min.js"></script>
</head>
<body>

<div class="ui padded centered card" id="card">

    <div class="content">
        <div class="header">
            更新单行图书信息
        </div>
    </div>

    <div class="content">
        <form class="ui form" action="book?op=update" method="post">
            <div class="field">
                <div class="ui labeled input">
                    <label for="name" class="ui label">书名</label>
                    <input type="text" id="name" name="name" value="${book.getName()}">
                    <input type="hidden" name="id" value="${book.getId()}">
                </div>
            </div>
            <div class="field">
                <div class="ui labeled input">
                    <label class="ui label" for="price">价格</label>
                    <input type="text" id="price" name="price" value="${book.getPrice()}">
                    <div class="ui basic label">.00</div>
                </div>
            </div>
            <div class="field">
                <div class="ui labeled input">
                    <label class="ui label" for="author">作者</label>
                    <input type="text" name="author" id="author" value="${book.getAuthor()}">
                </div>
            </div>
            <div class="field">
                <div class="ui labeled input">
                    <label class="ui label" for="select">类型</label>
                    <select class="ui selection" name="categoryId" id="select">
                        <c:forEach items="${cList}" var="category">
                            <c:choose>
                                <c:when test="${category.getId()==book.getCategoryId()}">
                                    <option value="${category.getId()}" selected>${category.getName()}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${category.getId()}">${category.getName()}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="field">
                <div class="ui labeled input">
                    <label for="date" class="ui label">日期</label>
                    <input type="text" name="pubDate" id="date" value="${book.getPubDate()}">
                </div>
            </div>

            <button class="right floated ui button primary" type="submit">提交</button>
        </form>
    </div>

</div>

</body>
</html>
