package Clases.Cruds;

import Clases.BaseDeDatos.Conectar;
import Clases.Modelos.Registro;
import Clases.Modelos.UsuarioActividad;
import Clases.Modelos.UsuarioActual;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class Registro_Crud {
    Conectar conectar = null;
    Connection conexionSQL = null;
    Statement  statementRegistro = null;
    PreparedStatement preparedStatementRegistro = null;
    ResultSet resultSetRegistro = null;
    String sqlQuery = "";
    UsuarioActividad actividad;
    LocalDate fecha;
    LocalTime hora;
    LogActivades_Crud log;

    public void nuevoRegistro(Registro registro){
        sqlQuery ="INSERT INTO registro(nombre, descripcion) VALUES(?,?);";
        try{
            conectar = new Conectar(UsuarioActual.getUsuarioActual().getNombres(),UsuarioActual.getUsuarioActual().getPassword());
            conexionSQL = conectar.getConnection();
            preparedStatementRegistro = conexionSQL.prepareStatement(sqlQuery);
            preparedStatementRegistro.setString(1,registro.getNombre());
            preparedStatementRegistro.setString(2, registro.getDescripcion());
            preparedStatementRegistro.executeUpdate();

            log = new LogActivades_Crud();
            fecha = LocalDate.now();
            hora = LocalTime.now();
            actividad = new UsuarioActividad(
                    UsuarioActual.getUsuarioActual().getId(),
                    UsuarioActual.getUsuarioActual().getNombres(),
                    UsuarioActual.getUsuarioActual().getApellidos(),
                    UsuarioActual.getUsuarioActual().getCorreo_electronico(),
                    "Nuevo registro",
                    "Registro.",
                    fecha,
                    hora
            );
            log.registrarActividad(actividad);
        }catch (SQLException ex){
           ex.printStackTrace();
        }finally {
            try {
                if (preparedStatementRegistro != null) {
                    preparedStatementRegistro.close();
                }
                if (conexionSQL != null) {
                    conexionSQL.close();
                }
                conectar.desconectar();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    public void borrarRegistro(Registro registro){
        sqlQuery = "DELETE FROM registro WHERE id=?";
        try {
            conectar = new Conectar(UsuarioActual.getUsuarioActual().getNombres(), UsuarioActual.getUsuarioActual().getPassword());
            conexionSQL = conectar.getConnection();
            preparedStatementRegistro = conexionSQL.prepareStatement(sqlQuery);
            preparedStatementRegistro.setInt(1, registro.getId());
            preparedStatementRegistro.executeUpdate();

            log = new LogActivades_Crud();
            fecha = LocalDate.now();
            hora = LocalTime.now();
            actividad = new UsuarioActividad(
                    UsuarioActual.getUsuarioActual().getId(),
                    UsuarioActual.getUsuarioActual().getNombres(),
                    UsuarioActual.getUsuarioActual().getApellidos(),
                    UsuarioActual.getUsuarioActual().getCorreo_electronico(),
                    "Borrar registro",
                    "Registro.",
                    fecha,
                    hora
            );
            log.registrarActividad(actividad);
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            try {
                if (preparedStatementRegistro != null) {
                    preparedStatementRegistro.close();
                }
                if (conexionSQL != null) {
                    conexionSQL.close();
                }
                conectar.desconectar();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    public ObservableList<Registro> buscarRegistro(Registro registro){
        ObservableList<Registro> listaDeRegistros = FXCollections.observableArrayList();
        sqlQuery = "SELECT id, nombre, descripcion FROM registro WHERE ";
        if(registro.getId() != 0){
            sqlQuery += " id = "+registro.getId()+" AND ";
        }
        if(!registro.getNombre().isEmpty()){
            sqlQuery += " nombre LIKE '%"+registro.getNombre()+"%' AND ";
        }

        //Corrigiendo la sentencia SQL
        char[] stringToArray = sqlQuery.toCharArray();
        String limpiarDatos = "";

        for(int i = 0;i < stringToArray.length-4; i ++){
            limpiarDatos = limpiarDatos + stringToArray[i];
        }

        sqlQuery = limpiarDatos;

        try{
            conectar = new Conectar(UsuarioActual.getUsuarioActual().getNombres(), UsuarioActual.getUsuarioActual().getPassword());
            conexionSQL = conectar.getConnection();
            statementRegistro = conexionSQL.createStatement();
            resultSetRegistro = statementRegistro.executeQuery(sqlQuery);

            while(resultSetRegistro.next()){
                int id = resultSetRegistro.getInt("id");
                String nombre = resultSetRegistro.getString("nombre");
                String descripcion = resultSetRegistro.getString("descripcion");

                listaDeRegistros.add(new Registro(id, nombre, descripcion));
            }

        }catch (SQLException ex){
            ex.printStackTrace();
         }finally {
            try {
                if (resultSetRegistro != null) {
                    resultSetRegistro.close();
                }
                if (preparedStatementRegistro != null) {
                    preparedStatementRegistro.close();
                }
                if (conexionSQL != null) {
                    conexionSQL.close();
                }
                conectar.desconectar();
            }catch (SQLException e){
                e.printStackTrace();
            }
        return listaDeRegistros;
        }
    }

    public void actualizarRegistro(Registro registro){
        sqlQuery = "UPDATE registro SET nombre = ?, descripcion = ? WHERE id = ?";
        try{
            conectar = new Conectar(UsuarioActual.getUsuarioActual().getNombres(), UsuarioActual.getUsuarioActual().getPassword());
            conexionSQL = conectar.getConnection();
            preparedStatementRegistro = conexionSQL.prepareStatement(sqlQuery);
            preparedStatementRegistro.setString(1, registro.getNombre());
            preparedStatementRegistro.setString(2, registro.getDescripcion());
            preparedStatementRegistro.setInt(3, registro.getId());
            preparedStatementRegistro.executeUpdate();

            log = new LogActivades_Crud();
            fecha = LocalDate.now();
            hora = LocalTime.now();
            actividad = new UsuarioActividad(
                    UsuarioActual.getUsuarioActual().getId(),
                    UsuarioActual.getUsuarioActual().getNombres(),
                    UsuarioActual.getUsuarioActual().getApellidos(),
                    UsuarioActual.getUsuarioActual().getCorreo_electronico(),
                    "Actualizar registro",
                    "Registro.",
                    fecha,
                    hora
            );
            log.registrarActividad(actividad);
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            try{
            if (preparedStatementRegistro != null) {
                preparedStatementRegistro.close();
            }
            if (conexionSQL != null) {
                conexionSQL.close();
            }
            conectar.desconectar();
        }catch (SQLException e){
            e.printStackTrace();
        }
        }
    }
}
