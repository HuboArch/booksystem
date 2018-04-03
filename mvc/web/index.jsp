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

<div class="ui centered orange card" id="card">

    <div class="content">
        <div class="header">
            图书录入界面
        </div>
        <div class="meta">
            展示 MVC 模式专用
        </div>
    </div>

    <div class="content">
        <form class="ui form" action="addServlet">
            <div class="field">
                <label>书名</label>
                <input type="text" name="name">
            </div>
            <div class="field">
                <label>价格</label>
                <input type="text" name="price">
            </div>
            <div class="field">
                <label>作者</label>
                <input type="text" name="author">
            </div>
            <div class="field">
                <label>出版日期</label>
                <input type="text" name="pubDate">
            </div>
            <div class="field">
                <label>所属类别</label>
                <input type="text" name="categoryId">
            </div>

            <button class="right floated ui button primary" type="submit">提交</button>
        </form>
    </div>

</div>

</body>
</html>
