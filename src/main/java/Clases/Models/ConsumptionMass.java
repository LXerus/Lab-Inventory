package Clases.Models;

/**
 * <h1>ConsumptionMass</h1>
 *<p>This class takes cares of converting and calculating the usage of a product, it will use the cost per unit and
 * the product's default presentation to calculate the estimated cost.</p>
 *
 *
 * */

public class ConsumptionMass {
    private String unitProduct = "g";
    private String unitConsume = "g";
    private double consumeAmount = 0;
    private double costPerUnit = 1;
    private double consumeCost = 1;
    private Product product;

    public ConsumptionMass(Product product) {
        this.product = product;
    }

    public double calculateConsumeCost(String unit, double consumeAmount){
        this.consumeAmount = consumeAmount;
        this.unitConsume = unit;
        costPerUnit = product.getCostPerUnit();
        consumeCost = costPerUnit * convert();
        return consumeCost;
    }

    public double convert(){
        unitProduct = product.getPresentation();
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
        return consumeAmount;
    }

    //Metodos de unidades

    private double kgConvert(){
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
        return consumeAmount;
    }

    private double hgConvert(){
        if(unitConsume.equals("kg")){
            hgTokg();
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
        return consumeAmount;
    }

    private double dagConvert(){
        if(unitConsume.equals("kg")){
            dagTokg();
        }
        if(unitConsume.equals("hg") ){
            dagTohg();
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
        return consumeAmount;
    }

    private double gConvert(){
        if(unitConsume.equals("kg")){
            gTokg();
        }
        if(unitConsume.equals("hg") ){
            gTohg();
        }
        if(unitConsume.equals("dag")){
            gTodag();
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
        return consumeAmount;
    }

    private double dgConvert(){
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
        if(unitConsume.equals("cg")){
            dgTocg();
        }
        if(unitConsume.equals("mg")){
            dgTomg();
        }
        return consumeAmount;
    }

    private double cgConvert(){
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
        if(unitConsume.equals("mg")){
            cgTomg();
        }
        return consumeAmount;
    }

    private double mgConvert(){
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
        return consumeAmount;
    }

    //Convertion Methods

    private double kgTohg(){
        return consumeAmount * 10;
    }
    private double kgTodag(){
        return consumeAmount * 100;
    }
    private double kgTog(){
        return consumeAmount * 1000;
    }
    private double kgTodg(){
        return consumeAmount * 10000;
    }
    private double kgTocg(){
        return consumeAmount * 100000;
    }
    private double kgTomg(){
        return consumeAmount * 1000000;
    }

    private double hgTokg(){
        return consumeAmount / 10;
    }
    private double hgTodag(){
        return consumeAmount * 10;
    }
    private double hgTog(){
        return consumeAmount * 100;
    }
    private double hgTodg(){
        return consumeAmount * 1000;
    }
    private double hgTocg(){
        return consumeAmount * 10000;
    }
    private double hgTomg(){
        return consumeAmount * 100000;
    }

    private double dagTokg(){
        return consumeAmount / 100;
    }
    private double dagTohg(){
        return consumeAmount / 10;
    }
    private double dagTog(){
        return consumeAmount * 10;
    }
    private double dagTodg(){
        return consumeAmount * 100;
    }
    private double dagTocg(){
        return consumeAmount * 1000;
    }
    private double dagTomg(){
        return consumeAmount * 10000;
    }

    private double gTokg(){
        return consumeAmount / 1000;
    }
    private double gTohg(){
        return consumeAmount / 100;
    }
    private double gTodag(){
        return consumeAmount / 10;
    }
    private double gTodg(){
        return consumeAmount * 10;
    }
    private double gTocg(){
        return consumeAmount * 100;
    }
    private double gTomg(){
        return consumeAmount * 1000;
    }

    private double dgTokg(){
        return consumeAmount / 10000;
    }
    private double dgTohg(){
        return consumeAmount / 1000;
    }
    private double dgTodag(){
        return consumeAmount / 100;
    }
    private double dgTog(){
        return consumeAmount / 10;
    }
    private double dgTocg(){
        return consumeAmount * 10;
    }
    private double dgTomg(){
        return consumeAmount * 100;
    }

    private double cgTokg(){
        return consumeAmount / 100000;
    }
    private double cgTohg(){
        return consumeAmount / 10000;
    }
    private double cgTodag(){
        return consumeAmount / 1000;
    }
    private double cgTog(){
        return consumeAmount / 100;
    }
    private double cgTodg(){
        return consumeAmount / 10;
    }
    private double cgTomg(){
        return consumeAmount * 10;
    }

    private double mgTokg(){
        return consumeAmount / 1000000;
    }
    private double mgTohg(){
        return consumeAmount / 100000;
    }
    private double mgTodag(){
        return consumeAmount / 10000;
    }
    private double mgTog(){
        return consumeAmount / 1000;
    }
    private double mgTodg(){
        return consumeAmount / 100;
    }
    private double mgTocg(){
        return consumeAmount / 10;
    }





}
