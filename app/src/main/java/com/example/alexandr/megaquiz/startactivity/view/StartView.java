package com.example.alexandr.megaquiz.startactivity.view;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.transition.TransitionManager;
import android.view.Gravity;
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
import com.example.alexandr.megaquiz.startactivity.StartInteractor;
import com.example.alexandr.megaquiz.startactivity.StartContract;
import com.example.alexandr.megaquiz.startactivity.presentation.StartPresenter;
import com.example.alexandr.megaquiz.startfragment.view.StartFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StartView extends FragmentActivity implements StartContract.View {

    private StartContract.Presenter mPresenter;

    /*
    @BindView(R.id.btn_randomQuiz)
    Button mRandomBtn;
    @BindView(R.id.btn_category)
    Button mCategoryBtn;
    @BindView(R.id.btn_test_general_questions)
    Button mTestGeneral;
    @BindView(R.id.doubt_image_view)
    ImageView mImageView;

    boolean visible = true;
    */

    //Binding navigation drawable
    StartFragment mStartFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start); // для ND
        ButterKnife.bind(this);
        mPresenter = new StartPresenter(this, new StartInteractor());
        /*
        mStartFragment = StartFragment.newInstance();
        getSupportFragmentManager().beginTransaction().replace(R.id.constraint_from_activity_start, mStartFragment).commit();

        */
    }

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, StartView.class);
        return intent;
    }

}
