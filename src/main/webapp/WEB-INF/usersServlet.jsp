<%@ page import="dao.UserDao" %>
<%@ page import="models.User" %>
<%@ page import="java.util.List" %>
<%@ page import="utils.UserUtils" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
  String messageInfo = null;

  if (session.getAttribute("messageInfo") != null) {
    messageInfo = (String) session.getAttribute("messageInfo");
    session.removeAttribute("messageInfo");
  }

  UserDao userDao = new UserDao();
  List<User> list = userDao.getUsers();

  String param = request.getParameter("param");
  User editUser = null;
  if (param != null && request.getParameter("userId") != null) {
    String userId = request.getParameter("userId");
    editUser = UserUtils.findUserByIdOrNull(list, userId);
  }
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
              Медикаменты
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" aria-current="page" href="/orders">
              Заказы
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="/users">
              Пользователи
            </a>
          </li>
        </ul>
      </div>
    </nav>
    <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
      <table class="table table-striped">
        <tr>
          <th>Пользователь</th>
          <th>Опции</th>
        </tr>
        <% for ( User user : list) {%>
        <tr>
          <td><%=user.getLogin()%></td>
          <td>
            <a class="btn btn-light" href="/users?param=userEdit&userId=<%=user.getId()%>" title="редактировать">
              <i class="bi bi-pencil-square"></i>
            </a>
            <a class="btn btn-light" href="/users?userDelete=<%=user.getId()%>" title="удалить">
              <i class="bi bi-x-square"></i>
            </a>
          </td>
        </tr>
        <% } %>
      </table>
      <div align="right">
        <a class="btn btn-secondary" href="/users?param=userAdd"><i class="bi bi-person-add"></i> Добавить пользователя</a>
      </div>
      <hr>


      <% if (param != null && param.equals("userEdit") && editUser != null) {%>
      <form class="row g-3" method="PUT">
        <input type="hidden" name="userId" value="<%=editUser.getId()%>">
        <div class="col-auto">
          <label for="staticEmail2" class="visually-hidden">Логин</label>
          <input type="text" readonly class="form-control-plaintext" name="login" id="staticEmail2" value="<%=editUser.getLogin()%>">
        </div>
        <div class="col-auto">
          <label for="inputPassword2" class="visually-hidden">Пароль</label>
          <input type="password" class="form-control" id="inputPassword2" placeholder="Пароль">
        </div>
        <div class="col-auto">
          <button type="submit" class="btn btn-primary mb-3">Сохранить</button>
        </div>
      </form>
      <%}%>

      <% if (param != null && param.equals("userAdd")) {%>
      <form class="row g-3" method="post">
        <div class="col-auto">
          <label for="staticEmail3" class="visually-hidden">Логин</label>
          <input type="text" class="form-control" id="staticEmail3" name="login" value="">
        </div>
        <div class="col-auto">
          <label for="inputPassword3" class="visually-hidden">Пароль</label>
          <input type="password" class="form-control" id="inputPassword3" placeholder="Пароль" name="pass">
        </div>
        <div class="col-auto">
          <button type="submit" class="btn btn-primary mb-3">Сохранить</button>
        </div>
      </form>
      <%}%>

      <div class="alert alert-danger alert-dismissible fade <%=messageInfo != null ? "show" : ""%>" role="alert">
        <%=messageInfo%>
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
      </div>

    </main>
  </div>
</div>
<%@include file="footer-include.html" %>
</body>
</html>
