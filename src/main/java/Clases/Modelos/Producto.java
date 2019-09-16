package Clases.Modelos;


import Clases.Cruds.Productos_Crud;

import java.time.LocalDate;

public class Producto {
    private Productos_Crud productosCrud = new Productos_Crud();
    private int id;
    private String nombre;
    private String marca;
    private String cas;
    private String codigo_interno;
    private String codigo_standard;
    private String lote;
    private LocalDate fecha_de_ingreso;
    private LocalDate fecha_de_vencimiento;
    private LocalDate fecha_de_factura;
    private LocalDate fecha_abierto;
    private String numero_de_factura;
    private double stock;
    private double costo;
    private double costoXUnidad;
    private boolean productoControlado;
    private int ghs;
    private int idBodega;
    private int idProveedor;
    private int idTipoDeProducto;
    private int idPresentacion;
    private int idRegistro;
    private String bodega; //Atributo cuya funcion es almacenar el nombre correscpondiente a idBodega
    private String proveedor;//Atributo cuya funcion es almacenar el nombre correscpondiente a idProveedor
    private String tipoDeProducto;//Atributo cuya funcion es almacenar el nombre correscpondiente a id_tipo_de_producto
    private String presentacion;//Atributo cuya funcion es almacenar el nombre correscpondiente a idPresentacion
    private String registro;//Atributo cuya funcion es almacenar el nombre correscpondiente a idRegistro

    public Producto(int id, String nombre, String marca, String cas, String codigo_interno, String codigo_standard, String lote, LocalDate fecha_de_ingreso, LocalDate fecha_de_vencimiento, LocalDate fecha_de_factura, LocalDate fecha_abierto, String numero_de_factura, double stock, double costo, double costoXUnidad, boolean productoControlado, int ghs, int idBodega, int idProveedor, int idTipoDeProducto, int idPresentacion, int idRegistro) {
        this.id = id;
        this.nombre = nombre;
        this.marca = marca;
        this.cas = cas;
        this.codigo_interno = codigo_interno;
        this.codigo_standard = codigo_standard;
        this.lote = lote;
        this.fecha_de_ingreso = fecha_de_ingreso;
        this.fecha_de_vencimiento = fecha_de_vencimiento;
        this.fecha_de_factura = fecha_de_factura;
        this.fecha_abierto = fecha_abierto;
        this.numero_de_factura = numero_de_factura;
        this.stock = stock;
        this.costo = costo;
        this.costoXUnidad = costoXUnidad;
        this.productoControlado = productoControlado;
        this.ghs = ghs;
        this.idBodega = idBodega;
        this.idProveedor = idProveedor;
        this.idTipoDeProducto = idTipoDeProducto;
        this.idPresentacion = idPresentacion;
        this.idRegistro = idRegistro;
        this.proveedor = productosCrud.buscarIDProveedor(idProveedor).getNombre();
        this.bodega = productosCrud.buscarIDBodega(idBodega).getNombre();
        this.tipoDeProducto = productosCrud.buscarIDTipoDeProducto(idTipoDeProducto).getTipoDeProducto();
        this.presentacion = productosCrud.buscarIDPresentacion(idPresentacion).getPresentacion();
        this.registro = productosCrud.buscarIDRegistro(idRegistro).getNombre();
    }

    public Producto(String nombre, String marca, String cas, String codigo_interno, String codigo_standard, String lote, LocalDate fecha_de_ingreso, LocalDate fecha_de_vencimiento, LocalDate fecha_abierto, LocalDate fecha_de_factura, String numero_de_factura, double stock, double costo, boolean productoControlado, int ghs, int idBodega, int idProveedor, int idTipoDeProducto, int idPresentacion, int idRegistro) {
        this.nombre = nombre;
        this.marca = marca;
        this.cas = cas;
        this.codigo_interno = codigo_interno;
        this.codigo_standard = codigo_standard;
        this.lote = lote;
        this.fecha_de_ingreso = fecha_de_ingreso;
        this.fecha_de_vencimiento = fecha_de_vencimiento;
        this.fecha_de_factura = fecha_de_factura;
        this.fecha_abierto = fecha_abierto;
        this.numero_de_factura = numero_de_factura;
        this.stock = stock;
        this.costo = costo;
        this.productoControlado = productoControlado;
        this.ghs = ghs;
        this.idBodega = idBodega;
        this.idProveedor = idProveedor;
        this.idTipoDeProducto = idTipoDeProducto;
        this.idPresentacion = idPresentacion;
        this.idRegistro = idRegistro;
        this.proveedor = productosCrud.buscarIDProveedor(idProveedor).getNombre();
        this.bodega = productosCrud.buscarIDBodega(idBodega).getNombre();
        this.tipoDeProducto = productosCrud.buscarIDTipoDeProducto(idTipoDeProducto).getTipoDeProducto();
        this.presentacion = productosCrud.buscarIDPresentacion(idPresentacion).getPresentacion();
        this.registro = productosCrud.buscarIDRegistro(idRegistro).getNombre();
    }

