package com.example.projectandroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.projectandroid.R;
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
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
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

        return convertView;
    }
}
