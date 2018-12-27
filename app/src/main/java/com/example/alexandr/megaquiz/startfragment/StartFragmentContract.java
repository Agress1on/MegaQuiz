package com.example.alexandr.megaquiz.startfragment;

/**
 * Created by Alexandr Mikhalev on 07.12.2018.
 *
 * @author Alexandr Mikhalev
 */
public interface StartFragmentContract {

    interface View {

    }

    interface Presenter {
        void onRandomButton();

        void onButtonCategory();

        void onTestButton();

        void attachView(StartFragmentContract.View view);

        void detachView();
    }

    interface Router {
        void goToQuizFragmentWithRandom();

        void goToQuizStorage();
    }
}
