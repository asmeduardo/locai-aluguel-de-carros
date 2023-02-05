package br.com.locaialugueldecarros.car_rental.model.entities;

public class CommonQuestions {

    private int id;
    private String question;
    private String answer;

    public CommonQuestions() {
    }

    public CommonQuestions(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public CommonQuestions(int id, String question, String answer) {
        this.id = id;
        this.question = question;
        this.answer = answer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}