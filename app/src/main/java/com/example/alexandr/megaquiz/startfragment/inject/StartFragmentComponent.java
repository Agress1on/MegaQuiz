package com.example.alexandr.megaquiz.startfragment.inject;

import com.example.alexandr.megaquiz.startfragment.view.StartFragment;

import dagger.Subcomponent;

/**
 * Created by Alexandr Mikhalev on 15.12.2018.
 *
 * @author Alexandr Mikhalev
 */
@Subcomponent(modules = StartFragmentPresenterModule.class)
public interface StartFragmentComponent {
    void inject(StartFragment startFragment);
}
