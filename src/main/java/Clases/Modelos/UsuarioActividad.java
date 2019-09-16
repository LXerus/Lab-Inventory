package Clases.Modelos;

import java.time.LocalDate;
import java.time.LocalTime;

public class UsuarioActividad {
    private int id_usuario;
    private String nombre_usuario;
    private String apellidos_usuario;
    private String correo_electronico;
    private String tipo_de_actividad;
    private String tabla;
    private LocalDate fecha;
    private LocalTime hora;
    private int id_producto;

    public UsuarioActividad(int id_usuario, String nombre_usuario, String apellidos_usuario, String correo_electronico, String tipo_de_actividad, String tabla, LocalDate fecha, LocalTime hora, int id_producto) {
        this.id_usuario = id_usuario;
        this.nombre_usuario = nombre_usuario;
        this.apellidos_usuario = apellidos_usuario;
        this.correo_electronico = correo_electronico;
        this.tipo_de_actividad = tipo_de_actividad;
        this.tabla = tabla;
        this.fecha = fecha;
        this.hora = hora;
        this.id_producto = id_producto;
    }

    public UsuarioActividad(int id_usuario, String nombre_usuario, String apellidos_usuario, String correo_electronico, String tipo_de_actividad, String tabla, LocalDate fecha, LocalTime hora) {
        this.id_usuario = id_usuario;
        this.nombre_usuario = nombre_usuario;
        this.apellidos_usuario = apellidos_usuario;
        this.tipo_de_actividad = tipo_de_actividad;
        this.correo_electronico = correo_electronico;
        this.tabla = tabla;
        this.fecha = fecha;
        this.hora = hora;
    }

    public String getApellidos_usuario() {
        return apellidos_usuario;
    }

    public void setApellidos_usuario(String apellidos_usuario) {
        this.apellidos_usuario = apellidos_usuario;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getCorreo_electronico() {
        return correo_electronico;
    }

    public void setCorreo_electronico(String correo_electronico) {
        this.correo_electronico = correo_electronico;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getTipo_de_actividad() {
        return tipo_de_actividad;
    }

    public void setTipo_de_actividad(String tipo_de_actividad) {
        this.tipo_de_actividad = tipo_de_actividad;
    }

    public String getTabla() {
        return tabla;
    }

    public void setTabla(String tabla) {
        this.tabla = tabla;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}
