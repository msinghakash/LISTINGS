package com.example.listings;

import Login_Signup.Login;
import Login_Signup.SignUp;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;

public class LoginScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Hiding Status Bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login_screen);
    }

    public void loginScreenCalling(View view) {
        //getApplicationContext() will get the name of the current file which is LoginScreen.java
        Intent intent = new Intent(getApplicationContext(), Login.class);
        //Creating animation inside a Pair
        //1 in the square bracket means the number of elements you want to animate.
        Pair[] pairs = new Pair[1];
        // transition_login is the name given by us for the transition in activity_login_screen.xml
        // this is helpful for short coding technique, we can also use the findViewById to locate the
        // login button id from activity_login_screen.xml file and then pass that variable name in the place of
        // transition_login String, assigned by us.
        pairs[0] = new Pair<View, String>(findViewById(R.id.login_btn), "transition_login");
        // ActivityOptions to transition between Activities using cross-Activity scene animations.
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(LoginScreen.this, pairs);
        startActivity(intent, options.toBundle());
    }

    public void signupScreenCalling(View view) {
        //getApplicationContext() will get the name of the current file which is LoginScreen.java
        Intent intent = new Intent(getApplicationContext(), SignUp.class);
        //Creating animation inside a Pair
        //1 in the square bracket means the number of elements you want to animate.
        Pair[] pairs = new Pair[1];
        // transition_login is the name given by us for the transition in activity_login_screen.xml
        // this is helpful for short coding technique, we can also use the findViewById to locate the
        // signup button id from activity_login_screen.xml file and then pass that variable name in the place of
        // transition_login String, assigned by us.
        pairs[0] = new Pair<View, String>(findViewById(R.id.signup_btn), "transition_signup");
        // ActivityOptions to transition between Activities using cross-Activity scene animations.
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(LoginScreen.this, pairs);
        startActivity(intent, options.toBundle());
    }
}