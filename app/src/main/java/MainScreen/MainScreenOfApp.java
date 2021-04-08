package MainScreen;

import DatabaseAndConnectors.Adapter;
import Login_Signup.Login;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.listings.R;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainScreenOfApp extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    //Variables-->RecyclerView
    RecyclerView itemsPresentInFirebase;
    List<String> titles;
    List<Integer> images;
    Adapter adapter;

    //Variables-->NavigationDrawer
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

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
        //Navigation Drawer Menu
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        //------------RecyclerView----------
        itemsPresentInFirebase = findViewById(R.id.items_in_firebase);

        //Here populate the list view with data;
        titles =  new ArrayList<>();
        images= new ArrayList<>();

        titles.add("Cross Image");
        titles.add("Brunei");
        titles.add("Chile");
        titles.add("Fiji");


        images.add(R.drawable.cross_image);
        images.add(R.drawable.flag_brunei);
        images.add(R.drawable.flag_chile);
        images.add(R.drawable.flag_fiji);

        adapter = new Adapter(this, titles, images);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        itemsPresentInFirebase.setLayoutManager(gridLayoutManager);
        itemsPresentInFirebase.setAdapter(adapter);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.nav_userInfo:
                Intent intent = new Intent(getApplicationContext(), userInfo.class);
                startActivity(intent);
                 break;
            case  R.id.nav_sell:
                Intent intent1 = new Intent(getApplicationContext(), SellScreen.class);
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
        }

        return true;
    }

    public void onBackPressed()
    {
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
    }
}