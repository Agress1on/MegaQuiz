package com.example.alexandr.megaquiz.quizfragment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Single;

/**
 * Created by Alexandr Mikhalev on 10.12.2018.
 *
 * @author Alexandr Mikhalev
 */
public interface QuizFragmentContract {

    interface Interactor {
        Single<List<String>> getQuestions(String key);

        Single<Integer> checkQuestions(String key, Map<Integer, Answer> answers);

        Single<String> getStringForRandom();
    }

    interface View {
        void setQuestionTextView(String text);

        void setButtonsEnabled(boolean result);

        void setQuestionCounter(String text);

        void setCorrectButtonStyle(int key);

        void showLoading();

        void hideLoading();
    }

    interface Presenter {
        void onStartView();

        void onNextButton();

        void onPrevButton();

        void onAnswer(Answer answer);

        void onDestroy();
    }

    interface Router {
        void setQuizFragment();

        void goToQuizResultFragment(int correctAnswers, String categoryName, HashMap<Integer, Boolean> map);
    }
}
