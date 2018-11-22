package com.example.alexandr.megaquiz.bankQuestion;

import com.example.alexandr.megaquiz.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Alexandr Mikhalev on 25.09.2018.
 *
 * @author Alexandr Mikhalev
 */
public class BankQuestion {
    private List<Question> mGeneralQuestions = new ArrayList<>();
    private List<Question> mHistoryQuestions = new ArrayList<>();
    private List<Question> mGeographyQuestions = new ArrayList<>();
    private List<Question> mNatureQuestions = new ArrayList<>();
    private Map<String, List<Question>> mBankQuestion = new TreeMap();

    public BankQuestion() {
        mGeneralQuestions.add(new Question("Андроид - лучшая платформа", true));
        mGeneralQuestions.add(new Question("Manchester is blue", false));
        mGeneralQuestions.add(new Question("Спартак Москва - лучший клуб", true));
        mGeneralQuestions.add(new Question("Земля имеет форму конуса", false));
        mGeneralQuestions.add(new Question("На Марсе есть жизнь", true));
        mGeneralQuestions.add(new Question("Алкоголь безвредный", false));

        mHistoryQuestions.add(new Question("Москва основана в 1147 году", true));
        mHistoryQuestions.add(new Question("Наполеон напал на Россию в 1815 году", false));

        mBankQuestion.put(Constants.GENERAL_QUESTIONS, mGeneralQuestions);
        mBankQuestion.put(Constants.HISTORY_QUESTIONS, mHistoryQuestions);
        mBankQuestion.put(Constants.GEOGRAPHY_QUESTIONS, mGeographyQuestions);
        mBankQuestion.put(Constants.NATURE_QUESTIONS, mNatureQuestions);

        for (int i = 0; i < 11; i++) {
            mBankQuestion.put(i + " category", new ArrayList<>());
        }
    }

    public Map<String, List<Question>> getBankQuestion() {
        return mBankQuestion;
    }
}
