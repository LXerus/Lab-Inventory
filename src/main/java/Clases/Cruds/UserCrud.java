package Clases.Cruds;

import Clases.BaseDeDatos.DBConnection;
import Clases.Models.User;
import Clases.Models.UserActivity;
import Clases.Models.CurrentUser;
import Iterfaces.ICrudable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;

public class UserCrud implements ICrudable {

    @Override
    public void create(Object object){
        User user = (User) object;
        JDBDBConnection = new DBConnection(CurrentUser.getCurrentUser().getName(), CurrentUser.getCurrentUser().getPassword());
        connection = JDBDBConnection.getConnection();
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

            activity.registerActivity(activity.REGISTER, "Usuario");
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
                JDBDBConnection.disconnect();
            }catch (SQLException ex){
                ex.printStackTrace();
            }           
        }
    }

    @Override
    public ObservableList<User> read(Object object){
        User user = (User) object;
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
            JDBDBConnection = new DBConnection(CurrentUser.getCurrentUser().getName(), CurrentUser.getCurrentUser().getPassword());
            connection = JDBDBConnection.getConnection();
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

            activity.registerActivity(activity.SEARCH, "Usuario");
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
                JDBDBConnection.disconnect();
            }catch (SQLException ex){
                ex.printStackTrace();
            }
        }
        return userList;
    }

    @Override
    public void update(Object object){
        User user = (User) object;
        String sqlQuery = "UPDATE usuario SET nombres =? , apellidos=?, password=?, fecha_de_ingreso=?, area=?, activo=?, correo_electronico=?, id_privilegios=? WHERE id="+ user.getId();
        statement = null;
        preparedStatement = null;
        try{
            JDBDBConnection = new DBConnection(CurrentUser.getCurrentUser().getName(), CurrentUser.getCurrentUser().getPassword());
            connection = JDBDBConnection.getConnection();
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

        /*    privilegiosActualizar = connection.createStatement();
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

            activity.registerActivity(activity.UPDATE, "Usuario");
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
                JDBDBConnection.disconnect();
            }catch (SQLException ex){
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void delete(Object object) {
        User user = (User) object;
        String sqlQuery = "DELETE FROM usuario Where id = ?";
        try {
            JDBDBConnection = new DBConnection(CurrentUser.getCurrentUser().getName(), CurrentUser.getCurrentUser().getPassword());
            connection = JDBDBConnection.getConnection();
            preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, user.getId());
            preparedStatement.executeUpdate();

            activity .registerActivity(activity.DELETE, "Usuario");
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
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public String getUserPrivileges(User user){
        String privilege = "";
        String sqlInstruction = "SELECT tipo_de_privilegios FROM privilegios_de_usuario WHERE id=?";
        ResultSet resultSet = null;
        try {
            JDBDBConnection = new DBConnection(CurrentUser.getCurrentUser().getName(), CurrentUser.getCurrentUser().getPassword());
            connection = JDBDBConnection.getConnection();
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
                JDBDBConnection.disconnect();
            }catch (SQLException ex){
                ex.printStackTrace();
            }
        }
        return privilege;
    }

    DBConnection JDBDBConnection;
    Connection connection;
    PreparedStatement preparedStatement;
    Statement statement = null;
    UserActivity activity = new UserActivity();
}
