package app;

import java.util.ArrayList;

import io.javalin.http.Context;
import io.javalin.http.Handler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Year;

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
    html = html + "<link rel='stylesheet' type='text/css' href='common.css' />";
    html = html + "<link rel='stylesheet' type='text/css' href='burgerNav.css' />";
    // adds a cool icon on the nav menu
    html = html
        + "<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css'>";
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
    html = html + """
           <div class='topnav'>
                            <a href='/'>Home</a>
                            <div class='dropDown'>
                            <button class='dropbtn'>Climate Data and Analysis
                            <i class='fa fa-caret-down'></i>
                            </button>
                            <div class='dropdown-content'>
                            <a href='page2A.html'>Temperature & Population Change By Country/World</a>
                            <a href='page2B.html'>Temperature Change By State/City</a>
                            <a href='page2C.html'>Global Land-Ocean Temperature Change</a>
                            <a href='page3A.html'>Change In Temperature Over Extended Periods</a>
                            <a href='page3B.html'>Time Periods With Similar Temperature/Population</a>
                            <a href='page3C.html'>Comparison Of Global Temperature Data Over Extended Periods</a>
                            </div>
                            </div>
                            <div class='dropDown'>
                            <button class='dropbtn'>About Us
                            <i class='fa fa-caret-down'></i>
                            </button>
                            <div class='dropdown-content'>
                            <a href='mission.html'>Our Mission</a>
                            <a href='mission.html#persona-section'>Personas</a>
                            <a href='mission.html#aboutUs-section'>Contact Us</a>
                            </div>
                            </div>
                            <div class='dropDown'>
                            <button class='dropbtn'>Help & Support
                            <i class='fa fa-caret-down'></i>
                            </button>
                            <div class='dropdown-content'>
                            <a href='PageHelp.html'>Page Assistance</a>
                            <a href='PageHelp.html#faq-section'>FAQ</a>
                            <a href='PageHelp.html#advanced-section'>Advanced Features</a>
                            </div>
                            </div>
                            </div>
        """;

    html = html + "<script>";
    html = html + """

                        function openNav() {
          document.getElementById('mySidenav').style.width = '250px';
        }

        function closeNav() {
          document.getElementById('mySidenav').style.width = '0px';
        }
                                        """;
    // take to help page
    html = html + """

             // Function to navigate to the help page
        function goToHelpPage() {
          window.location.href = 'PageHelp.html';
        }
                                document.addEventListener('keydown', function(event) {
                      // Check if the h key (key code 72) is pressed
                      if (event.keyCode === 72) {
                        goToHelpPage();
                      }
                    });
                                """;
    // take to home page
    html = html + """
             // Function to navigate to the home page
        function goToHomePage() {
          window.location.href = '/';
        }

        document.addEventListener('keydown', function(event) {
          // Check if the Esc key (key code 27) is pressed
          if (event.keyCode === 27) {
            goToHomePage();
          }
        });
                    """;
    html = html + "</script>";
    html = html + "<div class='SideNavBar'>";
    html = html
        + """
                    <div id='mySidenav' class='sidenav'>
                        <a href='javascript:void(0)' class='closebtn' onclick='closeNav()'>&times;</a>
                        <a href='/'>Home</a>
                        <p>Climate Data and Analysis</p>
                        <a href='page2A.html'>Temperature & Population Change By Country/World</a>
                        <a href='page2B.html'>Temperature Change By State/City</a>
                        <a href='page2C.html'>Global Land-Ocean Temperature Change</a>
                        <a href='page3A.html'>Change In Temperature Over Extended Periods</a>
                        <a href='page3B.html'>Time Periods With Similar Temperature/Population</a>
                        <a href='page3C.html'>Comparison Of Global Temperature Data Over Extended Periods</a>
                        <p>About Us</p>
                        <a href='mission.html'>Our Mission</a>
                <a href='mission.html#persona-section'>Personas</a>
                <a href='mission.html#aboutUs-section'>Contact Us</a>
                <p>Help & Support</p>
                <a href='PageHelp.html'>Page Assistance</a>
                <a href='PageHelp.html#faq-section'>FAQ</a>
                <a href='PageHelp.html#advanced-section'>Advanced Features</a>

                    </div>
            <span style='color: #f1f1f1; position: fixed; top:10px; right:20px; font-size:40px; cursor:pointer' onclick='openNav()'> &#9776;</span>
                    """;
    html = html + "</div>";
    // Add Div for page Content
    html = html + "<div class='content'>";

    // Add HTML for heading
    html = html + "<h2>Welcome To Climate Change Awareness</h2>";
    html = html + "<h3 id='year_heading'> Average Temperature Global Heat Map</h3>";
    html = html + "<div id='regions_div' Style='height: 600px;'></div>";
    // Testing for the extension graph we need to do

    JDBCConnection jdbc = new JDBCConnection();
    ArrayList<Climate> Countries = jdbc.getCountryClimateData();
    ArrayList<Climate> Years = jdbc.getCountryYearsOfData();
    int currentYearIndex = Years.size() - 1;
    html = html + "<form action='/' method='post'>";
    if (context.formParam("year_dropdown") != null) {
      html = html + "<h4 id='year_heading'> Average Country Temperature In " +
          context.formParam("year_dropdown") + ".</h4>";
    } else {
      html = html + "<h4 id='year_heading'> Average Temperature Of The World In " +
          Years.get(Years.size() - 1).getYear() + ".</h4>";
    }

    html = html + "<p> Please note data on certain countries may be unavailable over different time periods </p> ";

    // Create dropdown menu for selecting year
    html = html + "<select id='year_dropdown' name='year_dropdown'>";
    for (Climate year : Years) {
      html = html + "<option value='" + year.getYear() + "'>" + year.getYear() + "</option>";
    }
    html = html + "</select>";

    // Add submit button to update the graph
    html = html + "<button class='showGraph' onclick='submitForm()'>Submit</button>";
    html = html + "</form>";

    String Year = context.formParam("year_dropdown");
    if (Year == null) {
      Year = Integer.toString(Years.size() - 1); // Set a default value if the selected value is null
    }

    // Add HTML paragraph description
    html = html
        + "<p>Climate change is a growing issue for not only the world but for your futures and lives. Throughout this website we are giving you multiple tools to research and view this data for yourselves.</p>";

    jdbc = new JDBCConnection();
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

    // html = html + "<div id='regions_div'></div>";
    html = html + "<script type='text/javascript' src='https://www.gstatic.com/charts/loader.js'></script>";
    html = html + "<script type='text/javascript'>";
    html = html + "google.charts.load('current', { 'packages':['geochart'] });";
    html = html + "google.charts.setOnLoadCallback(drawRegionsMap);";
    html = html + "var currentYearIndex = " + currentYearIndex + ";";
    html = html + "function submitForm() {";
    html = html + "var dropdown = document.getElementById('year_dropdown');";
    html = html + "currentYearIndex = dropdown.selectedIndex;";
    html = html + "var currentYear = dropdown.value;";
    html = html
        + "document.getElementById('year_heading').innerText = 'Average Temperature Of The World In ' + currentYear + '.';";
    html = html + "drawRegionsMap();";
    html = html + "}";
    html = html + "function drawRegionsMap() {";
    html = html + "var data = google.visualization.arrayToDataTable([";
    html = html + "['Country', 'Average Temperature'],";
    for (Climate country : Countries) {
      if (Year != null) {

        if (country.getYear() == Integer.parseInt(Year)) {
          html = html + "['" + country.getCountryName() + "', " + country.getAverageTemperature() + "],";
        }
        // else {
        // html = html + "['" + country.getCountryName() + "', " +
        // country.getAverageTemperature() + "],";
        // }
      } else {
        html = html + "['" + Countries.get(0).getCountryName() + "', "
            + Countries.get(0).getAverageTemperature() + "],";
      }
    }
    html = html + "]);";
    html = html + "var options = {colorAxis: {colors: ['#00FF00', '#FFFF00', '#FF0000']},};";
    html = html + "var chart = new google.visualization.GeoChart(document.getElementById('regions_div'));";
    html = html + "chart.draw(data, options);";
    html = html + "}";
    html = html + "</script>";

    // end content section
    html = html + "</div>";

    // *Testing */
    // html = html + "<p>" + currentYearIndex + "</p>";

    // html = html + "<p>" + Year + "</p>";

    // TESTING html = html + "<p>" + Countries.get(0).getYear() + "</p>";
    // Footer
    html = html
        + """
               <div class='footer'>

                <div class='footerBlock'>
                            <div class='footerColumn'>
                              <p style='margin-top: 0;'>Shallow View</p>
                              <a href='page2A.html'>Temperature & Population Change By Country/World</a>
                              <a href='page2B.html'>Temperature Change By State/City</a>
                              <a href='page2C.html'>Global Land-Ocean Temperature Change</a>
                            </div>
                            <div class='footerColumn'>
                              <p style='margin-top: 0;'>In-Depth View</p>
                              <a href='page3A.html'>Change In Temperature Over Extended Periods</a>
                              <a href='page3B.html'>Time Periods With Similar Temperature/Population</a>
                              <a href='page3C.html'>Comparison Of Global Temperature Data Over Extended Periods</a>
                            </div>
                            <div class='footerColumn'>
                              <p style='margin-top: 0;'>About</p>
                              <a href='mission.html'>Our Mission</a>
                              <a href='mission.html#persona-section'>Personas</a>
                              <a href='mission.html#aboutUs-section'>Contact Us</a>
                            </div>
                            <div class='footerColumn'>
                              <p style='margin-top: 0;'>Help & Support</p>
                              <a href='PageHelp.html'>Page Assistance</a>
                              <a href='PageHelp.html#faq-section'>FAQ</a>
                              <a href='PageHelp.html#advanced-section'>Advanced Features</a>

                            </div>
                          </div>
                        </div>
            """;

    // Finish the HTML webpage
    html = html + "</body>" + "</html>";

    // DO NOT MODIFY THIS
    // Makes Javalin render the webpage
    context.html(html);
  }

}
