<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Cadastro</title>
    <link rel="stylesheet" href="css/registration.css">
    <script src='https://www.google.com/recaptcha/api.js'></script>
</head>
<body>
<div class="left-section">
    <div class="light-blue-section">
        <h1 class="welcome-text">Bem Vindo</h1>
        <p>Para entrar, você deve usar suas informações pessoais como
            e-mail e senha</p>
        <a href="login.jsp" class="login-button">ENTRAR</a>
    </div>
</div>
<div class="right-section">
    <div class="registration-card">
        <div class="card-header">
            <h1 class="company-name">LocAí</h1>
        </div>
        <div class="card-body">
            <form action="cadastrar" method="post">
                <div class="form-group">
                    <input type="text" id="firstName" name="firstName"
                           placeholder="Nome" required>
                </div>
                <div class="form-group">
                    <input type="text" id="lastName" name="lastName"
                           placeholder="Sobrenome" required>
                </div>
                <div class="form-group">
                    <input type="email" id="email" name="email" placeholder="Email"
                           required>
                </div>
                <div class="form-group">
                    <input type="password" id="password" name="password"
                           placeholder="Senha" required>
                </div>
                <div class="form-group">
                    <input type="checkbox" id="privacy" name="privacy"> <label
                        for="privacy" class="privacy-policy-text"> Eu concordo
                    com a <a href="#">Política de Privacidade</a>
                </label>
                </div>
                <div class="form-group">
                    <div class="g-recaptcha"
                         data-sitekey="6Lf7lyAkAAAAAJP4mGLyVBX6VP0QLseL2fYqronp" name="g-recaptcha-response"></div>
                </div>
                <div class="form-group">
                    <button type="submit" class="subscribe-button">INSCREVER-SE</button>
                </div>
            </form>
            <div class="form-group">
                <div id="error-message" class="error-message"></div>
            </div>
        </div>
    </div>
</div>
</body>
</html>

<%
    String errorMessage = (String) request.getAttribute("errorMessage");
    if (errorMessage != null) {
%>
<script>
    document.getElementById("error-message").innerHTML = "<%= errorMessage %>";
</script>
<%
    }
%>