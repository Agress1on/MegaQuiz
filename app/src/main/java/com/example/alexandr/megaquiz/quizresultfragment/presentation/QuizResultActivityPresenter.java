package com.example.alexandr.megaquiz.quizresultfragment.presentation;

import com.example.alexandr.megaquiz.quizresultfragment.QuizResultActivityContract;

/**
 * Created by Alexandr Mikhalev on 24.11.2018.
 *
 * @author Alexandr Mikhalev
 */
public class QuizResultActivityPresenter implements QuizResultActivityContract.Presenter {
    private QuizResultActivityContract.View mView;
    private QuizResultActivityContract.Interactor mInteractor;

    public QuizResultActivityPresenter(QuizResultActivityContract.View view, QuizResultActivityContract.Interactor interactor) {
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
