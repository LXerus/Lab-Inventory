package Clases.Models;

public class Cellar {
    private int id;
    private String name;
    private String condition;
    private String region;
    private String section;

    public Cellar(){}

    public Cellar(String name, String condition, String region, String section) {
        this.name = name;
        this.condition = condition;
        this.region = region;
        this.section = section;
    }

    public Cellar(int id, String name, String condition, String region, String section) {
        this.id = id;
        this.name = name;
        this.condition = condition;
        this.region = region;
        this.section = section;
    }

    public Cellar(int id, String name, String region) {
        this.id = id;
        this.name = name;
        this.region = region;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    @Override
    public String toString() {
        return "Cellar{" +
                "nombre='" + name + '\'' +
                ", condicion='" + condition + '\'' +
                ", region='" + region + '\'' +
                ", tramo='" + section + '\'' +
                '}';
    }
}
