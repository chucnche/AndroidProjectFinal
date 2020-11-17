package com.example.projectandroid.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.projectandroid.R;
import com.example.projectandroid.dal.BedDAO;
import com.example.projectandroid.dal.HistoryPaymentDAO;
import com.example.projectandroid.dal.RoomDAO;
import com.example.projectandroid.dal.StudentDAO;
import com.example.projectandroid.database.MyDatabase;
import com.example.projectandroid.domain.Bed;
import com.example.projectandroid.domain.HistoryPayment;
import com.example.projectandroid.domain.Student;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RoomInformationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RoomInformationFragment extends Fragment {
    private MyDatabase myDatabase;
    private TableLayout table;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RoomInformationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RoomInformationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RoomInformationFragment newInstance(String param1, String param2) {
        RoomInformationFragment fragment = new RoomInformationFragment();
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
        return inflater.inflate(R.layout.fragment_room_information, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        myDatabase = Room.databaseBuilder(getContext(), MyDatabase.class, "projectchucnc.db").allowMainThreadQueries().build();
        BedDAO bedDAO = myDatabase.createBedDAO();
//        bedDAO.insert(new Bed(9, "A114", 1, 0));
//        bedDAO.insert(new Bed(10, "A114", 2, 0));
//        bedDAO.insert(new Bed(11, "A114", 3, 0));
//        bedDAO.insert(new Bed(12, "A114", 4, 0));
//        bedDAO.insert(new Bed(13, "A114", 5, 0));
//        bedDAO.insert(new Bed(14, "A114", 6, 0));
        SharedPreferences sharedPreferences =this.getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        final String username = sharedPreferences.getString("username","student1");
        StudentDAO studentDAO=myDatabase.createStudentDAO();
        Student student = studentDAO.getStudentByUser(username);
        table = (TableLayout) view.findViewById(R.id.tblRoomInfo);
        if(student!=null && student.getStuID()!=null) {
            RoomDAO roomDAO = myDatabase.createRoomDAO();
            List<com.example.projectandroid.domain.Room> listroom = roomDAO.listRoom();
            for (int i = 0;i<listroom.size();i++){
                com.example.projectandroid.domain.Room room = listroom.get(i);
                TableRow tableRow = new TableRow(table.getContext());
                Button txtRoomName = new Button(table.getContext());
                Button txtBedEmpty = new Button(table.getContext());
                Button txtBedExist = new Button(table.getContext());
                String empty = "";
                List<Bed> listBedEmpty = bedDAO.getBedStatus(room.getRoomName(),0);
                for (int j = 0;j<listBedEmpty.size();j++){
                    empty = empty + listBedEmpty.get(j).getBedNo() +" ";
                }
                List<Bed> listBedExist = bedDAO.getBedStatus(room.getRoomName(),1);
                String exist = "";
                for (int j = 0;j<listBedExist.size();j++){
                    exist = exist + listBedExist.get(j).getBedNo() +" ";
                }
                txtRoomName.setText(room.getRoomName());
                txtBedEmpty.setText(empty);
                txtBedExist.setText(exist);
                txtRoomName.setEnabled(false);
                txtRoomName.setTextColor(Color.BLACK);
                txtBedEmpty.setEnabled(false);
                txtBedEmpty.setTextColor(Color.BLACK);
                txtBedExist.setEnabled(false);
                txtBedExist.setTextColor(Color.BLACK);
                tableRow.addView(txtRoomName);
                tableRow.addView(txtBedEmpty);
                tableRow.addView(txtBedExist);
                table.addView(tableRow);
            }

        }
    }
}