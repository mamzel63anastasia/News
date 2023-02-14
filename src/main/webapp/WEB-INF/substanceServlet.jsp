<%@ page import="dao.SubstanceDao" %>
<%@ page import="models.Substance" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    SubstanceDao substanceDao = new SubstanceDao();
   List<Substance> list = substanceDao.getSubstances();
   // List<Substance> list = new ArrayList<>();
%>


<html>
<head>
    <title>Аптека - главная</title>
    <%@include file="header-include.html" %>
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
        <nav id="sidebarMenu" class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse">
            <div class="position-sticky pt-3 sidebar-sticky">
                <ul class="nav flex-column">
                    <li class="nav-item">
                        <a class="nav-link" aria-current="page" href="/">
                            Главная
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" aria-current="page" href="/producers">
                            Производители
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="/substances">
                            Активные вещества
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" aria-current="page" href="/medicaments">
                            Медикаменты
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" aria-current="page" href="/orders">
                            Заказы
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" aria-current="page" href="/users">
                            Пользователи
                        </a>
                    </li>
                </ul>
            </div>
        </nav>
        <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
            <table class="table table-striped">
                <tr>
                    <th>МНН</th>
                    <th>Фармгруппа</th>
                    <th>Редактировать</th>
                    <th>Удалить</th>
                </tr>
                <% for (Substance  substance : list) { %>
                <tr>
                    <td><%=substance.getMnn()%></td>
                    <td><%=substance.getFarmgroup()%></td>
                    <td>
                        <a class="item-edit" href="/substance/edit?substanceId=<%=substance.getId()%>">
                            Редактировать
                        </a>
                    </td>
                    <td>
                        <a class="substance-delete-link" href="#" id="<%=substance.getId()%>">
                            Удалить
                        </a>
                    </td>
                </tr>
                <% } %>
            </table>
        </main>
    </div>
</div>
<%@include file="footer-include.html" %>
</body>
</html>
