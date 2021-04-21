package MainScreen;

import Login_Signup.Login;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.listings.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class userInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        // Initiallizing variables
        TextView usersname, usersphonenumber, usersemail, usersusername, userspassword;


        usersname = findViewById(R.id.usersname);
        usersphonenumber = findViewById(R.id.usersphonenumber);
        usersemail = findViewById(R.id.usersemail);
        usersusername = findViewById(R.id.usersusername);
        userspassword = findViewById(R.id.userspassword);


        String _phone = getIntent().getStringExtra("phone");

        //Getting data and comparing from database
        Query verifyUser = FirebaseDatabase.getInstance().getReference("Users").orderByChild("phoneNumber").equalTo(_phone);

        verifyUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //If snapShot arrives then it indicates that the some data has arrived from the Firebase
                if (snapshot.exists()) {

                    // Retrieving the user data
                    String get_saved_password_of_user = snapshot.child(_phone).child("password").getValue(String.class);
                        String fullName = snapshot.child(_phone).child("fullName").getValue(String.class);
                        String userName = snapshot.child(_phone).child("userName").getValue(String.class);
                        String email = snapshot.child(_phone).child("email").getValue(String.class);
                        String dob = snapshot.child(_phone).child("date").getValue(String.class);
                        String gender = snapshot.child(_phone).child("gender").getValue(String.class);

                        // Setting the data on the user screen
                        usersname.setText(fullName);
                        usersphonenumber.setText(_phone);
                        usersemail.setText(email);
                        usersusername.setText(userName);
                        userspassword.setText(get_saved_password_of_user);
                    }
                // Printing the ERROR message
                else {
                    Toast.makeText(userInfo.this, "User does not exist", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            // Printing the ERROR message.
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }
    }
