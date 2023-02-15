<%@ page import="models.Substance" %>
<%@ page import="dao.SubstanceDao" %>
<%@ page import="java.util.List" %>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    SubstanceDao substanceDao = new SubstanceDao();
    List<Substance> list = substanceDao.getSubstances();

    String param = request.getParameter("param");
    Substance edit = null;

    if (param != null && request.getParameter("id") != null) {
        Long id = Long.parseLong(request.getParameter("id"));
        edit = substanceDao.getSubstance(id);
    }
%>


<html>
<head>
    <title>Аптека - активные вещества</title>
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
                    <th>МНН</th>
                    <th>Фармгруппа</th>
                    <th>Опции</th>
                </tr>
                <% for (Substance item : list) {%>
                <tr>
                    <td><%=item.getMnn()%></td>
                    <td><%=item.getFarmGroup()%></td>
                    <td>
                        <a class="btn btn-light" href="/substances?param=edit&id=<%=item.getId()%>"
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
                <% } %>
            </table>
            <div align="right">
                <a class="btn btn-secondary" href="/substances?param=add">
                    <i class="bi bi-person-add"></i> Добавить запись</a>
            </div>
            <hr>
            <% if (param != null && param.equals("edit") && edit != null) {%>
            <form class="row g-3" method="put">
                <input type="hidden" name="id" value="<%=edit.getId()%>">
                <div class="col-auto">
                    <label for="staticEmail1" class="visually-hidden">МНН</label>
                    <input type="text" class="form-control" name="mnn" id="staticEmail1"
                           value="<%=edit.getMnn()%>" placeholder="МНН">
                </div>
                <div class="col-auto">
                    <label for="staticEmail2" class="visually-hidden">Фармгруппа</label>
                    <input type="text" class="form-control" name="farmGroup" id="staticEmail2"
                           value="<%=edit.getFarmGroup()%>" placeholder="Фармгруппа">
                </div>
                <div class="col-auto">
                    <button type="submit" class="btn btn-primary mb-3">Сохранить</button>
                </div>
            </form>
            <%}%>

            <% if (param != null && param.equals("add")) {%>
            <form class="row g-3" method="post">
                <div class="col-auto">
                    <label for="staticEmail0" class="visually-hidden">МНН</label>
                    <input type="text" class="form-control" name="mnn" id="staticEmail0" placeholder="МНН">
                </div>
                <div class="col-auto">
                    <label for="staticEmail3" class="visually-hidden">Фармгруппа</label>
                    <input type="text" class="form-control" id="staticEmail3" name="farmGroup" placeholder="Фармгруппа">
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
