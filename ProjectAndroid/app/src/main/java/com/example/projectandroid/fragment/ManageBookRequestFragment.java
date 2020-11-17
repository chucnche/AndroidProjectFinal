package com.example.projectandroid.fragment;

import android.content.ContextWrapper;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import com.example.projectandroid.R;
import com.example.projectandroid.adapter.ListBookingRequestAdapter;
import com.example.projectandroid.dal.BedDAO;
import com.example.projectandroid.dal.HistoryBookDAO;
import com.example.projectandroid.dal.RoomDAO;
import com.example.projectandroid.database.MyDatabase;
import com.example.projectandroid.domain.Bed;
import com.example.projectandroid.domain.HistoryBook;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ManageBookRequestFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ManageBookRequestFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ManageBookRequestFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AdminApproveFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ManageBookRequestFragment newInstance(String param1, String param2) {
        ManageBookRequestFragment fragment = new ManageBookRequestFragment();
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
        return inflater.inflate(R.layout.fragment_manage_book_request, container, false);
    }

    MyDatabase myDatabase;
    HistoryBookDAO historyBookDAO;
    RoomDAO roomDAO;
    BedDAO bedDAO;

    Spinner spinnerRoom;
    Spinner spinnerBed;

    ListView listViewBookingRequestManage;

    Button btnSearchRequest;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        myDatabase = Room.databaseBuilder(((ContextWrapper) view.getContext()).getBaseContext(), MyDatabase.class, "projectchucnc.db").allowMainThreadQueries().build();
        historyBookDAO = myDatabase.createHistoryBookDAO();
        roomDAO = myDatabase.createRoomDAO();
        bedDAO = myDatabase.createBedDAO();

        spinnerRoom = view.findViewById(R.id.spinnerRoom);
        spinnerBed = view.findViewById(R.id.spinnerBed);
        listViewBookingRequestManage = view.findViewById(R.id.listViewBookingRequestManage);
        btnSearchRequest = view.findViewById(R.id.btnSearchRequest);

        btnSearchRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<HistoryBook> historyBooks = historyBookDAO.listPendingHistoryBookRoomNameBedNo(spinnerRoom.getSelectedItem().toString(), Integer.parseInt(spinnerBed.getSelectedItem().toString()));
                ListBookingRequestAdapter listBookingRequestAdapter = new ListBookingRequestAdapter(getContext(), R.layout.adapter_list_booking_request, historyBooks);
                listViewBookingRequestManage.setAdapter(listBookingRequestAdapter);
                setListViewHeightBasedOnChildren(listViewBookingRequestManage);
            }
        });

        ArrayAdapter<String> roomArrayAdapter;
        List<com.example.projectandroid.domain.Room> rooms = roomDAO.listRoom();

        String[] roomNames = new String[rooms.size()];

        for (int i = 0; i < roomNames.length; i++) {
            roomNames[i] = rooms.get(i).getRoomName();
        }
        roomArrayAdapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_dropdown_item, roomNames);
        spinnerRoom.setAdapter(roomArrayAdapter);
        spinnerRoom.setSelection(0);
        spinnerRoom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getDataBedByRoom();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        getDataBedByRoom();

        List<HistoryBook> historyBooks = historyBookDAO.listPendingHistoryBookRoomNameBedNo(spinnerRoom.getSelectedItem().toString(), Integer.parseInt(spinnerBed.getSelectedItem().toString()));
        ListBookingRequestAdapter listBookingRequestAdapter = new ListBookingRequestAdapter(getContext(), R.layout.adapter_list_booking_request, historyBooks);
        listViewBookingRequestManage.setAdapter(listBookingRequestAdapter);
        setListViewHeightBasedOnChildren(listViewBookingRequestManage);

    }

    public void getDataBedByRoom() {
        ArrayAdapter<String> bedArrayAdapter;
        List<Bed> beds = bedDAO.listBedByRoomName(spinnerRoom.getSelectedItem().toString());

        String bedNos[] = new String[beds.size()];
        for (int i = 0; i < bedNos.length; i++) {
            bedNos[i] = beds.get(i).getBedNo() + "";
        }
        bedArrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, bedNos);
        spinnerBed.setAdapter(bedArrayAdapter);
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