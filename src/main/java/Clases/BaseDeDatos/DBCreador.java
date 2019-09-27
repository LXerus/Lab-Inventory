package Clases.BaseDeDatos;

import Clases.Modelos.Peresentation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.*;
import java.sql.*;

public class DBCreador {
    private Connection connection = null;
    private ResultSet resultSet = null;
    private String DBNombre = null;
    private String sqlQuery = null;
    private Statement statement = null;
    private String url = "jdbc:mysql://localhost:3306/";
    private String url2 = "jdbc:mysql://localhost:3306/?verifyServerCertificate=false&useSSL=true";
    private String user = "";
    private String password = "";


    public DBCreador(String DBNombre, String user, String password) {
        this.DBNombre = DBNombre;
        this.user = user;
        this.password = password;
        url += DBNombre+"?verifyServerCertificate=false&useSSL=true";
    }

    public void createDataBase(){
      /*  if(comprobarExistencia()){
            JOptionPane.showMessageDialog(null, "La base de datos ya existe");
        }else{
            try{
                sqlQuery = "CREATE DATABASE "+DBNombre;
                JDBConnection = DriverManager.getConnection(url, user, password);
                DBStatement = JDBConnection.createStatement();
                DBStatement.executeUpdate(sqlQuery);
                JOptionPane.showMessageDialog(null, "Base de datos creada exitosamente!");
            }catch (SQLException e){
                e.printStackTrace();
            }finally {
                if (DBStatement != null){
                    try{
                        DBStatement.close();
                    }catch (SQLException e){
                        e.printStackTrace();
                    }
                }
                if (JDBConnection != null){
                    try{
                        JDBConnection.close();
                    }catch (SQLException e){
                        e.printStackTrace();
                    }
                }

                sqlQuery = null;
            }
        }*/

        crearTablaPresentaciones();
        crearTablaBodega();
        crearTablaProveedores();
        crearTablaUsuarios();
        crearTablaProductos();
    }

    private boolean comprobarExistencia(){
        boolean existe = false;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url2, user, password);

