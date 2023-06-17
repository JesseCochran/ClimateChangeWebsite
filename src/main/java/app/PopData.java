package app;

public class PopData {
    private String name;
    private int year;
    private int population;


    public PopData(String name, int year, int population) {
        this.name = name;
        this.year = year;
        this.population = population;


    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPopulation(int population) {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }


}

