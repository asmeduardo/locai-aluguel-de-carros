<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page import="java.util.Date" %>
<%@ page import="br.com.locaialugueldecarros.authentication.model.User" %>
<%
    User user = (User) session.getAttribute("user");
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <title>LocAí | Aluguel de Veículos</title>
    <link rel="stylesheet" href="css/home.css">
</head>
<body onload="startTime()">
<header>
    <div id="logo">LocAí - Aluguel de Carros</div>
    <div id="menu">
        <nav>
            <a href="home.jsp">Home</a>
            <a href="#">Serviços</a>
            <a href="cars.jsp">Carros</a>
            <a href="#">FAQ</a>
            <a href="contact.jsp">Fale Conosco</a>
            <% if (user != null) { %>
            <div class="dropdown">
                <a href="#"><img id="account-img" src="images/user.png" alt="icone minha conta"></a>
                <div class="dropdown-content">
                    <a href="myAccount.jsp">Minha Conta</a>
                    <a href="logout.jsp">Sair</a>
                </div>
            </div>
            <% } else { %>
            <a href="registration.jsp">Cadastrar-se</a>
            <a href="login.jsp">Entrar</a>
            <% } %>
        </nav>
    </div>
</header>
<div class="content-container">
    <% if (user != null) { %>
    <h1>Bem vindo, <%=user.getFirstName() + " " + user.getLastName()%>!</h1>
    <p>A data atual é <%=new Date().toLocaleString()%>.</p>
    <p>A hora atual é <span id="time"></span>.</p>
    <% } else { %>
    <h1>Bem-vindo ao nosso aplicativo!</h1>
    <% } %>
</div>

<script src="js/home.js"></script>
</body>
</html>
