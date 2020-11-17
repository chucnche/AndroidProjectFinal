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
import com.example.projectandroid.dal.ElectricityWaterBillsDAO;
import com.example.projectandroid.dal.HistoryPaymentDAO;
import com.example.projectandroid.dal.StudentDAO;
import com.example.projectandroid.database.MyDatabase;
import com.example.projectandroid.domain.ElectricityWaterBills;
import com.example.projectandroid.domain.HistoryPayment;
import com.example.projectandroid.domain.Student;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HistoryPaymentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HistoryPaymentFragment extends Fragment {
    private MyDatabase myDatabase;
    private TableLayout table;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HistoryPaymentFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HistoryPaymentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HistoryPaymentFragment newInstance(String param1, String param2) {
        HistoryPaymentFragment fragment = new HistoryPaymentFragment();
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
        return inflater.inflate(R.layout.fragment_history_payment, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        myDatabase = Room.databaseBuilder(getContext(), MyDatabase.class, "db1.db").allowMainThreadQueries().build();
        HistoryPaymentDAO historyPaymentDAO = myDatabase.createHistoryPaymentDAO();
//        historyPaymentDAO.insert(new HistoryPayment(1,"HE130001","12/01/2020","Tiền Phòng",123,"A114"));
//        historyPaymentDAO.insert(new HistoryPayment(2,"HE130001","12/01/2020","Tiền điện nước",423,"A114"));
        SharedPreferences sharedPreferences =this.getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        final String username = sharedPreferences.getString("username","student1");
//        HistoryPaymentDAO historyPaymentDAO = myDatabase.createHistoryPaymentDAO();
//        historyPaymentDAO.insert(new HistoryPayment(1,"HE130001","12/01/2020","Tiền Phòng",123,"A114"));
//        historyPaymentDAO.insert(new HistoryPayment(2,"HE130001","12/01/2020","Tiền điện nước",423,"A114"));
        StudentDAO studentDAO=myDatabase.createStudentDAO();
        Student student = studentDAO.getStudentByUser(username);
        table = (TableLayout) view.findViewById(R.id.tblPayment);
        if(student!=null && student.getStuID()!=null) {
            List<HistoryPayment> listHistoryPayments = historyPaymentDAO.getHistoryPaymentByStudentId(student.getStuID());
            for (int i = 0;i<listHistoryPayments.size();i++){
                HistoryPayment historyPayment = listHistoryPayments.get(i);
                TableRow tableRow = new TableRow(table.getContext());

                tableRow.setGravity(Gravity.CENTER_HORIZONTAL);
                Button txtRoomName = new Button(table.getContext());
                Button txtDate = new Button(table.getContext());
                Button txtType = new Button(table.getContext());
                Button txtMoney = new Button(table.getContext());
                Button txtStuId = new Button(table.getContext());
                txtRoomName.setText(historyPayment.getRoomName());
                txtDate.setText(historyPayment.getDatePay());
                txtType.setText(historyPayment.getType());
                txtMoney.setText(String.valueOf(historyPayment.getMoneyPay()));
                txtStuId.setText(student.getName());
                txtRoomName.setEnabled(false);txtDate.setEnabled(false);txtType.setEnabled(false);txtMoney.setEnabled(false);txtStuId.setEnabled(false);
                tableRow.addView(txtStuId);
                tableRow.addView(txtRoomName);
                tableRow.addView(txtDate);
                tableRow.addView(txtMoney);
                tableRow.addView(txtType);

                table.addView(tableRow);
            }

        }
    }
}