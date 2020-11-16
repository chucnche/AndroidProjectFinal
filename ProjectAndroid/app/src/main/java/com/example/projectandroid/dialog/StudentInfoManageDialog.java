package com.example.projectandroid.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.projectandroid.R;
import com.example.projectandroid.domain.Student;

public class StudentInfoManageDialog extends Dialog implements View.OnClickListener {

    Context context;
    Student student;

    public StudentInfoManageDialog(Context context, Student student) {
        super(context);
        this.context = context;
        this.student = student;
    }

    EditText txtNameUser;
    EditText lblStudentID;
    EditText lblDOB;
    EditText lblGender;
    EditText lblRoomName;
    EditText lblBedNo;
    EditText lblBookingDate;
    EditText lblMoneyInAccount;
    EditText lblDebt;

    Button btnUpdateStudentInfo;
    Button btnSubmitStudentInfo;

    boolean isEditing = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_student_info_manage);

        getViews();

        if (isEditing) {
            btnSubmitStudentInfo.setEnabled(false);
            btnSubmitStudentInfo.setTextColor(Color.parseColor("#8C8A8A"));
        }

        updateViewByStudentInfo();

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
        lblGender.setText(student.getGender() == 0 ? "Female" : "Male");
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
        lblDOB.setFocusableInTouchMode(state);
        lblDOB.setFocusable(state);
        lblGender.setFocusableInTouchMode(state);
        lblGender.setFocusable(state);
        lblRoomName.setFocusableInTouchMode(state);
        lblRoomName.setFocusable(state);
        lblBookingDate.setFocusableInTouchMode(state);
        lblBookingDate.setFocusable(state);
        lblMoneyInAccount.setFocusableInTouchMode(state);
        lblMoneyInAccount.setFocusable(state);
        lblDebt.setFocusableInTouchMode(state);
        lblDebt.setFocusable(state);
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
                break;
            }
        }

    }
}