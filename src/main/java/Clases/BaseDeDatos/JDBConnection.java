package Clases.BaseDeDatos;

import Clases.Models.Configuration;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBConnection {
    private Connection connection;
    private static String port = "";
    private static String server = "";
    private static String dataBase = "";
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static String URL;

    public JDBConnection(String userName, String password){
       //Codigo para esteblecer la conexion con la base de datos con los datos del usuario actualmente en uso del programa.
        server = Configuration.getServer();
        port = Configuration.getPort();
        dataBase = Configuration.getDataBase();

        try{
            URL ="jdbc:mysql://"+ server +":"+ port +"/"+ dataBase +"?verifyServerCertificate=false&useSSL=true";
            connection = DriverManager.getConnection(URL, userName, password);
            if(connection !=null){
                System.out.println("Conexion exitosa!");
            }
        }catch (SQLException ex){
          JOptionPane.showMessageDialog(null, "Error en la conexion, el usuario o contraseña pueden ser invalidos, contacte al administrador de su red.");
        }
    }

    public Connection getConnection(){
        return connection;
    }

    public  void disconnect(){
        connection = null;
    }

    public static String getPort() {
        return port;
    }

    public static void setPort(String port) {
        JDBConnection.port = port;
    }

    public static String getURL() {
        return URL;
    }

    public static void setURL(String URL) {
        JDBConnection.URL = URL;
    }
}
