package Clases.Models;

import java.time.LocalDate;
import java.time.LocalTime;

public class CurrentUser {
    private static User currentUser;
    private static LocalDate loginDate;
    private static LocalTime loginTime;

    public CurrentUser(){}

    public static void getUserData(User user, LocalDate entryDate, LocalTime entryTime) {
        setLoginDate(entryDate);
        setLoginTime(entryTime);
        setCurrentUser(user);
    }

    public static LocalTime getLoginTime() {
        return loginTime;
    }

    public static void setLoginTime(LocalTime Hora) {
        loginTime = Hora;
    }

    public static LocalDate getLoginDate() {
        return loginDate;
    }

    public static void setLoginDate(LocalDate Fecha) {
        loginDate = Fecha;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User user) {
        currentUser = user;
    }
}
