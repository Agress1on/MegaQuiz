package com.example.alexandr.megaquiz.quizResult;

/**
 * Created by Alexandr Mikhalev on 24.11.2018.
 *
 * @author Alexandr Mikhalev
 */
public interface QuizResultContract {
    interface Interactor {

    }
    interface View {
        void setResultTextView(String text);
    }

    interface Presenter {
        String forResultTextView(int size, int correctAnswers, String categoryName);
    }
}
