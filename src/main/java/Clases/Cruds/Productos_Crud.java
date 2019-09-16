package Clases.Cruds;
import Clases.BaseDeDatos.Conectar;
import Clases.Modelos.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class Productos_Crud {
    //Clase encargada de todas las conexiones con la base de datos para la tabla productos.

    public void registrarProducto(Producto producto){
        conectar = new Conectar(UsuarioActual.getUsuarioActual().getNombres(), UsuarioActual.getUsuarioActual().getPassword());
        sqlConexion = conectar.getConnection();
        String sqlInstruction = "INSERT INTO productos(nombre, marca, cas, codigo_interno, codigo_standard, lote, fecha_ingreso, fecha_vence, " +
                                "fecha_abierto, fecha_factura, factura, stock, costo, producto_controlado, ghs, id_bodega, id_proveedor, " +
                                "id_tipo_producto, id_presentacion, id_registro) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            preparedStatementProductos = sqlConexion.prepareStatement(sqlInstruction);
            preparedStatementProductos.setString(1, producto.getNombre());
            preparedStatementProductos.setString(2, producto.getMarca());
            preparedStatementProductos.setString(3, producto.getCas());
            preparedStatementProductos.setString(4, producto.getCodigo_interno());
            preparedStatementProductos.setString(5, producto.getCodigo_standard());
            preparedStatementProductos.setString(6, producto.getLote());
            preparedStatementProductos.setDate(7, Date.valueOf(producto.getFecha_de_ingreso()));
            preparedStatementProductos.setDate(8, Date.valueOf(producto.getFecha_de_vencimiento()));
            preparedStatementProductos.setDate(9, Date.valueOf(producto.getFecha_abierto()));
            preparedStatementProductos.setDate(10, Date.valueOf(producto.getFecha_de_factura()));
            preparedStatementProductos.setString(11, producto.getNumero_de_factura());
            preparedStatementProductos.setDouble(12, producto.getStock());
            preparedStatementProductos.setDouble(13, producto.getCosto());
            preparedStatementProductos.setBoolean(14, producto.isProductoControlado());
            preparedStatementProductos.setInt(15, producto.getGhs());
            preparedStatementProductos.setInt(16, producto.getIdBodega());
            preparedStatementProductos.setInt(17, producto.getIdProveedor());
            preparedStatementProductos.setInt(18, producto.getIdTipoDeProducto());
            preparedStatementProductos.setInt(19, producto.getIdPresentacion());
            preparedStatementProductos.setInt(20, producto.getIdRegistro());
            preparedStatementProductos.executeUpdate();

            log = new LogActivades_Crud();
            fecha = LocalDate.now();
            hora = LocalTime.now();
            actividad = new UsuarioActividad(
                    UsuarioActual.getUsuarioActual().getId(),
                    UsuarioActual.getUsuarioActual().getNombres(),
                    UsuarioActual.getUsuarioActual().getApellidos(),
                    UsuarioActual.getUsuarioActual().getCorreo_electronico(),
                    "Registro de producto",
                    "Productos",
                    fecha,
                    hora
            );
            log.registrarActividad(actividad);

        }catch (SQLException e){
            e.printStackTrace();
        }finally{
            preparedStatementProductos = null;
            sqlConexion = null;
            conectar.desconectar();
        }
    }

    public ObservableList<Producto> buscarProductos(Producto producto){
        String sqlQuery = "SELECT id, nombre, marca, cas, codigo_interno, codigo_standard, lote, fecha_ingreso, fecha_vence, fecha_abierto," +
                          " fecha_factura, factura, stock, costo, costo_x_unidad, producto_controlado, ghs, id_bodega, id_proveedor, id_tipo_producto, id_presentacion," +
                          " id_registro FROM productos WHERE ";
        ObservableList<Producto> listaDeProductos = FXCollections.observableArrayList();
        conectar = new Conectar(UsuarioActual.getUsuarioActual().getNombres(), UsuarioActual.getUsuarioActual().getPassword());
        sqlConexion = conectar.getConnection();

        if(!producto.getNombre().isEmpty()){
            sqlQuery += "nombre LIKE '%"+producto.getNombre()+"%' AND ";
        }

        if(!producto.getMarca().isEmpty()){
            sqlQuery += "marca LIKE '%"+producto.getMarca()+"%' AND ";
        }

        if (!producto.getLote().isEmpty()){
            sqlQuery += "lote LIKE '%"+producto.getLote()+"%' AND ";
        }

        if (!producto.getNumero_de_factura().isEmpty()){
            sqlQuery += "factura LIKE '%"+producto.getNumero_de_factura()+"%' AND ";
        }

        if (!producto.getCas().isEmpty()){
            sqlQuery += "cas LIKE '%"+producto.getCas()+"%' AND ";
        }

        if (!producto.getCodigo_interno().isEmpty()){
            sqlQuery += "codigo_interno LIKE '%"+producto.getCodigo_interno()+"%' AND ";
        }

        if (!producto.getCodigo_standard().isEmpty()){
            sqlQuery += "codigo_standard LIKE '%"+producto.getCodigo_standard()+"%' AND ";
        }

        if (producto.getId() != 0){
            sqlQuery += "id_proveedor = "+producto.getIdProveedor()+" AND ";
        }

        if(producto.getIdBodega() != 0){
            sqlQuery += "id_bodega = "+producto.getIdBodega()+" AND ";
        }

        if(!(producto.getFecha_de_ingreso() ==null)){
            sqlQuery += "fecha_ingreso="+ producto.getFecha_de_ingreso().toString()+" AND ";
        }

        if(!(producto.getFecha_de_vencimiento() == null)){
            sqlQuery += "fecha_vence="+ producto.getFecha_de_vencimiento().toString()+" AND ";
        }

        char[] stringToArray = sqlQuery.toCharArray();
        String limpiarDatos = "";

        for(int i = 0;i < stringToArray.length-4; i ++){
            limpiarDatos = limpiarDatos + stringToArray[i];
        }

        sqlQuery = limpiarDatos;
        try{
            Statement productoStatement = sqlConexion.createStatement();
            ResultSet productoResultSet = productoStatement.executeQuery(sqlQuery);
            while(productoResultSet.next()){
                int id = productoResultSet.getInt("id");
                String nombre = productoResultSet.getString("nombre");
                String marca = productoResultSet.getString("marca");
                String cas = productoResultSet.getString("cas");
                String codigoInterno = productoResultSet.getString("codigo_interno");
                String codigoStandard = productoResultSet.getString("codigo_standard");
                String lote = productoResultSet.getString("lote");
                LocalDate fechaIngreso = productoResultSet.getDate("fecha_ingreso").toLocalDate();
                LocalDate fechaVencimiento = productoResultSet.getDate("fecha_vence").toLocalDate();
                LocalDate fechaAbierto = productoResultSet.getDate("fecha_abierto").toLocalDate();
                LocalDate fechaFactura = productoResultSet.getDate("fecha_factura").toLocalDate();
                String factura = productoResultSet.getString("factura");
                double stock = productoResultSet.getInt("stock");
                double costo = productoResultSet.getDouble("costo");
                double costoXUnidad = productoResultSet.getDouble("costo_x_unidad");
                Boolean productoControlado = productoResultSet.getBoolean("producto_controlado");
                int ghs = productoResultSet.getInt("ghs");
                int idBodega = productoResultSet.getInt("id_bodega");
                int idProveedor = productoResultSet.getInt("id_proveedor");
                int idTipoProducto = productoResultSet.getInt("id_tipo_producto");
                int idPresentacion = productoResultSet.getInt("id_presentacion");
                int idRegistro = productoResultSet.getInt("id_registro");
                listaDeProductos.add(new Producto(
                        id, nombre, marca, cas, codigoInterno, codigoStandard, lote, fechaIngreso, fechaVencimiento, fechaAbierto, fechaFactura,
                        factura, stock, costo, costoXUnidad, productoControlado, ghs, idBodega, idProveedor, idTipoProducto, idPresentacion, idRegistro
                   ));
            }

            log = new LogActivades_Crud();
            fecha = LocalDate.now();
            hora = LocalTime.now();
            actividad = new UsuarioActividad(
                    UsuarioActual.getUsuarioActual().getId(),
                    UsuarioActual.getUsuarioActual().getNombres(),
                    UsuarioActual.getUsuarioActual().getApellidos(),
                    UsuarioActual.getUsuarioActual().getCorreo_electronico(),
                    "Busqueda de producto",
                    "Productos",
                    fecha,
                    hora
            );
            log.registrarActividad(actividad);
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            sqlConexion = null;
            conectar.desconectar();
        }
        return listaDeProductos;
    }

    public void actualizarStock(int idProducto, double consumo){
        String sqlInstruction = "UPDATE productos SET stock=? WHERE id="+idProducto;
        conectar = new Conectar(UsuarioActual.getUsuarioActual().getNombres(), UsuarioActual.getUsuarioActual().getPassword());
        sqlConexion = conectar.getConnection();
        try{
            preparedStatementProductos = sqlConexion.prepareStatement(sqlInstruction);
            preparedStatementProductos.setDouble(1,consumo);
            preparedStatementProductos.executeUpdate();

            log = new LogActivades_Crud();
            fecha = LocalDate.now();
            hora = LocalTime.now();
            actividad = new UsuarioActividad(
                    UsuarioActual.getUsuarioActual().getId(),
                    UsuarioActual.getUsuarioActual().getNombres(),
                    UsuarioActual.getUsuarioActual().getApellidos(),
                    UsuarioActual.getUsuarioActual().getCorreo_electronico(),
                    "Consumo de producto",
                    "Productos",
                    fecha,
                    hora
            );
            log.registrarActividad(actividad);
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        }finally {
            preparedStatementProductos = null;
            sqlConexion = null;
            conectar.desconectar();
        }
    }
