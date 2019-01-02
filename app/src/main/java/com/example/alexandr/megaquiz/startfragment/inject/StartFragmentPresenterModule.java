package com.example.alexandr.megaquiz.startfragment.inject;

import com.example.alexandr.megaquiz.base.FragmentModule;
import com.example.alexandr.megaquiz.startfragment.StartFragmentContract;
import com.example.alexandr.megaquiz.startfragment.presentation.StartFragmentPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Alexandr Mikhalev on 15.12.2018.
 *
 * @author Alexandr Mikhalev
 */
@Module
public class StartFragmentPresenterModule implements FragmentModule {

    @StartFragmentScope
    @Provides
    StartFragmentContract.Presenter providePresenter() {
        return new StartFragmentPresenter();
    }
}
