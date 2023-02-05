<%@ page import="br.com.locaialugueldecarros.authentication.model.User" %>
<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    User user = (User)session.getAttribute("user");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Pesquisar Carros</title>
    <link rel="stylesheet" href="css/cars.css">
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
</head>
<body>
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
<main>
    <div class="content-container">
        <h1>Pesquisar Carros</h1>
        <form action="alugarCarro">
            <select id="pickupAddress">
                <option>--Endereço de retirada--</option>
                <c:forEach items="${addresses}" var="address">
                    <option value="${address.address_id}">${address.address}</option>
                </c:forEach>
            </select>
            <select id="returnAddress">
                <option>--Endereço de devolução--</option>
                <c:forEach items="${addresses}" var="address">
                    <option value="${address.address_id}">${address.address}</option>
                </c:forEach>
            </select>
            <input type="date" id="pickupDate">
            <input type="date" id="returnDate">
            <input type="button" value="Buscar" onclick="searchCars()">
        </form>
        <br>
        <div id="carCatalog">
            ${requestScope.avai}
            <c:forEach items="${cars}" var="car" varStatus="status">
                <c:if test="${status.index % 6 == 0}">
                    <div class="pageBreak">
                </c:if>
                <div class="carCard">
                    <img src="${car.photo}" alt="car photo">
                    <ul>
                        <li>Rent diary: ${car.rentDiary}</li>
                        <li>Make: ${car.make}</li>
                        <li>Model: ${car.model}</li>
                        <li>Year: ${car.year}</li>
                        <li>Registration Number: ${car.registration_number}</li>
                        <li>Owner: ${car.owner.name}</li>
                        <li>Insurance: ${car.owner.insurance.company_name}</li>
                    </ul>
                    <form action="alugarCarro" method="post">
                        <input type="hidden" name="car_id" value="${car.car_id}">
                        <select id="renter_address" name="renter_address">
                            <option>--Selecione o seu endereço--</option>
                            <c:forEach items="${renter_addresses}" var="address">
                                <option value="${address.address_id}">${address.address}</option>
                            </c:forEach>
                        </select>
                        <select id="payment_method" name="payment_method">
                            <option>--Selecione o método de pagamento--</option>
                            <c:forEach items="${payment_methods}" var="method">
                                <option value="${method.method_id}">${method.name}</option>
                            </c:forEach>
                        </select>
                        <input type="submit" value="Alugar Agora">
                    </form>
                </div>
                <c:if test="${status.index % 6 == 5 || status.last}">
                    </div>
                </c:if>
            </c:forEach>
        </div>
        <div id="pagination">
            <c:forEach begin="1" end="${numberOfPages}" var="i">
                <a href="#" onclick="showPage(${i})">${i}</a>
            </c:forEach>
        </div>
    </div>
</main>

</body>
</html>
