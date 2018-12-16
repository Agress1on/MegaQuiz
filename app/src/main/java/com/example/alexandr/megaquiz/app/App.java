package com.example.alexandr.megaquiz.app;

import android.app.Application;


/**
 * Created by Alexandr Mikhalev on 20.10.2018.
 *
 * @author Alexandr Mikhalev
 */
public class App extends Application {

    // private static QuizFragmentComponent sQuizFragmentComponent;
    private static AppComponent sAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        /*
        sComponent = DaggerQuizFragmentComponent.builder()
                .quizFragmentPresenterModule(new QuizFragmentPresenterModule(new QuizFragmentScope())).build();
        */
        sAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public static AppComponent getAppComponent() {
        return sAppComponent;
    }

    /*
    public static QuizFragmentComponent getComponent() {
        return sQuizFragmentComponent;
    }
    */
    /*
    private static QuizActivityComponent sComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        sComponent = DaggerQuizActivityComponent.builder().quizActivityPresenterModule(new QuizActivityPresenterModule(new QuizActivity())).build();
    }

    public static QuizActivityComponent getComponent() {
        return sComponent;
    }
    */
}
