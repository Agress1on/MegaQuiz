package com.example.alexandr.megaquiz.startrouter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.alexandr.megaquiz.R;
import com.example.alexandr.megaquiz.infofragment.view.InfoFragment;
import com.example.alexandr.megaquiz.quizrouter.QuizRouter;
import com.example.alexandr.megaquiz.quizstoragefragment.view.QuizStorageFragment;
import com.example.alexandr.megaquiz.startfragment.StartFragmentContract;
import com.example.alexandr.megaquiz.startfragment.view.StartFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StartRouter extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, StartFragmentContract.Router {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawer;

    @BindView(R.id.nav_view)
    NavigationView mNavigationView;

    private StartFragment mStartFragment;
    private InfoFragment mInfoFragment;
    private QuizStorageFragment mQuizStorageFragment;
    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nd_activity_main_for_start_view); // для ND
        ButterKnife.bind(this);

        //ND START
        setSupportActionBar(mToolbar);
        ActionBarDrawerToggle toggle =
                new ActionBarDrawerToggle(this, mDrawer, mToolbar,
                        R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.addDrawerListener(toggle);
        toggle.syncState();
        mNavigationView.setNavigationItemSelectedListener(this);
        //ND END

        mQuizStorageFragment = QuizStorageFragment.newInstance();
        mStartFragment = StartFragment.newInstance();
        mInfoFragment = InfoFragment.newInstance();
        mFragmentManager = getSupportFragmentManager();
        if (savedInstanceState == null) {
            setStartFragment();
        }
    }

    @Override
    public void setStartFragment() {
        mFragmentManager.beginTransaction()
                .add(R.id.container_for_fragments, mStartFragment)
                .commit();
    }

    @Override
    public void goToRandomQuestion() {
        Intent intent = QuizRouter.getIntent(this, "");
        startActivity(intent);
    }

    @Override
    public void goToQuizStorage() {
        mFragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.container_for_fragments, mQuizStorageFragment)
                .commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.about) {
            mFragmentManager.beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.container_for_fragments, mInfoFragment)
                    .commit();
        }
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

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, StartRouter.class);
        return intent;
    }
}
