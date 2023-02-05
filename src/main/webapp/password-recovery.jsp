<%@ page contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Recuperar Senha</title>
    <link rel="stylesheet" href="css/password-recovery.css">
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
    <div class="recovery-card">
        <div class="card-header">
            <h1 class="company-name">LocAí</h1>
        </div>
        <div class="card-body">
            <form action="recuperarSenha" method="post">
                <div class="form-group">
                    <input type="email" id="email" name="email" placeholder="Email"
                           required>
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
                    <button type="submit" class="recovery-button">ENVIAR</button>
                </div>
                <div id="recovery-text">Enviaremos a senha para o seu email</div>
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