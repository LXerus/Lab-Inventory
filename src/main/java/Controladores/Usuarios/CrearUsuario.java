package Controladores.Usuarios;

import Clases.BaseDeDatos.Conectar;
import Clases.Modelos.Usuario;
import Clases.Modelos.UsuarioActual;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class CrearUsuario {

    public void crearUsuario(Usuario usuario){

        try {
            String sqlQuery = "CREATE USER '?'@'%' IDENTIFIED BY '?';";
            Conectar conectar = new Conectar(UsuarioActual.getUsuarioActual().getNombres(), UsuarioActual.getUsuarioActual().getPassword());
            Connection sqlConnection = conectar.getConnection();

            Statement statement = sqlConnection.createStatement();
            statement.executeUpdate( "CREATE USER '"+usuario.getNombres()+"'@'%' IDENTIFIED BY '"+usuario.getPassword()+"';");

        }catch (SQLException e){
            e.getErrorCode();
        }
    }

    public void establecerPrivilegios(Usuario usuario){
        String sqlQuery = "";
        try {
            Conectar conectar = new Conectar(UsuarioActual.getUsuarioActual().getNombres(), UsuarioActual.getUsuarioActual().getPassword());
            Connection sqlConnection = conectar.getConnection();
            if (usuario.getPrivilegios() == 1) {
                sqlQuery = "GRANT ALL PRIVILEGES ON *.* TO '"+usuario.getNombres()+"'@'%' IDENTIFIED BY '"+usuario.getPassword()+"'";
            }
            if (usuario.getPrivilegios() == 2) {
                sqlQuery = "GRANT DELETE, INSERT, UPDATE, SELECT ON *.* TO '"+usuario.getNombres()+"'@'%' IDENTIFIED BY '"+usuario.getPassword()+"'";
            }

          Statement statement = sqlConnection.createStatement();
          statement.executeUpdate(sqlQuery);
          statement.executeUpdate("FLUSH PRIVILEGES;");
        }catch (SQLException e){
            e.getErrorCode();
        }
    }
}
