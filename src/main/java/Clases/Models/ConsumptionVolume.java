package Clases.Models;

/**
 * <h1>ConsumptionMass</h1>
 * <p>This class takes cares of converting and calculating the usage of a product, it will use the cost per unit and
 * the product's default presentation to calculate the estimated cost.</p>
 */

public class ConsumptionVolume {
    private String unitProduct;
    private String unitConsume;
    private double consumeAmount;
    private double costPerUnit;
    private double consumeCost;
    private double stockConsumed;
    private double result;

    public ConsumptionVolume(Product selectedProduct) {
        costPerUnit = selectedProduct.getCostPerUnit();
        unitProduct = selectedProduct.getPresentation();
    }

    public double calculateConsumeCost(String unit, double consumeAmount) {
        this.consumeAmount = consumeAmount;
        this.unitConsume = unit;
        convert();
        consumeCost = costPerUnit * result;
        return consumeCost;
    }

    public double calculateRemainingStock(String unit, double consumeAmount) {
        this.consumeAmount = consumeAmount;
        this.unitConsume = unit;
        convert();
        return stockConsumed;
    }

    private void convert() {
        switch (unitProduct) {
            case "kl":
                klConvert();
                break;
            case "hl":
                hlConvert();
                break;
            case "dal":
                dalConvert();
                break;
            case "L":
                lConvert();
                break;
            case "dl":
                dlConvert();
                break;
            case "cl":
                clConvert();
                break;
            case "ml":
                mlConvert();
                break;
            default:
                break;
        }

    }

    //Metodos de unidades

    private void klConvert() {
        if (unitConsume.equals("kl")) {
            result = consumeAmount * 1;
            stockConsumed = consumeAmount * 1;
        }
        if (unitConsume.equals("hl")) {
            klTohl();
        }
        if (unitConsume.equals("dal")) {
            klTodal();
        }
        if (unitConsume.equals("L")) {
            klToL();
        }
        if (unitConsume.equals("dl")) {
            klTodl();
        }
        if (unitConsume.equals("cl")) {
            klTocl();
        }
        if (unitConsume.equals("ml")) {
            klToml();
        }
    }

    private void hlConvert() {
        if (unitConsume.equals("kl")) {
            hlTokl();
        }
        if (unitConsume.equals("hl")) {
            result = consumeAmount * 1;
            stockConsumed = consumeAmount * 1;
        }
        if (unitConsume.equals("dal")) {
            hlTodal();
        }
        if (unitConsume.equals("l")) {
            hlToL();
        }
        if (unitConsume.equals("dl")) {
            hlTodl();
        }
        if (unitConsume.equals("cl")) {
            hlTocl();
        }
        if (unitConsume.equals("ml")) {
            hlToml();
        }
    }

    private void dalConvert() {
        if (unitConsume.equals("kl")) {
            dalTokl();
        }
        if (unitConsume.equals("hl")) {
            dalTohl();
        }
        if (unitConsume.equals("dal")) {
            result = consumeAmount * 1;
            stockConsumed = consumeAmount * 1;
        }
        if (unitConsume.equals("L")) {
            dalToL();
        }
        if (unitConsume.equals("dl")) {
            dalTodl();
        }
        if (unitConsume.equals("cl")) {
            dalTocl();
        }
        if (unitConsume.equals("ml")) {
            dalToml();
        }
    }

    private void lConvert() {
        if (unitConsume.equals("kl")) {
            lTokl();
        }
        if (unitConsume.equals("hl")) {
            lTohl();
        }
        if (unitConsume.equals("dal")) {
            lTodal();
        }
        if (unitConsume.equals("L")) {
            result = consumeAmount * 1;
            stockConsumed = consumeAmount * 1;
        }
        if (unitConsume.equals("dl")) {
            lTodl();
        }
        if (unitConsume.equals("cl")) {
            lTocl();
        }
        if (unitConsume.equals("ml")) {
            lToml();
        }
    }

    private void dlConvert() {
        if (unitConsume.equals("kl")) {
            dlTokl();
        }
        if (unitConsume.equals("hl")) {
            dlTohl();
        }
        if (unitConsume.equals("dal")) {
            dlTodal();
        }
        if (unitConsume.equals("L")) {
            dlTol();
        }
        if (unitConsume.equals("dl")) {
            result = consumeAmount * 1;
            stockConsumed = consumeAmount * 1;
        }
        if (unitConsume.equals("cl")) {
            dlTocl();
        }
        if (unitConsume.equals("ml")) {
            dlToml();
        }
    }

    private void clConvert() {
        if (unitConsume.equals("kl")) {
            clTokl();
        }
        if (unitConsume.equals("hl")) {
            clTohl();
        }
        if (unitConsume.equals("dal")) {
            clTodal();
        }
        if (unitConsume.equals("L")) {
            clToL();
        }
        if (unitConsume.equals("dl")) {
            clTodl();
        }
        if (unitConsume.equals("cl")) {
            result = consumeAmount * 1;
            stockConsumed = consumeAmount * 1;
        }
        if (unitConsume.equals("ml")) {
            clToml();
        }
    }

    private void mlConvert() {
        if (unitConsume.equals("kl")) {
            mlTokl();
        }
        if (unitConsume.equals("hl")) {
            mlTohl();
        }
        if (unitConsume.equals("dal")) {
            mlTodal();
        }
        if (unitConsume.equals("L")) {
            mlToL();
        }
        if (unitConsume.equals("dl")) {
            mlTodl();
        }
        if (unitConsume.equals("cl")) {
            mlTocl();
        }
        if (unitConsume.equals("ml")) {
            result = consumeAmount * 1;
            stockConsumed = consumeAmount * 1;
        }
    }

