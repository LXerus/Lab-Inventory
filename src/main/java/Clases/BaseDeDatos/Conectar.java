package Clases.BaseDeDatos;

import Clases.Modelos.Configuracion;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conectar {
    private Connection connection;
    private static String puerto = "";
    private static String servidor= "";
    private static String baseDeDatos = "";
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static String URL;

    public Conectar(String userName, String password){
       //Codigo para esteblecer la conexion con la base de datos con los datos del usuario actualmente en uso del programa.
        servidor = Configuracion.getServidor();
        puerto = Configuracion.getPuerto();
        baseDeDatos = Configuracion.getBaseDeDatos();

        try{
            URL ="jdbc:mysql://"+servidor+":"+puerto+"/"+baseDeDatos+"?verifyServerCertificate=false&useSSL=true";
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

    public  void desconectar(){
        connection = null;
    }

    public static String getPuerto() {
        return puerto;
    }

    public static void setPuerto(String puerto) {
        Conectar.puerto = puerto;
    }

    public static String getURL() {
        return URL;
    }

    public static void setURL(String URL) {
        Conectar.URL = URL;
    }
}
