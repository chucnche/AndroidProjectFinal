package com.example.projectandroid.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

import com.example.projectandroid.fragment.ManageStudentFragment;
import com.example.projectandroid.R;
import com.example.projectandroid.database.MyDatabase;

public class MainActivity extends AppCompatActivity {

    private MyDatabase myDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        txtUsername=findViewById(R.id.txtUsername);
//        txtPassword=findViewById(R.id.txtPassword);
//        checkboxRemember=findViewById(R.id.checkBoxRemember);
//        btnLogin=findViewById(R.id.btnLogin);
//        SharedPreferences sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
//        final String username = sharedPreferences.getString("username","");
//        final String password = sharedPreferences.getString("password","");
//        txtUsername.setText(username);
//        txtPassword.setText(password);
//        btnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String user=txtUsername.getText().toString();
//                String pass=txtPassword.getText().toString();
//                myDatabase = Room.databaseBuilder(getBaseContext(), MyDatabase.class, "projectchucnc.db").allowMainThreadQueries().build();
//                AccountDAO accountDAO=myDatabase.createAccountDAO();
//                Account account = accountDAO.getAccount(user, pass);
//                if(account!=null && account.getUsername()!=null){
//                    Intent intent = new Intent(Login.this,MainActivity.class);
//                    startActivity(intent);
//                }else{
//                    Toast.makeText(getBaseContext(),"Wrong username password",Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

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