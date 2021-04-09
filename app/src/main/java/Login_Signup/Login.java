package Login_Signup;

import MainScreen.MainScreenOfApp;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.listings.FirstAppScreen;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import com.example.listings.R;
import com.google.android.material.textfield.TextInputLayout;
import com.hbb20.CountryCodePicker;

import java.util.Queue;

public class Login extends AppCompatActivity {

    TextInputLayout phoneNumber, password_entered_by_user;
    CountryCodePicker countryCodePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Hiding STATUS BAR
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        countryCodePicker = findViewById(R.id.login_country_code_picker);
        phoneNumber = findViewById(R.id.phone_number_in_login);
        password_entered_by_user = findViewById(R.id.login_password);

    }

    public void userLogin(View view) {

        //Checking connection to the internet
        if(!isConnected(Login.this))
        {
            Log.d("Login.java", "userLogin: Entered to check if connected or not");
            showCustomDialogue();
        }


        if (!validateLoginPhoneNumber() | !validateLoginPassword()) {
            Log.d("Login", "Entering Empty");
            return;
        }

        final String password = password_entered_by_user.getEditText().getText().toString().trim();
        String _phoneNumber_entered_login = phoneNumber.getEditText().getText().toString();
        //Number added with country code

        if (_phoneNumber_entered_login.charAt(0) == '0') {
            _phoneNumber_entered_login = _phoneNumber_entered_login.substring(1);
        }

        String code_login = String.valueOf(countryCodePicker.getSelectedCountryCodeAsInt());
        String _phone = "+" + code_login + _phoneNumber_entered_login;


        //Getting data and comparing from database
        Query verifyUser = FirebaseDatabase.getInstance().getReference("Users").orderByChild("phoneNumber").equalTo(_phone);

        verifyUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //If snapShot arrives then it indicates that the some data has arrived from the Firebase
                if (snapshot.exists()) {
                    phoneNumber.setError(null);
                    phoneNumber.setErrorEnabled(false);

                    String get_saved_password_of_user = snapshot.child(_phone).child("password").getValue(String.class);
                    if (get_saved_password_of_user.equals(password)) {
                        password_entered_by_user.setError(null);
                        password_entered_by_user.setErrorEnabled(false);

                        String fullName = snapshot.child(_phone).child("fullName").getValue(String.class);
                        String userName = snapshot.child(_phone).child("userName").getValue(String.class);
                        String email = snapshot.child(_phone).child("email").getValue(String.class);
                        String dob = snapshot.child(_phone).child("date").getValue(String.class);
                        String gender = snapshot.child(_phone).child("gender").getValue(String.class);

                        Intent intent = new Intent(getApplicationContext(), MainScreenOfApp.class);
                        intent.putExtra("phone", _phone);
                        startActivity(intent);
                    } else {
                        Log.d("Test Message", get_saved_password_of_user);
                        Toast.makeText(Login.this, "Password does not match", Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(Login.this, "User does not exist", Toast.LENGTH_LONG).show();
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

        AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
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

    private boolean isConnected(Login login)
    {
        Log.d("Login.java", "isConnected: Entered isConnected method");
        ConnectivityManager connectivityManager = (ConnectivityManager) login.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo wifiConnection = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileConnection = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if((wifiConnection != null && wifiConnection.isConnected()) || (mobileConnection != null && mobileConnection.isConnected()))
        {
            return true;
        }
        else {
            return false;
        }
    }

    private Boolean validateLoginPhoneNumber() {
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

    private Boolean validateLoginPassword() {
        //trim to remove spaces
        String password_value = password_entered_by_user.getEditText().getText().toString().trim();

        if (password_value.isEmpty()) {
            password_entered_by_user.setError("Please enter password!");
            return false;
        } else {
            //This portion of the code will be executed when the full name is not empty
            //This is going to be the true statement case
            password_entered_by_user.setError(null);//This will remove the error
            password_entered_by_user.setErrorEnabled(false);//This will remove the space between each field that is taken to display error.
            return true;
        }
    }
}