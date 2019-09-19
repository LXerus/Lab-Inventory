package Controladores.Usuarios;

import Clases.BaseDeDatos.JDBConnection;
import Clases.Modelos.User;
import Clases.Modelos.CurrentUser;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CrearUsuario {

    public void crearUsuario(User user){

        try {
            String sqlQuery = "CREATE USER '?'@'%' IDENTIFIED BY '?';";
            JDBConnection JDBConnection = new JDBConnection(CurrentUser.getCurrentUser().getName(), CurrentUser.getCurrentUser().getPassword());
            Connection sqlConnection = JDBConnection.getConnection();

            Statement statement = sqlConnection.createStatement();
            statement.executeUpdate( "CREATE USER '"+ user.getName()+"'@'%' IDENTIFIED BY '"+ user.getPassword()+"';");

        }catch (SQLException e){
            e.getErrorCode();
        }
    }

    public void establecerPrivilegios(User user){
        String sqlQuery = "";
        try {
            JDBConnection JDBConnection = new JDBConnection(CurrentUser.getCurrentUser().getName(), CurrentUser.getCurrentUser().getPassword());
            Connection sqlConnection = JDBConnection.getConnection();
            if (user.getPrivileges() == 1) {
                sqlQuery = "GRANT ALL PRIVILEGES ON *.* TO '"+ user.getName()+"'@'%' IDENTIFIED BY '"+ user.getPassword()+"'";
            }
            if (user.getPrivileges() == 2) {
                sqlQuery = "GRANT DELETE, INSERT, UPDATE, SELECT ON *.* TO '"+ user.getName()+"'@'%' IDENTIFIED BY '"+ user.getPassword()+"'";
            }

          Statement statement = sqlConnection.createStatement();
          statement.executeUpdate(sqlQuery);
          statement.executeUpdate("FLUSH PRIVILEGES;");
        }catch (SQLException e){
            e.getErrorCode();
        }
    }
}
