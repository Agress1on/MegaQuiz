package com.example.alexandr.megaquiz.quizresultfragment.inject;

import com.example.alexandr.megaquiz.bankquestion.BankQuestion;
import com.example.alexandr.megaquiz.quizfragment.inject.QuizFragmentScope;
import com.example.alexandr.megaquiz.quizresultfragment.QuizResultFragmentContract;
import com.example.alexandr.megaquiz.quizresultfragment.domain.QuizResultFragmentInteractor;
import com.example.alexandr.megaquiz.quizresultfragment.presentation.QuizResultFragmentPresenter;

import java.util.HashMap;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Alexandr Mikhalev on 15.12.2018.
 *
 * @author Alexandr Mikhalev
 */
@Module
public class QuizResultFragmentPresenterModule {

    private QuizResultFragmentContract.View mView;
    private String mCategoryName;
    private HashMap<Integer, Boolean> mUserAnswersMap;

    public QuizResultFragmentPresenterModule(QuizResultFragmentContract.View view, String categoryName, HashMap<Integer, Boolean> userAnswersMap) {
        mView = view;
        mCategoryName = categoryName;
        mUserAnswersMap = userAnswersMap;
    }

    @QuizResultFragmentScope
    @Provides
    QuizResultFragmentContract.Presenter providePresenter(QuizResultFragmentContract.Interactor interactor) {
        return new QuizResultFragmentPresenter(mView, interactor, mCategoryName, mUserAnswersMap);
    }

    @QuizResultFragmentScope
    @Provides
    QuizResultFragmentContract.Interactor provideInteractor(BankQuestion bankQuestion) {
        return new QuizResultFragmentInteractor(bankQuestion);
    }

    @QuizResultFragmentScope
    @Provides
    @Named("keyForMap")
    HashMap<Integer, Boolean> provideUserAnswersMap() {
        return mUserAnswersMap;
    }

    @QuizResultFragmentScope
    @Provides
    @Named("keyCategoryForResult")
    String provideCategoryName() {
        return mCategoryName;
    }
}
