package br.com.locaialugueldecarros.car_rental.service;

import br.com.locaialugueldecarros.car_rental.dao.CommonQuestionsDAO;
import br.com.locaialugueldecarros.car_rental.model.entities.CommonQuestions;

import java.util.List;

public class CommonQuestionsService {

    private final CommonQuestionsDAO commonQuestionsDAO;

    public CommonQuestionsService(CommonQuestionsDAO commonQuestionsDAO) {
        this.commonQuestionsDAO = commonQuestionsDAO;
    }

    public List<CommonQuestions> getAllFAQs() {
        return commonQuestionsDAO.getFAQs();
    }
}
