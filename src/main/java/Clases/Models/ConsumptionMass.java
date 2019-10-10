package Clases.Models;

/**
 * <h1>ConsumptionMass</h1>
 *<p>This class takes cares of converting and calculating the usage of a product, it will use the cost per unit and
 * the product's default presentation to calculate the estimated cost.</p>
 *
 *
 * */

public class ConsumptionMass {
    private String unitProduct;
    private String unitConsume;
    private double consumeAmount;
    private double costPerUnit;
    private double consumeCost;
    private double stockConsumed;
    private double result;

    public ConsumptionMass(Product selectedProduct) {
        costPerUnit = selectedProduct.getCostPerUnit();
        unitProduct = selectedProduct.getPresentation();
    }

    public double calculateConsumeCost(String unit, double consumeAmount){
        this.consumeAmount = consumeAmount;
        this.unitConsume = unit;
        convert();
        consumeCost = costPerUnit * result;
        return consumeCost;
    }

    public double calculateRemainingStock(String unit, double consumeAmount){
        this.consumeAmount = consumeAmount;
        this.unitConsume = unit;
        convert();
        return stockConsumed;
    }

    private void convert(){
        switch (unitProduct){
            case "kg":
                kgConvert();
                break;
            case "hg":
                hgConvert();
                break;
            case "dag":
                dagConvert();
                break;
            case "g":
                gConvert();
                break;
            case "dg":
                dgConvert();
                break;
            case "cg":
                cgConvert();
                break;
            case "mg":
                mgConvert();
                break;
            default:
                break;
        }

    }

    //Metodos de unidades

    private void kgConvert(){
        if(unitConsume.equals("kg")){
            result = consumeAmount * 1;
            stockConsumed = consumeAmount * 1;
        }
        if(unitConsume.equals("hg")){
           kgTohg();
        }
        if(unitConsume.equals("dag") ){
            kgTodag();
        }
        if(unitConsume.equals("g")){
            kgTog();
        }
        if(unitConsume.equals("dg")){
            kgTodg();
        }
        if(unitConsume.equals("cg")){
            kgTocg();
        }
        if(unitConsume.equals("mg")){
            kgTomg();
        }
    }

    private void hgConvert(){
        if(unitConsume.equals("kg")){
            hgTokg();
        }
        if(unitConsume.equals("hg")){
            result = consumeAmount * 1;
            stockConsumed = consumeAmount * 1;
        }
        if(unitConsume.equals("dag") ){
            hgTodag();
        }
        if(unitConsume.equals("g")){
            hgTog();
        }
        if(unitConsume.equals("dg")){
            hgTodg();
        }
        if(unitConsume.equals("cg")){
            hgTocg();
        }
        if(unitConsume.equals("mg")){
            hgTomg();
        }
    }

    private void dagConvert(){
        if(unitConsume.equals("kg")){
            dagTokg();
        }
        if(unitConsume.equals("hg") ){
            dagTohg();
        }
        if(unitConsume.equals("dag")){
            result = consumeAmount * 1;
            stockConsumed = consumeAmount * 1;
        }
        if(unitConsume.equals("g")){
            dagTog();
        }
        if(unitConsume.equals("dg")){
            dagTodg();
        }
        if(unitConsume.equals("cg")){
            dagTocg();
        }
        if(unitConsume.equals("mg")){
            dagTomg();
        }
    }

    private void gConvert(){
        if(unitConsume.equals("kg")){
            gTokg();
        }
        if(unitConsume.equals("hg") ){
            gTohg();
        }
        if(unitConsume.equals("dag")){
            gTodag();
        }
        if(unitConsume.equals("g")){
            result = consumeAmount * 1;
            stockConsumed = consumeAmount * 1;
        }
        if(unitConsume.equals("dg")){
            gTodg();
        }
        if(unitConsume.equals("cg")){
            gTocg();
        }
        if(unitConsume.equals("mg")){
            gTomg();
        }
    }

    private void dgConvert(){
        if(unitConsume.equals("kg")){
            dgTokg();
        }
        if(unitConsume.equals("hg") ){
            dgTohg();
        }
        if(unitConsume.equals("dag")){
            dgTodag();
        }
        if(unitConsume.equals("g")){
            dgTog();
        }
        if(unitConsume.equals("dg")){
            result = consumeAmount * 1;
            stockConsumed = consumeAmount * 1;
        }
        if(unitConsume.equals("cg")){
            dgTocg();
        }
        if(unitConsume.equals("mg")){
            dgTomg();
        }
    }

    private void cgConvert(){
        if(unitConsume.equals("kg")){
            cgTokg();
        }
        if(unitConsume.equals("hg") ){
            cgTohg();
        }
        if(unitConsume.equals("dag")){
            cgTodag();
        }
        if(unitConsume.equals("g")){
            cgTog();
        }
        if(unitConsume.equals("dg")){
            cgTodg();
        }
        if(unitConsume.equals("cg")){
            result = consumeAmount * 1;
            stockConsumed = consumeAmount * 1;
        }
        if(unitConsume.equals("mg")){
            cgTomg();
        }
    }

    private void mgConvert(){
        if(unitConsume.equals("kg")){
            mgTokg();
        }
        if(unitConsume.equals("hg") ){
            mgTohg();
        }
        if(unitConsume.equals("dag")){
           mgTodag();
        }
        if(unitConsume.equals("g")){
            mgTog();
        }
        if(unitConsume.equals("dg")){
            mgTodg();
        }
        if(unitConsume.equals("cg")){
            mgTocg();
        }
        if(unitConsume.equals("mg")){
            result = consumeAmount * 1;
            stockConsumed = consumeAmount * 1;
        }
    }

