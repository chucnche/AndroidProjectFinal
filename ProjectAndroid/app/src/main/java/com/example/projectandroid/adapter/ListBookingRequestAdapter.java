package com.example.projectandroid.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.room.Room;

import com.example.projectandroid.R;
import com.example.projectandroid.dal.BedDAO;
import com.example.projectandroid.dal.HistoryBookDAO;
import com.example.projectandroid.dal.StudentDAO;
import com.example.projectandroid.database.MyDatabase;
import com.example.projectandroid.domain.Bed;
import com.example.projectandroid.domain.HistoryBook;
import com.example.projectandroid.domain.Student;

import java.util.List;

public class ListBookingRequestAdapter extends ArrayAdapter<HistoryBook> {
    private Context mContext;
    int mResource;
    MyDatabase myDatabase;
    HistoryBookDAO historyBookDAO;

    public ListBookingRequestAdapter(@NonNull Context context, int resource, List<HistoryBook> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
        myDatabase = Room.databaseBuilder(((ContextWrapper) context).getBaseContext(), MyDatabase.class, "projectchucnc.db").allowMainThreadQueries().build();
        historyBookDAO = myDatabase.createHistoryBookDAO();
    }

    boolean isApproved = false;
    HistoryBook historyBook;

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View v = convertView;

        //If convertView is null create a new view, else use convert view
        if (v == null) {
            v = ((LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.adapter_list_booking_request, null);
        }

        historyBook = getItem(position);

        final StudentDAO studentDAO = myDatabase.createStudentDAO();
        Student s = studentDAO.getStudentById(getItem(position).getStuID());

        String studentName = s.getName();
        String studentId = getItem(position).getStuID();
        String roomName = getItem(position).getRoomName();
        String bedNo = getItem(position).getBedNo() + "";

        TextView textViewStudentName = v.findViewById(R.id.textViewStudentName);
        TextView textViewStudentId = v.findViewById(R.id.textViewStudentId);
        TextView textViewRoom = v.findViewById(R.id.textViewRoom);
        TextView textViewBedNo = v.findViewById(R.id.textViewBedNo);

        textViewStudentName.setText(studentName);
        textViewStudentId.setText(studentId);
        textViewRoom.setText(roomName);
        textViewBedNo.setText(bedNo);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog();
            }
        });

        return v;
    }

    private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("Confirm approval");
        builder.setMessage("All other student will be reject");
        builder.setIcon(android.R.drawable.star_big_on);

        builder.setPositiveButton("Approve", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Approve one
                historyBook.setStatus(2);
                historyBookDAO.update(historyBook);

                BedDAO bedDAO = myDatabase.createBedDAO();
                Bed bed = bedDAO.listBedByRoomNameBed(historyBook.getRoomName(), historyBook.getBedNo());
                bed.setBedStatus(1);
                bedDAO.update(bed);

                StudentDAO studentDAO = myDatabase.createStudentDAO();
                Student student = studentDAO.getStudentById(historyBook.getStuID());
                student.setRoomName(historyBook.getRoomName());
                student.setBedNo(historyBook.getBedNo());
                studentDAO.update(student);

                Toast.makeText(mContext, "Student Approved", Toast.LENGTH_SHORT);

                // Reject all
                List<HistoryBook> historyBooks = historyBookDAO.listPendingHistoryBookRoomNameBedNo(historyBook.getRoomName(), historyBook.getBedNo());
                for (HistoryBook hb :
                        historyBooks) {
                    hb.setStatus(0);
                    historyBookDAO.update(hb);
                }
            }
        });

        builder.setNegativeButton("Reject", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Reject
                historyBook.setStatus(0);
            }
        });

        Dialog dialog = builder.create();
        dialog.show();
    }

}
