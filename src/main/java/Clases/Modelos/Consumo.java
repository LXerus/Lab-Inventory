package Clases.Modelos;

public class Consumo {
    private int idProducto;
    private double costoXUnidad;
    private double consumo;
    private double resultado;


    public double calcularCosto(double costo, double consumo){
        costoXUnidad = costo;
        resultado = (consumo * costoXUnidad);
        return  resultado;
    }


}
