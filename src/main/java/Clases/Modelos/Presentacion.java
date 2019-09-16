package Clases.Modelos;

public class Presentacion {
    private int id;
    private String presentacion;
    private String unidadDeMedida;

    public Presentacion(int id, String presentacion, String unidadDeMedida) {
        this.id = id;
        this.presentacion = presentacion;
        this.unidadDeMedida = unidadDeMedida;
    }

    public Presentacion(String presentacion, String unidadDeMedida) {
        this.presentacion = presentacion;
        this.unidadDeMedida = unidadDeMedida;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public String getUnidadDeMedida() {
        return unidadDeMedida;
    }

    public void setUnidadDeMedida(String unidadDeMedida) {
        this.unidadDeMedida = unidadDeMedida;
    }
}
