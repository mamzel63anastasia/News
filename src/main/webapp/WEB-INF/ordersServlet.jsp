<%@ page import="java.util.List" %>
<%@ page import="dao.*" %>
<%@ page import="models.*" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    MedicamentDao medicamentDao = new MedicamentDao();
    UserDao userDao = new UserDao();
    OrderDao orderDao = new OrderDao();

    List<Order> list = orderDao.getOrders();

    String param = request.getParameter("param");

    Order edit = null;

    if (param != null && request.getParameter("id") != null) {
        Long id = Long.parseLong(request.getParameter("id"));
        edit = orderDao.getOrder(id);
    }
%>
<html>
<head>
    <title>Аптека - медикаменты</title>
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
                    <th>Адрес отправления</th>
                    <th>Пользователь</th>
                    <th>Наименование медикамента и количетсво </th>
                    <th>Опции</th>
                </tr>
                <% for (Order item : list) {%>
                <tr>
                    <td><%=item.getAdress()%>
                    </td>
                    <td><%=item.getUser().getFio()%>
                    </td>
                    <td>
                        <%=item.getMedicament().getName()%> | <%=item.getMedicament().getNumber()%>
                    </td>
                    <td>
                        <a class="btn btn-light" href="/orders?param=edit&id=<%=item.getId()%>"
                           title="редактировать">
                            <i class="bi bi-pencil-square"></i>
                        </a>
                        <form method="delete">
                            <input type="hidden" name="id" value="<%=item.getId()%>">
                            <button type="submit" class="btn btn-light" title="удалить">
                                <i class="bi bi-x-square"></i>
                            </button>
                        </form>
                    </td>
                </tr>
                <%}%>
            </table>
            <div align="right">
                <a class="btn btn-secondary" href="/orders?param=add">
                    <i class="bi bi-person-add"></i> Добавить запись</a>
            </div>
            <hr>

            <% if (param != null && param.equals("add")) {%>
            <form method="post">
                <div class="mb-3">
                    <input class="form-control" placeholder="Наименование" name="adress">
                </div>
                <div class="mb-3">
                    <select name="user" class="form-control">
                        <%for (User item : userDao.getUsers()) {%>
                        <option value="<%=item.getId()%>"><%=item.getFio()%></option>
                        <%}%>
                    </select>
                </div>
                <div class="mb-3">
                    <select name="medicament" class="form-control">
                        <%for (Medicament item : medicamentDao.getMedicaments()){%>
                        <option value="<%=item.getId()%>"><%=item.getName()%></option>
                        <%}%>
                    </select>
                </div>

                <div class="mb-3">
                    <input type="submit" class="form-control" value="Добавить">
                </div>
            </form>
            <%}%>

            <% if (param != null && param.equals("edit") && edit != null) {%>
            <form method="put">
                <input type="hidden" name="id" value="<%=edit.getId()%>">
                <div class="mb-3">
                    <input class="form-control" placeholder="Наименование" name="adress" value="<%=edit.getAdress()%>">
                </div>
                <div class="mb-3">
                    <select name="user" class="form-control">
                        <%for (User item : userDao.getUsers()) {%>
                        <option value="<%=item.getId()%>" <%=item.getId().equals(edit.getUser().getId()) ? "selected" : ""%>>
                            <%=item.getFio()%>
                        </option>
                        <%}%>
                    </select>
                </div>
                <div class="mb-3">
                    <select name="medicament" class="form-control">
                        <%for (Medicament item : medicamentDao.getMedicaments()){%>
                        <option value="<%=item.getId()%>" <%=item.getId().equals(edit.getMedicament().getId()) ? "selected" : ""%>>
                            <%=item.getName()%>
                        </option>
                        <%}%>
                    </select>
                </div>

                <div class="mb-3">
                    <input type="submit" class="form-control" value="Добавить">
                </div>
            </form>
            <%}%>
        </main>
    </div>
</div>
<%@include file="include/footer.jsp" %>
</body>
</html>
