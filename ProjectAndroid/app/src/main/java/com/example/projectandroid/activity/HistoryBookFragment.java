package com.example.projectandroid.activity;

import android.content.Context;
import android.content.SharedPreferences;
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
import com.example.projectandroid.dal.HistoryBookDAO;
import com.example.projectandroid.dal.StudentDAO;
import com.example.projectandroid.database.MyDatabase;
import com.example.projectandroid.domain.ElectricityWaterBills;
import com.example.projectandroid.domain.HistoryBook;
import com.example.projectandroid.domain.Student;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HistoryBookFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HistoryBookFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private MyDatabase myDatabase;
    private TableLayout tableLayout;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HistoryBookFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HistoryBookFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HistoryBookFragment newInstance(String param1, String param2) {
        HistoryBookFragment fragment = new HistoryBookFragment();
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
        return inflater.inflate(R.layout.fragment_history_book, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SharedPreferences sharedPreferences =this.getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        final String username = sharedPreferences.getString("username","student1");
        myDatabase = Room.databaseBuilder(getContext(), MyDatabase.class, "db1.db").allowMainThreadQueries().build();

        StudentDAO studentDAO=myDatabase.createStudentDAO();
        Student student = studentDAO.getStudentByUser(username);
        HistoryBookDAO historyBookDAO=myDatabase.createHistoryBookDAO();
        myDatabase = Room.databaseBuilder(getContext(), MyDatabase.class, "db1.db").allowMainThreadQueries().build();
        tableLayout = (TableLayout) view.findViewById(R.id.tableLayout);
        if(student!=null && student.getStuID()!=null) {
            List<HistoryBook> historyBooks = historyBookDAO.listHistoryBookByUsername(student.getStuID());
            for (int i = 0;i<historyBooks.size();i++){
                HistoryBook historyBook = historyBooks.get(i);
                TableRow tableRow = new TableRow(tableLayout.getContext());

//                tableRow.setGravity(Gravity.CENTER_HORIZONTAL);
                Button txtId = new Button(tableLayout.getContext());
                Button txtRoomName = new Button(tableLayout.getContext());
                Button txtBedNo = new Button(tableLayout.getContext());
                Button txtDateBook = new Button(tableLayout.getContext());
                Button txtStatus = new Button(tableLayout.getContext());
                txtId.setText(String.valueOf(historyBook.getStuID()));
                txtRoomName.setText(historyBook.getRoomName());
                txtBedNo.setText(String.valueOf(historyBook.getBedNo()));
                txtDateBook.setText(String.valueOf(historyBook.getDateBook()));
                txtStatus.setText(String.valueOf(historyBook.getStatus()));
                tableRow.addView(txtId);
                tableRow.addView(txtRoomName);
                tableRow.addView(txtBedNo);
                tableRow.addView(txtDateBook);
                tableRow.addView(txtStatus);
                tableLayout.addView(tableRow);
            }

        }
    }
}