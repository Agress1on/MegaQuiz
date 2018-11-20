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
    private List<Question> generalQuestions = new ArrayList<>();
    private List<Question> historyQuestions = new ArrayList<>();
    private List<Question> geographyQuestions = new ArrayList<>();
    private List<Question> natureQuestions = new ArrayList<>();

    private Map<String, List<Question>> bankQuestion = new TreeMap();

    public BankQuestion() {
        generalQuestions.add(new Question("Андроид - лучшая платформа", true));
        generalQuestions.add(new Question("Manchester is blue", false));
        generalQuestions.add(new Question("Спартак Москва - лучший клуб", true));
        generalQuestions.add(new Question("Земля имеет форму конуса", false));
        generalQuestions.add(new Question("На Марсе есть жизнь", true));
        generalQuestions.add(new Question("Алкоголь безвредный", false));

        historyQuestions.add(new Question("Москва основана в 1147 году", true));
        historyQuestions.add(new Question("Наполеон напал на Россию в 1815 году", false));

        bankQuestion.put(Constants.GENERAL_QUESTIONS, generalQuestions);
        bankQuestion.put(Constants.HISTORY_QUESTIONS, historyQuestions);
        bankQuestion.put(Constants.GEOGRAPHY_QUESTIONS, geographyQuestions);
        bankQuestion.put(Constants.NATURE_QUESTIONS, natureQuestions);

        /*
        for (int i = 0; i < 10; i++) {
            bankQuestion.put(i + "category", new ArrayList<Question>());
        }
        */
    }

    public Map<String, List<Question>> getBankQuestion() {
        return bankQuestion;
    }
}
