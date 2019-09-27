package Clases.Cruds;

import Clases.BaseDeDatos.JDBConnection;
import Clases.Modelos.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class ProviderCrud {
    private Connection connection;
    private JDBConnection JDBConnection;
    private ObservableList<Provider> providersList;
    UserActivity activity;
    LocalDate date;
    LocalTime time;
    ActivityLogCrud log;

    public ProviderCrud(){}

    //clase encargada para todas las conexiones con la base de datos para los datos de proveedores.

    public void create(Provider provider){
        String name = provider.getName();
        String telephone = provider.getTelephone();
        String contact = provider.getContact();
        String code = provider.getProviderCode();
        String services = provider.getService();
        boolean critical = provider.isCritical();
        boolean approved = provider.isApproved();
        int rating = provider.getRating();
        LocalDate approvalDate = provider.getApprovalDate();
        LocalDate revalidationDate = provider.getRevalidationDate();

        JDBConnection = new JDBConnection(CurrentUser.getCurrentUser().getName(), CurrentUser.getCurrentUser().getPassword());
        PreparedStatement preparedStatement = null;
        String instruccionSQL = "INSERT INTO proveedores (nombre, telefono, contacto, codigo_de_proveedor, servicio, critico, aprobado, punteo, fecha_aprobacion, fecha_revalidacion) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try{
            connection = JDBConnection.getConnection();
            preparedStatement = connection.prepareStatement(instruccionSQL);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, telephone);
            preparedStatement.setString(3, contact);
            preparedStatement.setString(4,code);
            preparedStatement.setString(5, services);
            preparedStatement.setBoolean(6, critical);
            preparedStatement.setBoolean(7, approved);
            preparedStatement.setDouble(8, rating);
            preparedStatement.setDate(9, Date.valueOf(approvalDate));
            preparedStatement.setDate(10, Date.valueOf(revalidationDate));
            preparedStatement.executeUpdate();

            log = new ActivityLogCrud();
            date = LocalDate.now();
            time = LocalTime.now();
            activity = new UserActivity(
                    CurrentUser.getCurrentUser().getId(),
                    CurrentUser.getCurrentUser().getName(),
                    CurrentUser.getCurrentUser().getLastName(),
                    CurrentUser.getCurrentUser().getEmail(),
                    "Registro de proveedor",
                    "Proveedores",
                    date,
                    time
            );
            log.create(activity);
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            if (preparedStatement != null){
                preparedStatement = null;
            }
            if (connection != null){
                connection = null;
            }
            JDBConnection.disconnect();
        }
    }

    public ObservableList<Provider> read(Provider provider){
        providersList = null; //Esta linea es para regresar la lista a null cada vez que se vuelva a llamar la referencia
        providersList = FXCollections.observableArrayList();
        Statement statement = null;
        ResultSet resultSet = null;

        String sqlQuery = "SELECT id, nombre, telefono, contacto, codigo_de_proveedor, servicio, critico, aprobado, punteo, fecha_aprobacion, fecha_revalidacion FROM proveedores WHERE ";
        if(!(provider.getProviderCode().isEmpty())){
            sqlQuery += "codigo_de_proveedor LIKE '%"+ provider.getProviderCode()+"%' AND ";
        }
        if(!(provider.getName().isEmpty())){
            sqlQuery += "nombre LIKE '%"+ provider.getName()+"%' AND ";
        }
        if(!(provider.getTelephone().isEmpty())){
            sqlQuery += "telefono LIKE '%"+ provider.getTelephone()+"%' AND ";
        }
        if(!(provider.getContact().isEmpty())){
            sqlQuery += "contacto LIKE '%"+ provider.getContact()+"%' AND ";
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

            while (resultSet.next()){
                int id = resultSet.getInt(1);
               String name = resultSet.getString(2);
               String telephone = resultSet.getString(3);
               String contact = resultSet.getString(4);
               String code = resultSet.getString(5);
               String service = resultSet.getString(6);
               boolean critical = resultSet.getBoolean(7);
               boolean approved = resultSet.getBoolean(8);
               int rating = resultSet.getInt(9);
               LocalDate approvalDate = resultSet.getDate(10).toLocalDate();
               LocalDate revalidationDate = resultSet.getDate(11).toLocalDate();
               providersList.add(new Provider(id, name, telephone, contact, code, service, rating, critical, approved, approvalDate, revalidationDate));

                log = new ActivityLogCrud();
                date = LocalDate.now();
                time = LocalTime.now();
                activity = new UserActivity(
                        CurrentUser.getCurrentUser().getId(),
                        CurrentUser.getCurrentUser().getName(),
                        CurrentUser.getCurrentUser().getLastName(),
                        CurrentUser.getCurrentUser().getEmail(),
                        "Busqueda de proveedores.",
                        "Proveedores.",
                        date,
                        time
                );
                log.create(activity);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try{
                if(resultSet != null){
                    resultSet.close();
                }
            }catch (SQLException e){
               e.printStackTrace();
            }
            try{
                if(statement != null){
                    statement.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
            try{
                if(connection != null){
                    connection.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
            JDBConnection.disconnect();
        }
        return providersList;
    }

    public void update(Provider provider){
        String sqlInstruccion = "UPDATE proveedores SET nombre=?, telefono=?, contacto=?, codigo_de_proveedor=?, servicio=?, critico=?, aprobado=?, punteo=?, fecha_aprobacion=?, fecha_revalidacion=? WHERE id="+ provider.getId();
        PreparedStatement preparedStatement = null;
        try {
            JDBConnection = new JDBConnection(CurrentUser.getCurrentUser().getName(), CurrentUser.getCurrentUser().getPassword());
            connection = JDBConnection.getConnection();
            preparedStatement = connection.prepareStatement(sqlInstruccion);
            preparedStatement.setString(1, provider.getName());
            preparedStatement.setString(2, provider.getTelephone());
            preparedStatement.setString(3, provider.getContact());
            preparedStatement.setString(4, provider.getProviderCode());
            preparedStatement.setString(5, provider.getService());
            preparedStatement.setBoolean(6, provider.isCritical());
            preparedStatement.setBoolean(7, provider.isApproved());
            preparedStatement.setInt(8, provider.getRating());
            preparedStatement.setDate(9, Date.valueOf(provider.getApprovalDate()));
            preparedStatement.setDate(10, Date.valueOf(provider.getRevalidationDate()));
            preparedStatement.executeUpdate();

            log = new ActivityLogCrud();
            date = LocalDate.now();
            time = LocalTime.now();
            activity = new UserActivity(
                    CurrentUser.getCurrentUser().getId(),
                    CurrentUser.getCurrentUser().getName(),
                    CurrentUser.getCurrentUser().getLastName(),
                    CurrentUser.getCurrentUser().getEmail(),
                    "Actualizar Proveedor",
                    "Proveedor",
                    date,
                    time
            );
            log.create(activity);
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
}
