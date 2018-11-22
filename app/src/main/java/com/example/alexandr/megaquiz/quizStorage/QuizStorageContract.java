package com.example.alexandr.megaquiz.quizStorage;

import java.util.List;

/**
 * Created by Alexandr Mikhalev on 11.09.2018.
 *
 * @author Alexandr Mikhalev
 */
public interface QuizStorageContract {
    interface Interactor {
        List<QuizStorageItem> getListOfStorageItem();
        List<QuizStorageItem> getListOfStorageItemWithoutEmpty();
    }

    interface Presenter {
        List<QuizStorageItem> getCategoriesNamesForView();
        List<QuizStorageItem> getCategoriesNamesForViewWithoutEmpty();

        void onClick(String key);

    }

    interface View {
        void startActivityQuizView(String key);
    }
}
