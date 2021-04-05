package Login_Signup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ScrollView;

import com.example.listings.R;
import com.google.android.material.textfield.TextInputLayout;
import com.hbb20.CountryCodePicker;

public class SignUp3rdPageClass extends AppCompatActivity {

    //Variables
    ScrollView scrollView;
    TextInputLayout phoneNumber;
    CountryCodePicker countryCodePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Hiding STATUS BAR
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up3rd_page_class);

        //Hooks for animation
        scrollView = findViewById(R.id.scroll_view_signup_page3);
        countryCodePicker = findViewById(R.id.country_code_picker);
        phoneNumber = findViewById(R.id.phone_number_in_signup);

    }

    public void callOTPPage(View view) {
        if(!validatePhone())
        {
            Log.d("SIGNUP3", "callOTPPage: Empty");
            return;
        }

        Log.d("SIGNUP3", "callOTPPage: Number detected ");
        //Receiving all the data from the previous screen using getintent
        String _fullName = getIntent().getStringExtra("fullname");
        String _userName = getIntent().getStringExtra("username");
        String _email = getIntent().getStringExtra("email");
        String _password = getIntent().getStringExtra("password");
        String _date = getIntent().getStringExtra("date");
        String _gender = getIntent().getStringExtra("gender");

        String _phoneNumber_entered = phoneNumber.getEditText().getText().toString();
        //Number added with country code

        String code = String.valueOf(countryCodePicker.getSelectedCountryCodeAsInt());
        String _phone = "+"+code+_phoneNumber_entered;

        Intent intent = new Intent(getApplicationContext(), OTP_temp.class);

        //Passing data to next activity
        intent.putExtra("gender", _gender);
        intent.putExtra("date", _date);
        intent.putExtra("fullname", _fullName);
        intent.putExtra("email", _email);
        intent.putExtra("username", _userName);
        intent.putExtra("password", _password);
        intent.putExtra("gender", _gender);
        intent.putExtra("date", _date);
        intent.putExtra("phone", _phone);

        startActivity(intent);
    }
    private Boolean validatePhone() {
        //trim to remove spaces
        String phone_value = phoneNumber.getEditText().getText().toString().trim();

        if (phone_value.isEmpty()) {
            phoneNumber.setError("Please enter number!");
            return false;
        }else {
            //This portion of the code will be executed when the full name is not empty
            //This is going to be the true statement case
            phoneNumber.setError(null);//This will remove the error
            phoneNumber.setErrorEnabled(false);//This will remove the space between each field that is taken to display error.
            return true;
        }
    }
}