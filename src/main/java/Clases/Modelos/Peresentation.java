package Clases.Modelos;

public class Peresentation {
    private int id;
    private String presentation;
    private String measurementUnit;

    public Peresentation(int id, String presentation, String measurementUnit) {
        this.id = id;
        this.presentation = presentation;
        this.measurementUnit = measurementUnit;
    }

    public Peresentation(String presentation, String measurementUnit) {
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
}
