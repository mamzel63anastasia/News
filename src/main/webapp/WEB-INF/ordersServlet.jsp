<%@ page import="dao.OrderDao" %>
<%@ page import="java.util.List" %>
<%@ page import="models.Order" %>
<%@ page import="java.util.ArrayList" %>
<<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    OrderDao orderDao = new OrderDao();
    List<Order> list = orderDao.getOrders();
    //List<Order> list = new ArrayList<>();

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
                        <a class="nav-link" aria-current="page" href="/substances">
                            Активные вещества
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" aria-current="page" href="/medicaments">
                            Медикамент
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link activ" aria-current="page" href="/orders">
                            Заказ
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
            <table class="table">
                <tr>
                    <td>Адрес</td>
                    <td>Медикамент</td>
                    <td>Редактировать</td>
                    <td>Удалить</td>
                </tr>
                <% for (Order order : list) { %>
                <tr>
                    <td><%=order.getAdress()%></td>
                    <td><%=order.getClass()%></td>
                    <td>
                        <a class="item-edit" href="/order/edit?orderId=<%=order.getId()%>">
                            Редактировать
                        </a>
                    </td>
                    <td>
                        <a class="order-delete-link" href="#" id="<%=order.getId()%>">
                            Удалить
                        </a>
                    </td>
                </tr>
                <% } %>
            </table>
        </main>
    </div>
</div>
<%@include file="include/footer-include.html" %>
</body>
</html>
