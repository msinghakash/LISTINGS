package Login_Signup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.listings.R;

public class SetNewPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_new_password);
    }

    public void callUpdateSuccessfulMessage(View view) {
        Intent intent = new Intent(getApplicationContext(), ForgetPasswordUpdateSuccessfulMessageDisplay.class);
        startActivity(intent);
    }
}