//*****************************************************Listas para los ComboBox para los formularios de productos********************************************
    public ObservableList<Presentacion> obtenerListaDePresentaciones(){
        ObservableList<Presentacion> listaDePresentaciones = FXCollections.observableArrayList();
        String consultaSQL = "SELECT id, presentacion, unidad_medida FROM presentaciones";
        conectar = new Conectar(UsuarioActual.getUsuarioActual().getNombres(), UsuarioActual.getUsuarioActual().getPassword());
        sqlConexion = conectar.getConnection();
        try {
            Statement presentacionStatement = sqlConexion.createStatement();
            ResultSet presentacionResultSet = presentacionStatement.executeQuery(consultaSQL);
            while(presentacionResultSet.next()){
                int id = presentacionResultSet.getInt(1);
                String presentacion = presentacionResultSet.getString(2);
                String unidad_de_medida = presentacionResultSet.getString(3);
                listaDePresentaciones.add(new Presentacion(id, presentacion, unidad_de_medida));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            sqlConexion = null;
            conectar.desconectar();
        }
        return listaDePresentaciones;
    }

    public ObservableList<String> obtenerListaDeBodegas(){
        ObservableList<String> listaDeBodegas = FXCollections.observableArrayList();
        String consultaSQL = "SELECT nombre, condicion, region, tramo FROM bodega";
        conectar = new Conectar(UsuarioActual.getUsuarioActual().getNombres(), UsuarioActual.getUsuarioActual().getPassword());
        sqlConexion = conectar.getConnection();
        try {
            Statement bodegasStatement = sqlConexion.createStatement();
            ResultSet bodegasResultSet = bodegasStatement.executeQuery(consultaSQL);
            while(bodegasResultSet.next()){
                String nombre = bodegasResultSet.getString(1);
                String condicion = bodegasResultSet.getString(2);
                String region = bodegasResultSet.getString(3);
                String tramo = bodegasResultSet.getString(4);
                String celda = nombre+"  |  "+condicion+"  |  "+region+"  |  "+tramo;
                listaDeBodegas.add(nombre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            sqlConexion = null;
            conectar.desconectar();
        }
        return listaDeBodegas;
    }

    public ObservableList<String> obtenerListaTipoDeProductos(){
        String tipo_de_producto;
        ObservableList<String> listaTipoDeProductos = FXCollections.observableArrayList();
        String consultaSQL1 = "SELECT tipo_de_producto FROM tipo_producto";
        conectar = new Conectar(UsuarioActual.getUsuarioActual().getNombres(), UsuarioActual.getUsuarioActual().getPassword());
        sqlConexion = conectar.getConnection();
        try {
            Statement tiposDeProductosStatement = sqlConexion.createStatement();
            ResultSet tiposDeProductosResultSet = tiposDeProductosStatement.executeQuery(consultaSQL1);
            while(tiposDeProductosResultSet.next()){
                tipo_de_producto = tiposDeProductosResultSet.getString("tipo_de_producto");
                listaTipoDeProductos.add(tipo_de_producto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            sqlConexion = null;
            conectar.desconectar();
        }
        return listaTipoDeProductos;
    }

    public ObservableList<String> obtenerNumerosDeRegistro(){
        String nombre_de_registro;
        ObservableList<String> listaDeRegistros = FXCollections.observableArrayList();
        String consultaSQL1 = "SELECT nombre FROM registro";
        conectar = new Conectar(UsuarioActual.getUsuarioActual().getNombres(), UsuarioActual.getUsuarioActual().getPassword());
        sqlConexion = conectar.getConnection();
        try {
            Statement registroStatement = sqlConexion.createStatement();
            ResultSet registroResultSet = registroStatement.executeQuery(consultaSQL1);
            while(registroResultSet.next()){
                nombre_de_registro = registroResultSet.getString(1);
                String celda = nombre_de_registro;
                listaDeRegistros.add(celda);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            sqlConexion = null;
            conectar.desconectar();
        }
        return listaDeRegistros;
    }
    public ObservableList<Proveedor> obtenerProveedores(){

        ObservableList<Proveedor> listaDeProveedores = FXCollections.observableArrayList();
        String consultaSQL1 = "SELECT id, nombre, telefono, contacto, codigo_de_proveedor, servicio, critico, aprobado, punteo, fecha_aprobacion, fecha_revalidacion FROM "+Configuracion.getBaseDeDatos()+".proveedores";
        conectar = new Conectar(UsuarioActual.getUsuarioActual().getNombres(), UsuarioActual.getUsuarioActual().getPassword());
        sqlConexion = conectar.getConnection();
        try {
            Statement proveedorStatement = sqlConexion.createStatement();
            ResultSet proveedorResultSet = proveedorStatement.executeQuery(consultaSQL1);
            while(proveedorResultSet.next()){
                int id = proveedorResultSet.getInt(1);
                String nombre = proveedorResultSet.getString(2);
                String telefono = proveedorResultSet.getString(3);
                String contacto = proveedorResultSet.getString(4);
                String codigoProveedor = proveedorResultSet.getString(5);
                String servicio = proveedorResultSet.getString( 6);
                boolean critico = proveedorResultSet.getBoolean(7);
                boolean aprobado = proveedorResultSet.getBoolean(8);
                int punteo = proveedorResultSet.getInt(9);
                LocalDate fechaAprobacion = proveedorResultSet.getDate(10).toLocalDate();
                LocalDate fechaRevalidacion = proveedorResultSet.getDate(11).toLocalDate();
                listaDeProveedores.add(new Proveedor(id, nombre, telefono, contacto, codigoProveedor, servicio, punteo, critico, aprobado,  fechaAprobacion, fechaRevalidacion));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            sqlConexion = null;
            conectar.desconectar();
        }
        return listaDeProveedores;
    }


    //************************************************Estas funciones son encargadas de obtener los IDs de las clases seleccionadas cuando se registra un producto************************************************

    public int encontrarProveedorID(String nombreProveedor){ //Funcion que lee el nombre del proveedor seleccionado y encuentra su id en la base de datos
        int idProveedor = 0;
        String sqlQuery = "SELECT id FROM proveedores WHERE nombre ='"+nombreProveedor+"'";
        Statement statementProveedor = null;
        ResultSet resultSetProveedor = null;
        try {
            conectar = new Conectar(UsuarioActual.getUsuarioActual().getNombres(), UsuarioActual.getUsuarioActual().getPassword());
            sqlConexion = conectar.getConnection();
            statementProveedor = sqlConexion.createStatement();
            resultSetProveedor = statementProveedor.executeQuery(sqlQuery);

            while (resultSetProveedor.next()){
                idProveedor = resultSetProveedor.getInt("id");
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            try {
                if (resultSetProveedor != null) {
                    resultSetProveedor.close();
                }
                if (statementProveedor != null) {
                    statementProveedor.close();
                }
                if (sqlConexion != null) {
                    sqlConexion.close();
                }
                conectar.desconectar();
            }catch (SQLException ex){
                ex.printStackTrace();
            }
        }
        return idProveedor;
    }

    public int encontrarBodegaID(String bodegaNombre){//Funcion que lee el nombre de la bodega seleccionada y encuentra su id en la base de datos
        int idBodega = 0;
        String sqlQuery = "SELECT id FROM bodega WHERE nombre = '"+bodegaNombre+"'";
        Statement statementProveedor = null;
        ResultSet resultSetProveedor = null;
        try {
            conectar = new Conectar(UsuarioActual.getUsuarioActual().getNombres(), UsuarioActual.getUsuarioActual().getPassword());
            sqlConexion = conectar.getConnection();
            statementProveedor = sqlConexion.createStatement();
            resultSetProveedor = statementProveedor.executeQuery(sqlQuery);

            while (resultSetProveedor.next()){
                idBodega = resultSetProveedor.getInt("id");
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            try {
                if (resultSetProveedor != null) {
                    resultSetProveedor.close();
                }
                if (statementProveedor != null) {
                    statementProveedor.close();
                }
                if (sqlConexion != null) {
                    sqlConexion.close();
                }
                conectar.desconectar();
            }catch (SQLException ex){
                ex.printStackTrace();
            }
        }
        return idBodega;
    }

    public int encontrarTipoProductoID(String tipoProducto){//Funcion que lee el tipo de producto seleccionado y encuentra su id en la base de datos
        int idTipoProducto = 0;
        String sqlQuery = "SELECT id FROM tipo_producto WHERE tipo_de_producto = '"+tipoProducto+"'";
        Statement statementProveedor = null;
        ResultSet resultSetProveedor = null;
        try {
            conectar = new Conectar(UsuarioActual.getUsuarioActual().getNombres(), UsuarioActual.getUsuarioActual().getPassword());
            sqlConexion = conectar.getConnection();
            statementProveedor = sqlConexion.createStatement();
            resultSetProveedor = statementProveedor.executeQuery(sqlQuery);

            while (resultSetProveedor.next()){
                idTipoProducto = resultSetProveedor.getInt("id");
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            try {
                if (resultSetProveedor != null) {
                    resultSetProveedor.close();
                }
                if (statementProveedor != null) {
                    statementProveedor.close();
                }
                if (sqlConexion != null) {
                    sqlConexion.close();
                }
                conectar.desconectar();
            }catch (SQLException ex){
                ex.printStackTrace();
            }
        }
        return idTipoProducto;
    }

    public int encontrarPresentacionID(String presentacion){//Funcion que lee el nombre de la presentacion seleccionada y encuentra su id en la base de datos
        int idPresentacion = 0;
        String sqlQuery = "SELECT id FROM presentaciones WHERE presentacion = '"+presentacion+"'";
        Statement statementProveedor = null;
        ResultSet resultSetProveedor = null;
        try {
            conectar = new Conectar(UsuarioActual.getUsuarioActual().getNombres(), UsuarioActual.getUsuarioActual().getPassword());
            sqlConexion = conectar.getConnection();
            statementProveedor = sqlConexion.createStatement();
            resultSetProveedor = statementProveedor.executeQuery(sqlQuery);

            while (resultSetProveedor.next()){
                idPresentacion = resultSetProveedor.getInt("id");
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            try {
                if (resultSetProveedor != null) {
                    resultSetProveedor.close();
                }
                if (statementProveedor != null) {
                    statementProveedor.close();
                }
                if (sqlConexion != null) {
                    sqlConexion.close();
                }
                conectar.desconectar();
            }catch (SQLException ex){
                ex.printStackTrace();
            }
        }
        return idPresentacion;
    }

    public int encontrarRegistroID(String registro){//Funcion que lee el registro seleccionado y encuentra su id en la base de datos
        int idRegistro = 0;
        String sqlQuery = "SELECT id FROM registro WHERE nombre = '"+registro+"'";
        Statement statementProveedor = null;
        ResultSet resultSetProveedor = null;
        try {
            conectar = new Conectar(UsuarioActual.getUsuarioActual().getNombres(), UsuarioActual.getUsuarioActual().getPassword());
            sqlConexion = conectar.getConnection();
            statementProveedor = sqlConexion.createStatement();
            resultSetProveedor = statementProveedor.executeQuery(sqlQuery);

            while (resultSetProveedor.next()){
                idRegistro = resultSetProveedor.getInt("id");
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            try {
                if (resultSetProveedor != null) {
                    resultSetProveedor.close();
                }
                if (statementProveedor != null) {
                    statementProveedor.close();
                }
                if (sqlConexion != null) {
                    sqlConexion.close();
                }
                conectar.desconectar();
            }catch (SQLException ex){
                ex.printStackTrace();
            }
        }
        return idRegistro;
    }
    //******************************************************************************************************************************************************************************

    //************************************************Estas funciones son encargadas de obtener los objetos correspondiente a los ID************************************************
    //
    public Proveedor buscarIDProveedor(int idProveedor){
        Proveedor proveedor = null;
        String sqlQuery = "SELECT id, nombre, telefono, contacto, codigo_de_proveedor, servicio, critico, aprobado, punteo, fecha_aprobacion, fecha_revalidacion " +
                "FROM proveedores WHERE id = "+idProveedor;
        Statement statementProveedor = null;
        ResultSet resultSetProveedor = null;
        try {
            conectar = new Conectar(UsuarioActual.getUsuarioActual().getNombres(), UsuarioActual.getUsuarioActual().getPassword());
            sqlConexion = conectar.getConnection();
            statementProveedor = sqlConexion.createStatement();
            resultSetProveedor = statementProveedor.executeQuery(sqlQuery);
            while (resultSetProveedor.next()){
                int id = resultSetProveedor.getInt("id");
                String nombre = resultSetProveedor.getString("nombre");
                String telefono = resultSetProveedor.getString("telefono");
                String contacto = resultSetProveedor.getString("contacto");
                String codigo_de_proveedor = resultSetProveedor.getString("codigo_de_proveedor");
                String servicio = resultSetProveedor.getString("servicio");
                boolean critico = resultSetProveedor.getBoolean("critico");
                boolean aprobado = resultSetProveedor.getBoolean("aprobado");
                int punteo = resultSetProveedor.getInt("punteo");
                LocalDate fechaAprobacion = resultSetProveedor.getDate("fecha_aprobacion").toLocalDate();
                LocalDate fechaRevalidacion = resultSetProveedor.getDate("fecha_revalidacion").toLocalDate();

                proveedor = new Proveedor(id, nombre, telefono,contacto, codigo_de_proveedor, servicio, punteo, critico, aprobado,fechaAprobacion,fechaRevalidacion);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return proveedor;
    }

    public Bodega buscarIDBodega(int idBodega){
        Bodega bodega = null;
        String sqlQuery = "SELECT id, nombre, condicion, region, tramo FROM bodega WHERE id = "+idBodega;
        Statement statementBodega = null;
        ResultSet resultSetBodega = null;
        try {
            conectar = new Conectar(UsuarioActual.getUsuarioActual().getNombres(), UsuarioActual.getUsuarioActual().getPassword());
            sqlConexion = conectar.getConnection();
            statementBodega = sqlConexion.createStatement();
            resultSetBodega = statementBodega.executeQuery(sqlQuery);
            while (resultSetBodega.next()){
                int id = resultSetBodega.getInt("id");
                String nombre = resultSetBodega.getString("nombre");
                String condicion = resultSetBodega.getString("condicion");
                String region = resultSetBodega.getString("region");
                String tramo = resultSetBodega.getString("tramo");

                bodega = new Bodega(id, nombre, condicion, region, tramo);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return bodega;
    }

    public TipoDeProducto buscarIDTipoDeProducto(int idTipoDeProducto){
        TipoDeProducto tipoDeProducto = null;
        String sqlQuery = "SELECT id, tipo_de_producto, descripcion FROM tipo_producto WHERE id = "+idTipoDeProducto;
        Statement statementTipoDeProducto = null;
        ResultSet resultSetTipoDeProducto = null;
        try {
            conectar = new Conectar(UsuarioActual.getUsuarioActual().getNombres(), UsuarioActual.getUsuarioActual().getPassword());
            sqlConexion = conectar.getConnection();
            statementTipoDeProducto = sqlConexion.createStatement();
            resultSetTipoDeProducto = statementTipoDeProducto.executeQuery(sqlQuery);
            while (resultSetTipoDeProducto.next()){
                int id = resultSetTipoDeProducto.getInt("id");
                String tipo_de_producto = resultSetTipoDeProducto.getString("tipo_de_producto");
                String dscripcion = resultSetTipoDeProducto.getString("descripcion");

                tipoDeProducto = new TipoDeProducto(id, tipo_de_producto, dscripcion);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return tipoDeProducto;
    }

    public Presentacion buscarIDPresentacion(int idPresentacion){
        Presentacion presentacion = null;
        String sqlQuery = "SELECT id, presentacion, unidad_medida FROM presentaciones WHERE id = "+idPresentacion;
        Statement statementPresentacion = null;
        ResultSet resultSetPresentacion = null;
        try {
            conectar = new Conectar(UsuarioActual.getUsuarioActual().getNombres(), UsuarioActual.getUsuarioActual().getPassword());
            sqlConexion = conectar.getConnection();
            statementPresentacion = sqlConexion.createStatement();
            resultSetPresentacion = statementPresentacion.executeQuery(sqlQuery);
            while (resultSetPresentacion.next()){
                int id = resultSetPresentacion.getInt("id");
                String presentacion_re = resultSetPresentacion.getString("presentacion");
                String unidad_de_medida = resultSetPresentacion.getString("unidad_medida");


                presentacion = new Presentacion(id, presentacion_re, unidad_de_medida);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return presentacion;
    }

    public Registro buscarIDRegistro(int idRegistro) {
        Registro registro = null;
        String sqlQuery = "SELECT id, nombre, descripcion FROM registro WHERE id = " + idRegistro;
        Statement statementRegistro = null;
        ResultSet resultSetRegistro = null;
        try {
            conectar = new Conectar(UsuarioActual.getUsuarioActual().getNombres(), UsuarioActual.getUsuarioActual().getPassword());
            sqlConexion = conectar.getConnection();
            statementRegistro = sqlConexion.createStatement();
            resultSetRegistro = statementRegistro.executeQuery(sqlQuery);
            while (resultSetRegistro.next()) {
                int id = resultSetRegistro.getInt("id");
                String nombre = resultSetRegistro.getString("nombre");
                String descripcion = resultSetRegistro.getString("descripcion");

                registro = new Registro(id, nombre, descripcion);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return registro;
    }
    //**********************************************************************************************************************************************************************************************
    Conectar conectar;
    Connection sqlConexion;
    Configuracion configuracion = new Configuracion();
    private ObservableList<Bodega> listaDeProductos;
    private PreparedStatement preparedStatementProductos;
    UsuarioActividad actividad;
    LocalDate fecha;
    LocalTime hora;
    LogActivades_Crud log;
}
