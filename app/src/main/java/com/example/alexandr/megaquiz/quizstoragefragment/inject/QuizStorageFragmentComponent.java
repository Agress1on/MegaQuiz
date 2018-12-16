package com.example.alexandr.megaquiz.quizstoragefragment.inject;

import com.example.alexandr.megaquiz.quizstoragefragment.view.QuizStorageFragment;

import dagger.Subcomponent;

/**
 * Created by Alexandr Mikhalev on 15.12.2018.
 *
 * @author Alexandr Mikhalev
 */
@QuizStorageFragmentScope
@Subcomponent(modules = QuizStorageFragmentPresenterModule.class)
public interface QuizStorageFragmentComponent {
    void inject(QuizStorageFragment quizStorageFragment);
}
