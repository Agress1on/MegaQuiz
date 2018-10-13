package com.example.alexandr.megaquiz.quizStorageActivity;

import com.example.alexandr.megaquiz.bankQuestion.Question;

import java.util.List;
import java.util.Map;

/**
 * Created by Alexandr Mikhalev on 11.09.2018.
 *
 * @author Alexandr Mikhalev
 */
public interface QuizStorageContract {
    interface Model {
        Map<String, List<Question>> getBankQuestion();
        List<String> getCategoriesNames();
    }
    interface Presenter {
        List<String> getCategoriesNames();
    }
    interface View {

    }
}
