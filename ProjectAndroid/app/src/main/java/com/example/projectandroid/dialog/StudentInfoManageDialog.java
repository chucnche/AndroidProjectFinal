package com.example.projectandroid.dialog;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.room.Room;

import com.example.projectandroid.R;
import com.example.projectandroid.dal.AccountDAO;
import com.example.projectandroid.dal.StudentDAO;
import com.example.projectandroid.database.MyDatabase;
import com.example.projectandroid.domain.Account;
import com.example.projectandroid.domain.Student;

import java.util.Calendar;

public class StudentInfoManageDialog extends Dialog implements View.OnClickListener {

    Context context;
    Student student;
    boolean isAdd;

    public StudentInfoManageDialog(Context context, Student student, boolean isAdd) {
        super(context);
        this.context = context;
        this.student = student;
        this.isAdd = isAdd;
    }

    EditText txtNameUser;
    EditText lblStudentID;
    EditText lblDOB;
    Spinner lblGender;
    EditText lblRoomName;
    EditText lblBedNo;
    EditText lblBookingDate;
    EditText lblMoneyInAccount;
    EditText lblDebt;

    Button btnUpdateStudentInfo;
    Button btnSubmitStudentInfo;

    boolean isEditing = false;

    MyDatabase myDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_student_info_manage);

        myDatabase = Room.databaseBuilder(((ContextWrapper) context).getBaseContext(), MyDatabase.class, "projectchucnc.db").allowMainThreadQueries().build();

        getViews();

        lblDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(lblDOB);
            }
        });

        lblBookingDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(lblBookingDate);
            }
        });

        if (!isAdd) {
            if (isEditing) {
                // Disable button submit
                btnSubmitStudentInfo.setEnabled(false);
                btnSubmitStudentInfo.setTextColor(Color.parseColor("#8C8A8A"));
            }

            // Update views data
            updateViewByStudentInfo();
        } else {
            // Enable editing
            setFocusingView(true);

            // Hide the update button
            btnUpdateStudentInfo.setVisibility(View.GONE);

            // Enable button submit
            btnSubmitStudentInfo.setEnabled(true);
            btnSubmitStudentInfo.setTextColor(Color.BLACK);
        }

        btnUpdateStudentInfo.setOnClickListener(this);
        btnSubmitStudentInfo.setOnClickListener(this);

    }

    public void getViews() {
        txtNameUser = findViewById(R.id.txtNameUser);
        lblStudentID = findViewById(R.id.lblStudentID);
        lblDOB = findViewById(R.id.lblDOB);
        lblGender = findViewById(R.id.lblGender);
        lblRoomName = findViewById(R.id.lblRoomName);
        lblBedNo = findViewById(R.id.lblBedNo);
        lblBookingDate = findViewById(R.id.lblBookingDate);
        lblMoneyInAccount = findViewById(R.id.lblMoneyInAccount);
        lblDebt = findViewById(R.id.lblDebt);
        btnUpdateStudentInfo = findViewById(R.id.btnUpdateStudentInfo);
        btnSubmitStudentInfo = findViewById(R.id.btnSubmitStudentInfo);
    }

    public void updateViewByStudentInfo() {
        txtNameUser.setText(student.getName());
        lblStudentID.setText(student.getStuID());
        lblDOB.setText(student.getDob());
        lblGender.setSelection(student.getGender());
        lblRoomName.setText(student.getRoomName());
        lblBedNo.setText(student.getBedNo() + "");
        lblBookingDate.setText(student.getBookingDate());
        lblMoneyInAccount.setText(student.getMoneyAccount() + "");
        lblDebt.setText(student.getDebt() + "");
    }

    public void setFocusingView(boolean state) {
        txtNameUser.setFocusableInTouchMode(state);
        txtNameUser.setFocusable(state);
        lblStudentID.setFocusableInTouchMode(state);
        lblStudentID.setFocusable(state);
        lblRoomName.setFocusableInTouchMode(state);
        lblRoomName.setFocusable(state);
        lblBedNo.setFocusableInTouchMode(state);
        lblBedNo.setFocusable(state);
        lblMoneyInAccount.setFocusableInTouchMode(state);
        lblMoneyInAccount.setFocusable(state);
        lblDebt.setFocusableInTouchMode(state);
        lblDebt.setFocusable(state);
    }

    private void showDatePickerDialog(final EditText editText) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                editText.setText(dayOfMonth + "/" + ((month + 1) < 10 ? "0" + (month + 1) : (month + 1)) + "/" + year);
            }
        }, year, month, day);

        dialog.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnUpdateStudentInfo: {
                if (isEditing) {
                    btnSubmitStudentInfo.setEnabled(false);
                    btnSubmitStudentInfo.setTextColor(Color.parseColor("#8C8A8A"));
                    setFocusingView(false);
                    btnUpdateStudentInfo.setText("Update");
                    isEditing = false;
                } else {
                    btnSubmitStudentInfo.setEnabled(true);
                    btnSubmitStudentInfo.setTextColor(Color.BLACK);
                    setFocusingView(true);
                    btnUpdateStudentInfo.setText("Cancel");
                    isEditing = true;
                }
                break;
            }
            case R.id.btnSubmitStudentInfo: {
                StudentDAO studentDAO = myDatabase.createStudentDAO();

                String stuName = txtNameUser.getText().toString();
                String stuId = lblStudentID.getText().toString();
                String stuDob = lblDOB.getText().toString();
                int stuGender = lblGender.getSelectedItemPosition();
                String stuRoomName = lblRoomName.getText().toString();
                int stuBedNo = Integer.parseInt(lblBedNo.getText().toString());
                String stuBookingDate = lblBookingDate.getText().toString();
                int stuMoneyInAccount = Integer.parseInt(lblMoneyInAccount.getText().toString());
                int stuDebt = Integer.parseInt(lblDebt.getText().toString());

                if (isAdd) {
                    AccountDAO accountDAO = myDatabase.createAccountDAO();
                    Account a = new Account(stuName.replaceAll(" ", "").trim(), "123", 2);
                    accountDAO.insert(a);

                    Student s = new Student(stuId, a.getUsername(), stuName, stuDob, stuGender, stuRoomName, stuBedNo, stuBookingDate, stuMoneyInAccount, stuDebt);
                    studentDAO.insert(s);

                    Toast.makeText(context, "Student added successfully!", Toast.LENGTH_SHORT).show();
                } else {
                    student.setStuID(stuId);
                    student.setName(stuName);
                    student.setDob(stuDob);
                    student.setGender(stuGender);
                    student.setRoomName(stuRoomName);
                    student.setBedNo(stuBedNo);
                    student.setBookingDate(stuBookingDate);
                    student.setMoneyAccount(stuMoneyInAccount);
                    student.setDebt(stuDebt);
                    studentDAO.update(student);
                    Toast.makeText(context, "Student updated!", Toast.LENGTH_SHORT).show();
                }
                cancel();
                break;
            }
        }

    }
}