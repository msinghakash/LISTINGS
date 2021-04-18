package MainScreen;

import DatabaseAndConnectors.ImageAdapter;
import DatabaseAndConnectors.Upload;
import Login_Signup.Login;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.listings.NeedHelp;
import com.example.listings.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class MainScreenOfApp extends AppCompatActivity implements NavigationView.
        OnNavigationItemSelectedListener,
        ImageAdapter.OnItemClickListner {


    //Variables-->RecyclerView
    private RecyclerView mRecyclerView;
    private ImageAdapter mAdapter;

    private FirebaseStorage mStorage;
    private DatabaseReference mDatabaseRef;
    private ValueEventListener mDBListner;
    private List<Upload> mUploads;

    //Variables-->NavigationDrawer
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen_of_app);

        //-----------NavigationDrawer----------
        //Hooks
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolBar);


        navigationView.setNavigationItemSelectedListener(this);
        navigationView.bringToFront();
        //Setting toolbar as ActionBar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Navigation Drawer Menu
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        //------------RecyclerView----------
        mRecyclerView = findViewById(R.id.items_in_firebase);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,1));

        mUploads = new ArrayList<>();

        mAdapter = new ImageAdapter(MainScreenOfApp.this, mUploads);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListner(MainScreenOfApp.this);

        mStorage = FirebaseStorage.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("uploads");

        mDBListner = mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //Clearing the list before loading all the data again so that their is no duplication of data
                mUploads.clear();

                //Looping through all the images in database reference and getting snapshot of each children
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    Upload upload = postSnapshot.getValue(Upload.class);
                    upload.setKey(postSnapshot.getKey());
                    mUploads.add(upload);
                }

                mAdapter.notifyDataSetChanged();//This will update the recycler view after it is filled with the items.

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(MainScreenOfApp.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void onItemClick(int position) {

    }

    @Override
    public void onDeleteClick(int position) {

        Upload selectedItem = mUploads.get(position);
        String selectedKey = selectedItem.getKey();//Getting the key of the selected item

        StorageReference imageRef = mStorage.getReferenceFromUrl(selectedItem.getImageUrl());
        imageRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                mDatabaseRef.child(selectedKey).removeValue();
                Toast.makeText(MainScreenOfApp.this, "Item Deleted", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_userInfo:
                String _phone = getIntent().getStringExtra("phone");
                Intent intent = new Intent(getApplicationContext(), userInfo.class);
                intent.putExtra("phone", _phone);
                startActivity(intent);
                break;
            case R.id.nav_sell:
                String userphone = getIntent().getStringExtra("phone");
                Intent intent1 = new Intent(getApplicationContext(), SellScreen.class);
                intent1.putExtra("phone", userphone);
                startActivity(intent1);
                break;
            case R.id.nav_logout:
                Intent intent3 = new Intent(MainScreenOfApp.this, Login.class);
                startActivity(intent3);
                finish();
                break;
            case R.id.nav_about:
                Intent intent4 = new Intent(getApplicationContext(), AboutScreen.class);
                startActivity(intent4);
                break;
            case R.id.nav_help:
                Intent intent5 = new Intent(getApplicationContext(), NeedHelp.class);
                startActivity(intent5);
                break;
        }

        return true;
    }

    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDatabaseRef.removeEventListener(mDBListner);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        toggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        toggle.onConfigurationChanged(newConfig);
    }
}
