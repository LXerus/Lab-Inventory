package Clases.Cruds;

import Clases.BaseDeDatos.JDBConnection;
import Clases.Modelos.Registry;
import Clases.Modelos.UserActivity;
import Clases.Modelos.CurrentUser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class RegistryCrud {

    public void create(Registry registry){
        sqlQuery ="INSERT INTO registro(nombre, descripcion) VALUES(?,?);";
        try{
            JDBConnection = new JDBConnection(CurrentUser.getCurrentUser().getName(), CurrentUser.getCurrentUser().getPassword());
            connection = JDBConnection.getConnection();
            preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, registry.getName());
            preparedStatement.setString(2, registry.getDescription());
            preparedStatement.executeUpdate();

            log = new ActivityLogCrud();
            date = LocalDate.now();
            time = LocalTime.now();
            activity = new UserActivity(
                    CurrentUser.getCurrentUser().getId(),
                    CurrentUser.getCurrentUser().getName(),
                    CurrentUser.getCurrentUser().getLastName(),
                    CurrentUser.getCurrentUser().getEmail(),
                    "Nuevo registro",
                    "Registro.",
                    date,
                    time
            );
            log.create(activity);
        }catch (SQLException ex){
           ex.printStackTrace();
        }finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
                JDBConnection.disconnect();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    public ObservableList<Registry> read(Registry registry){
        ObservableList<Registry> registryList = FXCollections.observableArrayList();
        sqlQuery = "SELECT id, nombre, descripcion FROM registro WHERE ";
        if(registry.getId() != 0){
            sqlQuery += " id = "+ registry.getId()+" AND ";
        }
        if(!registry.getName().isEmpty()){
            sqlQuery += " nombre LIKE '%"+ registry.getName()+"%' AND ";
        }

        //Corrigiendo la sentencia SQL
        char[] stringToArray = sqlQuery.toCharArray();
        String cleanQuery = "";

        for(int i = 0;i < stringToArray.length-4; i ++){
            cleanQuery = cleanQuery + stringToArray[i];
        }

        sqlQuery = cleanQuery;

        try{
            JDBConnection = new JDBConnection(CurrentUser.getCurrentUser().getName(), CurrentUser.getCurrentUser().getPassword());
            connection = JDBConnection.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlQuery);

            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("nombre");
                String description = resultSet.getString("descripcion");

                registryList.add(new Registry(id, name, description));
            }

        }catch (SQLException ex){
            ex.printStackTrace();
         }finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
                JDBConnection.disconnect();
            }catch (SQLException e){
                e.printStackTrace();
            }
        return registryList;
        }
    }

    public void update(Registry registry){
        sqlQuery = "UPDATE registro SET nombre = ?, descripcion = ? WHERE id = ?";
        try{
            JDBConnection = new JDBConnection(CurrentUser.getCurrentUser().getName(), CurrentUser.getCurrentUser().getPassword());
            connection = JDBConnection.getConnection();
            preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, registry.getName());
            preparedStatement.setString(2, registry.getDescription());
            preparedStatement.setInt(3, registry.getId());
            preparedStatement.executeUpdate();

            log = new ActivityLogCrud();
            date = LocalDate.now();
            time = LocalTime.now();
            activity = new UserActivity(
                    CurrentUser.getCurrentUser().getId(),
                    CurrentUser.getCurrentUser().getName(),
                    CurrentUser.getCurrentUser().getLastName(),
                    CurrentUser.getCurrentUser().getEmail(),
                    "Actualizar registro",
                    "Registro.",
                    date,
                    time
            );
            log.create(activity);
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            try{
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
            JDBConnection.disconnect();
        }catch (SQLException e){
            e.printStackTrace();
        }
        }
    }

    public void delete(Registry registry){
        sqlQuery = "DELETE FROM registro WHERE id=?";
        try {
            JDBConnection = new JDBConnection(CurrentUser.getCurrentUser().getName(), CurrentUser.getCurrentUser().getPassword());
            connection = JDBConnection.getConnection();
            preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, registry.getId());
            preparedStatement.executeUpdate();

            log = new ActivityLogCrud();
            date = LocalDate.now();
            time = LocalTime.now();
            activity = new UserActivity(
                    CurrentUser.getCurrentUser().getId(),
                    CurrentUser.getCurrentUser().getName(),
                    CurrentUser.getCurrentUser().getLastName(),
                    CurrentUser.getCurrentUser().getEmail(),
                    "Borrar registro",
                    "Registro.",
                    date,
                    time
            );
            log.create(activity);
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
                JDBConnection.disconnect();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    JDBConnection JDBConnection = null;
    Connection connection = null;
    Statement statement = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    String sqlQuery = "";
    UserActivity activity;
    LocalDate date;
    LocalTime time;
    ActivityLogCrud log;
}
