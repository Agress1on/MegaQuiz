package com.example.alexandr.megaquiz.app;

import com.example.alexandr.megaquiz.quizactivity.inject.QuizActivityComponent;
import com.example.alexandr.megaquiz.quizactivity.inject.QuizActivityPresenterModule;
import com.example.alexandr.megaquiz.quizfragment.inject.QuizFragmentComponent;
import com.example.alexandr.megaquiz.quizfragment.inject.QuizFragmentPresenterModule;
import com.example.alexandr.megaquiz.quizresultfragment.inject.QuizResultFragmentComponent;
import com.example.alexandr.megaquiz.quizresultfragment.inject.QuizResultFragmentPresenterModule;
import com.example.alexandr.megaquiz.quizstoragefragment.inject.QuizStorageFragmentComponent;
import com.example.alexandr.megaquiz.quizstoragefragment.inject.QuizStorageFragmentPresenterModule;
import com.example.alexandr.megaquiz.startfragment.inject.StartFragmentComponent;
import com.example.alexandr.megaquiz.startfragment.inject.StartFragmentPresenterModule;

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
    QuizFragmentComponent createQuizFragmentComponent(QuizFragmentPresenterModule quizFragmentPresenterModule);

    StartFragmentComponent createStartFragmentComponent(StartFragmentPresenterModule startFragmentPresenterModule);

    QuizResultFragmentComponent createQuizResultFragmentComponent(QuizResultFragmentPresenterModule quizResultFragmentPresenterModule);

    QuizStorageFragmentComponent createQuizStorageFragmentComponent(QuizStorageFragmentPresenterModule quizStorageFragmentPresenterModule);

    QuizActivityComponent createQuizActivityComponent(QuizActivityPresenterModule quizActivityPresenterModule);

  //  StartActivityComponent createStartActivityComponent(StartActivityPresenterModule startActivityPresenterModule);
}
