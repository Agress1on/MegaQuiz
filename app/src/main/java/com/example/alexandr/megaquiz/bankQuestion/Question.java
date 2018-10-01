package com.example.alexandr.megaquiz.bankQuestion;

/**
 * Created by Alexandr Mikhalev on 25.09.2018.
 *
 * @author Alexandr Mikhalev
 */
public class Question {
    private String question;
    private boolean trueAnswer;

    public Question(String question, boolean trueAnswer) {
        this.question = question;
        this.trueAnswer = trueAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public boolean isTrueAnswer() {
        return trueAnswer;
    }
}
