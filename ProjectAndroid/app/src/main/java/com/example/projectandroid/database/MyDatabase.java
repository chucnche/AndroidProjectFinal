package com.example.projectandroid.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.projectandroid.dal.AccountDAO;
import com.example.projectandroid.dal.AdminDAO;
import com.example.projectandroid.dal.AvailibleBookingTimeDAO;
import com.example.projectandroid.dal.BedDAO;
import com.example.projectandroid.dal.ElectricityWaterBillsDAO;
import com.example.projectandroid.dal.HistoryBookDAO;
import com.example.projectandroid.dal.HistoryPaymentDAO;
import com.example.projectandroid.dal.ManagerDAO;
import com.example.projectandroid.dal.RequestDAO;
import com.example.projectandroid.dal.RoomDAO;
import com.example.projectandroid.dal.StudentDAO;
import com.example.projectandroid.domain.Account;
import com.example.projectandroid.domain.Admin;
import com.example.projectandroid.domain.AvailibleBookingTime;
import com.example.projectandroid.domain.Bed;
import com.example.projectandroid.domain.ElectricityWaterBills;
import com.example.projectandroid.domain.HistoryBook;
import com.example.projectandroid.domain.HistoryPayment;
import com.example.projectandroid.domain.Manager;
import com.example.projectandroid.domain.Request;
import com.example.projectandroid.domain.Room;
import com.example.projectandroid.domain.Student;

@Database(entities = {Account.class, Admin.class, AvailibleBookingTime.class, ElectricityWaterBills.class, HistoryBook.class, HistoryPayment.class, Manager.class, Request.class, Room.class, Bed.class, Student.class},version = 1)
public abstract class MyDatabase extends RoomDatabase {
    public abstract AccountDAO createAccountDAO();
    public abstract AdminDAO createAdminDAO();
    public abstract AvailibleBookingTimeDAO createAvailibleBookingTimeDAO();
    public abstract ElectricityWaterBillsDAO createElectricityWaterBillsDAO();
    public abstract HistoryBookDAO createHistoryBookDAO();
    public abstract HistoryPaymentDAO createHistoryPaymentDAO();
    public abstract ManagerDAO createManagerDAO();
    public abstract RequestDAO createRequestDAO();
    public abstract RoomDAO createRoomDAO();
    public abstract BedDAO createBedDAO();
    public abstract StudentDAO createStudentDAO();

}
