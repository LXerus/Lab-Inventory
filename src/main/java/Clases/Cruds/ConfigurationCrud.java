package Clases.Cruds;

import Clases.BaseDeDatos.JDBConnection;
import Clases.Models.UserActivity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;

public class ConfigurationCrud {
    JDBConnection JDBConnection = null;
    Connection ConexionSQL = null;
    Statement configuracionStatement = null;
    PreparedStatement configuracionPreparedStatement = null;
    ResultSet configuracionResultSet = null;
    String sqlQuery = "";
    UserActivity actividad = new UserActivity();


}
