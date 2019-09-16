package Clases.Cruds;

import Clases.BaseDeDatos.Conectar;
import Clases.Modelos.UsuarioActividad;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;

public class Configuracion_Crud {
    Conectar conectar = null;
    Connection ConexionSQL = null;
    Statement configuracionStatement = null;
    PreparedStatement configuracionPreparedStatement = null;
    ResultSet configuracionResultSet = null;
    String sqlQuery = "";
    UsuarioActividad actividad;
    LocalDate fecha;
    LocalTime hora;
    LogActivades_Crud log;


}
