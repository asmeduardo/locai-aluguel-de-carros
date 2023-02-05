<%@ page import="br.com.locaialugueldecarros.authentication.service.RememberMeService" %>
<%@ page contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Entrar</title>
    <link rel="stylesheet" href="css/login.css">
    <script src='https://www.google.com/recaptcha/api.js'></script>
</head>
<body>
<div class="left-section">
    <div class="light-blue-section">
        <h1 class="welcome-text">Bem Vindo</h1>
        <p>Para criar uma conta, você deve informar algumas informações.</p>
        <a href="registration.jsp" class="registration-button">INSCREVER-SE</a>
    </div>
</div>
<div class="right-section">
    <div class="login-card">
        <div class="card-header">
            <h1 class="company-name">LocAí</h1>
        </div>
        <div class="card-body">
            <form action="entrar" method="post">
                <div class="form-group">
                    <%String email = RememberMeService.getRememberedEmail(request);%>
                    <input type="email" id="email" name="email"
                           value="<%=(RememberMeService.isRememberMe(request)) ? email : ""%>"
                           placeholder="Email"
                           required>
                </div>
                <div class="form-group">
                    <input type="password" id="password" name="password"
                           placeholder="Senha" required>
                </div>
                <div class="form-group">
                    <input type="checkbox" id="remember"
                           name="remember" <%= (RememberMeService.getRememberedEmail(request) != null) ? "checked" : "" %>><label
                        for="remember" class="remember">Lembrar-me
                </label>
                </div>
                <div class="form-group">
                    <div class="g-recaptcha"
                         data-sitekey="6Lf7lyAkAAAAAJP4mGLyVBX6VP0QLseL2fYqronp" name="g-recaptcha-response"></div>
                </div>
                <div class="form-group">
                    <button type="submit" class="login-button">
                        ENTRAR
                    </button>
                </div>
                <div class="recovery">
                    <a href="password-recovery.jsp">Esqueci minha senha</a>
                </div>
            </form>
        </div>
        <div class="form-group">
            <div id="error-message" class="error-message"></div>
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