    public Producto(String nombre, String marca, String cas, String codigo_interno, String codigo_standard, String lote, LocalDate fecha_de_ingreso, LocalDate fecha_de_vencimiento, String numero_de_factura, int idBodega, int idProveedor) {
        this.nombre = nombre;
        this.marca = marca;
        this.cas = cas;
        this.codigo_interno = codigo_interno;
        this.codigo_standard = codigo_standard;
        this.lote = lote;
        this.fecha_de_ingreso = fecha_de_ingreso;
        this.fecha_de_vencimiento = fecha_de_vencimiento;
        this.numero_de_factura = numero_de_factura;
        this.idBodega = idBodega;
        this.idProveedor = idProveedor;
        if(idProveedor != 0) {
            this.proveedor = productosCrud.buscarIDProveedor(idProveedor).getNombre();
        }
        if(idBodega != 0) {
            this.bodega = productosCrud.buscarIDBodega(idBodega).getNombre();
        }
    }

    public int getIdPresentacion() {
        return idPresentacion;
    }
    public void setIdPresentacion(int idPresentacion) {
        this.idPresentacion = idPresentacion;
    }

    public int getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(int idRegistro) {
        this.idRegistro = idRegistro;
    }

    public int getIdTipoDeProducto() {
        return idTipoDeProducto;
    }

    public void setIdTipoDeProducto(int idTipoDeProducto) {
        this.idTipoDeProducto = idTipoDeProducto;
    }

    public double getCostoXUnidad() {
        return costoXUnidad;
    }

    public void setCostoXUnidad(double costoXUnidad) {
        this.costoXUnidad = costoXUnidad;
    }

    public void setProductoControlado(boolean productoControlado) {
        this.productoControlado = productoControlado;
    }

    public void setGhs(int ghs) {
        this.ghs = ghs;
    }

    public boolean isProductoControlado() {
        return productoControlado;
    }

    public int getGhs() {
        return ghs;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCas() {
        return cas;
    }

    public void setCas(String cas) {
        this.cas = cas;
    }

    public String getCodigo_interno() {
        return codigo_interno;
    }

    public void setCodigo_interno(String codigo_interno) {
        this.codigo_interno = codigo_interno;
    }

    public String getCodigo_standard() {
        return codigo_standard;
    }

    public void setCodigo_standard(String codigo_standard) {
        this.codigo_standard = codigo_standard;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public String getNumero_de_factura() {
        return numero_de_factura;
    }

    public void setNumero_de_factura(String numero_de_factura) {
        this.numero_de_factura = numero_de_factura;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public double getStock() {
        return stock;
    }

    public void setStock(double stock) {
        this.stock = stock;
    }

    public LocalDate getFecha_de_factura() {
        return fecha_de_factura;
    }

    public void setFecha_de_factura(LocalDate fecha_de_factura) {
        this.fecha_de_factura = fecha_de_factura;
    }

    public LocalDate getFecha_de_ingreso() {
        return fecha_de_ingreso;
    }

    public void setFecha_de_ingreso(LocalDate fecha_de_ingreso) {
        this.fecha_de_ingreso = fecha_de_ingreso;
    }

    public LocalDate getFecha_de_vencimiento() {
        return fecha_de_vencimiento;
    }

    public void setFecha_de_vencimiento(LocalDate fecha_de_vencimiento) {
        this.fecha_de_vencimiento = fecha_de_vencimiento;
    }

    public LocalDate getFecha_abierto() {
        return fecha_abierto;
    }

    public void setFecha_abierto(LocalDate fecha_abierto) {
        this.fecha_abierto = fecha_abierto;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public int getIdBodega() {
        return idBodega;
    }

    public void setIdBodega(int idBodega) {
        this.idBodega = idBodega;
    }

    public String getBodega() {
        return bodega;
    }

    public void setBodega(String bodega) {
        this.bodega = bodega;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getTipoDeProducto() {
        return tipoDeProducto;
    }

    public void setTipoDeProducto(String tipoDeProducto) {
        this.tipoDeProducto = tipoDeProducto;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public String getRegistro() {
        return registro;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
    }
}


