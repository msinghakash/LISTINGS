package Login_Signup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.listings.R;

public class ForgotPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
    }

    public void callNextForgotPasswordPage(View view) {
        Intent intent= new Intent(getApplicationContext(), ForgotPassword_Options.class);
        startActivity(intent);
    }
}