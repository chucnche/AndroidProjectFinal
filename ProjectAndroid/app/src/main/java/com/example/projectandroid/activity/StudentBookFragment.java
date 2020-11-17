package com.example.projectandroid.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.example.projectandroid.R;
import com.example.projectandroid.dal.BedDAO;
import com.example.projectandroid.dal.HistoryBookDAO;
import com.example.projectandroid.dal.RoomDAO;
import com.example.projectandroid.dal.StudentDAO;
import com.example.projectandroid.database.MyDatabase;
import com.example.projectandroid.domain.Bed;
import com.example.projectandroid.domain.HistoryBook;
import com.example.projectandroid.domain.Student;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StudentBookFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StudentBookFragment extends Fragment {

    private Spinner spinnerDom;
    private Spinner spinnerRoom;
    private Spinner spinnerBed;
    private Adapter adapterDom;
    private Adapter adapterRoom;
    private Adapter adapterBedNo;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private MyDatabase myDatabase;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Button btnBook;
    public StudentBookFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StudentBookFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StudentBookFragment newInstance(String param1, String param2) {
        StudentBookFragment fragment = new StudentBookFragment();
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
        return inflater.inflate(R.layout.fragment_student_book, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        spinnerDom=view.findViewById(R.id.spinnerDom);
        spinnerRoom=view.findViewById(R.id.spinnerRoom);
        spinnerBed=view.findViewById(R.id.spinnerBed);
        final List<String> listDom = new ArrayList<>();
        listDom.add("A");
        listDom.add("B");
        listDom.add("C");
        listDom.add("D");
        adapterDom = new ArrayAdapter<>(getContext(),android.R.layout.simple_list_item_1,listDom);
        spinnerDom.setAdapter((SpinnerAdapter) adapterDom);
        myDatabase = Room.databaseBuilder(getContext(), MyDatabase.class, "db1.db").allowMainThreadQueries().build();

        spinnerDom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                RoomDAO roomDAO = myDatabase.createRoomDAO();
                final List<com.example.projectandroid.domain.Room> listRoom=roomDAO.listRoomByDom(listDom.get(position));
                adapterRoom = new ArrayAdapter<>(getContext(),android.R.layout.simple_list_item_1,listRoom);
                spinnerRoom.setAdapter((SpinnerAdapter) adapterRoom);
                spinnerRoom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        BedDAO bedDAO = myDatabase.createBedDAO();
                        List<Bed> listBed=bedDAO.listBedByRoomName(listRoom.get(position).getRoomName());
                        adapterBedNo = new ArrayAdapter<>(getContext(),android.R.layout.simple_list_item_1,listBed);
                        spinnerBed.setAdapter((SpinnerAdapter) adapterBedNo);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btnBook=view.findViewById(R.id.btnBook);
        SharedPreferences sharedPreferences =this.getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        final String username = sharedPreferences.getString("username","student1");
        final StudentDAO studentDAO=myDatabase.createStudentDAO();
        final Student student = studentDAO.getStudentByUser(username);
        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(student.getMoneyAccount()>=0) {
                    student.setName(spinnerRoom.getSelectedItem().toString());
                    student.setBedNo(Integer.parseInt(spinnerBed.getSelectedItem().toString()));
//                    student.setMoneyAccount(student.getMoneyAccount()-0);
//                    studentDAO.update(student);
//                    BedDAO bedDAO = myDatabase.createBedDAO();
//                    Bed bed = (Bed) bedDAO.listBedByRoomNameBed(student.getRoomName(), student.getBedNo());
//                    bed.setBedStatus(1);
//                    bedDAO.update(bed);
                    HistoryBookDAO historyBookDAO=myDatabase.createHistoryBookDAO();
                    HistoryBook historyBook=new HistoryBook();
                    historyBook.setStuID(student.getStuID());
                    historyBook.setRoomName(student.getRoomName());
                    historyBook.setBedNo(student.getBedNo());
                    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
                    historyBook.setDateBook(simpleDateFormat.format(new Date()));
                    final java.util.Calendar cal = GregorianCalendar.getInstance();
                    cal.setTime(new Date());
                    cal.add( GregorianCalendar.MONTH, 4 ); // date manipulation
                    historyBook.setDateExpiry(cal.toString());
                    historyBook.setStatus(1);
                    historyBookDAO.insert(historyBook);
//                    Toast.makeText(getContext(), "Book Success", Toast.LENGTH_SHORT).show();
                    HistoryBookFragment addCatalog = new HistoryBookFragment();
                    FragmentManager manager = getFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    transaction.replace(R.id.frameBook, addCatalog);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }else{
                    Toast.makeText(getContext(), "Not enough money", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}