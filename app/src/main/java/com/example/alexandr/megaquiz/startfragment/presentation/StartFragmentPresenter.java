package com.example.alexandr.megaquiz.startfragment.presentation;

import com.example.alexandr.megaquiz.startfragment.StartFragmentContract;

/**
 * Created by Alexandr Mikhalev on 07.12.2018.
 *
 * @author Alexandr Mikhalev
 */
public class StartFragmentPresenter implements StartFragmentContract.Presenter {

    private StartFragmentContract.View mView;
    private StartFragmentContract.Router mRouter;

    @Override
    public void attachView(StartFragmentContract.View view, StartFragmentContract.Router router) {
        mView = view;
        mRouter = router;
    }

    @Override
    public void detachView() {
        mView = null;
        mRouter = null;
    }

    @Override
    public void onRandomButton() {
        mRouter.goToQuizFragmentWithRandom();
    }

    @Override
    public void onButtonCategory() {
       mRouter.goToQuizStorage();
    }

    @Override
    public void onTestButton() {

    }
}
