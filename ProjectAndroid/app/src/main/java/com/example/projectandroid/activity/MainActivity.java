package com.example.projectandroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.projectandroid.R;
import com.example.projectandroid.database.MyDatabase;
import com.example.projectandroid.fragment.ManageBookRequestFragment;
import com.example.projectandroid.fragment.ManageStudentFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private MyDatabase myDatabase;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    Toolbar toolbar;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer);
        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView = findViewById(R.id.nav_view);
        navigationView.inflateMenu(R.menu.drawer_menu);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                Fragment fragment = null;
                Intent intent = new Intent(MainActivity.this, ProfileStudentActivity.class);
                switch (id) {
                    case R.id.nav_profile:
                        ProfileFragment profileFragment = new ProfileFragment();
                        loadFragment(profileFragment);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.nav_book_room:
                        BookRoomFragment bookRoomFragment = new BookRoomFragment();
                        loadFragment(bookRoomFragment);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.nav_request:
                        RequestFragment requestFragment = new RequestFragment();
                        loadFragment(requestFragment);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.nav_electricity_water_bills:
                        loadFragment3();
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.nav_payment_history:
                        loadFragment4();
                        drawerLayout.closeDrawers();
                        break;
                    default:
                        return true;
                }
                return true;
            }
        });
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.frame, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


    private void loadFragment3() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        ManageStudentFragment manageStudentFragment = new ManageStudentFragment();
        fragmentTransaction.replace(R.id.frame, manageStudentFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void loadFragment4() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        ManageBookRequestFragment manageBookRequestFragment = new ManageBookRequestFragment();
        fragmentTransaction.replace(R.id.frame, manageBookRequestFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}