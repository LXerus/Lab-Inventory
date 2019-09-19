package Clases.Cruds;

import Clases.BaseDeDatos.JDBConnection;
import Clases.Modelos.User;
import Clases.Modelos.UserActivity;
import Clases.Modelos.CurrentUser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class UserCrud {
    JDBConnection JDBConnection;
    Connection connection;
    PreparedStatement preparedStatement;
    Statement statement = null;
    UserActivity activity;
    LocalDate date;
    LocalTime time;
    ActivityLogCrud log;


    public void create(User user){
        JDBConnection = new JDBConnection(CurrentUser.getCurrentUser().getName(), CurrentUser.getCurrentUser().getPassword());
        connection = JDBConnection.getConnection();
        String instruccionSQL = "INSERT INTO usuario (nombres, apellidos, password, fecha_de_ingreso, area, activo, correo_electronico, id_privilegios) "+
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            preparedStatement = connection.prepareStatement(instruccionSQL);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setDate(4, Date.valueOf(user.getStartDate()));
            preparedStatement.setString(5, user.getArea());
            preparedStatement.setBoolean(6, user.isActive());
            preparedStatement.setString(7, user.getEmail());
            preparedStatement.setInt(8, user.getPrivileges());
            preparedStatement.executeUpdate();

            log = new ActivityLogCrud();
            date = LocalDate.now();
            time = LocalTime.now();
            activity = new UserActivity(
                    CurrentUser.getCurrentUser().getId(),
                    CurrentUser.getCurrentUser().getName(),
                    CurrentUser.getCurrentUser().getLastName(),
                    CurrentUser.getCurrentUser().getEmail(),
                    "Registro de nuevo usuario",
                    "Usuario",
                    date,
                    time
            );
            log.registrarActividad(activity);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try{
                if (preparedStatement != null){
                    preparedStatement.close();
                }
                if (connection != null){
                    connection.close();
                }
                JDBConnection.disconnect();
            }catch (SQLException ex){
                ex.printStackTrace();
            }           
        }
    }

    public ObservableList<User> read(User user){
        //Retorna una lista observable con la lista de usuario dependiendo de los parametros recibidos.
        ObservableList<User> userList = FXCollections.observableArrayList();
        Statement usuarioStatement = null;
        ResultSet usuarioResultset = null;

        String sqlInstruction = "SELECT id, nombres, password, apellidos, fecha_de_ingreso, area, activo, correo_electronico, id_privilegios FROM usuario WHERE";
        if(!user.getName().isEmpty()){
            sqlInstruction += " nombres LIKE '%"+ user.getName()+"%' AND ";
        }
        if(!user.getLastName().isEmpty()){
            sqlInstruction += " apellidos LIKE '%"+ user.getLastName()+"%' AND ";
        }
        if(!user.getEmail().isEmpty()){
            sqlInstruction += " correo_electronico LIKE '%"+ user.getEmail()+"%' AND ";
        }
        if(!user.getArea().isEmpty()){
            sqlInstruction += " area LIKE '%"+ user.getArea()+"%' AND ";
        }
        if(user.getPrivileges() != 0){
            sqlInstruction += " id_privilegios="+ user.getPrivileges()+" AND ";
        }

        char[] stringToArray = sqlInstruction.toCharArray();
        String cleanQuery = "";

        //Algoritno para limpiar la sintaxis del query para sql
        for(int i = 0;i < stringToArray.length-4; i ++){
            cleanQuery = cleanQuery + stringToArray[i];
        }

        sqlInstruction = cleanQuery;

        try{
            JDBConnection = new JDBConnection(CurrentUser.getCurrentUser().getName(), CurrentUser.getCurrentUser().getPassword());
            connection = JDBConnection.getConnection();
            usuarioStatement = connection.createStatement();
            usuarioResultset = usuarioStatement.executeQuery(sqlInstruction);

             while (usuarioResultset.next()){
                 int id = usuarioResultset.getInt(1);
                 String name = usuarioResultset.getString(2);
                 String lastName = usuarioResultset.getString(3);
                 String password = usuarioResultset.getString(4);
                 LocalDate startDate = usuarioResultset.getDate(5).toLocalDate();
                 String area = usuarioResultset.getString(6);
                 boolean active = usuarioResultset.getBoolean(7);
                 String email = usuarioResultset.getString(8);
                 int id_privileges = usuarioResultset.getInt(9);

                 userList.add(new User(id, name, password, lastName, startDate, area, active, email, id_privileges));
             }

            log = new ActivityLogCrud();
            date = LocalDate.now();
            time = LocalTime.now();
            activity = new UserActivity(
                    CurrentUser.getCurrentUser().getId(),
                    CurrentUser.getCurrentUser().getName(),
                    CurrentUser.getCurrentUser().getLastName(),
                    CurrentUser.getCurrentUser().getEmail(),
                    "Busqueda de Usuario",
                    "Usuario",
                    date,
                    time
            );
            log.registrarActividad(activity);
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try{
                if(usuarioResultset != null){
                    usuarioResultset.close();
                }
                if(usuarioStatement != null){
                    usuarioStatement.close();
                }
                if(connection != null){
                    connection.close();
                }
                JDBConnection.disconnect();
            }catch (SQLException ex){
                ex.printStackTrace();
            }
        }
        return userList;
    }

    public void update(User user){
        String sqlQuery = "UPDATE usuario SET nombres =? , apellidos=?, password=?, fecha_de_ingreso=?, area=?, activo=?, correo_electronico=?, id_privilegios=? WHERE id="+ user.getId();
        statement = null;
        preparedStatement = null;
        try{
            JDBConnection = new JDBConnection(CurrentUser.getCurrentUser().getName(), CurrentUser.getCurrentUser().getPassword());
            connection = JDBConnection.getConnection();
            preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setDate(4, Date.valueOf(user.getStartDate()));
            preparedStatement.setString(5, user.getArea());
            preparedStatement.setBoolean(6, user.isActive());
            preparedStatement.setString(7, user.getEmail());
            preparedStatement.setInt(8, user.getPrivileges());
            preparedStatement.executeUpdate();

        /*    privilegiosActualizar = sqlConnection.createStatement();
            if(usuario.getPrivilegios() == 1){
                sqlQuery = "REVOKE ALL PRIVILEGES ON *.* FROM '"+usuario.getNombres()+"'@'%'";
                privilegiosActualizar.executeUpdate(sqlQuery);

                sqlQuery += "GRANT ALL PRIVILEGES ON *.* TO '"+usuario.getNombres()+"'@'%' IDENTIFIED BY '"+usuario.getPassword()+"'";
                privilegiosActualizar.executeUpdate(sqlQuery);
            }

            if(usuario.getPrivilegios() == 2){
                sqlQuery = "REVOKE ALL PRIVILEGES ON *.* FROM '"+usuario.getNombres()+"'@'%'";
                privilegiosActualizar.executeUpdate(sqlQuery);

                sqlQuery += "GRANT DELETE, INSERT, UPDATE, SELECT ON *.* TO '"+usuario.getNombres()+"'@'%' IDENTIFIED BY '"+usuario.getPassword()+"'";
                privilegiosActualizar.executeUpdate(sqlQuery);
            }

            privilegiosActualizar.executeUpdate("FLUSH PRIVILEGES");*/

            log = new ActivityLogCrud();
            date = LocalDate.now();
            time = LocalTime.now();
            activity = new UserActivity(
                    CurrentUser.getCurrentUser().getId(),
                    CurrentUser.getCurrentUser().getName(),
                    CurrentUser.getCurrentUser().getLastName(),
                    CurrentUser.getCurrentUser().getEmail(),
                    "Actualizacion de Usuario",
                    "Usuario",
                    date,
                    time
            );
            log.registrarActividad(activity);
        }catch (SQLException ex){
            ex.printStackTrace();
            ex.getErrorCode();
        }finally {
            try{
                if(statement != null){
                    statement.close();
                }
                if(preparedStatement != null){
                    preparedStatement.close();
                }
                if(connection != null){
                    connection.close();
                }
                JDBConnection.disconnect();
            }catch (SQLException ex){
                ex.printStackTrace();
            }
        }
    }

    public String getUserPrivileges(User user){
        String privilege = "";
        String sqlInstruction = "SELECT tipo_de_privilegios FROM privilegios_de_usuario WHERE id=?";
        ResultSet resultSet = null;
        try {
            JDBConnection = new JDBConnection(CurrentUser.getCurrentUser().getName(), CurrentUser.getCurrentUser().getPassword());
            connection = JDBConnection.getConnection();
            preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setInt(1, user.getPrivileges());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                privilege = resultSet.getString(1);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            try{
                if(resultSet != null){
                    resultSet.close();
                }
                if(preparedStatement != null){
                    preparedStatement.close();
                }
                if(connection != null){
                    connection.close();
                }
                JDBConnection.disconnect();
            }catch (SQLException ex){
                ex.printStackTrace();
            }
        }
        return privilege;
    }
}
