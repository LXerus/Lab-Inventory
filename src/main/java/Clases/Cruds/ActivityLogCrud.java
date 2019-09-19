package Clases.Cruds;

import Clases.BaseDeDatos.JDBConnection;
import Clases.Modelos.UserActivity;
import Clases.Modelos.CurrentUser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class ActivityLogCrud {
    JDBConnection JDBConnection;
    Connection sqlConnection;
    PreparedStatement actividadStatement;

    public void registrarActividad(UserActivity actividad){
        JDBConnection = new JDBConnection(CurrentUser.getCurrentUser().getName(), CurrentUser.getCurrentUser().getPassword());
        sqlConnection = JDBConnection.getConnection();
        String instruccionSQL = "INSERT INTO registro_de_actividades (id_usuario, nombre, apellidos, correo_electronico, tipo_de_actividad, tabla, fecha, hora, id_producto) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            actividadStatement = sqlConnection.prepareStatement(instruccionSQL);
            actividadStatement.setInt(1, actividad.getId_user());
            actividadStatement.setString(2, actividad.getUserName());
            actividadStatement.setString(3, actividad.getUserLastName());
            actividadStatement.setString(4, actividad.getUserEmail());
            actividadStatement.setString(5, actividad.getActivityType());
            actividadStatement.setString(6, actividad.getTable());
            actividadStatement.setDate(7, Date.valueOf(actividad.getDate()));
            actividadStatement.setTime(8, Time.valueOf(actividad.getTime()));
            actividadStatement.setInt(9, actividad.getId_product());
            actividadStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            actividadStatement = null;
            sqlConnection = null;
            JDBConnection.disconnect();
        }
    }

    public ObservableList<UserActivity> obtenerLogActividades(String query){
        ObservableList<UserActivity> logActividades = FXCollections.observableArrayList();
        JDBConnection = new JDBConnection(CurrentUser.getCurrentUser().getName(), CurrentUser.getCurrentUser().getPassword());
        sqlConnection = JDBConnection.getConnection();
        try{
            Statement logStatement = sqlConnection.createStatement();
            ResultSet logResultSet = logStatement.executeQuery(query);
            while(logResultSet.next()){
                int id = logResultSet.getInt(1);
                String nombres = logResultSet.getString(2);
                String apellidos = logResultSet.getString(3);
                String email = logResultSet.getString(4);
                String actividad = logResultSet.getString(5);
                String categoria = logResultSet.getString(6);
                LocalDate fecha = logResultSet.getDate(7).toLocalDate();
                LocalTime hora = logResultSet.getTime(8).toLocalTime();
                int id_producto = logResultSet.getInt(9);

                logActividades.add(new UserActivity(id, nombres, apellidos, email, actividad, categoria, fecha,hora,id_producto));
            }
        }catch (SQLException e){
            e.getErrorCode();
        }
        return logActividades;
    }
}
