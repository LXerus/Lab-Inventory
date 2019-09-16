package Clases.Cruds;

import Clases.BaseDeDatos.Conectar;
import Clases.Modelos.Registro;
import Clases.Modelos.TipoDeProducto;
import Clases.Modelos.UsuarioActividad;
import Clases.Modelos.UsuarioActual;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class TipoDeProductos_Crud {
    Conectar conectar = null;
    Connection conexionSQL = null;
    Statement statementTipoDeProductos = null;
    PreparedStatement preparedStatementTipoDeProductos = null;
    ResultSet resultSetTipoDeProductos = null;
    String sqlQuery = "";
    UsuarioActividad actividad;
    LocalDate fecha;
    LocalTime hora;
    LogActivades_Crud log;

    public void nuevoTipoDeProducto(TipoDeProducto tipoDeProducto){
        sqlQuery ="INSERT INTO tipo_producto(tipo_de_producto, descripcion) VALUES(?,?);";
        try{
            conectar = new Conectar(UsuarioActual.getUsuarioActual().getNombres(),UsuarioActual.getUsuarioActual().getPassword());
            conexionSQL = conectar.getConnection();
            preparedStatementTipoDeProductos = conexionSQL.prepareStatement(sqlQuery);
            preparedStatementTipoDeProductos.setString(1,tipoDeProducto.getTipoDeProducto());
            preparedStatementTipoDeProductos.setString(2, tipoDeProducto.getDescripcion());
            preparedStatementTipoDeProductos.executeUpdate();

            log = new LogActivades_Crud();
            fecha = LocalDate.now();
            hora = LocalTime.now();
            actividad = new UsuarioActividad(
                    UsuarioActual.getUsuarioActual().getId(),
                    UsuarioActual.getUsuarioActual().getNombres(),
                    UsuarioActual.getUsuarioActual().getApellidos(),
                    UsuarioActual.getUsuarioActual().getCorreo_electronico(),
                    "Nuevo Tipo de Producto.",
                    "Tipo de Producto.",
                    fecha,
                    hora
            );
            log.registrarActividad(actividad);
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            try {
                if (preparedStatementTipoDeProductos != null) {
                    preparedStatementTipoDeProductos.close();
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

    public void borrarTipoDeProducto(TipoDeProducto tipoDeProducto){
        sqlQuery = "DELETE FROM tipo_producto WHERE id=?";
        try {
            conectar = new Conectar(UsuarioActual.getUsuarioActual().getNombres(), UsuarioActual.getUsuarioActual().getPassword());
            conexionSQL = conectar.getConnection();
            preparedStatementTipoDeProductos = conexionSQL.prepareStatement(sqlQuery);
            preparedStatementTipoDeProductos.setInt(1, tipoDeProducto.getId());
            preparedStatementTipoDeProductos.executeUpdate();

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
                if (preparedStatementTipoDeProductos != null) {
                    preparedStatementTipoDeProductos.close();
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

    public ObservableList<TipoDeProducto> buscarTipoDeProducto(TipoDeProducto tipoDeProducto){
        ObservableList<TipoDeProducto> listaTipoDeProducto = FXCollections.observableArrayList();
        sqlQuery = "SELECT id, tipo_de_producto, descripcion FROM tipo_producto WHERE ";
        if(tipoDeProducto.getId() != 0){
            sqlQuery += " id = "+tipoDeProducto.getId()+" AND ";
        }
        if(!tipoDeProducto.getTipoDeProducto().isEmpty()){
            sqlQuery += " tipo_de_producto LIKE '%"+tipoDeProducto.getTipoDeProducto()+"%' AND ";
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
            statementTipoDeProductos = conexionSQL.createStatement();
            resultSetTipoDeProductos = statementTipoDeProductos.executeQuery(sqlQuery);

            while(resultSetTipoDeProductos.next()){
                int id = resultSetTipoDeProductos.getInt("id");
                String tipo_de_producto = resultSetTipoDeProductos.getString("tipo_de_producto");
                String descripcion = resultSetTipoDeProductos.getString("descripcion");

                listaTipoDeProducto.add(new TipoDeProducto(id, tipo_de_producto, descripcion));
            }

        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            try {
                if (resultSetTipoDeProductos != null) {
                    resultSetTipoDeProductos.close();
                }
                if (preparedStatementTipoDeProductos != null) {
                    preparedStatementTipoDeProductos.close();
                }
                if (conexionSQL != null) {
                    conexionSQL.close();
                }
                conectar.desconectar();
            }catch (SQLException e){
                e.printStackTrace();
            }
            return listaTipoDeProducto;
        }
    }

    public void actualizarTipoDeProducto(TipoDeProducto tipoDeProducto){
        sqlQuery = "UPDATE tipo_producto SET tipo_de_producto = ?, descripcion = ? WHERE id = ?";
        try{
            conectar = new Conectar(UsuarioActual.getUsuarioActual().getNombres(), UsuarioActual.getUsuarioActual().getPassword());
            conexionSQL = conectar.getConnection();
            preparedStatementTipoDeProductos = conexionSQL.prepareStatement(sqlQuery);
            preparedStatementTipoDeProductos.setString(1, tipoDeProducto.getTipoDeProducto());
            preparedStatementTipoDeProductos.setString(2, tipoDeProducto.getDescripcion());
            preparedStatementTipoDeProductos.setInt(3, tipoDeProducto.getId());
            preparedStatementTipoDeProductos.executeUpdate();

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
                if (preparedStatementTipoDeProductos != null) {
                    preparedStatementTipoDeProductos.close();
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
