package app;

public class Climate {
    // Country Name
    private String countryName;

    // State Name
    private String stateName;
    // City Name
    private String cityName;
    // Year of data
    private int year;

    // average temperature
    private float averageTemperature;
    // min temperature
    private float minimumTemperature;
    // max temperature
    private float maximumTemperature;
    // population level
    private long populationLevel;

    /**
     * Create an Climate object and set the fields
     */
    public Climate(String countryName, String statename, String cityname, int year, float averageTemperature,
            float minimumTemperature, float maximumTemperature, long populationLevel) {
        this.countryName = countryName;
        this.stateName = statename;
        this.cityName = cityname;
        this.year = year;
        this.averageTemperature = averageTemperature;
        this.minimumTemperature = minimumTemperature;
        this.maximumTemperature = maximumTemperature;
        this.populationLevel = populationLevel;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getStateName() {
        return stateName;
    }

    public String getCityName() {
        return cityName;
    }

    public int getYear() {
        return year;
    }

    public float getAverageTemperature() {
        return averageTemperature;
    }

    public float getMaximumTemperature() {
        return maximumTemperature;
    }

    public float getMinimumTemperature() {
        return minimumTemperature;
    }

    public long getPopulationLevel() {
        return populationLevel;
    }

}
