package Clases.Cruds;

import Clases.BaseDeDatos.Conectar;
import Clases.Modelos.UsuarioActividad;
import Clases.Modelos.UsuarioActual;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class LogActivades_Crud {
    Conectar conectar;
    Connection sqlConnection;
    PreparedStatement actividadStatement;

    public void registrarActividad(UsuarioActividad actividad){
        conectar = new Conectar(UsuarioActual.getUsuarioActual().getNombres(), UsuarioActual.getUsuarioActual().getPassword());
        sqlConnection = conectar.getConnection();
        String instruccionSQL = "INSERT INTO registro_de_actividades (id_usuario, nombre, apellidos, correo_electronico, tipo_de_actividad, tabla, fecha, hora, id_producto) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            actividadStatement = sqlConnection.prepareStatement(instruccionSQL);
            actividadStatement.setInt(1, actividad.getId_usuario());
            actividadStatement.setString(2, actividad.getNombre_usuario());
            actividadStatement.setString(3, actividad.getApellidos_usuario());
            actividadStatement.setString(4, actividad.getCorreo_electronico());
            actividadStatement.setString(5, actividad.getTipo_de_actividad());
            actividadStatement.setString(6, actividad.getTabla());
            actividadStatement.setDate(7, Date.valueOf(actividad.getFecha()));
            actividadStatement.setTime(8, Time.valueOf(actividad.getHora()));
            actividadStatement.setInt(9, actividad.getId_producto());
            actividadStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            actividadStatement = null;
            sqlConnection = null;
            conectar.desconectar();
        }
    }

    public ObservableList<UsuarioActividad> obtenerLogActividades(String query){
        ObservableList<UsuarioActividad> logActividades = FXCollections.observableArrayList();
        conectar = new Conectar(UsuarioActual.getUsuarioActual().getNombres(), UsuarioActual.getUsuarioActual().getPassword());
        sqlConnection = conectar.getConnection();
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

                logActividades.add(new UsuarioActividad(id, nombres, apellidos, email, actividad, categoria, fecha,hora,id_producto));
            }
        }catch (SQLException e){
            e.getErrorCode();
        }
        return logActividades;
    }
}
