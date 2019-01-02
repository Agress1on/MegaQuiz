package com.example.alexandr.megaquiz.quizresultfragment.inject;

import com.example.alexandr.megaquiz.bankquestion.BankQuestion;
import com.example.alexandr.megaquiz.base.FragmentModule;
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
public class QuizResultFragmentPresenterModule implements FragmentModule {

    private String mCategoryName;
    private HashMap<Integer, Boolean> mUserAnswersMap;
    private int mCorrectAnswers;

    public QuizResultFragmentPresenterModule(String categoryName, HashMap<Integer, Boolean> userAnswersMap, int correctAnswers) {
        mCategoryName = categoryName;
        mUserAnswersMap = userAnswersMap;
        mCorrectAnswers = correctAnswers;
    }

    @QuizResultFragmentScope
    @Provides
    QuizResultFragmentContract.Presenter providePresenter(QuizResultFragmentContract.Interactor interactor) {
        return new QuizResultFragmentPresenter(interactor, mCategoryName, mUserAnswersMap, mCorrectAnswers);
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

    @QuizResultFragmentScope
    @Provides
    @Named("keyForCorrectAnswers")
    Integer provideCorrectAnswers() {
        return mCorrectAnswers;
    }
}
