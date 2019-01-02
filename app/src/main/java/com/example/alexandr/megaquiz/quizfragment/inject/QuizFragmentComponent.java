package com.example.alexandr.megaquiz.quizfragment.inject;

import com.example.alexandr.megaquiz.base.FragmentComponent;
import com.example.alexandr.megaquiz.base.FragmentComponentBuilder;
import com.example.alexandr.megaquiz.quizfragment.view.QuizFragment;

import dagger.Subcomponent;

/**
 * Created by Alexandr Mikhalev on 10.12.2018.
 *
 * @author Alexandr Mikhalev
 */
@QuizFragmentScope
@Subcomponent(modules = {QuizFragmentPresenterModule.class})
public interface QuizFragmentComponent extends FragmentComponent<QuizFragment> {
    //void inject(QuizFragment quizFragment);
    @Subcomponent.Builder
    interface Builder extends FragmentComponentBuilder<QuizFragmentComponent, QuizFragmentPresenterModule> {

    }
}
