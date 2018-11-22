package com.example.alexandr.megaquiz.start;

/**
 * Created by Alexandr Mikhalev on 11.09.2018.
 *
 * @author Alexandr Mikhalev
 */
public interface StartContract {
    interface View {
        void startQuizViewWithRandom(String randomCategory);

        void startQuizStorage();

        void startTestGeneralQuestions();
    }

    interface Presenter {
        void onRandomButton();

        void onButtonCategory();

        void onButtonGeneralQuestionsTest();
    }

    interface Interactor {
        String getStringCategoryForRandomStart();
    }
}
