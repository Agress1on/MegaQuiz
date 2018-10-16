package com.example.alexandr.megaquiz.quizActivity;

import com.example.alexandr.megaquiz.bankQuestion.Question;

import java.util.List;
import java.util.Map;

/**
 * Created by Alexandr Mikhalev on 13.09.2018.
 *
 * @author Alexandr Mikhalev
 */
public interface QuizActivityContract {
    interface Model {
        int getQuizSize();
        Question getQuestion(int index);
        void initSelectedQuestionsList(String name);
        int getCurrentIndex();
        void setCurrentIndex(int currentIndex);
        void putAnswerInStorage(int index, boolean answer);
        boolean isAnsweredThisQuestion(int index);
        int getStorageOfResponseSize();
        int getTrueQuestionsCount();
        void setTrueQuestionsCount(int trueQuestionsCount);
    }

    interface View {
        void setQuestionTVText(String text);
        void showToast(String text);
        void buttonSwitcher(boolean result);
        void setQuestionCounter(String text);
        void setTrueQuestionCounter(String text);
    }

    interface Presenter {
        void viewIsReady();
        void onNextButton();
        void onPrevButton();
        void onTrueButton();
        void onFalseButton();
    }
}
