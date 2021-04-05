package Login_Signup;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;

import com.example.listings.R;
import com.google.android.material.textfield.TextInputLayout;

public class SignUp extends AppCompatActivity {
    //Variables
    ImageView backBtn;
    Button next;
    TextView titleText;
    //Variables to get data from the signup screen
    TextInputLayout fullname, email, username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Hiding STATUS BAR
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up);

        //Hooks for transition/animation
        backBtn = findViewById(R.id.signup_back_button);
        next = findViewById(R.id.signup_next_btn);
        titleText = findViewById(R.id.signup_title_text);

        //Hooks for getting the data from signup page
        fullname = findViewById(R.id.enter_full_name);
        email = findViewById(R.id.enter_email);
        username = findViewById(R.id.enter_username);
        password = findViewById(R.id.enter_password);
    }

    public void callNextSignupPage(View view) {

        if(!validateFullName() | !validateUsername() | !validatePassword())
        {
            return;
        }


        String fullname_value = fullname.getEditText().getText().toString();
        String email_value = email.getEditText().getText().toString().trim();
        String username_value = username.getEditText().getText().toString().trim();
        String password_value = password.getEditText().getText().toString().trim();

        Intent intent = new Intent(getApplicationContext(), SignUp2ndPageClass.class);
        //Adding Transition
        // 3 below represents the total number of transitions we want to perform
        Pair[] pairs = new Pair[3];

        pairs[0] = new Pair<View, String>(backBtn, "transition_back_button");
        pairs[1] = new Pair<View, String>(titleText, "transition_title_text");
        pairs[2] = new Pair<View, String>(next, "transition_next_btn");

        // ActivityOptions to transition between Activities using cross-Activity scene animations.
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SignUp.this, pairs);
        //Passing data to next activity
        intent.putExtra("fullName", fullname_value);
        intent.putExtra("email", email_value);
        intent.putExtra("username", username_value);
        intent.putExtra("password", password_value);

        startActivity(intent, options.toBundle()); //options.toBundle() will attach animations to the intent.
    }

    // Functions for validations
    //Function for getting data
    private Boolean validateFullName() {
        //trim to remove spaces
        String fullname_value = fullname.getEditText().getText().toString().trim();
        if (fullname_value.isEmpty()) {
            fullname.setError("Please enter name!");
            return false;
        } else {
            //This portion of the code will be executed when the full name is not empty
            //This is going to be the true statement case
            fullname.setError(null);//This will remove the error
            fullname.setErrorEnabled(false);//This will remove the space between each field that is taken to display error.
            return true;
        }
    }

    private Boolean validateEmail() {
        //trim to remove spaces
        String email_value = email.getEditText().getText().toString().trim();
        String validate_email =  "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (email_value.isEmpty()) {
            email.setError("Please enter name!");
            return false;
        } /*else if (email_value.contains(" ")) {
            email.setError("Please enter without space!");
            return false;
        } else if (email_value.matches(validate_email)) {
            email.setError("Username cannot be more than 30 letters!");
            return false;
        }*/ else {
            //This portion of the code will be executed when the full name is not empty
            //This is going to be the true statement case
            email.setError(null);//This will remove the error
            email.setErrorEnabled(false);//This will remove the space between each field that is taken to display error.
            return true;
        }
    }

    private Boolean validateUsername() {
        //trim to remove spaces
        String username_value = username.getEditText().getText().toString().trim();

        if (username_value.isEmpty()) {
            username.setError("Please enter username!");
            return false;
        } else if (username_value.contains(" ")) {
            username.setError("Please enter without space!");
            return false;
        } else {
            //This portion of the code will be executed when the full name is not empty
            //This is going to be the true statement case
            username.setError(null);// This will remove the error
            username.setErrorEnabled(false);//This will remove the space between each field that is taken to display error.
            return true;
        }
    }

    private Boolean validatePassword() {
        //trim to remove spaces
        String password_value = password.getEditText().getText().toString().trim();
        String validate_password = "^(?=.*[A-Za-z])(?=.*\\\\d)(?=.*[$@$!%*#?&])[A-Za-z\\\\d$@$!%*#?&]{8,}$";

        if (password_value.isEmpty()) {
            password.setError("Please enter password!");
            return false;
        } else if (password_value.contains(" ")) {
            password.setError("Please enter without space!");
            return false;
        } else if (password_value.matches(validate_password)) {
            password.setError("Username cannot be more than 30 letters!");
            return false;
        } else {
            //This portion of the code will be executed when the full name is not empty
            //This is going to be the true statement case
            password.setError(null);//This will remove the error
            password.setErrorEnabled(false);//This will remove the space between each field that is taken to display error.
            return true;
        }
    }
}