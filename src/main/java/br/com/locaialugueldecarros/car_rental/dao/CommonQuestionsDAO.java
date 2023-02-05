package br.com.locaialugueldecarros.car_rental.dao;

import br.com.locaialugueldecarros.car_rental.model.entities.CommonQuestions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommonQuestionsDAO {

    private final Connection connection;

    public CommonQuestionsDAO(Connection conn) {
        this.connection = conn;
    }

    public List<CommonQuestions> getFAQs() {
        List<CommonQuestions> commonQuestions = new ArrayList<>();
        try {
            String query = "SELECT * FROM faq";
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String question = rs.getString("question");
                String answer = rs.getString("answer");
                CommonQuestions faq = new CommonQuestions(id, question, answer);
                commonQuestions.add(faq);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return commonQuestions;
    }
}

