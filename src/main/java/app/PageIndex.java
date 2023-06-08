package app;

import java.util.ArrayList;

import io.javalin.http.Context;
import io.javalin.http.Handler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Example Index HTML class using Javalin
 * <p>
 * Generate a static HTML page using Javalin
 * by writing the raw HTML into a Java String object
 *
 * @author Timothy Wiley, 2023. email: timothy.wiley@rmit.edu.au
 * @author Santha Sumanasekara, 2021. email: santha.sumanasekara@rmit.edu.au
 */
public class PageIndex implements Handler {

    // URL of this page relative to http://localhost:7001/
    public static final String URL = "/";

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        String html = "<html>";

        // Add some Header information
        html = html + "<head>" +
                "<title>Homepage</title>";

        // Add some CSS (external file)
        html = html + "<link rel='stylesheet' type='text/css' href='JesseTesting2c.css' />";
        html = html + "</head>";

        // Add the body
        html = html + "<body>";

        // Add header content block with logo on the right
        html = html
                + """
                                <div class='header'>
                                <h1>
                                <a href='/'><img src='ClimateLogo.png' class='top-image' alt='Website Logo' height='120' width = '120' style='float: left;'></a>
                                    Climate Change Awareness
                                </h1>
                            </div>
                        """;

        // Add the topnav
        // This uses a Java v15+ Text Block
        html = html + """
                <div class='topnav'>
                <a href='/'>Homepage</a>
                <a href='mission.html'>Our Mission</a>
                <a href='page2A.html'>Sub Task 2.A</a>
                <a href='page2B.html'>Sub Task 2.B</a>
                <a href='page2C.html'>Sub Task 2.C</a>
                <a href='page3A.html'>Sub Task 3.A</a>
                <a href='page3B.html'>Sub Task 3.B</a>
                <a href='page3C.html'>Sub Task 3.C</a>
                <a href='PageHelp.html'>Help Page</a>
                </div>
                        """;

        // Add Div for page Content
        html = html + "<div class='content'>";

        // Add HTML for heading
        html = html + "<h2>Introduction to Climate Change Awareness</h2>";

        // Add HTML paragraph description
        html = html
                + "<p>Climate change is a growing issue for not only the world but for your futures and lives. Throughout this website we are giving you multiple tools to research and view this data for yourselves.</p>";

        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<Climate> populationTempRanges = jdbc.getPopulationTempRanges();
        int firstYear = populationTempRanges.get(0).getYear();
        String firstPopulation = String.format("%,d", populationTempRanges.get(0).getPopulationLevel());
        float firstTemp = populationTempRanges.get(0).getAverageTemperature();
        int secondYear = populationTempRanges.get(1).getYear();
        String secondPopulation = String.format("%,d", populationTempRanges.get(1).getPopulationLevel());
        float secondTemp = populationTempRanges.get(1).getAverageTemperature();

        ArrayList<Climate> tempYearRange = jdbc.getGlobalTempYears();

        int temperatureYears = tempYearRange.get(tempYearRange.size() - 1).getYear() - tempYearRange.get(0).getYear();

        int populationYears = populationTempRanges.get(1).getYear() - populationTempRanges.get(0).getYear();

        // Add HTML data specifications(1A)
        html = html
                + "<p>Here is a look at the ranges of data available and the global population and temperatures at the times. The data begins at "
                +
                "<strong>" + firstYear + "</strong> where the global population was <strong>" + firstPopulation
                + "</strong> and the average temperature was " +
                "<strong>" + firstTemp + "</strong>. It then ends at <strong>" + secondYear
                + "</strong> where the global population was <strong>" + secondPopulation +
                "</strong> and the average temperature was <strong>" + secondTemp + "</strong>. There is <strong>"
                + populationYears +
                "</strong> years of data for global population, however, there is <strong>" + temperatureYears
                + "</strong> years of data for global temperature as more data is available.</p>";

        html = html + "<h3>Climate Change Data Overview</h3>";

