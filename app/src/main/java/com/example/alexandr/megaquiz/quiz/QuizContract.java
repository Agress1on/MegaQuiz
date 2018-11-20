package com.example.alexandr.megaquiz.quiz;

import com.example.alexandr.megaquiz.bankQuestion.Question;

import java.util.List;
import java.util.Map;

/**
 * Created by Alexandr Mikhalev on 13.09.2018.
 *
 * @author Alexandr Mikhalev
 */
public interface QuizContract {
    interface Interactor {
        List<String> getQuestions(String key);

        int checkQuestions(Map<Integer, Boolean> answers);
    }

    interface View {
        void setQuestionTVText(String text);

        void showToast(String text);

        void switchButton(boolean result);

        void setQuestionCounter(String text);

        void setNumberQuestionCounter(String text);

        String sentToPresenterChoosenCategory();
    }

    interface Presenter {
        void prepareViewForFirstQuestion();

        void onNextButton();

        void onPrevButton();

        void onTrueButton();

        void onFalseButton();
    }
}