    //Convertion Methods
    //kg
    private void kgTohg(){
        result = consumeAmount / 10;
        stockConsumed = consumeAmount / 10;
    }
    private void kgTodag(){
        result= consumeAmount / 100;
        stockConsumed = consumeAmount / 100;
    }
    private void kgTog(){
        result = consumeAmount / 1000;
        stockConsumed = consumeAmount / 1000;
    }
    private void kgTodg(){
        result = consumeAmount / 10000;
        stockConsumed = consumeAmount / 1000;
    }
    private void kgTocg(){
        result = consumeAmount / 100000;
        stockConsumed = consumeAmount / 10000;
    }
    private void kgTomg(){
        result = consumeAmount / 1000000;
        stockConsumed = consumeAmount / 1000000;
    }

    //hg
    private void hgTokg(){
        result = consumeAmount * 10;
        stockConsumed = consumeAmount * 10;
    }
    private void hgTodag(){
        result = consumeAmount / 10;
        stockConsumed = consumeAmount / 10;;
    }
    private void hgTog(){
        result = consumeAmount / 100;
        stockConsumed = consumeAmount / 100;
    }
    private void hgTodg(){
        result = consumeAmount / 1000;
        stockConsumed = consumeAmount / 1000;
    }
    private void hgTocg(){
        result = consumeAmount / 10000;
        stockConsumed = consumeAmount / 10000;
    }
    private void hgTomg(){
        result = consumeAmount / 100000;
        stockConsumed = consumeAmount / 100000;
    }

    //dag
    private void dagTokg(){
        result = consumeAmount * 100;
        stockConsumed = consumeAmount * 100;
    }
    private void dagTohg(){
        result = consumeAmount * 10;
        stockConsumed = consumeAmount * 10;
    }
    private void dagTog(){
        result = consumeAmount / 10;
        stockConsumed = consumeAmount / 10;
    }
    private void dagTodg(){
        result = consumeAmount / 100;
        stockConsumed = consumeAmount / 100;
    }
    private void dagTocg(){
        result = consumeAmount / 1000;
        stockConsumed = consumeAmount / 1000;
    }
    private void dagTomg(){
        result = consumeAmount / 10000;
        stockConsumed = consumeAmount / 10000;
    }

    //g
    private void gTokg(){
        result = consumeAmount * 1000;
        stockConsumed = consumeAmount * 1000;
    }
    private void gTohg(){
        result = consumeAmount * 100;
        stockConsumed = consumeAmount * 100;
    }
    private void gTodag(){
        result = consumeAmount * 10;
        stockConsumed = consumeAmount * 10;
    }
    private void gTodg(){
        result = consumeAmount / 10;
        stockConsumed = consumeAmount / 10;
    }
    private void gTocg(){
        result = consumeAmount / 100;
        stockConsumed = consumeAmount / 100;
    }
    private void gTomg(){
        result = consumeAmount / 1000;
        stockConsumed = consumeAmount / 1000;;
    }

    //dg
    private void dgTokg(){
        result = consumeAmount * 10000;
        stockConsumed = consumeAmount * 10000;
    }
    private void dgTohg(){
        result = consumeAmount * 1000;
        stockConsumed = consumeAmount * 1000;
    }
    private void dgTodag(){
        result = consumeAmount * 100;
        stockConsumed = consumeAmount * 100;
    }
    private void dgTog(){
        result = consumeAmount * 10;
        stockConsumed = consumeAmount * 10;;
    }
    private void dgTocg(){
        result = consumeAmount / 10;
        stockConsumed = consumeAmount / 10;
    }
    private void dgTomg(){
        result = consumeAmount / 100;
        stockConsumed = consumeAmount / 100;
    }

    //cg
    private void cgTokg(){
        result = consumeAmount * 100000;
        stockConsumed = consumeAmount * 100000;
    }
    private void cgTohg(){
        result = consumeAmount * 10000;
        stockConsumed = consumeAmount * 10000;
    }
    private void cgTodag(){
        result = consumeAmount * 1000;
        stockConsumed = consumeAmount * 1000;
    }
    private void cgTog(){
        result = consumeAmount * 100;
        stockConsumed = consumeAmount * 100;
    }
    private void cgTodg(){
        result = consumeAmount * 10;
        stockConsumed = consumeAmount * 10;
    }
    private void cgTomg(){
        result = consumeAmount / 10;
        stockConsumed = consumeAmount / 10;
    }

    //mg
    private void mgTokg(){
        result = consumeAmount * 1000000;
        stockConsumed = consumeAmount * 1000000;
    }
    private void mgTohg(){
        result = consumeAmount * 100000;
        stockConsumed = consumeAmount * 100000;
    }
    private void mgTodag(){
        result = consumeAmount * 10000;
        stockConsumed = consumeAmount * 10000;
    }
    private void mgTog(){
        result = consumeAmount * 1000;
        stockConsumed = consumeAmount * 1000;
    }
    private void mgTodg(){
        result = consumeAmount * 100;
        stockConsumed = consumeAmount * 100;
    }
    private void mgTocg(){
        result = consumeAmount * 10;
        stockConsumed = consumeAmount * 10;
    }
}
