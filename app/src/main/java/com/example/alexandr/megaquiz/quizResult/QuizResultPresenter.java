package com.example.alexandr.megaquiz.quizResult;

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
        String level = "";
        float percent = (float) ((correctAnswers * 100) / size);
        if (percent <= 50) {
            level = "плохо";
        } else if (percent > 75 && percent <= 89) {
            level = "хорошо";
        } else if (percent >= 90) {
            level = "отлично";
        }
        String text = "Вы прошли опрос категории " + categoryName + ". Вы " + level + " владеете знаниями данной в области и дали " + percent + "% верных ответов. Что делать дальше?";
        return text;
    }
}
