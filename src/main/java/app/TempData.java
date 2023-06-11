package app;

import java.util.ArrayList;

public class TempData {
    private String name;

    private float avgTemp;
    private float minTemp;
    private int year;
    private float maxTemp;


    public TempData(String name, float avgTemp, float minTemp, int year, float maxTemp) {
        this.name = name;
        this.avgTemp = avgTemp;
        this.minTemp = minTemp;
        this.year = year;
        this.maxTemp = maxTemp;
    }
    // CustomImage image 
    // <img src="resources/images/" + image.getImagePath()

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

    public float getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(float minTemp) {
        this.minTemp = minTemp;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public float getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(float maxTemp) {


        this.maxTemp = maxTemp;
    }


}

