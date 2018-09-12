package com.example.alexandr.megaquiz.categoryActivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.alexandr.megaquiz.R;

/**
 * Created by Alexandr Mikhalev on 12.09.2018.
 *
 * @author Alexandr Mikhalev
 */
public class CategoryActivityView extends AppCompatActivity implements CategoryContract.View {
    private CategoryContract.Presenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        mPresenter = new CategoryActivityPresenter(this);
    }
}
