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
    }

    interface View {
        void setQuestionTextView(String text);

        void setButtonsEnabled(boolean result);

        void setQuestionCounter(String text);

        void startQuizResultFragment(int quizSize, int correctAnswers);

        void setCorrectButtonStyle(int key);

        void setProgressBar(boolean flag);
    }

    interface Presenter {
        HashMap<Integer, Boolean> getAnswers();

        void initQuestionList(String keyCategory);

        void prepareViewForFirstQuestion();

        void onNextButton();

        void onPrevButton();

        void onAnswer(Answer answer);

        void onDestroy();

    }
}
