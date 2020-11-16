package com.example.projectandroid.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Room;

import com.example.projectandroid.R;
import com.example.projectandroid.activity.ManageStudentActivity;
import com.example.projectandroid.dal.AccountDAO;
import com.example.projectandroid.dal.StudentDAO;
import com.example.projectandroid.database.MyDatabase;
import com.example.projectandroid.dialog.StudentInfoManageDialog;
import com.example.projectandroid.domain.Account;
import com.example.projectandroid.domain.Student;

import java.util.List;

public class ListStudentAdapter extends ArrayAdapter<Student> {

    private Context mContext;
    int mResource;
    MyDatabase myDatabase;

    public ListStudentAdapter(@NonNull Context context, int resource, List<Student> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
        myDatabase = Room.databaseBuilder(((ContextWrapper) context).getBaseContext(), MyDatabase.class, "projectchucnc.db").allowMainThreadQueries().build();
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String studentName = getItem(position).getName();
        String studentId = getItem(position).getStuID();
        String studentUsername = getItem(position).getUsername();

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView textViewStudentName = convertView.findViewById(R.id.textViewStudentName);
        TextView textViewStudentId = convertView.findViewById(R.id.textViewStudentId);
        TextView textViewStudentUsername = convertView.findViewById(R.id.textViewStudentUsername);
        Button btnDeleteStudent = convertView.findViewById(R.id.btnDeleteStudent);

        btnDeleteStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AccountDAO accountDAO = myDatabase.createAccountDAO();
                StudentDAO studentDAO = myDatabase.createStudentDAO();

                Account a = accountDAO.getAccountByUser(getItem(position).getUsername());
                accountDAO.delete(a);

                studentDAO.delete(getItem(position));
                Toast.makeText(mContext, "Student has been deleted", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(mContext, ManageStudentActivity.class);
                ((Activity) mContext).finish();
                mContext.startActivity(intent);
            }
        });

        textViewStudentName.setText(studentName);
        textViewStudentId.setText(studentId);
        textViewStudentUsername.setText(studentUsername);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Show dialog
                StudentInfoManageDialog studentInfoManageDialog = new StudentInfoManageDialog(mContext, getItem(position), false);

                // Restart activity when dialog return dismiss event
                studentInfoManageDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        Intent intent = new Intent(mContext, ManageStudentActivity.class);
                        ((Activity) mContext).finish();
                        mContext.startActivity(intent);
                    }
                });

                studentInfoManageDialog.show();

                // Resize dialog
                Display display = ((WindowManager) mContext.getSystemService(mContext.WINDOW_SERVICE)).getDefaultDisplay();
                int width = display.getWidth();
                int height = display.getHeight();
                Log.v("width", width + "");

                studentInfoManageDialog.getWindow().setLayout(width, (5 * height) / 6);

            }
        });

        return convertView;
    }
}
