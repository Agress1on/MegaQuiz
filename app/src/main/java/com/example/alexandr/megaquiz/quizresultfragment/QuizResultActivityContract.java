package com.example.alexandr.megaquiz.quizresultfragment;

/**
 * Created by Alexandr Mikhalev on 24.11.2018.
 *
 * @author Alexandr Mikhalev
 */
public interface QuizResultActivityContract {
    interface Interactor {

    }

    interface View {
        void setResultTextView(String text);
    }

    interface Presenter {
        String forResultTextView(int size, int correctAnswers, String categoryName);
    }
}
