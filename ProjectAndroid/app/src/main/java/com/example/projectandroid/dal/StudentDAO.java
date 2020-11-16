package com.example.projectandroid.dal;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.projectandroid.domain.Account;
import com.example.projectandroid.domain.Student;

import java.util.List;

@Dao
public interface StudentDAO {
    @Transaction
    @Insert
    public long insert(Student student);

    @Update
    public void update(Student student);

    @Delete
    void delete(Student student);

    @Query("SELECT * FROM student")
    public List<Student> listStudent();

    @Query("SELECT * FROM student where username =:username")
    public Student getStudentByUser(String username);

    @Query("SELECT * FROM student where stuID LIKE :stuId")
    public List<Student> getStudentLikeId(String stuId);
}
