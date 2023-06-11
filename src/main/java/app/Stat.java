package app;

public class Stat {
    private String name;
    private float proportion;

    public Stat(String name, float proportion) {
        this.name = name;
        this.proportion = proportion;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public float getProportion() {
        return proportion;
    }

    public void setProportion(float proportion) {
        this.proportion = proportion;
    }


}
