package Clases.Cruds;

import Clases.BaseDeDatos.JDBConnection;
import Clases.Models.Cellar;
import Clases.Models.UserActivity;
import Clases.Models.CurrentUser;
import Iterfaces.ICrudable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class CellarCrud implements ICrudable {

    public void create(Object object){
        Cellar cellar = (Cellar) object;
        String name = cellar.getName();
        String condition = cellar.getCondition();
        String region = cellar.getRegion();
        String section = cellar.getSection();
        JDBConnection = new JDBConnection(CurrentUser.getCurrentUser().getName(), CurrentUser.getCurrentUser().getPassword());

        try {
            String sqlQuery = "INSERT INTO bodega(nombre, condicion, region, tramo) VALUES(?, ?, ?, ?)";
            connection = JDBConnection.getConnection();
            preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, condition);
            preparedStatement.setString(3, region);
            preparedStatement.setString(4, section);
            preparedStatement.executeUpdate();

            activity .registerActivity(activity.REGISTER, "Bodegas");

        }catch (SQLException e){
            e.printStackTrace();
        }finally{
            if(preparedStatement != null){
                preparedStatement = null;
            }
            if (connection != null){
                connection = null;
            }
            JDBConnection.disconnect();
        }
    }

 public ObservableList<Cellar> read(Object object) {
     Cellar cellar = (Cellar) object;
     String sqlQuery = "SELECT id, nombre, condicion, region, tramo FROM bodega WHERE";
     if(!(cellar.getId() == 0)){
         sqlQuery = sqlQuery + " id LIKE '%"+ cellar.getId()+"%' AND ";
     }
     if (!cellar.getName().isEmpty()){
         sqlQuery = sqlQuery + " nombre LIKE '%"+cellar.getName()+"%' AND ";
     }
     if (!cellar.getRegion().isEmpty()){
         sqlQuery = sqlQuery + " region LIKE '%"+cellar.getRegion()+"%' AND ";
     }

     char[] stringToArray = sqlQuery.toCharArray();
     String cleanQuery = "";

     for(int i = 0;i < stringToArray.length-4; i ++){
         cleanQuery = cleanQuery + stringToArray[i];
     }

     sqlQuery = cleanQuery;

    ResultSet resultSet = null;
    cellarList = FXCollections.observableArrayList();
    JDBConnection = new JDBConnection(CurrentUser.getCurrentUser().getName(), CurrentUser.getCurrentUser().getPassword());
    connection = JDBConnection.getConnection();
    try {
        Statement consultarBase = connection.createStatement();
        resultSet = consultarBase.executeQuery(sqlQuery);

        while (resultSet.next()) {
            int id_bodega = resultSet.getInt(1);
            String name = resultSet.getString(2);
            String condition = resultSet.getString(3);
            String region = resultSet.getString(4);
            String section = resultSet.getString(5);
            cellarList.add(new Cellar(id_bodega, name, condition, region, section));
        }
        activity .registerActivity(activity.SEARCH, "Bodegas");
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            if(resultSet != null){
                resultSet = null;
            }
            if (connection != null){
                connection = null;
            }
            JDBConnection.disconnect();
        }
     return cellarList;
    }

    public void update(Object object){
        Cellar cellar = (Cellar) object;
        String sqlQuery = "UPDATE bodega SET nombre='?', condicion='?', tramo='?', region='?' WHERE id="+ cellar.getId();
        try {
            JDBConnection = new JDBConnection(CurrentUser.getCurrentUser().getName(), CurrentUser.getCurrentUser().getPassword());
            connection = JDBConnection.getConnection();
            preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, cellar.getName());
            preparedStatement.setString(2, cellar.getCondition());
            preparedStatement.setString(3, cellar.getSection());
            preparedStatement.setString(4, cellar.getRegion());
            preparedStatement.executeUpdate();

            activity .registerActivity(activity.UPDATE, "Bodegas");
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            try{
                if (preparedStatement != null){
                    preparedStatement.close();
                }
            }catch (SQLException ex){
                ex.printStackTrace();
            }

            try{
                if(connection != null){
                    connection.close();
                }
            }catch (SQLException ex){
                ex.printStackTrace();
            }

            if(JDBConnection != null){
                JDBConnection.disconnect();
            }
        }
    }

    @Override
    public void delete(Object object) {
        Cellar cellar = (Cellar) object;
        String sqlQuery = "DELETE FROM bodega Where id = ?";
        try {
            JDBConnection = new JDBConnection(CurrentUser.getCurrentUser().getName(), CurrentUser.getCurrentUser().getPassword());
            connection = JDBConnection.getConnection();
            preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, cellar.getId());
            preparedStatement.executeUpdate();

            activity .registerActivity(activity.DELETE, "Bodegas");
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

    private Connection connection;
    private JDBConnection JDBConnection;
    private ObservableList<Cellar> cellarList;
    private PreparedStatement preparedStatement;
    UserActivity activity = new UserActivity();
}
