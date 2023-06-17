package app;

public class Stat {
    private String id;
    private String name;
    private float proportion;

    public Stat(String id, float proportion) {
        this.id = id;
        this.proportion = proportion;
    }

    public Stat(String id, String name, float proportion) {
        this.id = id;
        this.proportion = proportion;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public float getProportion() {
        return proportion;
    }

    public void setProportion(float proportion) {
        this.proportion = proportion;
    }


}
