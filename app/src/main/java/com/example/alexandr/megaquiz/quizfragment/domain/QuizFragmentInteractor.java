package com.example.alexandr.megaquiz.quizfragment.domain;

import android.util.Pair;

import com.example.alexandr.megaquiz.bankquestion.BankQuestion;
import com.example.alexandr.megaquiz.bankquestion.Question;
import com.example.alexandr.megaquiz.quizfragment.Answer;
import com.example.alexandr.megaquiz.quizfragment.QuizFragmentContract;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Single;
import io.reactivex.functions.Function;

/**
 * Created by Alexandr Mikhalev on 10.12.2018.
 *
 * @author Alexandr Mikhalev
 */
public class QuizFragmentInteractor implements QuizFragmentContract.Interactor {

    private BankQuestion mBankQuestion;

    public QuizFragmentInteractor(BankQuestion bankQuestion) {
        this.mBankQuestion = bankQuestion;
    }

    @Override
    public Single<Pair<String, List<String>>> getQuestionsForRandom() {
        return mBankQuestion.getBankQuestionsAndAnswers()
                .map(new Function<Map<String, List<Question>>, Map<String, List<Question>>>() {
                    @Override
                    public Map<String, List<Question>> apply(Map<String, List<Question>> stringListMap) throws Exception {
                        Map<String, List<Question>> map = new HashMap<>();
                        for (Map.Entry<String, List<Question>> entry : stringListMap.entrySet()) {
                            if (entry.getValue().size() > 0)
                                map.put(entry.getKey(), entry.getValue());
                        }
                        return map;
                    }
                })
                .map(new Function<Map<String, List<Question>>, Pair<String, List<Question>>>() {
                    @Override
                    public Pair<String, List<Question>> apply(Map<String, List<Question>> stringListMap) throws Exception {
                        List<String> list = new ArrayList<>();
                        for (Map.Entry<String, List<Question>> entry : stringListMap.entrySet()) {
                            list.add(entry.getKey());
                        }
                        int first = 0;
                        int second = list.size();
                        int random = first + (int) (Math.random() * second);
                        Pair<String, List<Question>> pair = new Pair<>(list.get(random), stringListMap.get(list.get(random)));
                        return pair;
                    }
                })
                .map(new Function<Pair<String, List<Question>>, Pair<String, List<String>>>() {
                    @Override
                    public Pair<String, List<String>> apply(Pair<String, List<Question>> stringListPair) throws Exception {
                        List<String> list = new ArrayList<>();
                        for (Question question : stringListPair.second) {
                            list.add(question.getTextQuestion());
                        }
                        Pair<String, List<String>> pair = new Pair<>(stringListPair.first, list);
                        return pair;
                    }
                });
    }

    @Override
    public Single<List<String>> getQuestions(String key) {
        return mBankQuestion.getBankQuestionsAndAnswers()
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
                        }
                        return list;
                    }
                });
    }

    @Override
    public Single<String> getStringForRandom() {
        return mBankQuestion.getBankQuestionsAndAnswers()
                .map(new Function<Map<String, List<Question>>, List<String>>() {
                    @Override
                    public List<String> apply(Map<String, List<Question>> stringListMap) throws Exception {
                        List<String> listWithoutEmpty = new ArrayList<>();
                        for (Map.Entry<String, List<Question>> entry : stringListMap.entrySet()) {
                            if (entry.getValue().size() > 0)
                                listWithoutEmpty.add(entry.getKey());
                        }
                        return listWithoutEmpty;
                    }
                })
                .map(new Function<List<String>, String>() {
                    @Override
                    public String apply(List<String> stringList) throws Exception {
                        int first = 0;
                        int second = stringList.size();
                        int random = first + (int) (Math.random() * second);
                        return stringList.get(random);
                    }
                });
    }

    @Override
    public Single<Integer> checkQuestions(String key, Map<Integer, Answer> answers) {
        return mBankQuestion.getBankQuestionsAndAnswers()
                .map(new Function<Map<String, List<Question>>, List<Boolean>>() {
                    @Override
                    public List<Boolean> apply(Map<String, List<Question>> stringListMap) throws Exception {
                        List<Question> list = stringListMap.get(key);
                        List<Boolean> list2 = new ArrayList<>();
                        for (Question question : list) {
                            list2.add(question.isTrueAnswer());
                        }
                        return list2;
                    }
                })
                .map(new Function<List<Boolean>, Integer>() {
                    @Override
                    public Integer apply(List<Boolean> booleans) throws Exception {
                        int rightAnswers = 0;
                        for (Map.Entry<Integer, Answer> entry : answers.entrySet()) {
                            if (booleans.get(entry.getKey()) == entry.getValue().isResult())
                                rightAnswers++;
                        }
                        return rightAnswers;
                    }
                });
    }
}
