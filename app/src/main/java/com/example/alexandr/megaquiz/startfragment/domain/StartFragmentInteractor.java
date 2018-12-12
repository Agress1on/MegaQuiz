package com.example.alexandr.megaquiz.startfragment.domain;

import com.example.alexandr.megaquiz.bankquestion.BankQuestion;
import com.example.alexandr.megaquiz.bankquestion.Question;
import com.example.alexandr.megaquiz.startfragment.StartFragmentContract;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Single;
import io.reactivex.functions.Function;

/**
 * Created by Alexandr Mikhalev on 07.12.2018.
 *
 * @author Alexandr Mikhalev
 */
public class StartFragmentInteractor implements StartFragmentContract.Interactor {

    private BankQuestion mBank;

    public StartFragmentInteractor(BankQuestion bankQuestion) {
        mBank = bankQuestion;
    }

    @Override
    public Single<String> getStringCategoryForRandomStart() {
        return mBank.getBankQuestionsAndAnswers()
                .map(new Function<Map<String, List<Question>>, List<String>>() {
                    @Override
                    public List<String> apply(Map<String, List<Question>> stringListMap) throws Exception {
                        List<String> list = new ArrayList<>();
                        for (Map.Entry<String, List<Question>> entry : stringListMap.entrySet()) {
                            if (entry.getValue().size() > 0) list.add(entry.getKey());
                        }
                        return list;
                    }
                })
                .map(new Function<List<String>, String>() {
                    @Override
                    public String apply(List<String> strings) throws Exception {
                        int first = 0;
                        int second = strings.size();
                        int random = first + (int) (Math.random() * second);
                        return strings.get(random);
                    }
                });
    }
}
