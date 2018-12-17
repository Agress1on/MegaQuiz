package com.example.alexandr.megaquiz.quizactivity.inject;

import com.example.alexandr.megaquiz.quizactivity.view.QuizActivity;

import dagger.Subcomponent;

/**
 * Created by Alexandr Mikhalev on 17.12.2018.
 *
 * @author Alexandr Mikhalev
 */
@QuizActivityScope
@Subcomponent(modules = QuizActivityPresenterModule.class)
public interface QuizActivityComponent {
    void inject(QuizActivity quizActivity);
}
