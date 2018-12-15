package com.example.alexandr.megaquiz.quizfragment.inject;

import com.example.alexandr.megaquiz.quizfragment.view.QuizFragment;

import dagger.Subcomponent;

/**
 * Created by Alexandr Mikhalev on 10.12.2018.
 *
 * @author Alexandr Mikhalev
 */
@Subcomponent(modules = {QuizFragmentPresenterModule.class})
public interface QuizFragmentComponent {
    void inject(QuizFragment quizFragment);
}
