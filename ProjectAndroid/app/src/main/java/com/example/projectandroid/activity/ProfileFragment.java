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
import com.example.projectandroid.dal.StudentDAO;
import com.example.projectandroid.database.MyDatabase;
import com.example.projectandroid.domain.Account;
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
        final String username = sharedPreferences.getString("username","");
        myDatabase = Room.databaseBuilder(getContext(), MyDatabase.class, "projectchucnc.db").allowMainThreadQueries().build();
        StudentDAO studentDAO=myDatabase.createStudentDAO();
        Student student = studentDAO.getStudentByUser(username);
        if(student!=null && student.getStuID()!=null) {
            lblId.setText(student.getStuID());
            lblDob.setText(student.getDob());
            lblGender.setText(student.getGender());
            lblRoomName.setText(student.getRoomName());
            lblBedNo.setText(student.getBedNo());
            lblBookingDate.setText(student.getBookingDate());
            lblMoneyInAccount.setText(student.getMoneyAccount());
            lblDebt.setText(student.getDebt());
        }
    }
}