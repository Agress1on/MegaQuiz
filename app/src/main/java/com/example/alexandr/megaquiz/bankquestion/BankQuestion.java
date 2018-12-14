package com.example.alexandr.megaquiz.bankquestion;

import com.example.alexandr.megaquiz.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import io.reactivex.Single;
import io.reactivex.functions.Function;

/**
 * Created by Alexandr Mikhalev on 25.09.2018.
 *
 * @author Alexandr Mikhalev
 */
public class BankQuestion {

    private Map<String, List<Question>> mBankQuestion;

    public BankQuestion() {
        mBankQuestion = new TreeMap();
        List<Question> generalQuestions = new ArrayList<>();
        List<Question> hardGeneralQuestions = new ArrayList<>();
        List<Question> historyQuestions = new ArrayList<>();
        List<Question> economicQuestions = new ArrayList<>();
        List<Question> natureQuestions = new ArrayList<>();

        generalQuestions.add(new Question("Андроид - лучшая платформа", true));
        generalQuestions.add(new Question("Manchester is blue", false));
        generalQuestions.add(new Question("Спартак Москва - лучший клуб", true));
        generalQuestions.add(new Question("Земля имеет форму конуса", false));
        generalQuestions.add(new Question("На Марсе есть жизнь", true));
        generalQuestions.add(new Question("Алкоголь безвредный", false));

        hardGeneralQuestions.add(new Question("Электрон больше, чем атом", false));
        hardGeneralQuestions.add(new Question("Килиманджаро - самая высокая гора в мире", false));
        hardGeneralQuestions.add(new Question("Глаз страуса больше, чем его мозг", true));
        hardGeneralQuestions.add(new Question("Летучие мыши слепы", false));
        hardGeneralQuestions.add(new Question("У белого медведя черная кожа", true));
        hardGeneralQuestions.add(new Question("Швейцарский флаг - единственный квадратный в мире", false));


        historyQuestions.add(new Question("Москва основана в 1147 году", true));
        historyQuestions.add(new Question("Наполеон напал на Россию в 1815 году", false));

        mBankQuestion.put(Constants.GENERAL_QUESTIONS, generalQuestions);
        mBankQuestion.put(Constants.HISTORY_QUESTIONS, historyQuestions);
        mBankQuestion.put(Constants.ECONOMIC_QUESTIONS, economicQuestions);
        mBankQuestion.put(Constants.NATURE_QUESTIONS, natureQuestions);
        mBankQuestion.put(Constants.HARD_GENERAL_QUESTIONS, hardGeneralQuestions);

        for (int i = 0; i < 11; i++) {
            mBankQuestion.put("Пустая " + i, new ArrayList<>());
        }
    }

    public Single<Map<String, List<Question>>> getBankQuestionsAndAnswers() {
        return Single.just(mBankQuestion);
    }
}
