<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String messageInfo = null;

    if (session.getAttribute("messageInfo") != null) {
        messageInfo = (String) session.getAttribute("messageInfo");
        session.removeAttribute("messageInfo");
    }
%>
<html>
<head>
    <title>Аптека - регистрация</title>
    <%@include file="header-include.html"%>
</head>
<body class="text-center">
<style>
    .form-signin {
        max-width: 400px;
        padding: 15px;
    }
    .form-floating {
        margin-bottom: 7px;
    }
    .alert {
        margin-top: 10px;
    }
    .btn {
        width: 100%;
        margin-bottom: 10px;
    }
</style>
<main class="form-signin w-100 m-auto">
    <form method="post">
        <h1 class="h3 mb-3 fw-normal">Регистрация</h1>

        <div class="form-floating">
            <input type="text" class="form-control" id="floatingInput" placeholder="Логин" name="login">
            <label for="floatingInput">Введите логин</label>
        </div>
        <div class="form-floating">
            <input type="password" class="form-control" id="floatingPassword" placeholder="Пароль" name="pass">
            <label for="floatingPassword">Введите пароль</label>
        </div>
        <button class="w-100 btn btn-lg btn-primary" type="submit">Войти</button>

        <div class="alert alert-danger alert-dismissible fade <%=messageInfo != null ? "show" : ""%>" role="alert">
            <%=messageInfo%>
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
    </form>
</main>

<%@include file="footer-include.html"%>
</body>
</html>