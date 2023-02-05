## Instruções para Executar o Projeto LocAí - Aluguel de Carros (o projeto ainda não está terminado)

1. Certifique-se de ter o Java e o Maven instalados na sua máquina. Se não os tiver, siga estes passos:
   a. Para instalar o Java, acesse a página de downloads da Oracle Java SE (https://www.oracle.com/java/technologies/javase-downloads.html) e baixe a versão mais recente do Java Development Kit (JDK). Siga as instruções de instalação em https://docs.oracle.com/en/java/javase/19/install/overview-jdk-installation.html
   b. Para instalar o Maven, acesse o site Apache Maven (https://maven.apache.org/download.cgi) e baixe a versão mais recente. Siga as instruções de instalação em https://maven.apache.org/install.html

2. Clone o conteúdo do repositório para sua máquina.

3. Entre na pasta raiz do projeto.

4. Abra um terminal do sistema na pasta raiz do projeto.

5. Execute o comando "mvn clean install" para baixar todas as dependências e compilar o projeto.

6. Execute o comando "mvn jetty:run" para iniciar o servidor Jetty e rodar a aplicação.

7. Acesse a aplicação no navegador com o endereço "http://localhost:8080".

Observação: A porta 8080 pode ser alterada no arquivo "pom.xml", se necessário.

## NOTA

Para que todas as funcionalidades do projeto funcionem corretamente, será necessário criar as tabelas do banco de dados mySQL, criar suas chaves de recaptcha no site do Google e criar a senha de aplicativo nas configurações do seu email para poder enviar os emails de confirmação de criação de conta e recuperação de senha.

8. Criar as tabelas do banco de dados e conectar-se a ele:

a) Acesse as classes DAO no diretório "dao" do projeto e analise as consultas que acessam o banco de dados e veja quais tabelas e colunas precisam ser criadas.
b) Depois de criar as tabelas necessárias, acesse a classe BDConnectionFactory responsável por conectar ao banco de dados e insira as informações de conexão com o seu banco de dados.
c) Adicione o nome do seu banco ao final do link "jdbc:mysql://localhost:3306/nome_do_seu_banco".
d) Adicione o nome de usuário e a senha, caso exista uma senha em seu banco.

Caso queira, segue os codigos sql que utilizei para a criação as tabelas:

CREATE TABLE users (
user_id INT NOT NULL AUTO_INCREMENT,
first_name VARCHAR(255) NOT NULL,
last_name VARCHAR(255) NOT NULL,
email VARCHAR(255) NOT NULL,
password VARCHAR(255) NOT NULL,
phone VARCHAR(255) NOT NULL DEFAULT 'N/A',
cnh VARCHAR(255) NOT NULL DEFAULT 'N/A',
PRIMARY KEY (user_id)
);

CREATE TABLE addresses (
address_id INT NOT NULL AUTO_INCREMENT,
street VARCHAR(255) NOT NULL,
number INT NOT NULL,
neighborhood VARCHAR(255) NOT NULL,
city ​​VARCHAR(255) NOT NULL,
state VARCHAR(255) NOT NULL,
PRIMARY KEY (address_id)
);

CREATE TABLE cars (
car_id INT NOT NULL AUTO_INCREMENT,
manufacturer VARCHAR(255),
model VARCHAR(255) NOT NULL,
year INT NOT NULL,
fuel VARCHAR(255) NOT NULL,
steering VARCHAR(255) NOT NULL,
license_plate VARCHAR(255) NOT NULL,
color VARCHAR(255) NOT NULL,
INT seats NOT NULL,
ports INT NOT NULL,
PRIMARY KEY (car_id)
);

CREATE TABLE car_details (
car_id INT NOT NULL,
rental_price DECIMAL(10,2) NOT NULL,
payment_method VARCHAR(255) NOT NULL,
PRIMARY KEY(car_id),
FOREIGN KEY (car_id) REFERENCES cars(car_id)
);

CREATE TABLE insurers (
insurer_id INT NOT NULL AUTO_INCREMENT,
name VARCHAR(255) NOT NULL,
PRIMARY KEY (insurer_id)
);

