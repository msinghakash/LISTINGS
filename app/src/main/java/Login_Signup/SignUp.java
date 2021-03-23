package Login_Signup;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;

import com.example.listings.R;

public class SignUp extends AppCompatActivity {
    //Variables
    ImageView backBtn;
    Button next;
    TextView titleText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Hiding STATUS BAR
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up);

        //Hooks
        backBtn = findViewById(R.id.signup_back_button);
        next = findViewById(R.id.signup_next_btn);
        titleText = findViewById(R.id.signup_title_text);


    }

    public void callNextSignupPage(View view) {

        Intent intent = new Intent(SignUp.this, SignUp2ndPageClass.class);
        //Adding Transition
        // 3 below represents the total number of transitions we want to perform
        Pair[] pairs = new Pair[3];

        pairs[0] = new Pair<View, String>(backBtn, "transition_back_button");
        pairs[1] = new Pair<View, String>(titleText, "transition_title_text");
        pairs[2] = new Pair<View, String>(next, "transition_next_btn");

        // ActivityOptions to transition between Activities using cross-Activity scene animations.
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SignUp.this, pairs);
        startActivity(intent, options.toBundle()); //options.toBundle() will attach animations to the intent.
    }
}