package com.example.alexandr.megaquiz.startfragment.interactor;

import com.example.alexandr.megaquiz.bankquestion.BankQuestion;
import com.example.alexandr.megaquiz.bankquestion.Question;
import com.example.alexandr.megaquiz.startfragment.StartFragmentContract;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Alexandr Mikhalev on 07.12.2018.
 *
 * @author Alexandr Mikhalev
 */
public class StartFragmentInteractor implements StartFragmentContract.Interactor {
    private Map<String, List<Question>> mBankQuestion;

    public StartFragmentInteractor(BankQuestion bankQuestion) {
        mBankQuestion = bankQuestion.getBankQuestion();
    }

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

    private String getKeyRandomCategory() {
        List<String> list = new ArrayList<>(mBankQuestion.keySet());
        int first = 0;
        int second = list.size();
        int random = first + (int) (Math.random() * second);
        return list.get(random);
    }
}
