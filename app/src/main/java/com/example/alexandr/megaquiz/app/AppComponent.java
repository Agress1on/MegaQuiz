package com.example.alexandr.megaquiz.app;

import com.example.alexandr.megaquiz.quizfragment.inject.QuizFragmentComponent;
import com.example.alexandr.megaquiz.quizfragment.inject.QuizFragmentPresenterModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Alexandr Mikhalev on 15.12.2018.
 *
 * @author Alexandr Mikhalev
 */
@Singleton
@Component(
        modules = {
                AppModule.class,
        })
public interface AppComponent {
    QuizFragmentComponent createQuizComponent(QuizFragmentPresenterModule quizFragmentPresenterModule);
}
