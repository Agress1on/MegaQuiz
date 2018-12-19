package com.example.alexandr.megaquiz.startactivity.inject;

import com.example.alexandr.megaquiz.startactivity.view.StartActivity;

import dagger.Subcomponent;

/**
 * Created by Alexandr Mikhalev on 19.12.2018.
 *
 * @author Alexandr Mikhalev
 */
@StartActivityScope
@Subcomponent(modules = StartActivityPresenterModule.class)
public interface StartActivityComponent {
    void inject(StartActivity startActivity);
}
