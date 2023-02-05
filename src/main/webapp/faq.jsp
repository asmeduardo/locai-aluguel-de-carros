<%@ page import="br.com.locaialugueldecarros.car_rental.model.entities.CommonQuestions" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Perguntas Frequentes</title>
    <link rel="stylesheet" href="css/faq.css">
</head>
<body onload="document.forms[0].submit();">
<form action="faq" method="get" style="display:none;">
    <header>
        <div id="logo">LocAí - Aluguel de Carros</div>
        <div id="menu">
            <nav>
                <a href="home.jsp">Home</a>
                <a href="#">Serviços</a>
                <a href="#">Carros</a>
                <a href="#">FAQ</a>
                <a href="contact.jsp">Fale Conosco</a>
                <div class="dropdown">
                    <a href="#"><img id="account-img" src="images/user.png" alt="icone minha conta"></a>
                    <div class="dropdown-content">
                        <a href="logout.jsp">Sair</a>
                    </div>
                </div>
            </nav>
        </div>
    </header>
    <div class="container">
        <%
            List<CommonQuestions> faqs = (List<CommonQuestions>) request.getAttribute("faqs");
            for (CommonQuestions faq : faqs) {
        %>
        <div class="faq-box" onmouseover="toggleAnswer(this)">
            <div class="faq-question">
                <h3><%= faq.getQuestion() %>
                </h3>
            </div>
            <div class="faq-answer">
                <%= faq.getAnswer() %>
            </div>
        </div>
        <%
            }
        %>
    </div>

    <script src="js/faq.js"></script>
</body>
</html>

