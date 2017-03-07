package com.harrricdev.edwin.andelaexercise.profiles;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.transition.Fade;
import android.support.transition.TransitionSet;
import android.support.transition.Visibility;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.harrricdev.edwin.andelaexercise.Models.Developer;
import com.harrricdev.edwin.andelaexercise.R;
import com.squareup.picasso.Picasso;
import com.transitionseverywhere.TransitionManager;
import com.transitionseverywhere.extra.Scale;

public class ProfileActivity extends AppCompatActivity implements ProfileContract.ProfileView {

    TextView githubUrl, mProfileName, mUserName;
    LinearLayout gitUrlAction;
    ImageView gravatar;
    FloatingActionButton fab;
    CoordinatorLayout coordinatorLayout;

    boolean visible;

    Developer mDev;
    String login;
    ProgressBar mProgressBar;


    ProfileContract.ProfilePresenter profilePresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);




        Bundle b = getIntent().getExtras();

        if( b != null){
            login = b.getString("LOGIN");
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Android developer");
        setSupportActionBar(toolbar);

        githubUrl = (TextView) findViewById(R.id.githubUrl);
        mProfileName = (TextView) findViewById(R.id.profile_name);
        mUserName = (TextView) findViewById(R.id.username);
        gravatar = (ImageView) findViewById(R.id.gravar);
        mProgressBar = (ProgressBar) findViewById(R.id.progress);
        gitUrlAction = (LinearLayout) findViewById(R.id.url);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profilePresenter.shareProfile();
            }
        });

        gitUrlAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profilePresenter.loadProfile();
            }
        });

        profilePresenter = new ProfilePresenterImpl(this);


    }

    @Override
    protected void onResume() {
        super.onResume();
        profilePresenter.onResume(login);
    }

    @Override
    public void shareDeveloperProfile() {

        Intent myIntent = new Intent(Intent.ACTION_SEND);
        myIntent.setType("text/plain");
        String shareBody = "Check out this awesome developer @<"+ mDev.getLogin() +">, <"+ mDev.getHtmlUrl() +">.";
        myIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(myIntent, "share developer via"));
    }

    @Override
    public void showDeveloperProfile() {

        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse( mDev.getHtmlUrl() ));
        Intent browserChooserIntent = Intent.createChooser(browserIntent , "Choose browser of your choice");
        startActivity(browserChooserIntent );

    }

    @Override
    public void populateViews(Developer dev) {
        mDev = dev;
        mProfileName.setText(dev.getName());
        mUserName.setText(dev.getLogin());
        githubUrl.setText(dev.getHtmlUrl());

        /*Glide
                .with(this)
                .load(dev.getAvatarUrl())
                .placeholder(R.drawable.profile_place)
                .centerCrop()
                .crossFade()
                .into(gravatar); */

        Picasso.with(this).load(dev.getAvatarUrl()).placeholder(R.drawable.profile_place).into(gravatar);

        Log.d("AMiNNNN", dev.getAvatarUrl());


    }

    @Override
    public void hideProgress() {
        mProfileName.setVisibility(View.VISIBLE);
        mUserName.setVisibility(View.VISIBLE);
        fab.show();
        gitUrlAction.setVisibility(View.VISIBLE);
        //gitUrlAction.animate().alpha(1.0f).setDuration(500);

        mProgressBar.setVisibility(View.GONE);

    }

    @Override
    public void showProgress() {
        mProfileName.setVisibility(View.GONE);
        mUserName.setVisibility(View.GONE);
        fab.setVisibility(View.GONE);
        gitUrlAction.setVisibility(View.GONE);

        mProgressBar.setVisibility(View.VISIBLE);
    }
}
