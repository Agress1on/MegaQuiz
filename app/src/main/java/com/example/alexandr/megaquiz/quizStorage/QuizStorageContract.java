package com.example.alexandr.megaquiz.quizStorage;

import java.util.List;
import java.util.Map;

/**
 * Created by Alexandr Mikhalev on 11.09.2018.
 *
 * @author Alexandr Mikhalev
 */
public interface QuizStorageContract {
    interface Interactor {
        List<String> getCategoriesNames();

        Map<String, Integer> getMapOfNamesAndSizeCategory();
    }

    interface Presenter {
        List<String> getCategoriesNamesForView();

        void onClick(String key);
    }

    interface View {
        void startActivityQuizView(String key);
    }
}
