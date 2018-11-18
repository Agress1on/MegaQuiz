package com.example.alexandr.megaquiz.app;

import com.example.alexandr.megaquiz.quiz.QuizActivityView;
import com.example.alexandr.megaquiz.quiz.QuizContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Alexandr Mikhalev on 17.11.2018.
 *
 * @author Alexandr Mikhalev
 */
@Module
public class QuizViewModule {

    @Provides
    QuizActivityView provideView() {
        return new QuizActivityView();
    }
}
