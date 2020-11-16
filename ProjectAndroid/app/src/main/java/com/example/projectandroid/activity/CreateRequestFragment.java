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
import android.widget.Button;
import android.widget.EditText;

import com.example.projectandroid.R;
import com.example.projectandroid.dal.RequestDAO;
import com.example.projectandroid.dal.StudentDAO;
import com.example.projectandroid.database.MyDatabase;
import com.example.projectandroid.domain.Request;
import com.example.projectandroid.domain.Student;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CreateRequestFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateRequestFragment extends Fragment {
    private EditText txtRequest;
    private Button btnReuqest;
    private MyDatabase myDatabase;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CreateRequestFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CreateRequestFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CreateRequestFragment newInstance(String param1, String param2) {
        CreateRequestFragment fragment = new CreateRequestFragment();
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
        return inflater.inflate(R.layout.fragment_create_request, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txtRequest=view.findViewById(R.id.txtRequestContent);
        btnReuqest=view.findViewById(R.id.btnRequest);
        myDatabase = Room.databaseBuilder(getContext(), MyDatabase.class, "db1.db").allowMainThreadQueries().build();
        SharedPreferences sharedPreferences =this.getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        final String username = sharedPreferences.getString("username","student1");
        final StudentDAO studentDAO=myDatabase.createStudentDAO();
        final Student student = studentDAO.getStudentByUser(username);
        btnReuqest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text =txtRequest.getText().toString();
                if(text!=null && !text.isEmpty()){
                    RequestDAO requestDAO = myDatabase.createRequestDAO();
                    Request request=new Request();
                    request.setStuID(student.getStuID());
                    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
                    request.setDateRequest(simpleDateFormat.format(new Date()));
                    request.setRequestContent(text);
                    requestDAO.insert(request);
                    HistoryRequestFragment addCatalog = new HistoryRequestFragment();
                    FragmentManager manager = getFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    transaction.replace(R.id.frameRquest, addCatalog);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
            }
        });
    }
}