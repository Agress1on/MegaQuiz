package com.example.alexandr.megaquiz.bankquestion;

import com.example.alexandr.megaquiz.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import io.reactivex.Single;

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

        generalQuestions.add(new Question("У человека есть среднее ухо", true));
        generalQuestions.add(new Question("Ближайшая к Солнцу планета – Марс", false));
        generalQuestions.add(new Question("Если смешать красный и зеленый, то получится коричневый", true));
        generalQuestions.add(new Question("Если самолет будет разгоняться по ветру, он взлетит быстрее", false));
        generalQuestions.add(new Question("В производстве динамита используют арахис", true));
        generalQuestions.add(new Question("Древние Римляне носили брюки", false));

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
        return Single.just(mBankQuestion).delay(2, TimeUnit.SECONDS);
    }
}
