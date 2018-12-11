package com.example.alexandr.megaquiz.quizfragment;

import java.util.List;
import java.util.Map;

/**
 * Created by Alexandr Mikhalev on 10.12.2018.
 *
 * @author Alexandr Mikhalev
 */
public interface QuizFragmentContract {
    interface Interactor {
        List<String> getQuestions(String key);

        int checkQuestions(Map<Integer, Answer> answers);
    }

    interface View {
        void setQuestionTextView(String text);

        void setButtonsEnabled(boolean result);

        void setQuestionCounter(String text);

        void startQuizResultFragment(int quizSize, int correctAnswers);

        void setCorrectButtonStyle(int key);
    }

    interface Presenter {
        void initQuestionList(String keyCategory);

        void prepareViewForFirstQuestion();

        void onNextButton();

        void onPrevButton();

        void onAnswer(Answer answer);

        /*
        void onTrueButton();

        void onFalseButton();
        */
    }
}
