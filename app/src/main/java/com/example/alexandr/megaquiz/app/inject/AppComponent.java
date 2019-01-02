package com.example.alexandr.megaquiz.app.inject;

import com.example.alexandr.megaquiz.app.ComponentsHolder;

import dagger.Component;

/**
 * Created by Alexandr Mikhalev on 15.12.2018.
 *
 * @author Alexandr Mikhalev
 */
@AppScope
@Component(
        modules = {
                AppModule.class,
        })
public interface AppComponent {
    /*
    QuizFragmentComponent createQuizFragmentComponent(QuizFragmentPresenterModule quizFragmentPresenterModule);

    StartFragmentComponent createStartFragmentComponent(StartFragmentPresenterModule startFragmentPresenterModule);

    QuizResultFragmentComponent createQuizResultFragmentComponent(QuizResultFragmentPresenterModule quizResultFragmentPresenterModule);

    QuizStorageFragmentComponent createQuizStorageFragmentComponent(QuizStorageFragmentPresenterModule quizStorageFragmentPresenterModule);
    */
    void injectComponentsHolder(ComponentsHolder componentsHolder);
}
