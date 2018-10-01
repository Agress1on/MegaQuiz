package com.example.alexandr.megaquiz.quizActivity;

import com.example.alexandr.megaquiz.bankQuestion.Question;

import java.util.Map;

/**
 * Created by Alexandr Mikhalev on 13.09.2018.
 *
 * @author Alexandr Mikhalev
 */
public class QuizActivityPresenter implements QuizContract.Presenter {
    private QuizContract.View mView;
    private QuizContract.Model mModel;

    public QuizActivityPresenter(QuizContract.View view) {
        this.mView = view;
        this.mModel = new QuizActivityModel();
    }

    @Override
    public Question getFirstQuestion() {
        return mModel.getQuestionList().get(0);
    }

    @Override
    public Question getNextQuestion() {
        int currentIndex = (mModel.getCurrentIndex() + 1) % mModel.getQuestionList().size();
        mModel.setCurrentIndex(currentIndex);
        return mModel.getQuestionList().get(currentIndex);
    }

    @Override
    public Question getPrevQuestion() {
        int currentIndex = (mModel.getCurrentIndex() - 1) % mModel.getQuestionList().size();
        if (currentIndex < 0) currentIndex = 0;
        mModel.setCurrentIndex(currentIndex);
        return mModel.getQuestionList().get(currentIndex);
    }

    @Override
    public void pressTrueButton() {
        Map<Integer, Boolean> resultQuiz = mModel.getResultQuiz();
        resultQuiz.put(mModel.getCurrentIndex(), true);
        mModel.setResultQuiz(resultQuiz);
    }

    @Override
    public void pressFalseButton() {
        Map<Integer, Boolean> resultQuiz = mModel.getResultQuiz();
        resultQuiz.put(mModel.getCurrentIndex(), false);
        mModel.setResultQuiz(resultQuiz);
    }

    @Override
    public boolean resultQuestion() {
        boolean result = false;
        for (Map.Entry<Integer, Boolean> entry : mModel.getResultQuiz().entrySet()) {
            if (entry.getKey() == mModel.getCurrentIndex()) {
                if (entry.getValue() == mModel.getQuestionList().get(mModel.getCurrentIndex()).isTrueAnswer()) {
                    result = true;
                }
            }
        }
        return result;
    }

    @Override
    public boolean pressCheck() {
        boolean check = false;
        for (Map.Entry<Integer, Boolean> entry : mModel.getResultQuiz().entrySet()) {
            if (entry.getKey() == mModel.getCurrentIndex()) check = true;
        }
        return check;
    }

    @Override
    public boolean isQuizFinish() {
        return mModel.getResultQuiz().size() == mModel.getQuestionList().size();
    }

    @Override
    public String quizResult() {
        int count = mModel.getTrueQuestionsCount();
        for (Map.Entry<Integer, Boolean> entry : mModel.getResultQuiz().entrySet()) {
            if (mModel.getQuestionList().get(entry.getKey()).isTrueAnswer() == entry.getValue()) count++;
        }
        mModel.setTrueQuestionsCount(count);

        return "Всего вопросов: " + mModel.getQuestionList().size() + ", а правильных ответов: " + mModel.getTrueQuestionsCount();
    }


}
