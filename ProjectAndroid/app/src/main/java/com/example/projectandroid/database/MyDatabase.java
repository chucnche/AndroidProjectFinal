package com.example.projectandroid.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.projectchucn.dal.AccountDAO;
import com.example.projectchucn.dal.AdminDAO;
import com.example.projectchucn.dal.AvailibleBookingTimeDAO;
import com.example.projectchucn.dal.ElectricityWaterBillsDAO;
import com.example.projectchucn.dal.HistoryBookDAO;
import com.example.projectchucn.dal.HistoryPaymentDAO;
import com.example.projectchucn.dal.ManagerDAO;
import com.example.projectchucn.dal.RequestDAO;
import com.example.projectchucn.dal.RoomDAO;
import com.example.projectchucn.dal.StudentDAO;
import com.example.projectchucn.domain.Account;
import com.example.projectchucn.domain.Admin;
import com.example.projectchucn.domain.AvailibleBookingTime;
import com.example.projectchucn.domain.ElectricityWaterBills;
import com.example.projectchucn.domain.HistoryBook;
import com.example.projectchucn.domain.HistoryPayment;
import com.example.projectchucn.domain.Manager;
import com.example.projectchucn.domain.Request;
import com.example.projectchucn.domain.Room;
import com.example.projectchucn.domain.Student;

@Database(entities = {Account.class, Admin.class, AvailibleBookingTime.class, ElectricityWaterBills.class, HistoryBook.class, HistoryPayment.class, Manager.class, Request.class, Room.class, Student.class},version = 1)
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
    public abstract StudentDAO createStudentDAO();

}
