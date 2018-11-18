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

    private List<Boolean> generalTrueAnswers = new ArrayList<>();

    private Map<String, List<Question>> bankQuestion = new TreeMap();

    public BankQuestion() {
        generalQuestions.add(new Question("Андроид - лучшая платформа"));
        generalQuestions.add(new Question("Manchester is blue"));
        generalQuestions.add(new Question("Спартак Москва - лучший клуб"));
        generalQuestions.add(new Question("Земля имеет форму конуса"));
        generalQuestions.add(new Question("На Марсе есть жизнь"));
        generalQuestions.add(new Question("Алкоголь безвредный"));

        generalTrueAnswers.add(true);
        generalTrueAnswers.add(false);
        generalTrueAnswers.add(true);
        generalTrueAnswers.add(false);
        generalTrueAnswers.add(true);
        generalTrueAnswers.add(false);

        bankQuestion.put(Constants.GENERAL_QUESTIONS, generalQuestions);
        bankQuestion.put(Constants.HISTORY_QUESTIONS, historyQuestions);
        bankQuestion.put(Constants.GEOGRAPHY_QUESTIONS, geographyQuestions);
        bankQuestion.put(Constants.NATURE_QUESTIONS, natureQuestions);
        for (int i = 0; i < 10; i++) {
            bankQuestion.put(i + "category", new ArrayList<Question>());
        }
    }

    public Map<String, List<Question>> getBankQuestion() {
        return bankQuestion;
    }

    public List<Boolean> getGeneralTrueAnswers() {
        return generalTrueAnswers;
    }
}
