package com.example.alexandr.megaquiz.quizstoragefragment.inject;

import com.example.alexandr.megaquiz.bankquestion.BankQuestion;
import com.example.alexandr.megaquiz.quizstoragefragment.QuizStorageContract;
import com.example.alexandr.megaquiz.quizstoragefragment.domain.QuizStorageInteractor;
import com.example.alexandr.megaquiz.quizstoragefragment.presentation.QuizStoragePresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Alexandr Mikhalev on 15.12.2018.
 *
 * @author Alexandr Mikhalev
 */
@Module
public class QuizStorageFragmentPresenterModule {

    private QuizStorageContract.View mView;

    public QuizStorageFragmentPresenterModule(QuizStorageContract.View view) {
        mView = view;
    }

    @QuizStorageFragmentScope
    @Provides
    QuizStorageContract.Presenter providePresenter(QuizStorageContract.Interactor interactor) {
        return new QuizStoragePresenter(interactor);
    }

    @QuizStorageFragmentScope
    @Provides
    QuizStorageContract.Interactor provideInteractor(BankQuestion bankQuestion) {
        return new QuizStorageInteractor(bankQuestion);
    }
}
