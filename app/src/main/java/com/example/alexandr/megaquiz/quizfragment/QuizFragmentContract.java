package com.example.alexandr.megaquiz.quizfragment;

import java.util.LinkedHashMap;
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
    }

    interface View {
        void setQuestionTextView(String text);

        void setButtonsEnabled(boolean result);

        void setQuestionCounter(String text);

        void setCorrectButtonStyle(int key);

        void showProgressBarAndSetViewVisibility(int viewState, int progressBarState);

        void startQuizResultFragment(int quizSize, int correctAnswers, LinkedHashMap<Integer, Boolean> mapAnswers);
    }

    interface Presenter {
        void initQuestionList(String keyCategory);

        void prepareViewForFirstQuestion();

        void onNextButton();

        void onPrevButton();

        void onAnswer(Answer answer);

        void onDestroy();
    }
}
