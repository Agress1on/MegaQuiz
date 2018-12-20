package com.example.alexandr.megaquiz.startfragment;

import io.reactivex.Single;

/**
 * Created by Alexandr Mikhalev on 07.12.2018.
 *
 * @author Alexandr Mikhalev
 */
public interface StartFragmentContract {

    interface Interactor {
        Single<String> getStringCategoryForRandomStart();
    }

    interface View {
        void startQuizViewWithRandom(String randomCategory);

        void startQuizStorage();
    }

    interface Presenter {
        void initRandomCategory();

        void onRandomButton();

        void onButtonCategory();

        void onDestroy();
    }

    interface Router {
        void prepareStartScreen();
        void goToRandomQuestion();
        void goToQuizStorage();
    }
}
