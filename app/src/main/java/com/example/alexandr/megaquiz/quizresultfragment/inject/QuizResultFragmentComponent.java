package com.example.alexandr.megaquiz.quizresultfragment.inject;

import com.example.alexandr.megaquiz.base.FragmentComponent;
import com.example.alexandr.megaquiz.base.FragmentComponentBuilder;
import com.example.alexandr.megaquiz.quizresultfragment.view.QuizResultFragment;

import dagger.Subcomponent;

/**
 * Created by Alexandr Mikhalev on 15.12.2018.
 *
 * @author Alexandr Mikhalev
 */
@QuizResultFragmentScope
@Subcomponent(modules = QuizResultFragmentPresenterModule.class)
public interface QuizResultFragmentComponent extends FragmentComponent<QuizResultFragment> {
    //void inject(QuizResultFragment quizResultFragment);
    @Subcomponent.Builder
    interface Builder extends FragmentComponentBuilder<QuizResultFragmentComponent, QuizResultFragmentPresenterModule> {

    }
}
