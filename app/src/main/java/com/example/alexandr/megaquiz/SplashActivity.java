package com.example.alexandr.megaquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.alexandr.megaquiz.startactivity.view.StartView;

/**
 * Created by Alexandr Mikhalev on 10.09.2018.
 *
 * @author Alexandr Mikhalev
 */
public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = StartView.getIntent(this);
        startActivity(intent);
        finish();
    }
}
