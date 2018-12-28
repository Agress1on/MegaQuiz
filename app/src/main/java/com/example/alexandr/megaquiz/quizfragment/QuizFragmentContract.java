package com.example.alexandr.megaquiz.quizfragment;

import android.util.Pair;

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

        Single<Pair<String, List<String>>> getQuestionsForRandom();
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
        void attachView(QuizFragmentContract.View view, QuizFragmentContract.Router router);

        void detachView();

        void onStart();

        void onNextButton();

        void onPrevButton();

        void onAnswer(Answer answer);

        void onStop();
    }

    interface Router {
        void goToQuizResultFragment(int correctAnswers, String categoryName, HashMap<Integer, Boolean> map);
    }
}
