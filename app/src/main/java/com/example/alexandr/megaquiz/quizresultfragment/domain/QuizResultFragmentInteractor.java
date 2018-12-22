package com.example.alexandr.megaquiz.quizresultfragment.domain;

import com.example.alexandr.megaquiz.bankquestion.BankQuestion;
import com.example.alexandr.megaquiz.bankquestion.Question;
import com.example.alexandr.megaquiz.quizresultfragment.QuizResultFragmentContract;
import com.example.alexandr.megaquiz.quizresultfragment.QuizResultItem;

import java.util.ArrayList;
import java.util.HashMap;
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
    public Single<List<QuizResultItem>> getQuizResultItems(String key, HashMap<Integer, Boolean> userAnswersMap) {
        return mBankQuestion.getBankQuestionsAndAnswers()
                .map(new Function<Map<String, List<Question>>, List<Question>>() {
                    @Override
                    public List<Question> apply(Map<String, List<Question>> stringListMap) throws Exception {
                        List<Question> list = new ArrayList<>(stringListMap.get(key));
                        return list;
                    }
                })
                .map(new Function<List<Question>, List<QuizResultItem>>() {
                    @Override
                    public List<QuizResultItem> apply(List<Question> questions) throws Exception {
                        List<QuizResultItem> list = new ArrayList<>();
                        for (HashMap.Entry<Integer, Boolean> map : userAnswersMap.entrySet()) {
                            QuizResultItem quizResultItem =
                                    new QuizResultItem(map.getKey(), questions.get(map.getKey()).getTextQuestion(), questions.get(map.getKey()).isTrueAnswer(), map.getValue());
                            list.add(quizResultItem);
                        }
                        return list;
                    }
                });
    }
}
