package com.example.alexandr.megaquiz.startactivity.inject;

import com.example.alexandr.megaquiz.startactivity.StartContract;
import com.example.alexandr.megaquiz.startactivity.domain.StartActivityInteractor;
import com.example.alexandr.megaquiz.startactivity.presentation.StartActivityPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Alexandr Mikhalev on 19.12.2018.
 *
 * @author Alexandr Mikhalev
 */
@Module
public class StartActivityPresenterModule {

    private StartContract.View mView;

    public StartActivityPresenterModule(StartContract.View view) {
        mView = view;
    }

    @StartActivityScope
    @Provides
    StartContract.Presenter providePresenter(StartContract.Interactor interactor) {
        return new StartActivityPresenter(mView, interactor);
    }

    @StartActivityScope
    @Provides
    StartContract.Interactor provideInteractor() {
        return new StartActivityInteractor();
    }
}
