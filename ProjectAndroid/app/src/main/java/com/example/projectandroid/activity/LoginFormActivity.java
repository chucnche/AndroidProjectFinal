package com.example.projectandroid.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projectandroid.R;
import com.example.projectandroid.dal.AccountDAO;
import com.example.projectandroid.dal.StudentDAO;
import com.example.projectandroid.database.MyDatabase;
import com.example.projectandroid.domain.Account;

public class LoginFormActivity extends AppCompatActivity {
    private EditText txtUsername;
    private EditText txtPassword;
    private Button btnLogin;
    private MyDatabase myDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_form);
        txtUsername=findViewById(R.id.txtUsername);
        txtPassword=findViewById(R.id.txtPassword);
        btnLogin=findViewById(R.id.btnLogin);
        myDatabase = Room.databaseBuilder(getBaseContext(), MyDatabase.class, "db1.db").allowMainThreadQueries().build();
        final AccountDAO accountDAO=myDatabase.createAccountDAO();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Account account=accountDAO.getAccount(txtUsername.getText().toString(),txtPassword.getText().toString());
                if(account!=null && account.getUsername()!=null){
                    SharedPreferences pref =getSharedPreferences("login", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor= pref.edit();
                    editor.putString("user",txtUsername.getText().toString());
                    editor.putString("pass",txtPassword.getText().toString());
                    editor.commit();
                    Intent intent = new Intent(LoginFormActivity.this,MainActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getBaseContext(), "Login Fail", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}