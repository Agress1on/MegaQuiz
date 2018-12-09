package com.example.alexandr.megaquiz.startactivity.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.alexandr.megaquiz.R;
import com.example.alexandr.megaquiz.startactivity.interactor.StartInteractor;
import com.example.alexandr.megaquiz.startactivity.StartContract;
import com.example.alexandr.megaquiz.startactivity.presentation.StartPresenter;
import com.example.alexandr.megaquiz.startfragment.view.StartFragment;

import butterknife.ButterKnife;

public class StartActivity extends AppCompatActivity implements StartContract.View {

    private StartContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start); // для ND
        ButterKnife.bind(this);
        mPresenter = new StartPresenter(this, new StartInteractor());


        StartFragment fragment = StartFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.linear_for_edit, fragment)
                .commit();

    }

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, StartActivity.class);
        return intent;
    }


}
