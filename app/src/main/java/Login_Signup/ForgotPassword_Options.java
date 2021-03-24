package Login_Signup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.listings.R;
public class ForgotPassword_Options extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password__options);
    }

    public void callForgotPasswordOptionsPage(View view) {
        Intent intent= new Intent(getApplicationContext(), SetNewPassword.class);
        startActivity(intent);
    }
}