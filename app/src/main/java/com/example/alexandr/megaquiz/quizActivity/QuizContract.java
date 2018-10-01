package com.example.alexandr.megaquiz.quizActivity;

import com.example.alexandr.megaquiz.bankQuestion.Question;

import java.util.List;
import java.util.Map;

/**
 * Created by Alexandr Mikhalev on 13.09.2018.
 *
 * @author Alexandr Mikhalev
 */
public interface QuizContract {
    interface Model {
        List<Question> initSelectedQuestionsList(String name);
        int getCurrentIndex();
        void setCurrentIndex(int currentIndex);
        List<Question> getQuestionList();
        Map<Integer, Boolean> getResultQuiz();
        void setResultQuiz(Map<Integer, Boolean> resultQuiz);
        int getTrueQuestionsCount();
        void setTrueQuestionsCount(int trueQuestionsCount);
    }

    interface View {
        void questionAnswerResult();
        void buttonSwitcher(boolean b);
        void pressChecker();
        void quizResult();
    }

    interface Presenter {
        Question getFirstQuestion();
        Question getNextQuestion();
        Question getPrevQuestion();
        void pressTrueButton();
        void pressFalseButton();
        boolean resultQuestion();
        boolean pressCheck();
        boolean isQuizFinish();
        String quizResult();
    }
}
