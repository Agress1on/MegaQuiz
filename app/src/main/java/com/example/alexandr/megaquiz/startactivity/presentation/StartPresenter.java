package com.example.alexandr.megaquiz.startactivity.presentation;

import com.example.alexandr.megaquiz.startactivity.StartContract;

/**
 * Created by Alexandr Mikhalev on 11.09.2018.
 *
 * @author Alexandr Mikhalev
 */
public class StartPresenter implements StartContract.Presenter {
    private StartContract.View mView;
    private StartContract.Interactor mInteractor;

    public StartPresenter(StartContract.View view, StartContract.Interactor interactor) {
        this.mView = view;
        this.mInteractor = interactor;
    }

    /*
    @Override
    public void onRandomButton() {
        mView.startQuizViewWithRandom(mInteractor.getStringCategoryForRandomStart());
    }

    @Override
    public void onButtonCategory() {
        mView.startQuizStorage();
    }

    @Override
    public void onButtonGeneralQuestionsTest() {
        mView.startTestGeneralQuestions();
    }
    */
}
