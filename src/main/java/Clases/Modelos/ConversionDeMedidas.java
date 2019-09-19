package Clases.Modelos;

public class ConversionDeMedidas {
    private String unidadDeMedidaConsumo;
    private double consumo;
    private double costoPorUnidad;
    private Product product;

    public ConversionDeMedidas(Product product, String unidadDeMedidaConsumo, double consumo) {
        this.unidadDeMedidaConsumo = unidadDeMedidaConsumo;
        this.product = product;
        this.consumo = consumo;
    }

    public double conversionMasa(){
        String unidadMedidaProducto = product.getPresentation();
        costoPorUnidad = product.getCostPerUnit();
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
