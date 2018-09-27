package com.example.alexandr.megaquiz.categoryStorageActivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.alexandr.megaquiz.R;

/**
 * Created by Alexandr Mikhalev on 11.09.2018.
 *
 * @author Alexandr Mikhalev
 */
public class CategoryStorageActivityView extends AppCompatActivity implements CategoryStorageContract.View {
    private CategoryStorageContract.Presenter mPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage_category);

        mPresenter = new CategoryStorageActivityPresenter(this);
    }
}
