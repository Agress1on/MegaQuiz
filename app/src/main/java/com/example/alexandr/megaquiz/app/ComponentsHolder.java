package com.example.alexandr.megaquiz.app;

import android.content.Context;

import com.example.alexandr.megaquiz.quizfragment.inject.QuizFragmentComponent;
import com.example.alexandr.megaquiz.quizfragment.inject.QuizFragmentPresenterModule;
import com.example.alexandr.megaquiz.quizfragment.view.QuizFragment;
import com.example.alexandr.megaquiz.quizresultfragment.inject.QuizResultFragmentComponent;
import com.example.alexandr.megaquiz.quizresultfragment.inject.QuizResultFragmentPresenterModule;
import com.example.alexandr.megaquiz.quizresultfragment.view.QuizResultFragment;
import com.example.alexandr.megaquiz.quizstoragefragment.inject.QuizStorageFragmentComponent;
import com.example.alexandr.megaquiz.quizstoragefragment.inject.QuizStorageFragmentPresenterModule;
import com.example.alexandr.megaquiz.quizstoragefragment.view.QuizStorageFragment;
import com.example.alexandr.megaquiz.startfragment.StartFragmentContract;
import com.example.alexandr.megaquiz.startfragment.inject.StartFragmentComponent;
import com.example.alexandr.megaquiz.startfragment.inject.StartFragmentPresenterModule;
import com.example.alexandr.megaquiz.startfragment.view.StartFragment;

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

  //  private QuizActivityComponent mQuizActivityComponent;
    //  private StartActivityComponent mStartActivityComponent;

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
     *//*
    StartActivityComponent
    *//*

    public StartActivityComponent getStartActivityComponent(StartRouter startActivity) {
        if (mStartActivityComponent == null) {
            mStartActivityComponent = getAppComponent().createStartActivityComponent(new StartActivityPresenterModule(startActivity));
        }
        return mStartActivityComponent;
    }

    public void releaseStartActivityComponent() {
        mStartActivityComponent = null;
    }

    */

    /*
    StartFragmentComponent
    */

    public StartFragmentComponent getStartFragmentComponent(StartFragment startFragment, StartFragmentContract.Router router) {
        if (mStartFragmentComponent == null) {
            mStartFragmentComponent = getAppComponent().createStartFragmentComponent(new StartFragmentPresenterModule(startFragment, router));
        }
        return mStartFragmentComponent;
    }

    public void releaseStartFragmentComponent() {
        mStartFragmentComponent = null;
    }

    /*
    QuizStorageFragmentComponent
    */

    public QuizStorageFragmentComponent getQuizStorageFragmentComponent(QuizStorageFragment quizStorageFragment) {
        if (mQuizStorageFragmentComponent == null) {
            mQuizStorageFragmentComponent = getAppComponent().createQuizStorageFragmentComponent(new QuizStorageFragmentPresenterModule(quizStorageFragment));
        }
        return mQuizStorageFragmentComponent;
    }

    public void releaseQuizStorageFragmentComponent() {
        mQuizStorageFragmentComponent = null;
    }

    /*
    QuizFragmentComponent
    */

    public QuizFragmentComponent getQuizFragmentComponent(QuizFragment quizFragment) {
        if (mQuizFragmentComponent == null) {
            mQuizFragmentComponent = getAppComponent().createQuizFragmentComponent(new QuizFragmentPresenterModule(quizFragment));
        }
        return mQuizFragmentComponent;
    }

    public void releaseQuizFragmentComponent() {
        mQuizFragmentComponent = null;
    }

    /*
    QuizResultFragmentComponent
    */

    public QuizResultFragmentComponent getQuizResultFragmentComponent(QuizResultFragment quizResultFragment) {
        if (mQuizResultFragmentComponent == null) {
            mQuizResultFragmentComponent = getAppComponent().createQuizResultFragmentComponent(new QuizResultFragmentPresenterModule(quizResultFragment));
        }
        return mQuizResultFragmentComponent;
    }

    public void releaseQuizResultFragmentComponent() {
        mQuizResultFragmentComponent = null;
    }

    /*
     *//*
    QuizActivityComponent
    *//*

    public QuizActivityComponent getQuizActivityComponent(QuizRouter quizActivity) {
        if (mQuizActivityComponent == null) {
            mQuizActivityComponent = getAppComponent().createQuizActivityComponent(new QuizActivityPresenterModule(quizActivity));
        }
        return mQuizActivityComponent;
    }

    public void releaseQuizActivityComponent() {
        mQuizActivityComponent = null;
    }
    */

}
