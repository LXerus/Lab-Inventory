package Clases.Modelos;

public class TipoDeProducto {
    private int id;
    private String tipoDeProducto;
    private String descripcion;

    public TipoDeProducto(int id, String tipoDeProducto, String descripcion) {
        this.id = id;
        this.tipoDeProducto = tipoDeProducto;
        this.descripcion = descripcion;
    }

    public TipoDeProducto(int id, String tipoDeProducto) {
        this.id = id;
        this.tipoDeProducto = tipoDeProducto;
    }

    public TipoDeProducto(String tipoDeProducto, String descripcion) {
        this.tipoDeProducto = tipoDeProducto;
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipoDeProducto() {
        return tipoDeProducto;
    }

    public void setTipoDeProducto(String tipoDeProducto) {
        this.tipoDeProducto = tipoDeProducto;
    }
}
