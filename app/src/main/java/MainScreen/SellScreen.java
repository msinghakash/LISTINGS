package MainScreen;

import DatabaseAndConnectors.Upload;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.listings.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class SellScreen extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;//Number wil be used to identify the image rewuest

    private Button mButtonChooseImage;
    private Button mButtonImageUpload;

    private EditText mImageTextDescription;
    private EditText mSellingInformationEmail;
    private EditText mEditTextFileName;
    private ImageView mImageView;
    private ProgressBar mProgressBar;

    private Uri mImageUri;

    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;
    private DatabaseReference userDatabaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_screen);

        mButtonChooseImage = findViewById(R.id.button_choose_image);
        mButtonImageUpload = findViewById(R.id.button_image_upload);

        mImageTextDescription = findViewById(R.id.text_view_image_description);
        mSellingInformationEmail = findViewById(R.id.selling_information_email);
        mEditTextFileName = findViewById(R.id.edit_text_image_file_name);
        mImageView = findViewById(R.id.image_view);
        mProgressBar = findViewById(R.id.progress_bar);

        String _phone = getIntent().getStringExtra("phone");

        mStorageRef = FirebaseStorage.getInstance().getReference("uploads");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("uploads");

        mButtonChooseImage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openFileChooser();

            }

        });
       mButtonImageUpload.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               uploadFile();

           }
       });
    }

    private void openFileChooser()
    {
        Intent intent = new Intent();
        intent.setType("image/*");//This will make to show only image files in the folder
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null)
        {
            mImageUri = data.getData();
            Picasso.with(this).load(mImageUri).into(mImageView);
        }
        {

        }
    }
    //Getting file extension of image
    private String getFileExtension(Uri uri)
    {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void uploadFile()
    {
        if(mImageUri != null)
        {
            StorageReference fileReference = mStorageRef.child(System.currentTimeMillis()+"."+getFileExtension(mImageUri));

            fileReference.putFile(mImageUri).continueWithTask(
                    new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                        @Override
                        public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                            if (!task.isSuccessful()) {
                                throw task.getException(); }
                            return fileReference.getDownloadUrl();
                        } })
                    .addOnCompleteListener(new OnCompleteListener<Uri>() {
                        @Override
                        public void onComplete(@NonNull Task<Uri> task) {
                            if (task.isSuccessful()) { Uri downloadUri = task.getResult();
                                Upload upload = new Upload(mEditTextFileName.getText().toString().trim(), downloadUri.toString(), mImageTextDescription.getText().toString(), mSellingInformationEmail.getText().toString().trim());
                                mDatabaseRef.push().setValue(upload);
                                Toast.makeText(SellScreen.this, "Upload Successful", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(SellScreen.this, MainScreenOfApp.class);
                                startActivity(intent);
                            }
                            else { Toast.makeText(SellScreen.this, "Upload Failed: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            Toast.makeText(SellScreen.this, e.getMessage(), Toast.LENGTH_LONG).show();

                        }
                    });
        }
        else
        {
            Toast.makeText(this, "No File Selected", Toast.LENGTH_SHORT).show();
        }

    }
}