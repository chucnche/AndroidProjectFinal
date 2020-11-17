package com.example.projectandroid.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import androidx.room.Room;

import com.example.projectandroid.R;
import com.example.projectandroid.dal.AccountDAO;
import com.example.projectandroid.database.MyDatabase;
import com.example.projectandroid.domain.Account;
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
        SharedPreferences sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
        String user = sharedPreferences.getString("user","");
        myDatabase = Room.databaseBuilder(getBaseContext(), MyDatabase.class, "db1.db").allowMainThreadQueries().build();
        final AccountDAO accountDAO=myDatabase.createAccountDAO();
        Account account=accountDAO.getAccountByUser(user);
        if(account.getType()==2) {
            ProfileFragment profileFragment = new ProfileFragment();
            loadFragment(profileFragment);
            navigationView.inflateMenu(R.menu.drawer_menu);
        }
        if(account.getType()==1){
            ManageBookRequestFragment manageBookRequestFragment = new ManageBookRequestFragment();
            loadFragment(manageBookRequestFragment);
            navigationView.inflateMenu(R.menu.draw_menu_manager);
        }
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                Fragment fragment = null;
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
                        ElectricWaterBillFragment electricWaterBillFragment = new ElectricWaterBillFragment();
                        loadFragment(electricWaterBillFragment);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.nav_payment_history:
                        HistoryPaymentFragment historyPaymentFragment = new HistoryPaymentFragment();
                        loadFragment(historyPaymentFragment);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.nav_student:
                        ManageStudentFragment manageStudentFragment = new ManageStudentFragment();
                        loadFragment(manageStudentFragment);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.nav_book_request:
                        ManageBookRequestFragment manageBookRequestFragment = new ManageBookRequestFragment();
                        loadFragment(manageBookRequestFragment);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.nav_logout_manager:
                        finish();
                        break;
                    case R.id.nav_logout_student:
                        Intent intent = new Intent(MainActivity.this,LoginFormActivity.class);
                        startActivity(intent);
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


}