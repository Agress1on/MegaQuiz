package com.example.alexandr.megaquiz.app.inject;

import android.content.Context;

import com.example.alexandr.megaquiz.bankquestion.BankQuestion;
import com.example.alexandr.megaquiz.base.FragmentComponentBuilder;
import com.example.alexandr.megaquiz.quizfragment.inject.QuizFragmentComponent;
import com.example.alexandr.megaquiz.quizfragment.view.QuizFragment;
import com.example.alexandr.megaquiz.quizresultfragment.inject.QuizResultFragmentComponent;
import com.example.alexandr.megaquiz.quizresultfragment.view.QuizResultFragment;
import com.example.alexandr.megaquiz.quizstoragefragment.inject.QuizStorageFragmentComponent;
import com.example.alexandr.megaquiz.quizstoragefragment.view.QuizStorageFragment;
import com.example.alexandr.megaquiz.startfragment.inject.StartFragmentComponent;
import com.example.alexandr.megaquiz.startfragment.view.StartFragment;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;

/**
 * Created by Alexandr Mikhalev on 15.12.2018.
 *
 * @author Alexandr Mikhalev
 */
//@Module
@Module(subcomponents = {StartFragmentComponent.class, QuizStorageFragmentComponent.class, QuizResultFragmentComponent.class, QuizFragmentComponent.class})
public class AppModule {

    private final Context mContext;

    public AppModule(Context context) {
        this.mContext = context;
    }

    @AppScope
    @Provides
    BankQuestion provideBankQuestion() {
        return new BankQuestion();
    }

    @AppScope
    @Provides
    Context provideContext() {
        return mContext;
    }

    //new code

    @Provides
    @IntoMap
    @ClassKey(StartFragment.class)
    FragmentComponentBuilder provideStartFragmentBuilder(StartFragmentComponent.Builder builder) {
        return builder;
    }

    @Provides
    @IntoMap
    @ClassKey(QuizStorageFragment.class)
    FragmentComponentBuilder provideQuizStorageFragmentBuilder(QuizStorageFragmentComponent.Builder builder) {
        return builder;
    }

    @Provides
    @IntoMap
    @ClassKey(QuizResultFragment.class)
    FragmentComponentBuilder provideQuizResultFragmentBuilder(QuizResultFragmentComponent.Builder builder) {
        return builder;
    }

    @Provides
    @IntoMap
    @ClassKey(QuizFragment.class)
    FragmentComponentBuilder provideQuizFragmentBuilder(QuizFragmentComponent.Builder builder) {
        return builder;
    }
}
