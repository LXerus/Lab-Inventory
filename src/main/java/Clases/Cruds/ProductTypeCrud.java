package Clases.Cruds;

import Clases.BaseDeDatos.JDBConnection;
import Clases.Modelos.ProductType;
import Clases.Modelos.UserActivity;
import Clases.Modelos.CurrentUser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class ProductTypeCrud {

    public void create(ProductType productType){
        sqlQuery ="INSERT INTO tipo_producto(tipo_de_producto, descripcion) VALUES(?,?);";
        try{
            JDBConnection = new JDBConnection(CurrentUser.getCurrentUser().getName(), CurrentUser.getCurrentUser().getPassword());
            connection = JDBConnection.getConnection();
            preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, productType.getProductType());
            preparedStatement.setString(2, productType.getDescription());
            preparedStatement.executeUpdate();

            log = new ActivityLogCrud();
            date = LocalDate.now();
            time = LocalTime.now();
            activity = new UserActivity(
                    CurrentUser.getCurrentUser().getId(),
                    CurrentUser.getCurrentUser().getName(),
                    CurrentUser.getCurrentUser().getLastName(),
                    CurrentUser.getCurrentUser().getEmail(),
                    "Nuevo Tipo de Producto.",
                    "Tipo de Producto.",
                    date,
                    time
            );
            log.registrarActividad(activity);
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

    public ObservableList<ProductType> read(ProductType productType){
        ObservableList<ProductType> productTypeList = FXCollections.observableArrayList();
        sqlQuery = "SELECT id, tipo_de_producto, descripcion FROM tipo_producto WHERE ";
        if(productType.getId() != 0){
            sqlQuery += " id = "+ productType.getId()+" AND ";
        }
        if(!productType.getProductType().isEmpty()){
            sqlQuery += " tipo_de_producto LIKE '%"+ productType.getProductType()+"%' AND ";
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
                String tipo_de_producto = resultSet.getString("tipo_de_producto");
                String descripcion = resultSet.getString("descripcion");

                productTypeList.add(new ProductType(id, tipo_de_producto, descripcion));
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
            return productTypeList;
        }
    }

    public void update(ProductType productType){
        sqlQuery = "UPDATE tipo_producto SET tipo_de_producto = ?, descripcion = ? WHERE id = ?";
        try{
            JDBConnection = new JDBConnection(CurrentUser.getCurrentUser().getName(), CurrentUser.getCurrentUser().getPassword());
            connection = JDBConnection.getConnection();
            preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, productType.getProductType());
            preparedStatement.setString(2, productType.getDescription());
            preparedStatement.setInt(3, productType.getId());
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
            log.registrarActividad(activity);
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

    public void delete(ProductType productType){
        sqlQuery = "DELETE FROM tipo_producto WHERE id=?";
        try {
            JDBConnection = new JDBConnection(CurrentUser.getCurrentUser().getName(), CurrentUser.getCurrentUser().getPassword());
            connection = JDBConnection.getConnection();
            preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, productType.getId());
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
            log.registrarActividad(activity);
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
