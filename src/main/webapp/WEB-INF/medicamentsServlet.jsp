<%@ page import="dao.MedicamentDao" %>
<%@ page import="java.util.List" %>
<%@ page import="models.Medicament" %>
<%@ page import="dao.ProducerDao" %>
<%@ page import="dao.SubstanceDao" %>
<%@ page import="models.Substance" %>
<%@ page import="models.Producer" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    MedicamentDao medicamentDao = new MedicamentDao();
    ProducerDao producerDao = new ProducerDao();
    SubstanceDao substanceDao = new SubstanceDao();

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
                    <th>Доза</th>
                    <th>Номер</th>
                    <th>Активное вещество</th>
                    <th>Производитель</th>
                    <th>Опции</th>
                </tr>
                <% for (Medicament item : list) {%>
                <tr>
                    <td><%=item.getName()%>
                    </td>
                    <td><%=item.getDose()%>
                    </td>
                    <td><%=item.getNumber()%>
                    </td>
                    <td><%=item.getSubstance().getFarmGroup()%> | <%=item.getSubstance().getMnn()%>
                    </td>
                    <td><%=item.getProducer().getName()%>
                    </td>
                    <td>
                        <a class="btn btn-light" href="/medicaments?param=edit&id=<%=item.getId()%>"
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
                <a class="btn btn-secondary" href="/medicaments?param=add">
                    <i class="bi bi-person-add"></i> Добавить запись</a>
            </div>
            <hr>

            <% if (param != null && param.equals("add")) {%>
            <form method="post">
                <div class="mb-3">
                    <input class="form-control" placeholder="Наименование" name="name">
                </div>
                <div class="mb-3">
                    <input class="form-control" placeholder="Доза" name="dose">
                </div>
                <div class="mb-3">
                    <input class="form-control" placeholder="Количество" name="number">
                </div>
                <div class="mb-3">
                    <select name="substance" class="form-control">
                        <% for (Substance item : substanceDao.getSubstances()) {%>
                        <option value="<%=item.getId()%>"><%=item.getMnn()%> | <%=item.getFarmGroup()%></option>
                        <%}%>
                    </select>
                </div>
                <div class="mb-3">
                    <select name="producer" class="form-control">
                        <% for (Producer item : producerDao.getProducers()) {%>
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
                    <input class="form-control" placeholder="Наименование" name="name" value="<%=edit.getName()%>">
                </div>
                <div class="mb-3">
                    <input class="form-control" placeholder="Доза" name="dose" value="<%=edit.getDose()%>">
                </div>
                <div class="mb-3">
                    <input class="form-control" placeholder="Количество" name="number" value="<%=edit.getNumber()%>">
                </div>
                <div class="mb-3">
                    <select name="substance" class="form-control">
                        <% for (Substance item : substanceDao.getSubstances()) {%>
                        <option value="<%=item.getId()%>" <%=item.getId().equals(edit.getSubstance().getId()) ? "selected" : ""%>>
                            <%=item.getMnn()%> | <%=item.getFarmGroup()%>
                        </option>
                        <%}%>
                    </select>
                </div>
                <div class="mb-3">
                    <select name="producer" class="form-control">
                        <% for (Producer item : producerDao.getProducers()) {%>
                        <option value="<%=item.getId()%>" <%=item.getId().equals(edit.getProducer().getId()) ? "selected" : ""%>>
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
