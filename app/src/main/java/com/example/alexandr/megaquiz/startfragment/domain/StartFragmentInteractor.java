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
    public Single<String> getRxStringCategoryForRandomStart() {
        return mBank.getListKeysNotEmptyCategories()
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

    /*
    @Override
    public String getStringCategoryForRandomStart() {
        boolean flag = true;
        String randomCategory = null;
        while (flag) {
            randomCategory = getKeyRandomCategory();
            for (Map.Entry<String, List<Question>> entry : mBankQuestion.entrySet()) {
                if (entry.getKey().equals(randomCategory)) {
                    if (entry.getValue().size() > 0) flag = false;
                    break;
                }
            }
        }
        return randomCategory;
    }
    */

    /*
    private String getKeyRandomCategory() {
        List<String> list = new ArrayList<>(mBankQuestion.keySet());
        int first = 0;
        int second = list.size();
        int random = first + (int) (Math.random() * second);
        return list.get(random);
    }
    */
}
