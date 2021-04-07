package Login_Signup;

import Database.CheckInternetConnection;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.listings.FirstAppScreen;
import com.example.listings.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SetNewPassword extends AppCompatActivity {

    TextInputLayout newPassword, confirmPassword;
    Button update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_new_password);

        //Hiding STATUS BAR
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_set_new_password);

        newPassword = findViewById(R.id.newPassword);
        confirmPassword = findViewById(R.id.confirmPassword);
    }

    public void setNewPassword(View view) {

        CheckInternetConnection checkInternetConnection = new CheckInternetConnection();
        if (!checkInternetConnection.isConnected(this)) {
            showCustomDialogue();
        }

        if (!validateNewPassword() | !validateConfirmPassword()) {

            return;
        }

        String _newPassword = newPassword.getEditText().toString().trim();
        String _phoneNumber = getIntent().getStringExtra("phoneNumber");

        //Updating data in the Database
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.child("phoneNumber").child("password").setValue(_newPassword);

        startActivity(new Intent(getApplicationContext(), ForgetPasswordUpdateSuccessfulMessageDisplay.class));
        finish();
    }
    private void showCustomDialogue() {

        Log.d("Login.java", "showCustomDialogue: Entered showCustomDialogue");

        AlertDialog.Builder builder = new AlertDialog.Builder(SetNewPassword.this);
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

    private boolean validateNewPassword() {
        //trim to remove spaces
        String newPasswordValue = newPassword.getEditText().getText().toString().trim();

        if (newPasswordValue.isEmpty()) {
            newPassword.setError("Please enter password!");
            return false;
        } else {
            //This portion of the code will be executed when the full name is not empty
            //This is going to be the true statement case
            newPassword.setError(null);//This will remove the error
            newPassword.setErrorEnabled(false);//This will remove the space between each field that is taken to display error.
            return true;
        }
    }
    private boolean validateConfirmPassword() {
        //trim to remove spaces
        String confirmPasswordValue = confirmPassword.getEditText().getText().toString().trim();

        if (confirmPasswordValue.isEmpty()) {
            confirmPassword.setError("Please enter password!");
            return false;
        } else {
            //This portion of the code will be executed when the full name is not empty
            //This is going to be the true statement case
            confirmPassword.setError(null);//This will remove the error
            confirmPassword.setErrorEnabled(false);//This will remove the space between each field that is taken to display error.
            return true;
        }
    }
}