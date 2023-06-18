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
    private String question;
    private String answer;
    private int startYear;
    private int endYear;
    private long startPopulation;
    private long endPopulation;
    private float populationPercent;
    private float startTemp;
    private float endTemp;
    private float tempPercent;
    private String dataType;
    private float avgDiff;

    /**
     * Create an Climate object and set the fields
     */
    /*
     * public Climate(String countryName, String statename, String cityname, int
     * year, float averageTemperature,
     * float minimumTemperature, float maximumTemperature, long populationLevel) {
     * this.countryName = countryName;
     * this.stateName = statename;
     * this.cityName = cityname;
     * this.year = year;
     * this.averageTemperature = averageTemperature;
     * this.minimumTemperature = minimumTemperature;
     * this.maximumTemperature = maximumTemperature;
     * this.populationLevel = populationLevel;
     * }
     */
    public Climate() {

    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public int getEndYear() {
        return endYear;
    }

    public int getStartYear() {
        return startYear;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setQuestion(String question) {
        this.question = question;
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

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setAverageTemperature(float averageTemperature) {
        this.averageTemperature = averageTemperature;
    }

    public void setMinimumTemperature(float minimumTemperature) {
        this.minimumTemperature = minimumTemperature;
    }

    public void setMaximumTemperature(float maximumTemperature) {
        this.maximumTemperature = maximumTemperature;
    }

    public void setPopulationLevel(long populationLevel) {
        this.populationLevel = populationLevel;
    }

    public void setStartPopulation(long startPopulation) {
        this.startPopulation = startPopulation;
    }

    public long getStartPopulation() {
        return startPopulation;
    }

    public void setEndPopulation(long endPopulation) {
        this.endPopulation = endPopulation;
    }

    public long getEndPopulation() {
        return endPopulation;
    }

    public void setPopulationPercent(float populationPercent) {
        this.populationPercent = populationPercent;
    }

    public float getPopulationPercent() {
        return populationPercent;
    }

    public void setStartTemp(float startTemp) {
        this.startTemp = startTemp;
    }

    public float getStartTemp() {
        return startTemp;
    }

    public void setEndTemp(float endTemp) {
        this.endTemp = endTemp;
    }

    public float getEndTemp() {
        return endTemp;
    }

    public void setTempPercent(float tempPercent) {
        this.tempPercent = tempPercent;
    }

    public float getTempPercent() {
        return tempPercent;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public void setAverageDifference(float avgDiff) {
        this.avgDiff = avgDiff;
    }

    public float getAverageDifference() {
        return avgDiff;
    }

}
