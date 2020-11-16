package com.example.projectandroid.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.projectandroid.R;
import com.example.projectandroid.dal.AccountDAO;
import com.example.projectandroid.dal.AdminDAO;
import com.example.projectandroid.dal.BedDAO;
import com.example.projectandroid.dal.ManagerDAO;
import com.example.projectandroid.dal.RoomDAO;
import com.example.projectandroid.dal.StudentDAO;
import com.example.projectandroid.database.MyDatabase;
import com.example.projectandroid.domain.Account;
import com.example.projectandroid.domain.Admin;
import com.example.projectandroid.domain.Bed;
import com.example.projectandroid.domain.Manager;
import com.example.projectandroid.domain.Student;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    private TextView lblId;
    private TextView lblDob;
    private TextView lblGender;
    private TextView lblRoomName;
    private TextView lblBedNo;
    private TextView lblBookingDate;
    private TextView lblMoneyInAccount;
    private TextView lblDebt;
    private MyDatabase myDatabase;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lblId=view.findViewById(R.id.lblStudentID);
        lblDob=view.findViewById(R.id.lblDOB);
        lblGender=view.findViewById(R.id.lblGender);
        lblRoomName=view.findViewById(R.id.lblRoomName);
        lblBedNo=view.findViewById(R.id.lblBedNo);
        lblBookingDate=view.findViewById(R.id.lblBookingDate);
        lblMoneyInAccount=view.findViewById(R.id.lblMoneyInAccount);
        lblDebt=view.findViewById(R.id.lblDebt);
        SharedPreferences sharedPreferences =this.getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        final String username = sharedPreferences.getString("username","student1");
        myDatabase = Room.databaseBuilder(getContext(), MyDatabase.class, "db1.db").allowMainThreadQueries().build();
        StudentDAO studentDAO=myDatabase.createStudentDAO();
        Student student = studentDAO.getStudentByUser(username);
        if(student!=null && student.getStuID()!=null) {
            lblId.setText(student.getStuID());
            lblDob.setText(student.getDob());
            lblGender.setText(String.valueOf(student.getGender()));
            lblRoomName.setText(student.getRoomName());
            lblBedNo.setText(String.valueOf(student.getBedNo()));
            lblBookingDate.setText(student.getBookingDate());
            lblMoneyInAccount.setText(String.valueOf(student.getMoneyAccount()));
            lblDebt.setText(String.valueOf(student.getDebt()));
        }
//        RoomDAO roomDAO = myDatabase.createRoomDAO();
//        roomDAO.insert(new com.example.projectandroid.domain.Room("A114", 1, 250));
//        roomDAO.insert(new com.example.projectandroid.domain.Room("A205", 0, 250));
//
//        BedDAO bedDAO = myDatabase.createBedDAO();
//        bedDAO.insert(new Bed(9, "A114", 1, 0));
//        bedDAO.insert(new Bed(10, "A114", 2, 0));
//        bedDAO.insert(new Bed(11, "A114", 3, 0));
//        bedDAO.insert(new Bed(12, "A114", 4, 0));
//        bedDAO.insert(new Bed(13, "A205", 1, 0));
//        bedDAO.insert(new Bed(14, "A205", 2, 0));
//        bedDAO.insert(new Bed(15, "A205", 3, 0));
//        bedDAO.insert(new Bed(16, "A205", 7, 0));
//
//        AccountDAO accountDAO = myDatabase.createAccountDAO();
//        accountDAO.insert(new Account("admin","123",0));
//        accountDAO.insert(new Account("manager","123",1));
//        accountDAO.insert(new Account("student1","123",2));
//        accountDAO.insert(new Account("student2","123",2));
//        accountDAO.insert(new Account("student3","123",2));
//        accountDAO.insert(new Account("student4","123",2));
//        accountDAO.insert(new Account("student5","123",2));
//        accountDAO.insert(new Account("student6","123",2));
//        accountDAO.insert(new Account("student7","123",2));
//        accountDAO.insert(new Account("student8","123",2));
//
//        AdminDAO adminDAO = myDatabase.createAdminDAO();
//        adminDAO.insert(new Admin(1, "admin"));
//
//        ManagerDAO managerDAO = myDatabase.createManagerDAO();
//        managerDAO.insert(new Manager("ma1", "manager", "15/08/1994", 1, "ma1@gmail.com", "0123456789", "Ma"));
//
////        StudentDAO studentDAO = myDatabase.createStudentDAO();
//        studentDAO.insert(new Student("HE130001", "student1", "Student One", "12/09/1998", 1, "A114", 7, "18/08/2016", 0, 0));
//        studentDAO.insert(new Student("HE130002", "student2", "Student Two", "12/01/1998", 1, "A114", 8, "18/08/2016", 0, 0));
//        studentDAO.insert(new Student("HE130003", "student3", "Student Three", "16/03/1998", 1, "A114", 9, "18/08/2016", 0, 0));
//        studentDAO.insert(new Student("HE130004", "student4", "Student Four", "21/12/1998", 1, "A114", 10, "18/08/2016", 0, 0));
//        studentDAO.insert(new Student("HE130005", "student5", "Student Five", "19/08/1998", 0, "A205", 3, "18/08/2016", 0, 0));
//        studentDAO.insert(new Student("HE130006", "student6", "Student Six", "05/09/1998", 0, "A205", 4, "18/08/2016", 0, 0));
//        studentDAO.insert(new Student("HE130007", "student7", "Student Seven", "03/03/1998", 0, "A205", 5, "18/08/2016", 0, 0));
//        studentDAO.insert(new Student("HE130008", "student8", "Student Eight", "01/02/1998", 0, "A205", 6, "18/08/2016", 0, 0));


    }
}