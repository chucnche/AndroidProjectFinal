package com.example.projectandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

import com.example.projectandroid.fragment.ManageStudentFragment;

public class ManageStudentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_student);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        ManageStudentFragment manageStudentFragment = new ManageStudentFragment();
        fragmentTransaction.add(R.id.frameLayout, manageStudentFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater maMenuInflater = getMenuInflater();
        maMenuInflater.inflate(R.menu.drawer_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

}