package Clases.Models;

public class Presentation {
    private int id;
    private String presentation;
    private String measurementUnit;
    private String presentationType = "";

    public Presentation() {
    }

    public Presentation(int id, String presentation, String measurementUnit) {
        this.id = id;
        this.presentation = presentation;
        this.measurementUnit = measurementUnit;
    }

    public Presentation(String presentation, String measurementUnit) {
        this.presentation = presentation;
        this.measurementUnit = measurementUnit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPresentation() {
        return presentation;
    }

    public void setPresentation(String presentation) {
        this.presentation = presentation;
    }

    public String getMeasurementUnit() {
        return measurementUnit;
    }

    public void setMeasurementUnit(String measurementUnit) {
        this.measurementUnit = measurementUnit;
    }

    public String presentationType(Product product){
        presentationType = product.getPresentation();
        if(presentationType.equals("kg") ||
                presentationType.equals("hg") ||
                presentationType.equals("dag") ||
                presentationType.equals("g") ||
                presentationType.equals("dg") ||
                presentationType.equals("cg") ||
                presentationType.equals("mg")){
        return "mass";
        }
        return "volume";
    }

}
