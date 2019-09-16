package Clases.Modelos;

import java.time.LocalDate;

public class Proveedor {
    private int id;
    private String nombre;
    private String telefono;
    private String contacto;
    private String codigoDeProveedor;
    private String servicio;
    private int punteo;
    private boolean critico;
    private boolean aprobado;
    private LocalDate fechaDeAprobacion;
    private LocalDate fechaDeRevalidacion;

    public Proveedor(int id, String nombre, String telefono, String contacto, String codigoDeProveedor, String servicio, int punteo, boolean critico, boolean aprobado, LocalDate fechaDeAprobacion, LocalDate fechaDeRevalidacion) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.contacto = contacto;
        this.codigoDeProveedor = codigoDeProveedor;
        this.servicio = servicio;
        this.punteo = punteo;
        this.critico = critico;
        this.aprobado = aprobado;
        this.fechaDeAprobacion = fechaDeAprobacion;
        this.fechaDeRevalidacion = fechaDeRevalidacion;
    }

    public Proveedor(String nombre, String telefono, String contacto, String codigoDeProveedor, String servicio, int punteo, boolean critico, boolean aprobado, LocalDate fechaDeAprobacion, LocalDate fechaDeRevalidacion) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.contacto = contacto;
        this.codigoDeProveedor = codigoDeProveedor;
        this.servicio = servicio;
        this.punteo = punteo;
        this.critico = critico;
        this.aprobado = aprobado;
        this.fechaDeAprobacion = fechaDeAprobacion;
        this.fechaDeRevalidacion = fechaDeRevalidacion;
    }

    public Proveedor(String nombre, String telefono, String contacto, String codigoDeProveedor) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.contacto = contacto;
        this.codigoDeProveedor = codigoDeProveedor;
    }

    public String getCodigoDeProveedor() {
        return codigoDeProveedor;
    }

    public void setCodigoDeProveedor(String codigoDeProveedor) {
        this.codigoDeProveedor = codigoDeProveedor;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public int getPunteo() {
        return punteo;
    }

    public void setPunteo(int punteo) {
        this.punteo = punteo;
    }

    public boolean isCritico() {
        return critico;
    }

    public void setCritico(boolean critico) {
        this.critico = critico;
    }

    public boolean isAprobado() {
        return aprobado;
    }

    public void setAprobado(boolean aprobado) {
        this.aprobado = aprobado;
    }

    public LocalDate getFechaDeAprobacion() {
        return fechaDeAprobacion;
    }

    public void setFechaDeAprobacion(LocalDate fechaDeAprobacion) {
        this.fechaDeAprobacion = fechaDeAprobacion;
    }

    public LocalDate getFechaDeRevalidacion() {
        return fechaDeRevalidacion;
    }

    public void setFechaDeRevalidacion(LocalDate fechaDeRevalidacion) {
        this.fechaDeRevalidacion = fechaDeRevalidacion;
    }

}
