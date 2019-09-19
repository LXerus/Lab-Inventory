package Clases.Modelos;

import java.time.LocalDate;
import java.time.LocalTime;

public class UserActivity {
    private int id_user;
    private String userName;
    private String userLastName;
    private String userEmail;
    private String activityType;
    private String table;
    private LocalDate date;
    private LocalTime time;
    private int id_product;

    public UserActivity(int id_user, String userName, String userLastName, String userEmail, String activityType, String table, LocalDate date, LocalTime time, int id_product) {
        this.id_user = id_user;
        this.userName = userName;
        this.userLastName = userLastName;
        this.userEmail = userEmail;
        this.activityType = activityType;
        this.table = table;
        this.date = date;
        this.time = time;
        this.id_product = id_product;
    }

    public UserActivity(int id_user, String userName, String userLastName, String userEmail, String activityType, String table, LocalDate date, LocalTime time) {
        this.id_user = id_user;
        this.userName = userName;
        this.userLastName = userLastName;
        this.activityType = activityType;
        this.userEmail = userEmail;
        this.table = table;
        this.date = date;
        this.time = time;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public int getId_product() {
        return id_product;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
