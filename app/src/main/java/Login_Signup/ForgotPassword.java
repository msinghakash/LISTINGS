package Login_Signup;

import Database.CheckInternetConnection;
import MainScreen.MainScreenOfApp;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.listings.FirstAppScreen;
import com.example.listings.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.hbb20.CountryCodePicker;

public class ForgotPassword extends AppCompatActivity {

    private TextView forgotPassword_title, forgotPassword_description;
    private TextInputLayout phoneNumber;
    private CountryCodePicker countryCodePicker;
    private Button nextButton;
    private Animation animation;
    String stepAfterVerificationofPhoneNumber = "testingVariable";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        //Hiding STATUS BAR
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_forgot_password);

        //Hooks
        forgotPassword_title = findViewById(R.id.forgot_password_title);
        forgotPassword_description = findViewById(R.id.forgot_password_display_message);
        phoneNumber = findViewById(R.id.phone_number_in_forgotPassword);
        countryCodePicker = findViewById(R.id.country_code_picker_forgotPassword);
        nextButton = findViewById(R.id.forgot_password_next_btn);

    }

    public void verifyingthePhoneNumber(View view) {

        //Checking the internet connection
        CheckInternetConnection checkInternetConnection = new CheckInternetConnection();
        if (!checkInternetConnection.isConnected(this)) {
            showCustomDialogue();
        }


        if (!validateForgotPasswordPhoneNumber()) {

            return;
        }

        String _phoneNumber_entered_login = phoneNumber.getEditText().getText().toString();
        //Number added with country code

        if (_phoneNumber_entered_login.charAt(0) == '0') {
            _phoneNumber_entered_login = _phoneNumber_entered_login.substring(1);
        }

        String code_login = String.valueOf(countryCodePicker.getSelectedCountryCodeAsInt());
        String _phone = "+" + code_login + _phoneNumber_entered_login;

        //Checking the presence of user in the database
        //Getting data and comparing from database
        Query verifyUser = FirebaseDatabase.getInstance().getReference("Users").orderByChild("phoneNumber").equalTo(_phone);
        verifyUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //If snapShot arrives then it indicates that the some data has arrived from the Firebase
                if (snapshot.exists()) {
                    phoneNumber.setError(null);
                    phoneNumber.setErrorEnabled(false);

                    stepAfterVerificationofPhoneNumber = "updatePassword";
                    Intent intent = new Intent(getApplicationContext(), OTP_temp.class);
                    intent.putExtra("phone", _phone);
                    intent.putExtra("stepAfterVerificationofPhoneNumber", stepAfterVerificationofPhoneNumber);
                    startActivity(intent);
                    finish();
                } else {
                    phoneNumber.setError("This user does not exists");
                    phoneNumber.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }
    private void showCustomDialogue() {

        Log.d("Login.java", "showCustomDialogue: Entered showCustomDialogue");

        AlertDialog.Builder builder = new AlertDialog.Builder(ForgotPassword.this);
        builder.setMessage("Please connect to the internet").setCancelable(false).setPositiveButton("Connect", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                startActivity(new Intent(getApplicationContext(), FirstAppScreen.class));
                finish();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
    private boolean validateForgotPasswordPhoneNumber() {
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