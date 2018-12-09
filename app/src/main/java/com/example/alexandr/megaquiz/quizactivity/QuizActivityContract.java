package com.example.alexandr.megaquiz.quizactivity;

import java.util.List;
import java.util.Map;

/**
 * Created by Alexandr Mikhalev on 13.09.2018.
 *
 * @author Alexandr Mikhalev
 */
public interface QuizActivityContract {
    interface Interactor {
        List<String> getQuestions(String key);

        int checkQuestions(Map<Integer, Boolean> answers);
    }

    interface View {
        void setQuestionTextView(String text);

        void switchButton(boolean result);

        /*
        void setStaticQuizSizeTextView(String text);

        void setStaticNumberOfQuestion(String text);
        */

        void setQuestionCount(String text);

        String sentToPresenterSelectedCategory();

        void startQuizResultActivity(int quizSize, int correctAnswers);

        void setCorrectButtonStyle(int key);
    }

    interface Presenter {
        void prepareViewForFirstQuestion();

        void onNextButton();

        void onPrevButton();

        void onTrueButton();

        void onFalseButton();
    }
}
