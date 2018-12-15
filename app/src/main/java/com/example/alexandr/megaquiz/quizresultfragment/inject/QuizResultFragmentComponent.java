package com.example.alexandr.megaquiz.quizresultfragment.inject;

import com.example.alexandr.megaquiz.quizresultfragment.view.QuizResultFragment;

import dagger.Subcomponent;

/**
 * Created by Alexandr Mikhalev on 15.12.2018.
 *
 * @author Alexandr Mikhalev
 */
@Subcomponent(modules = QuizResultFragmentPresenterModule.class)
public interface QuizResultFragmentComponent {
    void inject(QuizResultFragment quizResultFragment);
}
