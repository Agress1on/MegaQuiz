package com.example.alexandr.megaquiz.bankquestion;

/**
 * Created by Alexandr Mikhalev on 25.09.2018.
 *
 * @author Alexandr Mikhalev
 */
public class Question {

    private String mQuestion;
    private boolean mTrueAnswer;

    public Question(String question, boolean trueAnswer) {
        mQuestion = question;
        mTrueAnswer = trueAnswer;
    }

    public String getTextQuestion() {
        return mQuestion;
    }

    public boolean isTrueAnswer() {
        return mTrueAnswer;
    }
}
