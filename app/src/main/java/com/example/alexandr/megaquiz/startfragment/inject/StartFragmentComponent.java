package com.example.alexandr.megaquiz.startfragment.inject;

import com.example.alexandr.megaquiz.base.FragmentComponent;
import com.example.alexandr.megaquiz.base.FragmentComponentBuilder;
import com.example.alexandr.megaquiz.startfragment.view.StartFragment;

import dagger.Subcomponent;

/**
 * Created by Alexandr Mikhalev on 15.12.2018.
 *
 * @author Alexandr Mikhalev
 */
@StartFragmentScope
@Subcomponent(modules = StartFragmentPresenterModule.class)
public interface StartFragmentComponent extends FragmentComponent<StartFragment> {
    //void inject(StartFragment startFragment);
    @Subcomponent.Builder
    interface Builder extends FragmentComponentBuilder<StartFragmentComponent, StartFragmentPresenterModule> {

    }
}
