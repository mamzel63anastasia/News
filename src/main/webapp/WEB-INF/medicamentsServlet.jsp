<%@ page import="dao.MedicamentDao" %>
<%@ page import="java.util.List" %>
<%@ page import="models.Medicament" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    MedicamentDao medicamentDao = new MedicamentDao();
    List<Medicament> list = medicamentDao.getMedicaments();

    String param = request.getParameter("param");

    Medicament edit = null;

    if (param != null && request.getParameter("id") != null) {
        Long id = Long.parseLong(request.getParameter("id"));
        edit = medicamentDao.getMedicament(id);
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
                    <th>Наименование</th>
                    <th>МНН</th>
                    <th>Название производителя</th>
                    <th>Доза</th>
                    <th>Количество</th>
                    <th>Опции</th>
                </tr>
                <% for (Medicament  medicament : list) { %>
                <tr>
                    <td><%=medicament.getName()%></td>
                    <td><%=medicament.getSubstance()%></td>
                    <td><%=medicament.getProducer()%></td>
                    <td><%=medicament.getDose()%></td>
                    <td><%=medicament.getNumber()%></td>
                    <td>
                        <a class="item-edit" href="/medicament/edit?medicamentId=<%=medicament.getId()%>">
                            Редактировать
                        </a>
                    </td>
                    <td>
                        <a class="medicament-delete-link" href="#" id="<%=medicament.getId()%>">
                            Удалить
                        </a>
                    </td>
                </tr>
                <% } %>
            </table>
        </main>
    </div>
</div>
<%@include file="include/footer.jsp" %>
</body>
</html>
