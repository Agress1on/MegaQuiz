package com.example.alexandr.megaquiz.quizfragment.domain;

import com.example.alexandr.megaquiz.bankquestion.BankQuestion;
import com.example.alexandr.megaquiz.bankquestion.Question;
import com.example.alexandr.megaquiz.quizfragment.Answer;
import com.example.alexandr.megaquiz.quizfragment.QuizFragmentContract;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Single;
import io.reactivex.functions.Function;

/**
 * Created by Alexandr Mikhalev on 10.12.2018.
 *
 * @author Alexandr Mikhalev
 */
public class QuizFragmentInteractor implements QuizFragmentContract.Interactor {

    private List<Boolean> mTrueAnswers;
    private int mRightAnswersCounter;

    private BankQuestion mBank;

    public QuizFragmentInteractor(BankQuestion bankQuestion) {
        this.mBank = bankQuestion;
        this.mTrueAnswers = new ArrayList<>();
        this.mRightAnswersCounter = 0;
    }

    @Override
    public Single<List<String>> getQuestions(String key) {
        return Single.just(mBank.getBankQuestion())
                .map(new Function<Map<String, List<Question>>, List<Question>>() {
                    @Override
                    public List<Question> apply(Map<String, List<Question>> stringListMap) throws Exception {
                        return stringListMap.get(key);
                    }
                })
                .map(new Function<List<Question>, List<String>>() {
                    @Override
                    public List<String> apply(List<Question> questions) throws Exception {
                        List<String> list = new ArrayList<>();
                        for (Question question : questions) {
                            list.add(question.getTextQuestion());
                            mTrueAnswers.add(question.isTrueAnswer()); // должно убраться в итоге
                        }
                        return list;
                    }
                }).delay(2, TimeUnit.SECONDS);
    }

    @Override
    public int checkQuestions(Map<Integer, Answer> answers) {
        for (Map.Entry<Integer, Answer> entry : answers.entrySet()) {
            if (mTrueAnswers.get(entry.getKey()) == entry.getValue().isResult()) mRightAnswersCounter++;
        }
        return mRightAnswersCounter;
    }
}
