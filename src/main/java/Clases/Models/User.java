package Clases.Models;

import java.time.LocalDate;

public class User {
    int id;
    String name;
    String lastName;
    String password;
    LocalDate startDate;
    String area;
    boolean active = true;
    String status;
    String email;
    int privileges;
    String privilegesType;

    public User(int id, String name, String lastName, String password, LocalDate startDate, String area, boolean active, String email, int privileges) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.password = password;
        this.startDate = startDate;
        this.area = area;
        this.active = active;
        this.email = email;
        this.privileges = privileges;
        if(active){
            this.status = "Activo";
        }else {
            this.status = "De baja";
        }
        if(privileges == 1){
            this.privilegesType = "Administrador";
        }else {
            this.privilegesType = "User";
        }
    }

    public User(String name, String lastName, String password, LocalDate startDate, String area, boolean active, String email, int privileges) {
        this.name = name;
        this.lastName = lastName;
        this.password = password;
        this.startDate = startDate;
        this.area = area;
        this.active = active;
        this.email = email;
        this.privileges = privileges;
        if(active){
            this.status = "Activo";
        }else {
            this.status = "De baja";
        }
        if(privileges == 1){
            this.privilegesType = "Administrador";
        }else {
            this.privilegesType = "User";
        }
    }

    public User(String name, String lastName, String area, String email, int privileges) {
        this.name = name;
        this.lastName = lastName;
        this.area = area;
        this.email = email;
        this.privileges = privileges;
        if(privileges == 1){
            this.privilegesType = "Administrador";
        }else {
            this.privilegesType = "User";
        }
    }

    public String getPrivilegesType() {
        return privilegesType;
    }

    public void setPrivilegesType(String privilegesType) {
        this.privilegesType = privilegesType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPrivileges() {
        return privileges;
    }

    public void setPrivileges(int privileges) {
        this.privileges = privileges;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
