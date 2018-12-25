package com.example.alexandr.megaquiz.app.inject;

import android.content.Context;

import com.example.alexandr.megaquiz.bankquestion.BankQuestion;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Alexandr Mikhalev on 15.12.2018.
 *
 * @author Alexandr Mikhalev
 */
@Module
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
}
