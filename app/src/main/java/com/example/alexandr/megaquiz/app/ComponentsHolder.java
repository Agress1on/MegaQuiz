package com.example.alexandr.megaquiz.app;

import android.content.Context;

import com.example.alexandr.megaquiz.quizfragment.inject.QuizFragmentComponent;
import com.example.alexandr.megaquiz.quizfragment.inject.QuizFragmentPresenterModule;
import com.example.alexandr.megaquiz.quizfragment.view.QuizFragment;
import com.example.alexandr.megaquiz.quizresultfragment.inject.QuizResultFragmentComponent;
import com.example.alexandr.megaquiz.quizstoragefragment.inject.QuizStorageFragmentComponent;
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

    public StartFragmentComponent getStartFragmentComponent(StartFragment startFragment) {
        if (mStartFragmentComponent == null) {
            mStartFragmentComponent = getAppComponent().createStartFragmentComponent(new StartFragmentPresenterModule(startFragment));
        }
        return mStartFragmentComponent;
    }

    public void releaseStartFragmentComponent() {
        mStartFragmentComponent = null;
    }

    public QuizFragmentComponent getQuizFragmentComponent(QuizFragment quizFragment) {
        if (mQuizFragmentComponent == null) {
            mQuizFragmentComponent = getAppComponent().createQuizFragmentComponent(new QuizFragmentPresenterModule(quizFragment));
        }
        return mQuizFragmentComponent;
    }

    public void releaseQuizFragmentComponent() {
        mQuizFragmentComponent = null;
    }
}
