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
    private List<Question> mHardGeneralQuestions = new ArrayList<>();
    private List<Question> mHistoryQuestions = new ArrayList<>();
    private List<Question> mEconomicQuestions = new ArrayList<>();
    private List<Question> mNatureQuestions = new ArrayList<>();
    private Map<String, List<Question>> mBankQuestion = new TreeMap();

    public BankQuestion() {
        mGeneralQuestions.add(new Question("Андроид - лучшая платформа", true));
        mGeneralQuestions.add(new Question("Manchester is blue", false));
        mGeneralQuestions.add(new Question("Спартак Москва - лучший клуб", true));
        mGeneralQuestions.add(new Question("Земля имеет форму конуса", false));
        mGeneralQuestions.add(new Question("На Марсе есть жизнь", true));
        mGeneralQuestions.add(new Question("Алкоголь безвредный", false));

        mHardGeneralQuestions.add(new Question("Электрон больше, чем атом", false));
        mHardGeneralQuestions.add(new Question("Килиманджаро - самая высокая гора в мире", false));
        mHardGeneralQuestions.add(new Question("Глаз страуса больше, чем его мозг", true));
        mHardGeneralQuestions.add(new Question("Летучие мыши слепы", false));
        mHardGeneralQuestions.add(new Question("У белого медведя черная кожа", true));
        mHardGeneralQuestions.add(new Question("Швейцарский флаг - единственный квадратный в мире", false));

        mHistoryQuestions.add(new Question("Москва основана в 1147 году", true));
        mHistoryQuestions.add(new Question("Наполеон напал на Россию в 1815 году", false));

        mBankQuestion.put(Constants.GENERAL_QUESTIONS, mGeneralQuestions);
        mBankQuestion.put(Constants.HISTORY_QUESTIONS, mHistoryQuestions);
        mBankQuestion.put(Constants.ECONOMIC_QUESTIONS, mEconomicQuestions);
        mBankQuestion.put(Constants.NATURE_QUESTIONS, mNatureQuestions);
        mBankQuestion.put(Constants.HARD_GENERAL_QUESTIONS, mHardGeneralQuestions);

        for (int i = 0; i < 11; i++) {
            mBankQuestion.put("Пустая " + i, new ArrayList<>());
        }
    }

    public Map<String, List<Question>> getBankQuestion() {
        return mBankQuestion;
    }
}
