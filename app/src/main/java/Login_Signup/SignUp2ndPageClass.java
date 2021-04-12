package Login_Signup;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.listings.R;

import android.widget.Button;
import android.widget.Toast;

public class SignUp2ndPageClass extends AppCompatActivity {
    //Variables
    ImageView backBtn;
    Button next;
    TextView titleText;
    RadioGroup radioGroup;
    RadioButton choosenGender;
    DatePicker datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Hiding STATUS BAR
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up2nd_page_class);

        //Hooks

        next = findViewById(R.id.signup_next_btn);
        titleText = findViewById(R.id.signup_title_text);
        radioGroup = findViewById(R.id.radiogroup);
        datePicker = findViewById(R.id.select_age);

    }

    public void callNextSignupPage(View view) {
        if(!validateGender())
        {
            return;
        }

        choosenGender = findViewById(radioGroup.getCheckedRadioButtonId());
        String gender = choosenGender.getText().toString();// '_' is at the start of the String variable because it is a local String

        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year = datePicker.getYear();
        //Combining all variables to form date
        String date = day+"/"+month+"/"+year;

        //Getting data from previous activity
        //Receiving all the data from the previous screen using getintent
        String _fullName = getIntent().getStringExtra("fullName");
        String _email = getIntent().getStringExtra("email");
        String _userName = getIntent().getStringExtra("username");
        String _password = getIntent().getStringExtra("password");

        Intent intent = new Intent(getApplicationContext(), SignUp3rdPageClass.class);

        //Passing data to next activity
        intent.putExtra("gender", gender);
        intent.putExtra("date", date);
        intent.putExtra("fullname", _fullName);
        intent.putExtra("email", _email);
        intent.putExtra("username", _userName);
        intent.putExtra("password", _password);

        startActivity(intent); //options.toBundle() will attach animations to the intent.
    }

    private boolean validateGender() {
        if (radioGroup.getCheckedRadioButtonId() == -1)//-1 represents that no radiobutton is selected.
        {
            Toast.makeText(this, "Please select gender", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }
}
