package Clases.Cruds;

import Clases.BaseDeDatos.Conectar;
import Clases.Modelos.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class Proveedores_Crud {
    private Connection jdbConection;
    private Conectar conectar;
    private ObservableList<Proveedor> listaDeProveedores;
    UsuarioActividad actividad;
    LocalDate fecha;
    LocalTime hora;
    LogActivades_Crud log;

    public Proveedores_Crud(){}

    //clase encargada para todas las conexiones con la base de datos para los datos de proveedores.

    public void nuevoProveedor(Proveedor proveedor){
        String nombre = proveedor.getNombre();
        String telefono = proveedor.getTelefono();
        String contacto = proveedor.getContacto();
        String codigo = proveedor.getCodigoDeProveedor();
        String servicios = proveedor.getServicio();
        boolean critico = proveedor.isCritico();
        boolean aprovado = proveedor.isAprobado();
        int punteo = proveedor.getPunteo();
        LocalDate fecha_de_aprovacion = proveedor.getFechaDeAprobacion();
        LocalDate fecha_de_revalidacion = proveedor.getFechaDeRevalidacion();

        conectar = new Conectar(UsuarioActual.getUsuarioActual().getNombres(), UsuarioActual.getUsuarioActual().getPassword());
        PreparedStatement preparedStatement = null;
        String instruccionSQL = "INSERT INTO proveedores (nombre, telefono, contacto, codigo_de_proveedor, servicio, critico, aprobado, punteo, fecha_aprobacion, fecha_revalidacion) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try{
            jdbConection = conectar.getConnection();
            preparedStatement = jdbConection.prepareStatement(instruccionSQL);
            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, telefono);
            preparedStatement.setString(3, contacto);
            preparedStatement.setString(4,codigo);
            preparedStatement.setString(5, servicios);
            preparedStatement.setBoolean(6, critico);
            preparedStatement.setBoolean(7, aprovado);
            preparedStatement.setDouble(8, punteo);
            preparedStatement.setDate(9, Date.valueOf(fecha_de_aprovacion));
            preparedStatement.setDate(10, Date.valueOf(fecha_de_revalidacion));
            preparedStatement.executeUpdate();

            log = new LogActivades_Crud();
            fecha = LocalDate.now();
            hora = LocalTime.now();
            actividad = new UsuarioActividad(
                    UsuarioActual.getUsuarioActual().getId(),
                    UsuarioActual.getUsuarioActual().getNombres(),
                    UsuarioActual.getUsuarioActual().getApellidos(),
                    UsuarioActual.getUsuarioActual().getCorreo_electronico(),
                    "Registro de proveedor",
                    "Proveedores",
                    fecha,
                    hora
            );
            log.registrarActividad(actividad);
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            preparedStatement = null;
            jdbConection = null;
         }
    }

    public ObservableList<Proveedor> buscarProveedor(Proveedor proveedor){
        listaDeProveedores = null;
        listaDeProveedores = FXCollections.observableArrayList();
        ResultSet resultSetConsultarProveedores = null;
        Statement consultarBase = null;
        String sqlQuery = "SELECT id, nombre, telefono, contacto, codigo_de_proveedor, servicio, critico, aprobado, punteo, fecha_aprobacion, fecha_revalidacion FROM proveedores WHERE ";
        if(!(proveedor.getCodigoDeProveedor().isEmpty())){
            sqlQuery += "codigo_de_proveedor LIKE '%"+proveedor.getCodigoDeProveedor()+"%' AND ";
        }
        if(!(proveedor.getNombre().isEmpty())){
            sqlQuery += "nombre LIKE '%"+proveedor.getNombre()+"%' AND ";
        }
        if(!(proveedor.getTelefono().isEmpty())){
            sqlQuery += "telefono LIKE '%"+proveedor.getTelefono()+"%' AND ";
        }
        if(!(proveedor.getContacto().isEmpty())){
            sqlQuery += "contacto LIKE '%"+proveedor.getContacto()+"%' AND ";
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
            jdbConection = conectar.getConnection();
            consultarBase = jdbConection.createStatement();
            resultSetConsultarProveedores = consultarBase.executeQuery(sqlQuery);

            while (resultSetConsultarProveedores.next()){
                int id = resultSetConsultarProveedores.getInt(1);
               String nombre = resultSetConsultarProveedores.getString(2);
               String telefono = resultSetConsultarProveedores.getString(3);
               String contacto = resultSetConsultarProveedores.getString(4);
               String codigo = resultSetConsultarProveedores.getString(5);
               String servicio = resultSetConsultarProveedores.getString(6);
               boolean critico = resultSetConsultarProveedores.getBoolean(7);
               boolean aprobado = resultSetConsultarProveedores.getBoolean(8);
               int punteo = resultSetConsultarProveedores.getInt(9);
               LocalDate fechaAprobacion = resultSetConsultarProveedores.getDate(10).toLocalDate();
               LocalDate fechaRevalidacion = resultSetConsultarProveedores.getDate(11).toLocalDate();
               listaDeProveedores.add(new Proveedor(id, nombre, telefono, contacto, codigo, servicio, punteo, critico, aprobado, fechaAprobacion, fechaRevalidacion));

                log = new LogActivades_Crud();
                fecha = LocalDate.now();
                hora = LocalTime.now();
                actividad = new UsuarioActividad(
                        UsuarioActual.getUsuarioActual().getId(),
                        UsuarioActual.getUsuarioActual().getNombres(),
                        UsuarioActual.getUsuarioActual().getApellidos(),
                        UsuarioActual.getUsuarioActual().getCorreo_electronico(),
                        "Busqueda de proveedores.",
                        "Proveedores.",
                        fecha,
                        hora
                );
                log.registrarActividad(actividad);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try{
                if(resultSetConsultarProveedores != null){
                    resultSetConsultarProveedores.close();
                }
            }catch (SQLException e){
               e.printStackTrace();
            }
            try{
                if(consultarBase != null){
                    consultarBase.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
            try{
                if(jdbConection != null){
                    jdbConection.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
            conectar.desconectar();
        }
        return listaDeProveedores;
    }

    public void actualizarProveedor(Proveedor proveedor){
        String sqlInstruccion = "UPDATE proveedores SET nombre=?, telefono=?, contacto=?, codigo_de_proveedor=?, servicio=?, critico=?, aprobado=?, punteo=?, fecha_aprobacion=?, fecha_revalidacion=? WHERE id="+proveedor.getId();
        PreparedStatement preparedStatementProveedor = null;
        try {
            conectar = new Conectar(UsuarioActual.getUsuarioActual().getNombres(), UsuarioActual.getUsuarioActual().getPassword());
            jdbConection = conectar.getConnection();
            preparedStatementProveedor = jdbConection.prepareStatement(sqlInstruccion);
            preparedStatementProveedor.setString(1, proveedor.getNombre());
            preparedStatementProveedor.setString(2, proveedor.getTelefono());
            preparedStatementProveedor.setString(3, proveedor.getContacto());
            preparedStatementProveedor.setString(4, proveedor.getCodigoDeProveedor());
            preparedStatementProveedor.setString(5, proveedor.getServicio());
            preparedStatementProveedor.setBoolean(6, proveedor.isCritico());
            preparedStatementProveedor.setBoolean(7, proveedor.isAprobado());
            preparedStatementProveedor.setInt(8, proveedor.getPunteo());
            preparedStatementProveedor.setDate(9, Date.valueOf(proveedor.getFechaDeAprobacion()));
            preparedStatementProveedor.setDate(10, Date.valueOf(proveedor.getFechaDeRevalidacion()));
            preparedStatementProveedor.executeUpdate();

            log = new LogActivades_Crud();
            fecha = LocalDate.now();
            hora = LocalTime.now();
            actividad = new UsuarioActividad(
                    UsuarioActual.getUsuarioActual().getId(),
                    UsuarioActual.getUsuarioActual().getNombres(),
                    UsuarioActual.getUsuarioActual().getApellidos(),
                    UsuarioActual.getUsuarioActual().getCorreo_electronico(),
                    "Actualizar Proveedor",
                    "Proveedor",
                    fecha,
                    hora
            );
            log.registrarActividad(actividad);
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            try{
                if (preparedStatementProveedor != null){
                    preparedStatementProveedor.close();
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
