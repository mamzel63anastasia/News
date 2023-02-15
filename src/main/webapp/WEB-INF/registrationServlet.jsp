<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Аптека - регистрация</title>
    <%@include file="include/header.jsp" %>
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
            <input type="text" class="form-control" id="floatingInput1" placeholder="Логин" name="fio">
            <label for="floatingInput1">Введите ФИО</label>
        </div>

        <div class="form-floating">
            <input type="text" class="form-control" id="floatingInput" placeholder="Логин" name="login">
            <label for="floatingInput">Введите логин</label>
        </div>
        <div class="form-floating">
            <input type="password" class="form-control" id="floatingPassword" placeholder="Пароль" name="password">
            <label for="floatingPassword">Введите пароль</label>
        </div>
        <button class="w-100 btn btn-lg btn-primary" type="submit">Регистрация</button>
    </form>
</main>

<%@include file="include/footer.jsp" %>
</body>
</html>