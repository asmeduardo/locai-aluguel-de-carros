<%@ page import="br.com.locaialugueldecarros.authentication.model.User" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    User user = (User) session.getAttribute("user");
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <title>Contact Us - LocAí - Aluguel de Carros</title>
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/contact.css">
    <script src='https://www.google.com/recaptcha/api.js'></script>
</head>
<body>
<header>
    <div id="logo">LocAí - Aluguel de Carros</div>
    <div id="menu">
        <nav>
            <a href="home.jsp">Home</a>
            <a href="#">Serviços</a>
            <a href="#">Carros</a>
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
<h1>Fale Conosco</h1>
<div class="form-container">
    <form action="contato" method="post">
        <input type="text" id="name" name="name" placeholder="Nome" required>
        <input type="text" id="lastname" name="lastname" placeholder="Sobrenome" required>
        <input type="email" id="email" name="email" placeholder="Email" required>
        <textarea id="message" name="message" rows="5" placeholder="Escreva sua mensagem aqui..."
                  required style="resize: none"></textarea>
        <div class="g-recaptcha"
             data-sitekey="6Lf7lyAkAAAAAJP4mGLyVBX6VP0QLseL2fYqronp" name="g-recaptcha-response"></div>
        <input type="submit" value="Enviar Mensagem">
        <div>
            <div class="message"></div>
        </div>
    </form>
    <div class="card-container">
        <div class="card">
            <div class="company-name">LocAí - Aluguel de Carros</div>
            <div class="company-info">
                Endereço: Rua das Flores, 123<br>
                Telefone: (31) 1234-5678<br>
                Email: contato@locaialugueldecarros.com.br
            </div>
        </div>
    </div>
</div>
</body>
</html>

<%
    String message = (String) request.getAttribute("message");
    if (message != null) {
%>
<script>
    document.querySelector(".message").innerHTML = "<%= message %>";
</script>
<%
    }
%>