    //Convertion Methods

    //kl
    private void klTohl() {
        result = consumeAmount / 10;
        stockConsumed = consumeAmount / 10;
    }

    private void klTodal() {
        result = consumeAmount / 100;
        stockConsumed = consumeAmount / 100;
    }

    private void klToL() {
        result = consumeAmount / 1000;
        stockConsumed = consumeAmount / 1000;
    }

    private void klTodl() {
        result = consumeAmount / 10000;
        stockConsumed = consumeAmount / 1000;
    }

    private void klTocl() {
        result = consumeAmount / 100000;
        stockConsumed = consumeAmount / 10000;
    }

    private void klToml() {
        result = consumeAmount / 1000000;
        stockConsumed = consumeAmount / 1000000;
    }

    //hl
    private void hlTokl() {
        result = consumeAmount * 10;
        stockConsumed = consumeAmount * 10;
    }

    private void hlTodal() {
        result = consumeAmount / 10;
        stockConsumed = consumeAmount / 10;
        ;
    }

    private void hlToL() {
        result = consumeAmount / 100;
        stockConsumed = consumeAmount / 100;
    }

    private void hlTodl() {
        result = consumeAmount / 1000;
        stockConsumed = consumeAmount / 1000;
    }

    private void hlTocl() {
        result = consumeAmount / 10000;
        stockConsumed = consumeAmount / 10000;
    }

    private void hlToml() {
        result = consumeAmount / 100000;
        stockConsumed = consumeAmount / 100000;
    }

    //dal
    private void dalTokl() {
        result = consumeAmount * 100;
        stockConsumed = consumeAmount * 100;
    }

    private void dalTohl() {
        result = consumeAmount * 10;
        stockConsumed = consumeAmount * 10;
    }

    private void dalToL() {
        result = consumeAmount / 10;
        stockConsumed = consumeAmount / 10;
    }

    private void dalTodl() {
        result = consumeAmount / 100;
        stockConsumed = consumeAmount / 100;
    }

    private void dalTocl() {
        result = consumeAmount / 1000;
        stockConsumed = consumeAmount / 1000;
    }

    private void dalToml() {
        result = consumeAmount / 10000;
        stockConsumed = consumeAmount / 10000;
    }

    //L
    private void lTokl() {
        result = consumeAmount * 1000;
        stockConsumed = consumeAmount * 1000;
    }

    private void lTohl() {
        result = consumeAmount * 100;
        stockConsumed = consumeAmount * 100;
    }

    private void lTodal() {
        result = consumeAmount * 10;
        stockConsumed = consumeAmount * 10;
    }

    private void lTodl() {
        result = consumeAmount / 10;
        stockConsumed = consumeAmount / 10;
    }

    private void lTocl() {
        result = consumeAmount / 100;
        stockConsumed = consumeAmount / 100;
    }

    private void lToml() {
        result = consumeAmount / 1000;
        stockConsumed = consumeAmount / 1000;
        ;
    }

    //dl
    private void dlTokl() {
        result = consumeAmount * 10000;
        stockConsumed = consumeAmount * 10000;
    }

    private void dlTohl() {
        result = consumeAmount * 1000;
        stockConsumed = consumeAmount * 1000;
    }

    private void dlTodal() {
        result = consumeAmount * 100;
        stockConsumed = consumeAmount * 100;
    }

    private void dlTol() {
        result = consumeAmount * 10;
        stockConsumed = consumeAmount * 10;
        ;
    }

    private void dlTocl() {
        result = consumeAmount / 10;
        stockConsumed = consumeAmount / 10;
    }

    private void dlToml() {
        result = consumeAmount / 100;
        stockConsumed = consumeAmount / 100;
    }

    //cl
    private void clTokl() {
        result = consumeAmount * 100000;
        stockConsumed = consumeAmount * 100000;
    }

    private void clTohl() {
        result = consumeAmount * 10000;
        stockConsumed = consumeAmount * 10000;
    }

    private void clTodal() {
        result = consumeAmount * 1000;
        stockConsumed = consumeAmount * 1000;
    }

    private void clToL() {
        result = consumeAmount * 100;
        stockConsumed = consumeAmount * 100;
    }

    private void clTodl() {
        result = consumeAmount * 10;
        stockConsumed = consumeAmount * 10;
    }

    private void clToml() {
        result = consumeAmount / 10;
        stockConsumed = consumeAmount / 10;
    }

    //ml
    private void mlTokl() {
        result = consumeAmount * 1000000;
        stockConsumed = consumeAmount * 1000000;
    }

    private void mlTohl() {
        result = consumeAmount * 100000;
        stockConsumed = consumeAmount * 100000;
    }

    private void mlTodal() {
        result = consumeAmount * 10000;
        stockConsumed = consumeAmount * 10000;
    }

    private void mlToL() {
        result = consumeAmount * 1000;
        stockConsumed = consumeAmount * 1000;
    }

    private void mlTodl() {
        result = consumeAmount * 100;
        stockConsumed = consumeAmount * 100;
    }

    private void mlTocl() {
        result = consumeAmount * 10;
        stockConsumed = consumeAmount * 10;
    }
}