        // Add table for global temperature and population ranges
        html = html
                + """
                        <table>
                              <tr>
                                <th>Year</th>
                                <th>Population</th>
                                <th>Average Temperature</th>
                                <th>Minimum Temperature</th>
                                <th>Maximum Temperature</th>
                              </tr>
                              """;

        for (int i = 0; i < populationTempRanges.size(); ++i) {
            html = html + " <tr> <td>" + populationTempRanges.get(i).getYear() + "</td> " + "<td>"
                    + String.format("%,d", populationTempRanges.get(i).getPopulationLevel()) + "</td>" + "<td>"
                    + populationTempRanges.get(i).getAverageTemperature() + "</td>" + "<td>"
                    + populationTempRanges.get(i).getMinimumTemperature() + "</td>" + "<td>"
                    + populationTempRanges.get(i).getMaximumTemperature() + "</td> </tr>";
        }

        html = html + "</table>";

        // Close Content div
        html = html + "</div>";

        // Bar chart for temperature range
        html = html + "<div id='barchart'></div>";

        html = html + "<script type='text/javascript' src='https://www.gstatic.com/charts/loader.js'></script>";
        html = html + "<script type='text/javascript'>";
        html = html + "google.charts.load('current', {'packages':['corechart']});";
        html = html + "google.charts.setOnLoadCallback(drawChart);";
        html = html + "function drawChart() {";
        html = html + "var data = google.visualization.arrayToDataTable([";
        html = html + "['Year', 'Population'],";
        html = html + "[" + firstYear + ", " + populationTempRanges.get(0).getPopulationLevel() + "],";
        html = html + "[" + secondYear + ", " + populationTempRanges.get(1).getPopulationLevel() + "],";
        html = html + "]);";
        html = html + "var options = {'title':'Population Change', 'width':550, 'height':400};";
        html = html + "var chart = new google.visualization.ColumnChart(document.getElementById('barchart'));";
        html = html + "chart.draw(data, options);";
        html = html + "}";
        html = html + "</script>";

        // Testing for the extension graph we need to do
        jdbc = new JDBCConnection();
        ArrayList<Climate> Countries = jdbc.getCountryClimateData();
        // jdbc = new JDBCConnection(); Found out you only need one of these
        ArrayList<Climate> Years = jdbc.getCountryYearsOfData();
        int currentYearIndex = 0;
        html = html + "<h4> Average Temperature Of The World In " +
                Years.get(0).getYear() + ".</h4>";
        html = html
                + "<p> Please note data on certain countries may be unavailable over different time periods </p> ";
        html = html + "<button onclick='updateGraph()'>Next Year</button>";
        // attempt at a geo graph
        html = html + "<div id='regions_div'></div>";
        html = html + """
                        <script type='text/javascript' src='https://www.gstatic.com/charts/loader.js'></script>
                <script type='text/javascript'>
                  google.charts.load('current', {
                    'packages':['geochart'],
                  });
                  google.charts.setOnLoadCallback(drawRegionsMap);

                  function drawRegionsMap() {
                    var data = google.visualization.arrayToDataTable([
                        ['Country', 'Average Temperature'],
                        """;

        for (Climate Country : Countries) {
            // this is to display the first year of data from the db eg just change the
            // number 0 to something else to show another year

            if (Country.getYear() == Years.get(0).getYear()) {
                html = html + "['" + Country.getCountryName() + "', " + Country.getAverageTemperature() + "],";
            }
        }
        html = html + """
                    ]);

                    var options = {};

                    var chart = new google.visualization.GeoChart(document.getElementById('regions_div'));

                    chart.draw(data, options);
                  }
                </script>
                """;
        // TESTING html = html + "<p>" + Countries.get(0).getYear() + "</p>";
        // Footer
        html = html + """
                    <div class='footer'>
                        <p>COSC2803 - Studio Project Starter Code (Apr23)</p>
                    </div>
                """;

        // Finish the HTML webpage
        html = html + "</body>" + "</html>";

        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(html);
    }

}