CREATE TABLE car_insurers (
car_id INT NOT NULL,
insurer_id INT NOT NULL,
PRIMARY KEY(car_id, insurer_id),
FOREIGN KEY (car_id) REFERENCES cars(car_id),
FOREIGN KEY (insurer_id) REFERENCES insurers(insurer_id)
);

CREATE TABLE payments (
payment_id INT NOT NULL AUTO_INCREMENT,
amount DECIMAL(10,2) NOT NULL,
method VARCHAR(255) NOT NULL,
PRIMARY KEY (payment_id)
);

CREATE TABLE car_rentals (
rental_id INT NOT NULL AUTO_INCREMENT,
car_id INT NOT NULL,
pickup_address_id INT NOT NULL,
pickup_datetime DATETIME NOT NULL,
return_address_id INT NOT NULL,
return_datetime DATETIME NOT NULL,
rental_type ENUM('locatario','locador'),
PRIMARY KEY (rental_id)
);

CREATE TABLE parking_spaces (
address_id INT NOT NULL,
parking_spaces INT NOT NULL, PRIMARY KEY (address_id),
FOREIGN KEY (address_id) REFERENCES addresses(address_id)
);

ALTER TABLE car_rentals ADD FOREIGN KEY (car_id) REFERENCES cars(car_id), ADD FOREIGN KEY (pickup_address_id) REFERENCES addresses(address_id), ADD FOREIGN KEY (return_address_id) REFERENCES addresses(address_id);

CREATE TABLE user_addresses (
user_id INT NOT NULL,
address_id INT NOT NULL,
PRIMARY KEY(user_id, address_id),
FOREIGN KEY (user_id) REFERENCES users(user_id),
FOREIGN KEY (address_id) REFERENCES addresses(address_id)
);

CREATE TABLE car_rentals_payments (
rental_id INT NOT NULL,
payment_id INT NOT NULL,
PRIMARY KEY(rental_id, payment_id),
FOREIGN KEY (rental_id) REFERENCES car_rentals(rental_id),
FOREIGN KEY (payment_id) REFERENCES payments(payment_id)
);CREATE TABLE car_owners (
car_id INT NOT NULL,
user_id INT NOT NULL,
PRIMARY KEY(car_id, user_id),
FOREIGN KEY (car_id) REFERENCES cars(car_id),
FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE faq (
  id INT NOT NULL AUTO_INCREMENT,
  question VARCHAR(255) NOT NULL,
  answer VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE contact (
  id INT NOT NULL AUTO_INCREMENT,
  first_name VARCHAR(255) NOT NULL,
  last_name VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  message VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
);


9 - Chaves ReCAPTCHA:

a) Acesse o link do Google ReCAPTCHA (https://www.google.com/recaptcha/admin/create) para criar suas chaves de API e siga o passo a passo.
b) Depois de criar suas chaves, adicione a chave do site no atributo "data-sitekey" nas páginas JSP onde há um ReCAPTCHA.
c) Adicione sua chave secreta na variável "secret" na classe RecaptchaUtil, no diretório "util" do projeto.

10 - Senha de aplicativo do Google:

Para o projeto, utilizei um e-mail do Google para enviar os e-mails da aplicação. Se desejar utilizar outro e-mail, é necessário modificar o código para se adequar ao provedor escolhido e obter informações sobre como funcionam os envios de e-mails.

a) Entre na sua conta de e-mail que será utilizada para o envio de e-mails, clique no ícone da sua conta no canto superior direito e clique em "Gerenciar sua conta do Google".
b) No menu à esquerda da tela, clique em "Segurança".
c) Siga os passos para ativar a verificação em duas etapas na sua conta de e-mail.
d) Depois de ativar a verificação em duas etapas, clique em "Senhas de aplicativo" e siga os passos para criar sua senha.
e) Insira sua senha na variável "FROM_EMAIL_PASSWORD" na classe EmailUtil, no diretório "util" do projeto.



