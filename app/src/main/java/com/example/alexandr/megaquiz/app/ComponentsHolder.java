package com.example.alexandr.megaquiz.app;

import android.content.Context;

import com.example.alexandr.megaquiz.app.inject.AppComponent;
import com.example.alexandr.megaquiz.app.inject.AppModule;
import com.example.alexandr.megaquiz.app.inject.DaggerAppComponent;
import com.example.alexandr.megaquiz.quizfragment.inject.QuizFragmentComponent;
import com.example.alexandr.megaquiz.quizfragment.inject.QuizFragmentPresenterModule;
import com.example.alexandr.megaquiz.quizresultfragment.inject.QuizResultFragmentComponent;
import com.example.alexandr.megaquiz.quizresultfragment.inject.QuizResultFragmentPresenterModule;
import com.example.alexandr.megaquiz.quizstoragefragment.inject.QuizStorageFragmentComponent;
import com.example.alexandr.megaquiz.quizstoragefragment.inject.QuizStorageFragmentPresenterModule;
import com.example.alexandr.megaquiz.startfragment.inject.StartFragmentComponent;
import com.example.alexandr.megaquiz.startfragment.inject.StartFragmentPresenterModule;

import java.util.HashMap;

/**
 * Created by Alexandr Mikhalev on 17.12.2018.
 *
 * @author Alexandr Mikhalev
 */
public class ComponentsHolder {
    private final Context mContext;

    private AppComponent mAppComponent;

    private StartFragmentComponent mStartFragmentComponent;
    private QuizStorageFragmentComponent mQuizStorageFragmentComponent;
    private QuizResultFragmentComponent mQuizResultFragmentComponent;
    private QuizFragmentComponent mQuizFragmentComponent;

    public ComponentsHolder(Context context) {
        mContext = context;
    }

    void init() {
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(mContext))
                .build();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

    /*
    StartFragmentComponent
    */

    public StartFragmentComponent getStartFragmentComponent() {
        if (mStartFragmentComponent == null) {
            mStartFragmentComponent = getAppComponent().createStartFragmentComponent(new StartFragmentPresenterModule());
        }
        return mStartFragmentComponent;
    }

    public void releaseStartFragmentComponent() {
        mStartFragmentComponent = null;
    }

    /*
    QuizStorageFragmentComponent
    */

    public QuizStorageFragmentComponent getQuizStorageFragmentComponent() {
        if (mQuizStorageFragmentComponent == null) {
            mQuizStorageFragmentComponent = getAppComponent().createQuizStorageFragmentComponent(new QuizStorageFragmentPresenterModule());
        }
        return mQuizStorageFragmentComponent;
    }

    public void releaseQuizStorageFragmentComponent() {
        mQuizStorageFragmentComponent = null;
    }

    /*
    QuizFragmentComponent
    */

    public QuizFragmentComponent getQuizFragmentComponent(String categoryName) {
        if (mQuizFragmentComponent == null) {
            mQuizFragmentComponent = getAppComponent().createQuizFragmentComponent(new QuizFragmentPresenterModule(categoryName));
        }
        return mQuizFragmentComponent;
    }

    public void releaseQuizFragmentComponent() {
        mQuizFragmentComponent = null;
    }

    /*
    QuizResultFragmentComponent
    */

    public QuizResultFragmentComponent getQuizResultFragmentComponent(String categoryName, HashMap<Integer, Boolean> userAnswersMap, Integer correctAnswers) {
        if (mQuizResultFragmentComponent == null) {
            mQuizResultFragmentComponent = getAppComponent().createQuizResultFragmentComponent(new QuizResultFragmentPresenterModule(categoryName, userAnswersMap, correctAnswers));
        }
        return mQuizResultFragmentComponent;
    }

    public void releaseQuizResultFragmentComponent() {
        mQuizResultFragmentComponent = null;
    }
}
