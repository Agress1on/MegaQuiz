package com.example.alexandr.megaquiz.startfragment;

/**
 * Created by Alexandr Mikhalev on 07.12.2018.
 *
 * @author Alexandr Mikhalev
 */
public interface StartFragmentContract {
    interface Interactor {
        String getStringCategoryForRandomStart();
    }

    interface View {
        void startQuizViewWithRandom(String randomCategory);

        void startQuizStorage();

        void startTestGeneralQuestions();
    }

    interface Presenter {
        void onRandomButton();

        void onButtonCategory();
    }
}
