package app;

public class Temp {
    private String name;
    private float avgTemp;
    private int year;


    public Temp(String name, float avgTemp, int year) {
        this.name = name;
        this.avgTemp = avgTemp;
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getAvgTemp() {
        return avgTemp;
    }

    public void setAvgTemp(float avgTemp) {
        this.avgTemp = avgTemp;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

}
