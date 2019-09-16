package Clases.Modelos;

import java.time.LocalDate;

public class Usuario {
    int id;
    String nombres;
    String apellidos;
    String password;
    LocalDate fecha_de_ingreso;
    String area;
    boolean activo = true;
    String estado;
    String correo_electronico;
    int privilegios;
    String tipoPrivilegios;

    public Usuario(int id, String nombres, String apellidos, String password, LocalDate fecha_de_ingreso, String area, boolean activo, String correo_electronico, int privilegios) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.password = password;
        this.fecha_de_ingreso = fecha_de_ingreso;
        this.area = area;
        this.activo = activo;
        this.correo_electronico = correo_electronico;
        this.privilegios = privilegios;
        if(activo){
            this.estado = "Activo";
        }else {
            this.estado = "De baja";
        }
        if(privilegios == 1){
            this.tipoPrivilegios = "Administrador";
        }else {
            this.tipoPrivilegios = "Usuario";
        }
    }

    public Usuario(String nombres, String apellidos, String password, LocalDate fecha_de_ingreso, String area, boolean activo, String correo_electronico, int privilegios) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.password = password;
        this.fecha_de_ingreso = fecha_de_ingreso;
        this.area = area;
        this.activo = activo;
        this.correo_electronico = correo_electronico;
        this.privilegios = privilegios;
        if(activo){
            this.estado = "Activo";
        }else {
            this.estado = "De baja";
        }
        if(privilegios == 1){
            this.tipoPrivilegios = "Administrador";
        }else {
            this.tipoPrivilegios = "Usuario";
        }
    }

    public Usuario(String nombres, String apellidos, String area, String correo_electronico, int privilegios) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.area = area;
        this.correo_electronico = correo_electronico;
        this.privilegios = privilegios;
        if(privilegios == 1){
            this.tipoPrivilegios = "Administrador";
        }else {
            this.tipoPrivilegios = "Usuario";
        }
    }

    public String getTipoPrivilegios() {
        return tipoPrivilegios;
    }

    public void setTipoPrivilegios(String tipoPrivilegios) {
        this.tipoPrivilegios = tipoPrivilegios;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getPrivilegios() {
        return privilegios;
    }

    public void setPrivilegios(int privilegios) {
        this.privilegios = privilegios;
    }

    public String getCorreo_electronico() {
        return correo_electronico;
    }

    public void setCorreo_electronico(String correo_electronico) {
        this.correo_electronico = correo_electronico;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public LocalDate getFecha_de_ingreso() {
        return fecha_de_ingreso;
    }

    public void setFecha_de_ingreso(LocalDate fecha_de_ingreso) {
        this.fecha_de_ingreso = fecha_de_ingreso;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
