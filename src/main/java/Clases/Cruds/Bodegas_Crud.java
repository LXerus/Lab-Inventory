package Clases.Cruds;

import Clases.BaseDeDatos.Conectar;
import Clases.Modelos.Bodega;
import Clases.Modelos.UsuarioActividad;
import Clases.Modelos.UsuarioActual;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class Bodegas_Crud {
    private Connection jdbConection;
    private Conectar conectar;
    private ObservableList<Bodega> listaDeBodegas;
    private PreparedStatement preparedStatementBodegas;
    UsuarioActividad actividad;
    LocalDate fecha;
    LocalTime hora;
    LogActivades_Crud log;



    public void nuevaBodega(Bodega bodega){
        String nombre = bodega.getNombre();
        String condicion = bodega.getCondicion();
        String region = bodega.getRegion();
        String tramo = bodega.getTramo();
        conectar = new Conectar(UsuarioActual.getUsuarioActual().getNombres(), UsuarioActual.getUsuarioActual().getPassword());

        try {
            String sqlInstruction = "INSERT INTO bodega(nombre, condicion, region, tramo) VALUES(?, ?, ?, ?)";
            jdbConection = conectar.getConnection();
            preparedStatementBodegas = jdbConection.prepareStatement(sqlInstruction);
            preparedStatementBodegas.setString(1, nombre);
            preparedStatementBodegas.setString(2, condicion);
            preparedStatementBodegas.setString(3, region);
            preparedStatementBodegas.setString(4, tramo);
            preparedStatementBodegas.executeUpdate();

            log = new LogActivades_Crud();
            fecha = LocalDate.now();
            hora = LocalTime.now();
            actividad = new UsuarioActividad(
                    UsuarioActual.getUsuarioActual().getId(),
                    UsuarioActual.getUsuarioActual().getNombres(),
                    UsuarioActual.getUsuarioActual().getApellidos(),
                    UsuarioActual.getUsuarioActual().getCorreo_electronico(),
                    "Registro de Bodega",
                    "Bodegas",
                    fecha,
                    hora
            );
            log.registrarActividad(actividad);

        }catch (SQLException e){
            e.printStackTrace();
        }finally{
            preparedStatementBodegas = null;
            jdbConection = null;
            conectar.desconectar();
        }
    }

 public ObservableList<Bodega> buscarBodega(String query) {
        ResultSet resultSetConsultarBodegas = null;
        listaDeBodegas = FXCollections.observableArrayList();
        conectar = new Conectar(UsuarioActual.getUsuarioActual().getNombres(), UsuarioActual.getUsuarioActual().getPassword());
        jdbConection = conectar.getConnection();
        try {
            Statement consultarBase = jdbConection.createStatement();
            resultSetConsultarBodegas = consultarBase.executeQuery(query);

            while (resultSetConsultarBodegas.next()) {
                int id_bodega = resultSetConsultarBodegas.getInt(1);
                String nombre_bodega = resultSetConsultarBodegas.getString(2);
                String condicion_bodega = resultSetConsultarBodegas.getString(3);
                String region_bodega = resultSetConsultarBodegas.getString(4);
                String tramo_bodega = resultSetConsultarBodegas.getString(5);
                listaDeBodegas.add(new Bodega(id_bodega, nombre_bodega, condicion_bodega, region_bodega, tramo_bodega));
            }
            log = new LogActivades_Crud();
            fecha = LocalDate.now();
            hora = LocalTime.now();
            actividad = new UsuarioActividad(
                    UsuarioActual.getUsuarioActual().getId(),
                    UsuarioActual.getUsuarioActual().getNombres(),
                    UsuarioActual.getUsuarioActual().getApellidos(),
                    UsuarioActual.getUsuarioActual().getCorreo_electronico(),
                    "Busqueda de Bodega",
                    "Bodegas",
                    fecha,
                    hora
            );
            log.registrarActividad(actividad);
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            resultSetConsultarBodegas = null;
            jdbConection = null;
            conectar.desconectar();
        }
     return listaDeBodegas;
    }

    public void actualizarDatos(Bodega bodega){
        String sqlInstruccion = "UPDATE bodega SET nombre='?', condicion='?', tramo='?', region='?' WHERE id="+bodega.getId();
        try {
            conectar = new Conectar(UsuarioActual.getUsuarioActual().getNombres(), UsuarioActual.getUsuarioActual().getPassword());
            jdbConection = conectar.getConnection();
            preparedStatementBodegas = jdbConection.prepareStatement(sqlInstruccion);
            preparedStatementBodegas.setString(1, bodega.getNombre());
            preparedStatementBodegas.setString(2, bodega.getCondicion());
            preparedStatementBodegas.setString(3, bodega.getTramo());
            preparedStatementBodegas.setString(4, bodega.getRegion());
            preparedStatementBodegas.executeUpdate();
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            try{
                if (preparedStatementBodegas != null){
                    preparedStatementBodegas.close();
                }
            }catch (SQLException ex){
                ex.printStackTrace();
            }

            try{
                if(jdbConection != null){
                    jdbConection.close();
                }
            }catch (SQLException ex){
                ex.printStackTrace();
            }

            if(conectar != null){
                conectar.desconectar();
            }
        }
    }
}
