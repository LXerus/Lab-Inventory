package Clases.Cruds;

import Clases.BaseDeDatos.JDBConnection;
import Clases.Modelos.Cellar;
import Clases.Modelos.UserActivity;
import Clases.Modelos.CurrentUser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class CellarCrud {
    private Connection connection;
    private JDBConnection JDBConnection;
    private ObservableList<Cellar> cellarList;
    private PreparedStatement preparedStatementCellar;
    UserActivity activity;
    LocalDate Date;
    LocalTime time;
    ActivityLogCrud log;


    public void create(Cellar cellar){
        String nombre = cellar.getName();
        String condicion = cellar.getCondition();
        String region = cellar.getRegion();
        String tramo = cellar.getSection();
        JDBConnection = new JDBConnection(CurrentUser.getCurrentUser().getName(), CurrentUser.getCurrentUser().getPassword());

        try {
            String sqlInstruction = "INSERT INTO bodega(nombre, condicion, region, tramo) VALUES(?, ?, ?, ?)";
            connection = JDBConnection.getConnection();
            preparedStatementCellar = connection.prepareStatement(sqlInstruction);
            preparedStatementCellar.setString(1, nombre);
            preparedStatementCellar.setString(2, condicion);
            preparedStatementCellar.setString(3, region);
            preparedStatementCellar.setString(4, tramo);
            preparedStatementCellar.executeUpdate();

            log = new ActivityLogCrud();
            Date = LocalDate.now();
            time = LocalTime.now();
            activity = new UserActivity(
                    CurrentUser.getCurrentUser().getId(),
                    CurrentUser.getCurrentUser().getName(),
                    CurrentUser.getCurrentUser().getLastName(),
                    CurrentUser.getCurrentUser().getEmail(),
                    "Registro de Bodega",
                    "Bodegas",
                    Date,
                    time
            );
            log.registrarActividad(activity);

        }catch (SQLException e){
            e.printStackTrace();
        }finally{
            if(preparedStatementCellar != null){
                preparedStatementCellar = null;
            }
            if (connection != null){
                connection = null;
            }
            JDBConnection.disconnect();
        }
    }

 public ObservableList<Cellar> read(String query) {
        ResultSet resultSetConsultarBodegas = null;
        cellarList = FXCollections.observableArrayList();
        JDBConnection = new JDBConnection(CurrentUser.getCurrentUser().getName(), CurrentUser.getCurrentUser().getPassword());
        connection = JDBConnection.getConnection();
        try {
            Statement consultarBase = connection.createStatement();
            resultSetConsultarBodegas = consultarBase.executeQuery(query);

            while (resultSetConsultarBodegas.next()) {
                int id_bodega = resultSetConsultarBodegas.getInt(1);
                String name = resultSetConsultarBodegas.getString(2);
                String condition = resultSetConsultarBodegas.getString(3);
                String region = resultSetConsultarBodegas.getString(4);
                String section = resultSetConsultarBodegas.getString(5);
                cellarList.add(new Cellar(id_bodega, name, condition, region, section));
            }
            log = new ActivityLogCrud();
            Date = LocalDate.now();
            time = LocalTime.now();
            activity = new UserActivity(
                    CurrentUser.getCurrentUser().getId(),
                    CurrentUser.getCurrentUser().getName(),
                    CurrentUser.getCurrentUser().getLastName(),
                    CurrentUser.getCurrentUser().getEmail(),
                    "Busqueda de Bodega",
                    "Bodegas",
                    Date,
                    time
            );
            log.registrarActividad(activity);
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            if(resultSetConsultarBodegas != null){
                resultSetConsultarBodegas = null;
            }
            if (connection != null){
                connection = null;
            }
            JDBConnection.disconnect();
        }
     return cellarList;
    }

    public void update(Cellar cellar){
        String sqlInstruccion = "UPDATE bodega SET nombre='?', condicion='?', tramo='?', region='?' WHERE id="+ cellar.getId();
        try {
            JDBConnection = new JDBConnection(CurrentUser.getCurrentUser().getName(), CurrentUser.getCurrentUser().getPassword());
            connection = JDBConnection.getConnection();
            preparedStatementCellar = connection.prepareStatement(sqlInstruccion);
            preparedStatementCellar.setString(1, cellar.getName());
            preparedStatementCellar.setString(2, cellar.getCondition());
            preparedStatementCellar.setString(3, cellar.getSection());
            preparedStatementCellar.setString(4, cellar.getRegion());
            preparedStatementCellar.executeUpdate();
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            try{
                if (preparedStatementCellar != null){
                    preparedStatementCellar.close();
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
