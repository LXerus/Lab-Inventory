package Clases.Cruds;

import Clases.BaseDeDatos.DBConnection;
import Clases.Models.UserActivity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConfigurationCrud {
    DBConnection JDBDBConnection = null;
    Connection ConexionSQL = null;
    Statement configuracionStatement = null;
    PreparedStatement configuracionPreparedStatement = null;
    ResultSet configuracionResultSet = null;
    String sqlQuery = "";
    UserActivity actividad = new UserActivity();


}
