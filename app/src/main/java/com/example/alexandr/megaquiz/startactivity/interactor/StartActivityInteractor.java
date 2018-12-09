package com.example.alexandr.megaquiz.startactivity.interactor;

import com.example.alexandr.megaquiz.startactivity.StartContract;

/**
 * Created by Alexandr Mikhalev on 11.09.2018.
 *
 * @author Alexandr Mikhalev
 */
public class StartActivityInteractor implements StartContract.Interactor {
    /*
    private Map<String, List<Question>> mBankQuestion;

    public StartActivityInteractor(BankQuestion bankQuestion) {
        this.mBankQuestion = bankQuestion.getBankQuestion();
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
    */

}
