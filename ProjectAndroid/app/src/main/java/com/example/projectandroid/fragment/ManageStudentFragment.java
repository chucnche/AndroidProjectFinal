package com.example.projectandroid.fragment;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.projectandroid.R;
import com.example.projectandroid.adapter.ListStudentAdapter;
import com.example.projectandroid.dal.StudentDAO;
import com.example.projectandroid.database.MyDatabase;
import com.example.projectandroid.dialog.StudentInfoManageDialog;
import com.example.projectandroid.domain.Student;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ManageStudentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ManageStudentFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ManageStudentFragment() {
        // Required empty public constructor
    }

    private MyDatabase myDatabase;
    ListView listViewStudentManage;
    List<Student> students;
    StudentDAO studentDAO;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ManageStudentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ManageStudentFragment newInstance(String param1, String param2) {
        ManageStudentFragment fragment = new ManageStudentFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_manage_student, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        myDatabase = Room.databaseBuilder(getActivity().getBaseContext(), MyDatabase.class, "projectchucnc.db").allowMainThreadQueries().build();
        studentDAO = myDatabase.createStudentDAO();

        final EditText editTextStudentId = getView().findViewById(R.id.editTextStudentId);
        listViewStudentManage = getView().findViewById(R.id.listViewBookingRequestManage);
        Button btnAddStudent = getView().findViewById(R.id.btnAddStudent);
        btnAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StudentInfoManageDialog studentInfoManageDialog = new StudentInfoManageDialog(getContext(), null, true);

                // Restart activity when dialog return dismiss event
                studentInfoManageDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        editTextStudentId.setText("");
                        students = studentDAO.listStudent();
                        listViewStudentManage.setAdapter(new ListStudentAdapter(getContext(), R.layout.adapter_list_student, students));
                        setListViewHeightBasedOnChildren(listViewStudentManage);
                    }
                });

                studentInfoManageDialog.show();

                // Resize dialog
                Display display = ((WindowManager) getContext().getSystemService(getContext().WINDOW_SERVICE)).getDefaultDisplay();
                int width = display.getWidth();
                int height = display.getHeight();
                Log.v("width", width + "");

                studentInfoManageDialog.getWindow().setLayout(width, (5 * height) / 6);
            }
        });

        Button btnSearchStudent = getView().findViewById(R.id.btnSearchStudent);
        btnSearchStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                students = studentDAO.getStudentLikeId("%" + editTextStudentId.getText().toString() + "%");
                listViewStudentManage.setAdapter(new ListStudentAdapter(getContext(), R.layout.adapter_list_student, students));
                setListViewHeightBasedOnChildren(listViewStudentManage);
            }
        });

        students = studentDAO.listStudent();
        listViewStudentManage.setAdapter(new ListStudentAdapter(getContext(), R.layout.adapter_list_student, students));

        setListViewHeightBasedOnChildren(listViewStudentManage);
    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }
}