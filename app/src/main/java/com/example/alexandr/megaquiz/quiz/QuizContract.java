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
        List<Question> getQuestions();
        int checkQuestions(Map<Integer, Boolean> answers);
    }

    interface View {
        void setQuestionTVText(String text);
        void showToast(String text);
        void buttonSwitcher(boolean result);
        void setQuestionCounter(String text);
        void setTrueQuestionCounter(String text);
        void setNumberQuestionCounter(String text);
    }

    interface Presenter {
        void viewIsReady();
        void onNextButton();
        void onPrevButton();
        void onTrueButton();
        void onFalseButton();
    }
}
