package Clases.Modelos;

import java.time.LocalDate;
import java.time.LocalTime;

public class UsuarioActual {
    private static Usuario usuarioActual;
    private static LocalDate loginFecha;
    private static LocalTime loginHora;

    public UsuarioActual(){}

    public static void obtenerDatosDeUsuario(Usuario usuario, LocalDate fechaDeEntrada, LocalTime horaDeEntrada) {
        setLoginFecha(fechaDeEntrada);
        setLoginHora(horaDeEntrada);
        setUsuarioActual(usuario);
    }

    public static LocalTime getLoginHora() {
        return loginHora;
    }

    public static void setLoginHora(LocalTime Hora) {
        loginHora = Hora;
    }

    public static LocalDate getLoginFecha() {
        return loginFecha;
    }

    public static void setLoginFecha(LocalDate Fecha) {
        loginFecha = Fecha;
    }

    public static Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public static void setUsuarioActual(Usuario usuario) {
        usuarioActual = usuario;
    }
}
