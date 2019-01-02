package com.example.alexandr.megaquiz.app;

import android.content.Context;

import com.example.alexandr.megaquiz.app.inject.AppComponent;
import com.example.alexandr.megaquiz.app.inject.AppModule;
import com.example.alexandr.megaquiz.app.inject.DaggerAppComponent;
import com.example.alexandr.megaquiz.base.FragmentComponent;
import com.example.alexandr.megaquiz.base.FragmentComponentBuilder;
import com.example.alexandr.megaquiz.base.FragmentModule;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;

/**
 * Created by Alexandr Mikhalev on 17.12.2018.
 *
 * @author Alexandr Mikhalev
 */
public class ComponentsHolder {

    private final Context mContext;
    private AppComponent mAppComponent;

    @Inject
    Map<Class<?>, Provider<FragmentComponentBuilder>> mBuilders;

    private Map<Class<?>, FragmentComponent> mComponents;

    /*
    private StartFragmentComponent mStartFragmentComponent;
    private QuizStorageFragmentComponent mQuizStorageFragmentComponent;
    private QuizResultFragmentComponent mQuizResultFragmentComponent;
    private QuizFragmentComponent mQuizFragmentComponent;
    */

    public ComponentsHolder(Context context) {
        mContext = context;
    }

    void init() {
        /*
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(mContext))
                .build();
        */
        mAppComponent = DaggerAppComponent.builder().appModule(new AppModule(mContext)).build();
        mAppComponent.injectComponentsHolder(this);
        mComponents = new HashMap<>();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

    public FragmentComponent getFragmentComponent(Class<?> cls) {
        return getFragmentComponent(cls, null);
    }

    public FragmentComponent getFragmentComponent(Class<?> cls, FragmentModule module) {
        FragmentComponent component = mComponents.get(cls);
        if (component == null) {
            FragmentComponentBuilder builder = mBuilders.get(cls).get();
            if (module != null) {
                builder.module(module);
            }
            component = builder.build();
            mComponents.put(cls, component);
        }
        return component;
    }

    public void releaseFragmentComponent(Class<?> cls) {
        mComponents.put(cls, null);
    }



    /*
    *//*
    StartFragmentComponent
    *//*

    public StartFragmentComponent getStartFragmentComponent() {
        if (mStartFragmentComponent == null) {
            mStartFragmentComponent = getAppComponent().createStartFragmentComponent(new StartFragmentPresenterModule());
        }
        return mStartFragmentComponent;
    }

    public void releaseStartFragmentComponent() {
        mStartFragmentComponent = null;
    }

    *//*
    QuizStorageFragmentComponent
    *//*

    public QuizStorageFragmentComponent getQuizStorageFragmentComponent() {
        if (mQuizStorageFragmentComponent == null) {
            mQuizStorageFragmentComponent = getAppComponent().createQuizStorageFragmentComponent(new QuizStorageFragmentPresenterModule());
        }
        return mQuizStorageFragmentComponent;
    }

    public void releaseQuizStorageFragmentComponent() {
        mQuizStorageFragmentComponent = null;
    }

    *//*
    QuizFragmentComponent
    *//*

    public QuizFragmentComponent getQuizFragmentComponent(String categoryName) {
        if (mQuizFragmentComponent == null) {
            mQuizFragmentComponent = getAppComponent().createQuizFragmentComponent(new QuizFragmentPresenterModule(categoryName));
        }
        return mQuizFragmentComponent;
    }

    public void releaseQuizFragmentComponent() {
        mQuizFragmentComponent = null;
    }

    *//*
    QuizResultFragmentComponent
    *//*

    public QuizResultFragmentComponent getQuizResultFragmentComponent(String categoryName, HashMap<Integer, Boolean> userAnswersMap, Integer correctAnswers) {
        if (mQuizResultFragmentComponent == null) {
            mQuizResultFragmentComponent = getAppComponent().createQuizResultFragmentComponent(new QuizResultFragmentPresenterModule(categoryName, userAnswersMap, correctAnswers));
        }
        return mQuizResultFragmentComponent;
    }

    public void releaseQuizResultFragmentComponent() {
        mQuizResultFragmentComponent = null;
    }
    */
}
