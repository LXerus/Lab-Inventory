package Clases.Modelos;

public class ConversionDeMedidas {
    private String unidadDeMedidaConsumo;
    private double consumo;
    private double costoPorUnidad;
    private Producto producto;

    public ConversionDeMedidas(Producto producto, String unidadDeMedidaConsumo, double consumo) {
        this.unidadDeMedidaConsumo = unidadDeMedidaConsumo;
        this.producto = producto;
        this.consumo = consumo;
    }

    public double conversionMasa(){
        String unidadMedidaProducto = producto.getPresentacion();
        costoPorUnidad = producto.getCostoXUnidad();
        double costoTotal = 0;
         switch (unidadMedidaProducto){
             case "kg":
                 if(unidadDeMedidaConsumo.equals("g")){
                     costoTotal = (costoPorUnidad / 1000) * consumo;
                     return costoTotal;
                 }
                 if(unidadDeMedidaConsumo.equals("mg")){
                     costoTotal = (costoPorUnidad / 1000000) * consumo;
                     return costoTotal;
                 }
                 break;

             case "g":
                 if(unidadDeMedidaConsumo.equals("kg")){
                     costoTotal = (costoPorUnidad * 1000) * consumo;
                     return costoTotal;
                 }
                 if(unidadDeMedidaConsumo.equals("mg")){
                     costoTotal = (costoPorUnidad / 1000) * consumo;
                     return costoTotal;
                 }

             case "mg":
                 if(unidadDeMedidaConsumo.equals("g")){
                     costoTotal = (costoPorUnidad * 1000) * consumo;
                     return costoTotal;
                 }
                 if(unidadDeMedidaConsumo.equals("kg")){
                     costoTotal = (costoPorUnidad * 1000000) * consumo;
                     return costoTotal;
                 }
         }
         return costoTotal;
    }
}
