package Clases.Modelos;

public class Bodega {
    private int id;
    private String nombre;
    private String condicion;
    private String region;
    private String tramo;

    public Bodega(){}

    public Bodega(String nombre, String condicion, String region, String tramo) {
        this.nombre = nombre;
        this.condicion = condicion;
        this.region = region;
        this.tramo = tramo;
    }

    public Bodega(int id, String nombre, String condicion, String region, String tramo) {
        this.id = id;
        this.nombre = nombre;
        this.condicion = condicion;
        this.region = region;
        this.tramo = tramo;
    }

    public Bodega(int id, String nombre, String region) {
        this.id = id;
        this.nombre = nombre;
        this.region = region;
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

    public String getCondicion() {
        return condicion;
    }

    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getTramo() {
        return tramo;
    }

    public void setTramo(String tramo) {
        this.tramo = tramo;
    }

    @Override
    public String toString() {
        return "Bodega{" +
                "nombre='" + nombre + '\'' +
                ", condicion='" + condicion + '\'' +
                ", region='" + region + '\'' +
                ", tramo='" + tramo + '\'' +
                '}';
    }
}
