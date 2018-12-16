package com.example.alexandr.megaquiz.startfragment.inject;

import com.example.alexandr.megaquiz.bankquestion.BankQuestion;
import com.example.alexandr.megaquiz.startfragment.StartFragmentContract;
import com.example.alexandr.megaquiz.startfragment.domain.StartFragmentInteractor;
import com.example.alexandr.megaquiz.startfragment.presentation.StartFragmentPresenter;
import com.example.alexandr.megaquiz.startfragment.view.StartFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Alexandr Mikhalev on 15.12.2018.
 *
 * @author Alexandr Mikhalev
 */
@Module
public class StartFragmentPresenterModule {
    private StartFragmentContract.View mView;

    public StartFragmentPresenterModule(StartFragmentContract.View startFragment) {
        mView = startFragment;
    }

    @StartFragmentScope
    @Provides
    StartFragmentContract.Presenter providePresenter(StartFragmentContract.Interactor startFragmentInteractor) {
        return new StartFragmentPresenter(mView, startFragmentInteractor);
    }

    @StartFragmentScope
    @Provides
    StartFragmentContract.Interactor provideInteractor(BankQuestion bankQuestion) {
        return new StartFragmentInteractor(bankQuestion);
    }
}
