package Clases.Cruds;

import Clases.BaseDeDatos.connection;
import Clases.Models.UserActivity;
import Clases.Models.CurrentUser;
import Iterfaces.ICrudable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * <h2>ActivityLogCrud</h2>
 * <p>This class has the responsability to handle the SQL queries and register
 * the log in it's table</p> 
 * 
 * 
 * 
 */

public class ActivityLogCrud implements ICrudable {
    Clases.BaseDeDatos.connection JDBConnection;
    Connection connection;
    PreparedStatement preparedStatement;

    public void create(Object object){
        UserActivity activity = (UserActivity) object;
        JDBConnection = new connection(CurrentUser.getCurrentUser().getName(), CurrentUser.getCurrentUser().getPassword());
        connection = JDBConnection.getConnection();
        String sqlQuery = "INSERT INTO registro_de_actividades (id_usuario, nombre, apellidos, correo_electronico, tipo_de_actividad, tabla, fecha, hora, id_producto) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, activity.getId_user());
            preparedStatement.setString(2, activity.getUserName());
            preparedStatement.setString(3, activity.getUserLastName());
            preparedStatement.setString(4, activity.getUserEmail());
            preparedStatement.setString(5, activity.getActivityType());
            preparedStatement.setString(6, activity.getTable());
            preparedStatement.setDate(7, Date.valueOf(activity.getDate()));
            preparedStatement.setTime(8, Time.valueOf(activity.getTime()));
            preparedStatement.setInt(9, activity.getId_product());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(preparedStatement != null){
                preparedStatement = null;
            }
            if (connection != null){
                connection = null;
            }
            JDBConnection.disconnect();
        }
    }

    public ObservableList<UserActivity> read(Object object){
        UserActivity userActivity = (UserActivity) object;
        ObservableList<UserActivity> logActividades = FXCollections.observableArrayList();

        String sqlQuery = "SELECT id_usuario, nombre, apellidos, correo_electronico, tipo_de_actividad, tabla, fecha, hora, id_producto FROM registro_de_actividades WHERE ";
        if(!(userActivity.getUserName().isEmpty())){
            sqlQuery += "nombre LIKE '%"+userActivity.getUserName()+"%' AND ";
        }

        if(!userActivity.getUserLastName().isEmpty()){
            sqlQuery += "apellidos LIKE '%"+userActivity.getUserLastName()+"%' AND ";
        }

        if(!userActivity.getUserEmail().isEmpty()){
            sqlQuery += "correo_electronico LIKE '%"+userActivity.getUserEmail()+"%' AND ";
        }

        if(!(userActivity.getId_user() == 0)){
            sqlQuery += "id_usuario LIKE '%"+userActivity.getId_user()+"%' AND ";
        }

        char[] stringToArray = sqlQuery.toCharArray();
        String cleanQuery = "";

        for(int i = 0;i < stringToArray.length-4; i ++){
            cleanQuery = cleanQuery + stringToArray[i];
        }

        sqlQuery = cleanQuery;


        JDBConnection = new connection(CurrentUser.getCurrentUser().getName(), CurrentUser.getCurrentUser().getPassword());
        connection = JDBConnection.getConnection();
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while(resultSet.next()){
                int id = resultSet.getInt(1);
                String nombres = resultSet.getString(2);
                String apellidos = resultSet.getString(3);
                String email = resultSet.getString(4);
                String actividad = resultSet.getString(5);
                String categoria = resultSet.getString(6);
                LocalDate fecha = resultSet.getDate(7).toLocalDate();
                LocalTime hora = resultSet.getTime(8).toLocalTime();
                int id_producto = resultSet.getInt(9);

                logActividades.add(new UserActivity(id, nombres, apellidos, email, actividad, categoria, fecha, hora));
            }
        }catch (SQLException e){
            e.getErrorCode();
        }finally {
            try{
                if(connection != null){
                    connection.close();
                }
                JDBConnection.disconnect();
            }catch (SQLException ex){
                ex.printStackTrace();
            }
        }
        return logActividades;
    }

    @Override
    public void update(Object object) {
        
    }

    @Override
    public void delete(Object object) {

    }
}
