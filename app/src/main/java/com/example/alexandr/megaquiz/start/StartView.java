package com.example.alexandr.megaquiz.start;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.alexandr.megaquiz.Constants;
import com.example.alexandr.megaquiz.R;
import com.example.alexandr.megaquiz.bankQuestion.BankQuestion;
import com.example.alexandr.megaquiz.quiz.QuizView;
import com.example.alexandr.megaquiz.quizStorage.QuizStorageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StartView extends AppCompatActivity implements StartContract.View, NavigationView.OnNavigationItemSelectedListener {

    private StartPresenter mPresenter;

    @BindView(R.id.btn_randomQuiz)
    Button mRandomBtn;
    @BindView(R.id.btn_category)
    Button mCategoryBtn;
    @BindView(R.id.btn_test_general_questions)
    Button mTestGeneral;
    @BindView(R.id.doubt_image_view)
    ImageView mImageView;

    boolean visible = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_start);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mPresenter = new StartPresenter(this, new StartInteractor(new BankQuestion()));

        // все что ниже для Navigation Drawer
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, StartView.class);
        return intent;
    }

    @Override
    public void startQuizViewWithRandom(String randomCategory) {
        Intent intent = QuizView.getIntent(StartView.this, randomCategory);
        startActivity(intent);
    }

    @Override
    public void startQuizStorage() {
        Intent intent = QuizStorageView.getIntent(StartView.this);
        startActivity(intent);
    }

    @Override
    public void startTestGeneralQuestions() {
        Intent intent = QuizView.getIntent(StartView.this, Constants.GENERAL_QUESTIONS);
        startActivity(intent);
    }

    @OnClick({R.id.btn_randomQuiz, R.id.btn_category, R.id.btn_test_general_questions})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_randomQuiz:
                mPresenter.onRandomButton();
                break;
            case R.id.btn_category:
                mPresenter.onButtonCategory();
                break;
            case R.id.btn_test_general_questions:
              //  mPresenter.onButtonGeneralQuestionsTest();
                animation();
        }
    }

    private void animation() {
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.start_constraint);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            TransitionManager.beginDelayedTransition(viewGroup, new Slide(Gravity.RIGHT));
        }
        visible = !visible;
        mImageView.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    // все что ниже - для Navigation Drawer
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.about) {
            // Handle the camera action
            Toast.makeText(this, "Click about us", Toast.LENGTH_SHORT).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
