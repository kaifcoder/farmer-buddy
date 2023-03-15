package com.example.farmerbuddy;

public class QuestionAnswer {
    private String question;
    private String answer;

    public QuestionAnswer(){


    }

    public QuestionAnswer(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }
}

