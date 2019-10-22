package Clases.Cruds;

import Clases.BaseDeDatos.DBConnection;
import Clases.Models.Registry;
import Clases.Models.UserActivity;
import Clases.Models.CurrentUser;
import Iterfaces.ICrudable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class RegistryCrud implements ICrudable {

    @Override
    public void create(Object object){
        Registry registry = (Registry) object;
        sqlQuery ="INSERT INTO registro(nombre, descripcion) VALUES(?,?);";
        try{
            JDBDBConnection = new DBConnection(CurrentUser.getCurrentUser().getName(), CurrentUser.getCurrentUser().getPassword());
            connection = JDBDBConnection.getConnection();
            preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, registry.getName());
            preparedStatement.setString(2, registry.getDescription());
            preparedStatement.executeUpdate();

            activity.registerActivity(activity.REGISTER, "Registro");
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
                JDBDBConnection.disconnect();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public ObservableList<Registry> read(Object object){
        Registry registry = (Registry) object;
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
            JDBDBConnection = new DBConnection(CurrentUser.getCurrentUser().getName(), CurrentUser.getCurrentUser().getPassword());
            connection = JDBDBConnection.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlQuery);

            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("nombre");
                String description = resultSet.getString("descripcion");

                registryList.add(new Registry(id, name, description));
            }

            activity.registerActivity(activity.SEARCH, "Registro");

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
                JDBDBConnection.disconnect();
            }catch (SQLException e){
                e.printStackTrace();
            }
        return registryList;
        }
    }

    @Override
    public void update(Object object){
        Registry registry = (Registry) object;
        sqlQuery = "UPDATE registro SET nombre = ?, descripcion = ? WHERE id = ?";
        try{
            JDBDBConnection = new DBConnection(CurrentUser.getCurrentUser().getName(), CurrentUser.getCurrentUser().getPassword());
            connection = JDBDBConnection.getConnection();
            preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, registry.getName());
            preparedStatement.setString(2, registry.getDescription());
            preparedStatement.setInt(3, registry.getId());
            preparedStatement.executeUpdate();

            activity.registerActivity(activity.UPDATE, "Registro");
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
                JDBDBConnection.disconnect();
        }catch (SQLException e){
            e.printStackTrace();
        }
        }
    }

    @Override
    public void delete(Object object){
        Registry registry = (Registry) object;
        sqlQuery = "DELETE FROM registro WHERE id=?";
        try {
            JDBDBConnection = new DBConnection(CurrentUser.getCurrentUser().getName(), CurrentUser.getCurrentUser().getPassword());
            connection = JDBDBConnection.getConnection();
            preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, registry.getId());
            preparedStatement.executeUpdate();

            activity.registerActivity(activity.DELETE, "Registro");
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
                JDBDBConnection.disconnect();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    DBConnection JDBDBConnection = null;
    Connection connection = null;
    Statement statement = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    String sqlQuery = "";
    UserActivity activity = new UserActivity();
}
