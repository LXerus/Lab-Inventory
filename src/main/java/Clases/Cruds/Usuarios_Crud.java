package Clases.Cruds;

import Clases.BaseDeDatos.Conectar;
import Clases.Modelos.Usuario;
import Clases.Modelos.UsuarioActividad;
import Clases.Modelos.UsuarioActual;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class Usuarios_Crud {
    Conectar conectar;
    Connection sqlConnection;
    PreparedStatement usuarioPreparedStatement;
    UsuarioActividad actividad;
    LocalDate fecha;
    LocalTime hora;
    LogActivades_Crud log;


    public void registrarUsuario(Usuario usuario){
        conectar = new Conectar(UsuarioActual.getUsuarioActual().getNombres(), UsuarioActual.getUsuarioActual().getPassword());
        sqlConnection = conectar.getConnection();
        String instruccionSQL = "INSERT INTO usuario (nombres, apellidos, password, fecha_de_ingreso, area, activo, correo_electronico, id_privilegios) "+
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            usuarioPreparedStatement = sqlConnection.prepareStatement(instruccionSQL);
            usuarioPreparedStatement.setString(1, usuario.getNombres());
            usuarioPreparedStatement.setString(2, usuario.getApellidos());
            usuarioPreparedStatement.setString(3, usuario.getPassword());
            usuarioPreparedStatement.setDate(4, Date.valueOf(usuario.getFecha_de_ingreso()));
            usuarioPreparedStatement.setString(5, usuario.getArea());
            usuarioPreparedStatement.setBoolean(6, usuario.isActivo());
            usuarioPreparedStatement.setString(7, usuario.getCorreo_electronico());
            usuarioPreparedStatement.setInt(8, usuario.getPrivilegios());
            usuarioPreparedStatement.executeUpdate();

            log = new LogActivades_Crud();
            fecha = LocalDate.now();
            hora = LocalTime.now();
            actividad = new UsuarioActividad(
                    UsuarioActual.getUsuarioActual().getId(),
                    UsuarioActual.getUsuarioActual().getNombres(),
                    UsuarioActual.getUsuarioActual().getApellidos(),
                    UsuarioActual.getUsuarioActual().getCorreo_electronico(),
                    "Registro de nuevo usuario",
                    "Usuario",
                    fecha,
                    hora
            );
            log.registrarActividad(actividad);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            usuarioPreparedStatement = null;
            sqlConnection = null;
            conectar.desconectar();
        }
    }

    public ObservableList<Usuario> buscarUsuario(Usuario usuario){
        //Retorna una lista observable con la lista de usuario dependiendo de los parametros recibidos.
        ObservableList<Usuario> listaDeUsuarios = FXCollections.observableArrayList();
        Statement usuarioStatement = null;
        ResultSet usuarioResultset = null;

        String sqlInstruction = "SELECT id, nombres, password, apellidos, fecha_de_ingreso, area, activo, correo_electronico, id_privilegios FROM usuario WHERE";
        if(!usuario.getNombres().isEmpty()){
            sqlInstruction += " nombres LIKE '%"+usuario.getNombres()+"%' AND ";
        }
        if(!usuario.getApellidos().isEmpty()){
            sqlInstruction += " apellidos LIKE '%"+usuario.getApellidos()+"%' AND ";
        }
        if(!usuario.getCorreo_electronico().isEmpty()){
            sqlInstruction += " correo_electronico LIKE '%"+usuario.getCorreo_electronico()+"%' AND ";
        }
        if(!usuario.getArea().isEmpty()){
            sqlInstruction += " area LIKE '%"+usuario.getArea()+"%' AND ";
        }
        if(usuario.getPrivilegios() != 0){
            sqlInstruction += " id_privilegios="+usuario.getPrivilegios()+" AND ";
        }

        char[] stringToArray = sqlInstruction.toCharArray();
        String limpiarDatos = "";

        //Algoritno para limpiar la sintaxis del query para sql
        for(int i = 0;i < stringToArray.length-4; i ++){
            limpiarDatos = limpiarDatos + stringToArray[i];
        }

        sqlInstruction = limpiarDatos;

        try{
            conectar = new Conectar(UsuarioActual.getUsuarioActual().getNombres(), UsuarioActual.getUsuarioActual().getPassword());
            sqlConnection  = conectar.getConnection();
            usuarioStatement = sqlConnection.createStatement();
            usuarioResultset = usuarioStatement.executeQuery(sqlInstruction);

             while (usuarioResultset.next()){
                 int id = usuarioResultset.getInt(1);
                 String nombre = usuarioResultset.getString(2);
                 String apellidos = usuarioResultset.getString(3);
                 String password = usuarioResultset.getString(4);
                 LocalDate fechaIngreso = usuarioResultset.getDate(5).toLocalDate();
                 String area = usuarioResultset.getString(6);
                 boolean activo = usuarioResultset.getBoolean(7);
                 String email = usuarioResultset.getString(8);
                 int id_privilegio = usuarioResultset.getInt(9);

                 listaDeUsuarios.add(new Usuario(id, nombre, password, apellidos, fechaIngreso, area, activo, email, id_privilegio));
             }

            log = new LogActivades_Crud();
            fecha = LocalDate.now();
            hora = LocalTime.now();
            actividad = new UsuarioActividad(
                    UsuarioActual.getUsuarioActual().getId(),
                    UsuarioActual.getUsuarioActual().getNombres(),
                    UsuarioActual.getUsuarioActual().getApellidos(),
                    UsuarioActual.getUsuarioActual().getCorreo_electronico(),
                    "Busqueda de Usuario",
                    "Usuario",
                    fecha,
                    hora
            );
            log.registrarActividad(actividad);
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
                if(sqlConnection != null){
                    sqlConnection.close();
                }
                conectar.desconectar();
            }catch (SQLException ex){
                ex.printStackTrace();
            }
        }
        return listaDeUsuarios;
    }

    public void actualizarUsuario(Usuario usuario){
        String sqlQuery = "UPDATE usuario SET nombres =? , apellidos=?, password=?, fecha_de_ingreso=?, area=?, activo=?, correo_electronico=?, id_privilegios=? WHERE id="+usuario.getId();
        Statement privilegiosActualizar = null;
        usuarioPreparedStatement = null;
        try{
            conectar = new Conectar(UsuarioActual.getUsuarioActual().getNombres(), UsuarioActual.getUsuarioActual().getPassword());
            sqlConnection = conectar.getConnection();
            usuarioPreparedStatement = sqlConnection.prepareStatement(sqlQuery);
            usuarioPreparedStatement.setString(1, usuario.getNombres());
            usuarioPreparedStatement.setString(2, usuario.getApellidos());
            usuarioPreparedStatement.setString(3, usuario.getPassword());
            usuarioPreparedStatement.setDate(4, Date.valueOf(usuario.getFecha_de_ingreso()));
            usuarioPreparedStatement.setString(5, usuario.getArea());
            usuarioPreparedStatement.setBoolean(6, usuario.isActivo());
            usuarioPreparedStatement.setString(7, usuario.getCorreo_electronico());
            usuarioPreparedStatement.setInt(8, usuario.getPrivilegios());
            usuarioPreparedStatement.executeUpdate();

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

            log = new LogActivades_Crud();
            fecha = LocalDate.now();
            hora = LocalTime.now();
            actividad = new UsuarioActividad(
                    UsuarioActual.getUsuarioActual().getId(),
                    UsuarioActual.getUsuarioActual().getNombres(),
                    UsuarioActual.getUsuarioActual().getApellidos(),
                    UsuarioActual.getUsuarioActual().getCorreo_electronico(),
                    "Actualizacion de Usuario",
                    "Usuario",
                    fecha,
                    hora
            );
            log.registrarActividad(actividad);
        }catch (SQLException ex){
            ex.printStackTrace();
            ex.getErrorCode();
        }finally {
            try{
                if(privilegiosActualizar != null){
                    privilegiosActualizar.close();
                }
                if(usuarioPreparedStatement != null){
                    usuarioPreparedStatement.close();
                }
                if(sqlConnection != null){
                    sqlConnection.close();
                }
                conectar.desconectar();
            }catch (SQLException ex){
                ex.printStackTrace();
            }
        }
    }

    public String obtenerPrivilegiosDeUsuario(Usuario usuario){
        String privilegio = "";
        String sqlInstruction = "SELECT tipo_de_privilegios FROM privilegios_de_usuario WHERE id=?";
        ResultSet usuarioResultSet = null;
        try {
            conectar = new Conectar(UsuarioActual.getUsuarioActual().getNombres(), UsuarioActual.getUsuarioActual().getPassword());
            sqlConnection = conectar.getConnection();
            usuarioPreparedStatement = sqlConnection.prepareStatement(sqlInstruction);
            usuarioPreparedStatement.setInt(1, usuario.getPrivilegios());
            usuarioResultSet = usuarioPreparedStatement.executeQuery();
            while (usuarioResultSet.next()){
                privilegio = usuarioResultSet.getString(1);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            try{
                if(usuarioResultSet != null){
                    usuarioResultSet.close();
                }
                if(usuarioPreparedStatement != null){
                    usuarioPreparedStatement.close();
                }
                if(sqlConnection != null){
                    sqlConnection.close();
                }
                conectar.desconectar();
            }catch (SQLException ex){
                ex.printStackTrace();
            }
        }
        return privilegio;
    }
}
