<%@ page import="dao.ProducerDao" %>
<%@ page import="models.Producer" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    ProducerDao producerDao = new ProducerDao();
    List<Producer> list = producerDao.getProducers();

    String param = request.getParameter("param");

    Producer producerEdit = null;

    if (param != null && request.getParameter("producerId") != null) {
        producerEdit = producerDao.getProducer(Long.parseLong(request.getParameter("producerId")));
    }

%>


<html>
<head>
    <title>Аптека - производители</title>
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
                    <th>Страна изготовитель</th>
                    <th>Опции</th>

                </tr>
                <% for (Producer producer : list) { %>
                <tr>
                    <td><%=producer.getName()%></td>
                    <td><%=producer.getCountry()%></td>
                    <td>
                        <a class="btn btn-light" href="/producers?param=producerEdit&producerId=<%=producer.getId()%>" title="редактировать">
                            <i class="bi bi-pencil-square"></i>
                        </a>
                        <form method="delete">
                            <input type="hidden" name="id" value="<%=producer.getId()%>">
                            <button class="btn btn-light" type="submit"><i class="bi bi-x-square"></i></button>
                        </form>
                    </td>
                </tr>
                <% } %>
            </table>
            <div align="right">
                <a class="btn btn-secondary" href="/producers?param=producerAdd"><i class="bi bi-person-add"></i> Добавить производителя</a>
            </div>
            <hr>

            <% if (param != null && param.equals("producerAdd")) {%>
            <form class="row g-3" method="post">
                <div class="col-auto">
                    <label for="staticEmail3" class="visually-hidden">Название</label>
                    <input type="text" class="form-control" id="staticEmail3" name="name" value="" placeholder="Укажите название">
                </div>
                <div class="col-auto">
                    <label for="inputPassword3" class="visually-hidden">Страна</label>
                    <input type="text" class="form-control" id="inputPassword3" placeholder="Укажите страну" name="country">
                </div>
                <div class="col-auto">
                    <button type="submit" class="btn btn-primary mb-3">Сохранить</button>
                </div>
            </form>
            <%}%>

            <% if (param != null && param.equals("producerEdit")) {%>
            <form class="row g-3" method="put">
                <input type="hidden" name="id" value="<%=producerEdit.getId()%>">
                <div class="col-auto">
                    <label for="staticEmail2" class="visually-hidden">Название</label>
                    <input type="text" class="form-control" id="staticEmail2" name="name" value="<%=producerEdit.getName()%>" placeholder="Укажите название">
                </div>
                <div class="col-auto">
                    <label for="inputPassword2" class="visually-hidden">Страна</label>
                    <input type="text" class="form-control" id="inputPassword2" placeholder="Укажите страну" name="country" value="<%=producerEdit.getCountry()%>">
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

