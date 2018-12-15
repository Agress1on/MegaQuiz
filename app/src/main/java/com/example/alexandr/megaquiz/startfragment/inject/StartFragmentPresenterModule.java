package com.example.alexandr.megaquiz.startfragment.inject;

import com.example.alexandr.megaquiz.bankquestion.BankQuestion;
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
    private StartFragment mStartFragment;

    public StartFragmentPresenterModule(StartFragment startFragment) {
        mStartFragment = startFragment;
    }

    @Provides
    StartFragmentPresenter providePresenter(StartFragmentInteractor startFragmentInteractor) {
        return new StartFragmentPresenter(mStartFragment, startFragmentInteractor);
    }

    @Provides
    StartFragmentInteractor provideInteractor(BankQuestion bankQuestion) {
        return new StartFragmentInteractor(bankQuestion);
    }
}
