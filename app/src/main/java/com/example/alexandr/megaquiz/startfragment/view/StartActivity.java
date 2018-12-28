package com.example.alexandr.megaquiz.startfragment.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.alexandr.megaquiz.R;
import com.example.alexandr.megaquiz.infofragment.view.InfoFragment;
import com.example.alexandr.megaquiz.quizfragment.view.QuizActivity;
import com.example.alexandr.megaquiz.quizstoragefragment.view.QuizStorageFragment;
import com.example.alexandr.megaquiz.startfragment.StartFragmentContract;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StartActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, StartFragmentContract.Router {

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

        //ND START
        setSupportActionBar(mToolbar);
        ActionBarDrawerToggle toggle =
                new ActionBarDrawerToggle(this, mDrawer, mToolbar,
                        R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.addDrawerListener(toggle);
        toggle.syncState();
        mNavigationView.setNavigationItemSelectedListener(this);
        //ND END

        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.container_for_fragments);
        if (fragment == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container_for_fragments, StartFragment.newInstance())
                    .commit();
        }

    }

    private void setFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .addToBackStack(null)
                .replace(R.id.container_for_fragments, fragment)
                .commit();
    }

    @Override
    public void goToRandomQuiz() {
        Intent intent = QuizActivity.getIntent(this, "");
        startActivity(intent);
    }

    @Override
    public void goToQuizStorage() {
        setFragment(QuizStorageFragment.newInstance());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.about) {
            setFragment(InfoFragment.newInstance());
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
        return new Intent(context, StartActivity.class);
    }
}
