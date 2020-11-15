package com.example.projectandroid.activity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.projectandroid.R;
import com.example.projectandroid.adapter.ListStudentAdapter;
import com.example.projectandroid.dal.AccountDAO;
import com.example.projectandroid.dal.AdminDAO;
import com.example.projectandroid.dal.BedDAO;
import com.example.projectandroid.dal.ManagerDAO;
import com.example.projectandroid.dal.RoomDAO;
import com.example.projectandroid.dal.StudentDAO;
import com.example.projectandroid.database.MyDatabase;
import com.example.projectandroid.domain.Account;
import com.example.projectandroid.domain.Admin;
import com.example.projectandroid.domain.Bed;
import com.example.projectandroid.domain.Manager;
import com.example.projectandroid.domain.Student;

import java.util.List;

public class ManageStudentActivity extends AppCompatActivity {

    private MyDatabase myDatabase;
    ListView listViewStudentManage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_student);

        myDatabase = Room.databaseBuilder(getBaseContext(), MyDatabase.class, "projectchucnc.db").allowMainThreadQueries().build();

        listViewStudentManage = findViewById(R.id.listViewStudentManage);

        RoomDAO roomDAO = myDatabase.createRoomDAO();
//        roomDAO.insert(new com.example.projectandroid.domain.Room("A114", 1, 250));
//        roomDAO.insert(new com.example.projectandroid.domain.Room("A205", 0, 250));

        BedDAO bedDAO = myDatabase.createBedDAO();
//        bedDAO.insert(new Bed(1, "A114", 7, 1));
//        bedDAO.insert(new Bed(2, "A114", 8, 1));
//        bedDAO.insert(new Bed(3, "A114", 9, 1));
//        bedDAO.insert(new Bed(4, "A114", 10, 1));
//        bedDAO.insert(new Bed(5, "A205", 3, 1));
//        bedDAO.insert(new Bed(6, "A205", 4, 1));
//        bedDAO.insert(new Bed(7, "A205", 5, 1));
//        bedDAO.insert(new Bed(8, "A205", 6, 1));

        AccountDAO accountDAO = myDatabase.createAccountDAO();
//        accountDAO.insert(new Account("admin","123",0));
//        accountDAO.insert(new Account("manager","123",1));
//        accountDAO.insert(new Account("student1","123",2));
//        accountDAO.insert(new Account("student2","123",2));
//        accountDAO.insert(new Account("student3","123",2));
//        accountDAO.insert(new Account("student4","123",2));
//        accountDAO.insert(new Account("student5","123",2));
//        accountDAO.insert(new Account("student6","123",2));
//        accountDAO.insert(new Account("student7","123",2));
//        accountDAO.insert(new Account("student8","123",2));

        AdminDAO adminDAO = myDatabase.createAdminDAO();
//        adminDAO.insert(new Admin(1, "admin"));

        ManagerDAO managerDAO = myDatabase.createManagerDAO();
//        managerDAO.insert(new Manager("ma1", "manager", "15/08/1994", 1, "ma1@gmail.com", "0123456789", "Ma"));

        StudentDAO studentDAO = myDatabase.createStudentDAO();
//        studentDAO.insert(new Student("HE130001", "student1", "Student One", "12/09/1998", 1, "A114", 7, "18/08/2016", 0, 0));
//        studentDAO.insert(new Student("HE130002", "student2", "Student Two", "12/01/1998", 1, "A114", 8, "18/08/2016", 0, 0));
//        studentDAO.insert(new Student("HE130003", "student3", "Student Three", "16/03/1998", 1, "A114", 9, "18/08/2016", 0, 0));
//        studentDAO.insert(new Student("HE130004", "student4", "Student Four", "21/12/1998", 1, "A114", 10, "18/08/2016", 0, 0));
//        studentDAO.insert(new Student("HE130005", "student5", "Student Five", "19/08/1998", 0, "A205", 3, "18/08/2016", 0, 0));
//        studentDAO.insert(new Student("HE130006", "student6", "Student Six", "05/09/1998", 0, "A205", 4, "18/08/2016", 0, 0));
//        studentDAO.insert(new Student("HE130007", "student7", "Student Seven", "03/03/1998", 0, "A205", 5, "18/08/2016", 0, 0));
//        studentDAO.insert(new Student("HE130008", "student8", "Student Eight", "01/02/1998", 0, "A205", 6, "18/08/2016", 0, 0));

        List<Student> students = studentDAO.listStudent();
        listViewStudentManage.setAdapter(new ListStudentAdapter(this, R.layout.list_student_adapter, students));

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