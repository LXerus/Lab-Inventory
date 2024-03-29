package Controllers.User;

import Clases.BaseDeDatos.DBConnection;
import Clases.Models.User;
import Clases.Models.CurrentUser;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateUser {

    public void createUser(User user){

        try {
            String sqlQuery = "CREATE USER '?'@'%' IDENTIFIED BY '?';";
            DBConnection JDBDBConnection = new DBConnection(CurrentUser.getCurrentUser().getName(), CurrentUser.getCurrentUser().getPassword());
            Connection connection = JDBDBConnection.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate( "CREATE USER '"+ user.getName()+"'@'%' IDENTIFIED BY '"+ user.getPassword()+"';");

        }catch (SQLException e){
            e.getErrorCode();
        }
    }

    public void setPrivileges(User user){
        String sqlQuery = "";
        try {
            DBConnection JDBDBConnection = new DBConnection(CurrentUser.getCurrentUser().getName(), CurrentUser.getCurrentUser().getPassword());
            Connection connection = JDBDBConnection.getConnection();
            if (user.getPrivileges() == 1) {
                sqlQuery = "GRANT ALL PRIVILEGES ON *.* TO '"+ user.getName()+"'@'%' IDENTIFIED BY '"+ user.getPassword()+"'";
            }
            if (user.getPrivileges() == 2) {
                sqlQuery = "GRANT DELETE, INSERT, UPDATE, SELECT ON *.* TO '"+ user.getName()+"'@'%' IDENTIFIED BY '"+ user.getPassword()+"'";
            }

          Statement statement = connection.createStatement();
          statement.executeUpdate(sqlQuery);
          statement.executeUpdate("FLUSH PRIVILEGES;");
        }catch (SQLException e){
            e.getErrorCode();
        }
    }
}
