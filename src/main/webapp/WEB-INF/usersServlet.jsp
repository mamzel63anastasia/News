<%@ page import="dao.UserDao" %>
<%@ page import="models.User" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    UserDao userDao = new UserDao();
    List<User> list = userDao.getUsers();

    String param = request.getParameter("param");
    User editUser = null;
    if (param != null && request.getParameter("userId") != null) {
        Long userId = Long.parseLong(request.getParameter("userId"));
        editUser = userDao.getUser(userId);
    }
%>

<html>
<head>
    <title>Аптека - пользователи</title>
    <%@include file="include/header.jsp" %>
</head>
<body>
<header class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0 shadow">
    <a class="navbar-brand col-md-3 col-lg-2 me-0 px-3 fs-6" href="/">Аптека</a>
    <button class="navbar-toggler position-absolute d-md-none collapsed" type="button" data-bs-toggle="collapse"
            data-bs-target="#sidebarMenu" aria-controls="sidebarMenu" aria-expanded="false"
            aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="navbar-nav">
        <div class="nav-item text-nowrap">
            <a class="nav-link px-3" href="#">Выход</a>
        </div>
    </div>
</header>
<div class="container-fluid">
    <div class="row">
        <%@include file="include/menu.jsp" %>
        <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
            <table class="table table-striped">
                <tr>
                    <th>Фио</th>
                    <th>Логин</th>
                    <th>Опции</th>
                </tr>
                <% for (User user : list) {%>
                <tr>
                    <td><%=user.getFio()%>
                    </td>
                    <td><%=user.getLogin()%>
                    </td>
                    <td>
                        <a class="btn btn-light" href="/users?param=userEdit&userId=<%=user.getId()%>"
                           title="редактировать">
                            <i class="bi bi-pencil-square"></i>
                        </a>
                        <form method="delete">
                            <input type="hidden" name="id" value="<%=user.getId()%>">
                            <button type="submit" class="btn btn-light" title="удалить">
                                <i class="bi bi-x-square"></i>
                            </button>
                        </form>
                    </td>
                </tr>
                <% } %>
            </table>
            <div align="right">
                <a class="btn btn-secondary" href="/users?param=userAdd"><i class="bi bi-person-add"></i> Добавить
                    пользователя</a>
            </div>
            <hr>


            <% if (param != null && param.equals("userEdit") && editUser != null) {%>
            <form class="row g-3" method="put">
                <input type="hidden" name="userId" value="<%=editUser.getId()%>">
                <div class="col-auto">
                    <label for="staticEmail1" class="visually-hidden">ФИО</label>
                    <input type="text" readonly class="form-control-plaintext" name="fio" id="staticEmail1"
                           value="<%=editUser.getFio()%>">
                </div>
                <div class="col-auto">
                    <label for="staticEmail2" class="visually-hidden">Логин</label>
                    <input type="text" readonly class="form-control-plaintext" name="login" id="staticEmail2"
                           value="<%=editUser.getLogin()%>">
                </div>
                <div class="col-auto">
                    <label for="inputPassword2" class="visually-hidden">Пароль</label>
                    <input type="password" class="form-control" id="inputPassword2" placeholder="Пароль" name="password">
                </div>
                <div class="col-auto">
                    <button type="submit" class="btn btn-primary mb-3">Сохранить</button>
                </div>
            </form>
            <%}%>

            <% if (param != null && param.equals("userAdd")) {%>
            <form class="row g-3" method="post">
                <div class="col-auto">
                    <label for="staticEmail0" class="visually-hidden">ФИО</label>
                    <input type="text" class="form-control" name="fio" id="staticEmail0">
                </div>
                <div class="col-auto">
                    <label for="staticEmail3" class="visually-hidden">Логин</label>
                    <input type="text" class="form-control" id="staticEmail3" name="login" value="">
                </div>
                <div class="col-auto">
                    <label for="inputPassword3" class="visually-hidden">Пароль</label>
                    <input type="password" class="form-control" id="inputPassword3" placeholder="Пароль" name="password">
                </div>
                <div class="col-auto">
                    <button type="submit" class="btn btn-primary mb-3">Сохранить</button>
                </div>
            </form>
            <%}%>
        </main>
    </div>
</div>
<%@include file="include/footer.jsp" %>
</body>
</html>
