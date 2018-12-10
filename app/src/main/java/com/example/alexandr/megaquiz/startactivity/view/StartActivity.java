package com.example.alexandr.megaquiz.startactivity.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.alexandr.megaquiz.R;
import com.example.alexandr.megaquiz.startactivity.interactor.StartActivityInteractor;
import com.example.alexandr.megaquiz.startactivity.StartContract;
import com.example.alexandr.megaquiz.startactivity.presentation.StartActivityPresenter;
import com.example.alexandr.megaquiz.startfragment.view.StartFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StartActivity extends AppCompatActivity implements StartContract.View, NavigationView.OnNavigationItemSelectedListener {

    private StartContract.Presenter mPresenter;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawer;
    @BindView(R.id.nav_view)
    NavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nd_activity_main_for_start_view); // для ND
        ButterKnife.bind(this);
        mPresenter = new StartActivityPresenter(this, new StartActivityInteractor());




        //ND
        setSupportActionBar(mToolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.addDrawerListener(toggle);
        toggle.syncState();
        mNavigationView.setNavigationItemSelectedListener(this);
        //

        /*
        StartFragment fragment = StartFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.linear_for_edit_for_nd, fragment)
                .commit();
        */
    }

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, StartActivity.class);
        return intent;
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.about) {
            // Handle the camera action
            Toast.makeText(this, "Click about us", Toast.LENGTH_SHORT).show();
        }
        /*
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        */
        mDrawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
