package com.example.alexandr.megaquiz.quizresult.presentation;

import com.example.alexandr.megaquiz.quizresult.QuizResultContract;

/**
 * Created by Alexandr Mikhalev on 24.11.2018.
 *
 * @author Alexandr Mikhalev
 */
public class QuizResultPresenter implements QuizResultContract.Presenter {
    private QuizResultContract.View mView;
    private QuizResultContract.Interactor mInteractor;

    public QuizResultPresenter(QuizResultContract.View view, QuizResultContract.Interactor interactor) {
        this.mView = view;
        this.mInteractor = interactor;
    }

    public String forResultTextView(int size, int correctAnswers, String categoryName) {
        String level;
        int percent = ((correctAnswers * 100) / size);
        if (percent < 50) {
            level = "ужасно";
        } else if (percent < 75) {
            level = "удовлетворительно";
        } else if (percent <= 89) {
            level = "хорошо";
        } else {
            level = "отлично";
        }
        String text = "Вы прошли опрос категории \"" + categoryName + "\". Вы " + level + " владеете знаниями данной в области и дали " + percent + "% верных ответов.\nЧто делать дальше?";
        return text;
    }
}
