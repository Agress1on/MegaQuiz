package com.example.alexandr.megaquiz.quizresultfragment;

/**
 * Created by Alexandr Mikhalev on 13.12.2018.
 *
 * @author Alexandr Mikhalev
 */
public class QuizResultItem {

    private int mNumberOfQuestion;
    private String mQuestion;
    private boolean mRealAnswer;
    private boolean mUserAnswer;

    public QuizResultItem(int numberOfQuestion, String question, boolean realAnswer, boolean userAnswer) {
        mNumberOfQuestion = numberOfQuestion;
        mQuestion = question;
        mRealAnswer = realAnswer;
        mUserAnswer = userAnswer;
    }

    public int getNumberOfQuestion() {
        return mNumberOfQuestion;
    }

    public String getQuestion() {
        return mQuestion;
    }

    public boolean isRealAnswer() {
        return mRealAnswer;
    }

    public boolean isUserAnswer() {
        return mUserAnswer;
    }
}
