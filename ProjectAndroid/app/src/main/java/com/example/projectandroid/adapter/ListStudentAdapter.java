package com.example.projectandroid.adapter;

import android.content.Context;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.projectandroid.R;
import com.example.projectandroid.dialog.StudentInfoManageDialog;
import com.example.projectandroid.domain.Student;

import java.util.List;

public class ListStudentAdapter extends ArrayAdapter<Student> {

    private Context mContext;
    int mResource;

    public ListStudentAdapter(@NonNull Context context, int resource, List<Student> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
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

        textViewStudentName.setText(studentName);
        textViewStudentId.setText(studentId);
        textViewStudentUsername.setText(studentUsername);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "abc", Toast.LENGTH_SHORT).show();
                // TODO: Show dialog
                StudentInfoManageDialog studentInfoManageDialog = new StudentInfoManageDialog(mContext, getItem(position));
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
