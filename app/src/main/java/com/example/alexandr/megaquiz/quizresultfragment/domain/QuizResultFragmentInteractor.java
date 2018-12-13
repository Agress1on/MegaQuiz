package com.example.alexandr.megaquiz.quizresultfragment.domain;

import com.example.alexandr.megaquiz.bankquestion.BankQuestion;
import com.example.alexandr.megaquiz.bankquestion.Question;
import com.example.alexandr.megaquiz.quizresultfragment.QuizResultFragmentContract;

import java.util.List;
import java.util.Map;

import io.reactivex.Single;
import io.reactivex.functions.Function;

/**
 * Created by Alexandr Mikhalev on 24.11.2018.
 *
 * @author Alexandr Mikhalev
 */
public class QuizResultFragmentInteractor implements QuizResultFragmentContract.Interactor {

    private BankQuestion mBankQuestion;

    public QuizResultFragmentInteractor(BankQuestion bankQuestion) {
        this.mBankQuestion = bankQuestion;
    }

    @Override
    public Single<List<Question>> getQuestion(String key) {
        return mBankQuestion.getBankQuestionsAndAnswers()
                .map(new Function<Map<String, List<Question>>, List<Question>>() {
                    @Override
                    public List<Question> apply(Map<String, List<Question>> stringListMap) throws Exception {
                        return stringListMap.get(key);
                    }
                });
    }
}