            if(connection != null){
                resultSet = connection.getMetaData().getCatalogs();

                while (resultSet.next()) {
                    String cataLog = resultSet.getString(1);
                    if (DBNombre.equals(cataLog)) {
                        existe = true;
                    }else{
                        existe = false;
                    }
                }
            }else{
                JOptionPane.showMessageDialog(null, "No se pudo establecer la conexion con la base de datos.");
            }

        }catch (ClassNotFoundException |SQLException ex) {
            ex.printStackTrace();
        }finally {
            if(resultSet != null){
                try{
                    resultSet.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (connection != null){
                try{
                    connection.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            sqlQuery = null;
        }
        return existe;
    }

    private void crearTablaPresentaciones(){
        sqlQuery = "create table presentaciones(id int(11) not null auto_increment, " +
                "presentacion varchar(255) not null, " +
                "unidad_medida varcahr(255) not null, " +
                "constraint pk_id_presentacion primary key(id))";
        try{
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
            statement.executeUpdate(sqlQuery);
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            if(statement != null){
                try{
                    statement.close();
                }catch (SQLException ex){
                    ex.printStackTrace();
                }
            }
            if (connection != null){
                try{
                    connection.close();
                }catch (SQLException ex){
                    ex.printStackTrace();
                }
            }
            sqlQuery = null;
        }

        sqlQuery = "INSERT INTO presentaciones(presentacion, unidad_medida) VALUES (?,?)";
        try{
            connection = DriverManager.getConnection(url, user, password);
            ObservableList<Peresentation> listaPresentaciones = FXCollections.observableArrayList();
            listaPresentaciones.addAll(new Peresentation("mg", "Masa"), new Peresentation("g", "Masa"),
                                        new Peresentation("kg","Masa"), new Peresentation("litro","Volumen"));
            for(int i = 0; i <listaPresentaciones.size();i++){
                PreparedStatement DBPreparedStatement = connection.prepareStatement(sqlQuery);
                DBPreparedStatement.setString(1, listaPresentaciones.get(i).getPresentation());
                DBPreparedStatement.setString(2, listaPresentaciones.get(i).getMeasurementUnit());
                DBPreparedStatement.execute();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            if(statement != null){
                try{
                    statement.close();
                }catch (SQLException ex){
                    ex.printStackTrace();
                }
            }
            if (connection != null){
                try{
                    connection.close();
                }catch (SQLException ex){
                    ex.printStackTrace();
                }
            }
            sqlQuery = null;
        }
    }

    private void crearTablaBodega(){
        sqlQuery = "CREATE TABLE bodegas " +
                "(id int unsigned not null auto_increment, " +
                "nombre varchar(255) not null, " +
                "condicion varchar(255) not null, " +
                "region varchar(255) not null, " +
                "tramo varchar(255) not null, " +
                "constraint pk_bodegas primary key(id))";
        try{
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
            statement.executeUpdate(sqlQuery);
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            if(statement != null){
                try{
                    statement.close();
                }catch (SQLException ex){
                    ex.printStackTrace();
                }
            }
            if (connection != null){
                try{
                    connection.close();
                }catch (SQLException ex){
                    ex.printStackTrace();
                }
            }
            sqlQuery = null;
        }
    }

    private void crearTablaProveedores(){
        sqlQuery = "CREATE TABLE proveedores " +
                "(id int(11) usigned not null auto_increment, " +
                "nombre varchar(255) not null, " +
                "telefono varchar(255) not null, " +
                "contacto varchar(255) not null, " +
                "codigo_de_proveedor varchar(255) null, " +
                "servicio varchar(255) not null" +
                "critico tinyint(4) not null, " +
                "aprobado tinyint(4) not null, " +
                "punteo int(11) not null, " +
                "fecha_aprobacion date not null, " +
                "fecha_revalidacion date not null, " +
                "constraint pk_proveedores primary key(id))";
        try{
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
            statement.executeUpdate(sqlQuery);
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            if(statement != null){
                try{
                    statement.close();
                }catch (SQLException ex){
                    ex.printStackTrace();
                }
            }
            if (connection != null){
                try{
                    connection.close();
                }catch (SQLException ex){
                    ex.printStackTrace();
                }
            }
            sqlQuery = null;
        }
    }

    private void crearTablaUsuarios(){
        sqlQuery = "CREATE TABLE usuarios " +
                "(id int(11) not null auto_increment, " +
                "nombres varchar(255) not null, " +
                "apellidos varchar(255) not null, " +
                "password varchar(255) not null, " +
                "fecha_de_ingreso date not null, " +
                "area varchar(255) not null" +
                "activo tinyint(4) not null, " +
                "correo_electronico varchar(255) not null, " +
                "id_privilegios int(11) not null, " +
                "constraint pk_usuarios primary key(id))";
        try{
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
            statement.executeUpdate(sqlQuery);
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            if(statement != null){
                try{
                    statement.close();
                }catch (SQLException ex){
                    ex.printStackTrace();
                }
            }
            if (connection != null){
                try{
                    connection.close();
                }catch (SQLException ex){
                    ex.printStackTrace();
                }
            }
            sqlQuery = null;
        }
    }

    private void crearTablaProductos(){
        sqlQuery = "CREATE TABLE productos " +
                "(id int(11) not null auto_increment, " +
                "marca varchar(255) not null, " +
                "cas varchar(255) not null, " +
                "codigo_interno varchar(255) not null," +
                "codigo_standard varchar(255) not null," +
                "lote varchar(255) not null," +
                "fecha_ingreso date not null," +
                "fecha_vence date not null," +
                "fecha_abierto date not null," +
                "id_presentacion int(11) not null," +
                "stock double not null," +
                "id_bodega int(11) not null," +
                "factura varchar(255) not null," +
                "costo double not null," +
                "costo_x_cantidad double null," +
                "id_proveedor int(11) not null," +
                "producto_controlado tinyint(4) not null," +
                "ghs int(11) not null," +
                "constraint pk_producto primary key(id)," +
                "foreing key(id_presentacion) references presentaciones(id)," +
                "foreing key(id_bodega) references bodega(id)," +
                "foreing key(id_proveedor) references proveedores(id))";
        try{
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
            statement.executeUpdate(sqlQuery);
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            if(statement != null){
                try{
                    statement.close();
                }catch (SQLException ex){
                    ex.printStackTrace();
                }
            }
            if (connection != null){
                try{
                    connection.close();
                }catch (SQLException ex){
                    ex.printStackTrace();
                }
            }
        }
        sqlQuery = null;
    }
}
