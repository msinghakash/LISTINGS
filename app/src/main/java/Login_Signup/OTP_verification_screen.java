package Login_Signup;

import Database.UserHelperClass;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.example.listings.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;
public class OTP_verification_screen extends AppCompatActivity {

    PinView pinEntered;
    String gettingSystemCode;

    String fullName, phoneNumber, email, userName, password, date, gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("OTP_VERIFICATION_SCREEN", "onCreate: ENTER");
        //Hiding STATUS BAR
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_o_t_p_verification_screen);

        pinEntered = findViewById(R.id.pin_entered);

        fullName = getIntent().getStringExtra("fullname");
        email = getIntent().getStringExtra("email");
        userName = getIntent().getStringExtra("username");
        password = getIntent().getStringExtra("password");
        phoneNumber = getIntent().getStringExtra("phone");
        gender = getIntent().getStringExtra("gender");
        date = getIntent().getStringExtra("date");

        sendOTPToUser(phoneNumber);

    }

    private void sendOTPToUser(String phoneNumber)
    {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber, //PhoneNumber to Verify
                60,        //Timeout duration
                TimeUnit.SECONDS,//Unit of timeout
                TaskExecutors.MAIN_THREAD,//Activity(gor call back binding)
                mCallbacks);//OnVerificationChangedCallbacks
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks =
            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                //Below function 'onCodeSent' is overridden because we want to create the facility of Manual input of OTP as well
                /*The code(OTP) passed is already in String s variable passed as a parameter in the function.
                Therefore, to get that system generated code(OTP) we have to make a variable
                And we have done that in the variable name assigned at the top as 'gettingSystemCode'
                 */
                @Override
                public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                    super.onCodeSent(s, forceResendingToken);
                    gettingSystemCode = s;//Now this variable can be used to make comparison with the OTP value entered by the user.
                }

                @Override
                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                    String otp = phoneAuthCredential.getSmsCode();
                    if(otp!=null)
                    {
                        pinEntered.setText(otp);//This will show the user the arrived code on the screen
                        //Starting verification in the below function
                        verifyCode(otp);
                    }

                }

                @Override
                public void onVerificationFailed(@NonNull FirebaseException e) {
                    Toast.makeText(OTP_verification_screen.this, e.getMessage(),Toast.LENGTH_SHORT).show();

                }
            };

    private void verifyCode(String otp) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(gettingSystemCode, otp);
        signInWithPhoneAuthCredential(credential);
    }
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d("OTP Verification", "Checking the verified part");
                            //below function will store the data into the firebase.
                            storingNewUserData();
                        } else {
                            // Sign in failed, display a message
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                Toast.makeText(OTP_verification_screen.this,"ERROR IN VERIFICATION", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });
    }
    //This method will run when the user will press VERIFY button
    public void callNextScreenFromOTP(View view)
    {
        String otpByuser = pinEntered.getText().toString();
        if(!otpByuser.isEmpty())
        {
            verifyCode(otpByuser);
        }
    }

    public void storingNewUserData()
    {
        Log.d("OTP Verification","Entered storing new data of user!!!!!!!!");
        Toast.makeText(OTP_verification_screen.this,"VERIFIED", Toast.LENGTH_LONG).show();
        FirebaseDatabase rootNode = FirebaseDatabase.getInstance();//This 'rootNode' will start pointing to the database.
        DatabaseReference reference = rootNode.getReference("Users");//This will point to all the reference/tables in the database.

        UserHelperClass addNewUser = new UserHelperClass(fullName, email, userName, password, date, gender, phoneNumber);

        reference.child(phoneNumber).setValue(addNewUser);
    }
}