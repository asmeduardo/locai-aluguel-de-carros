package br.com.locaialugueldecarros.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionFactory {
    private static DBConnectionFactory instance = null;
    private Connection connection = null;

    // Utilizando o padrão GRASP Creator para criar uma nova conexão
    private DBConnectionFactory() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/nome_do_seu_banco_aqui",
                    "seu_usuário", "sua_senha");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    // Utilizando o padrão GoF Singleton para garantir que apenas uma instância da conexão seja criada
    public static DBConnectionFactory getInstance() {
        if (instance == null) {
            instance = new DBConnectionFactory();
        }
        return instance;
    }

    // Utilizando o padrão GRASP Information Expert para fornecer acesso à conexão
    public Connection getConnection() {
        return connection;
    }
}