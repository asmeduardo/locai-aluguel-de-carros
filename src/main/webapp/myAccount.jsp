<%@ page import="br.com.locaialugueldecarros.authentication.model.User" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <title>Conta de Usuário</title>
    <link rel="stylesheet" href="css/myAccount.css">
    <style>
        .tabs {
            display: flex;
            margin-bottom: 30px;
        }

        .tab {
            background-color: #189CF4;
            color: #FFF;
            padding: 10px 20px;
            border-radius: 10px 10px 0 0;
            cursor: pointer;
            margin-right: 10px;
        }

        .tab .active {
            background-color: #157bb4;
            color: #fff;
        }

        .tab-content {
            display: none;
            padding: 20px;
        }

        .tab-content.active {
            display: block;
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        th, td {
            border: 1px solid #333;
            padding: 8px;
            text-align: left;
        }

        th {
            background-color: #189CF4;
            color: #fff;
        }

        tr:nth-child(even) {
            background-color: #FFF;
        }
    </style>
</head>
<body>
<header>
    <div id="logo">LocAí - Aluguel de Carros</div>
    <div id="menu">
        <nav>
            <a href="home.jsp">Home</a>
            <a href="services.jsp">Serviços</a>
            <a href="cars.jsp">Carros</a>
            <a href="faq.jsp">FAQ</a>
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
<section class="content-container">
    <div class="tabs">
        <div class="tab active" id="tab-1">Meus Dados</div>
        <div class="tab" id="tab-2">Meus Veículos</div>
        <div class="tab" id="tab-3">Colocar meu carro para aluguel</div>
        <div class="tab" id="tab-4">Alterar minha senha</div>
    </div>
    <div class="tab-content active" id="tab-1-content">
        <h2>Meus Dados</h2>
        <table>
            <tr>
                <th>Nome</th>
                <td><%= ((User) session.getAttribute("user")).getFirstName() + " "
                        + ((User) session.getAttribute("user")).getLastName() %>
                </td>
            </tr>
            <tr>
                <th>E-mail</th>
                <td><%= ((User) session.getAttribute("user")).getEmail() %>
                </td>
            </tr>
        </table>
    </div>
    <div class="tab-content" id="tab-2-content">
        <h2>Meus Veículos</h2>
        <table>
            <tr>
                <th>Modelo</th>
                <th>Placa</th>
                <th>Cor</th>
            </tr>

        </table>
    </div>
    <div class="tab-content" id="tab-3-content">
        <h2>Colocar meu carro para aluguel</h2>
        <form action="alugarCarro" method="post">
            <label for="manufacturer">Fabricante:</label>
            <input type="text" id="manufacturer" name="manufacturer"><br>
            <label for="model">Modelo:</label>
            <input type="text" id="model" name="model"><br>
            <label for="year">Ano de Fabricação:</label>
            <input type="number" id="year" name="year"><br>
            <label for="fuel">Combustível:</label>
            <input type="select" id="fuel" name="fuel"><br>
            <label for="steering">Direção:</label>
            <input type="select" id="steering" name="steering"><br>
            <label for="license_plate">Placa do Carro:</label>
            <input type="text" id="license_plate" name="license_plate"><br>
            <label for="color">Cor:</label>
            <input type="text" id="color" name="color"><br>
            <label for="seats">Número de Assentos:</label>
            <input type="number" id="seats" name="seats"><br>
            <label for="ports">Número de Portas:</label>
            <input type="number" id="ports" name="ports"><br>
            <label for="insurer">Seguradora:</label>
            <input type="select" id="insurer" name="insurer"><br>
            <label for="payment_method">Metodo de Pagamento:</label>
            <input type="select" id="payment_method" name="payment_method"><br>
            <label for="rentStartDate">Data e hora de início do aluguel:</label>
            <input type="datetime-local" id="rentStartDate" name="rentStartDate"><br>
            <label for="rentEndDate">Data e hora de término do aluguel:</label>
            <input type="datetime-local" id="rentEndDate" name="rentEndDate"><br>
            <input type="submit" value="Adicionar Carro">
        </form>
    </div>
    <div class="tab-content" id="tab-4-content">
        <h2>Alterar minha senha</h2>
        <form action="mudarSenha" method="post">
            <label for="currentPassword">Senha Atual:</label>
            <input type="password" id="currentPassword" name="currentPassword"><br>
            <label for="newPassword">Nova Senha:</label>
            <input type="password" id="newPassword" name="newPassword"><br>
            <label for="confirmPassword">Confirmar Senha:</label>
            <input type="password" id="confirmPassword" name="confirmPassword"><br>
            <input type="submit" value="Alterar Senha">
        </form>
    </div>
</section>

<script>
    let tabs = document.querySelectorAll('.tab');
    let tabContents = document.querySelectorAll('.tab-content');

    tabs.forEach(function (tab) {
        tab.addEventListener('click', function () {
            let activeTab = document.querySelector('.tab.active');
            let activeTabContent = document.querySelector('.tab-content.active');

            activeTab.classList.remove('active');
            activeTabContent.classList.remove('active');

            this.classList.add('active');
            document.querySelector('#' + this.id + '-content').classList.add('active');
        });
    });
</script>
</body>
</html>