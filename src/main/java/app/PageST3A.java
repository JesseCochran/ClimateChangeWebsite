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
public class PageST3A implements Handler {

  // URL of this page relative to http://localhost:7001/
  public static final String URL = "/page3A.html";

  @Override
  public void handle(Context context) throws Exception {
    // Create a simple HTML webpage in a String
    String html = "<html>";

    // Add some Head information
    html = html + "<head>" +
        "<title>Subtask 3.1</title>";

    // Add some CSS (external file)
    html = html + "<link rel='stylesheet' type='text/css' href='common.css' />";
    html = html + "<link rel='stylesheet' type='text/css' href='ST3A.css' />";
    html = html + "<link rel='stylesheet' type='text/css' href='burgerNav.css' />";

    // adds a cool icon on the nav menu
    html = html
        + "<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css'>";
    html = html + "</head>";

    // Add the body
    html = html + "<body>";

    // Add header content block
    html = html
        + """
                <div class='header'>
                    <h1><a href='/'><img src='ClimateLogo.png' class='top-image' alt='Website Logo' height='120' width = '120' style='float: left;'></a>
                    Climate Change Awareness</h1>
                </div>
            """;
    html = html + "<script>";
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
    // Add the topnav
    // This uses a Java v15+ Text Block
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

    // Add HTML for the page content
    html = html + """
        <h2>Identify changes in temperature over extended periods</h2>
        """;

    html = html + "<p>This page allows you to select multiple allows you to view average temperatures over different time periods and compare the changes to other geographic locations over the same period." +
                  " As a result you can actively see the ways climate change has affected temperature change over the coarse of history.</p>";
    html = html + "<form action='/page3A.html' method='post'>";
    html = html + "<p><strong>Note:</strong> If data is unavailable it will be presented in the table as '0.0'</p>";
    // geo location drop down
    html = html + "<h3>Choose a Geographic Type & Location</h3>";
    html = html + "<div class='location-container'>";
    html = html + "<div id='top'>";
    html = html + "     <label for='GeoLocation_drop'>Select Global, Country, State or City:</label>";
    html = html + "     <select id='GeoLocation_drop' name='GeoLocation_drop' onchange='showSelectedElements()' size='1'>";
    html = html + "<option value='' disabled selected hidden>--select type--</option>";
    html = html + "     <option>Global</option>";
    html = html + "     <option>Country</option>";
    html = html + "     <option>State</option>";
    html = html + "     <option>City</option>";
    html = html + "     </select>";
    html = html + "</div>";

    JDBCConnection jdbc = new JDBCConnection();
    ArrayList<Climate> countryNames = jdbc.getCountryName();

    // country select dropdown
    html = html + "<div id='geoLayer1' class='hide-element'>";
    html = html + "     <label for='Country1_drop'>Select a Country:</label>";
    html = html + "     <select id='Country1_drop' name='Country1_drop' size='1'>";
    html = html + "<option value='' disabled selected hidden>--select country--</option>";
    for (Climate countryName : countryNames) {
      html = html + "<option>" + countryName.getCountryName() + "</option>";
    }
    html = html + "</select>";
    html = html + "</div>";

    ArrayList<Climate> stateNames = jdbc.getStateName();
    // state select dropdown
    html = html + "<div id='geoLayer2' class='hide-element'>";
    html = html + "     <label for='State1_drop'>Select a State:</label>";
    html = html + "     <select id='State1_drop' name='State1_drop' size='1'>";
    html = html + "<option value='' disabled selected hidden>--select state--</option>";
    for (Climate stateName : stateNames) {
      html = html + "<option>" + stateName.getStateName() + "</option>";
    }
    html = html + "</select>";
    html = html + "</div>";

    ArrayList<Climate> cityNames = jdbc.getCityName();
    // city select dropdown
    html = html + "<div id='geoLayer3' class='hide-element'>";
    html = html + "     <label for='City1_drop'>Select a City:</label>";
    html = html + "     <select id='City1_drop' name='City1_drop' size='1'>";
    html = html + "<option value='' disabled selected hidden>--select city--</option>";
    for (Climate cityName : cityNames) {
      html = html + "<option>" + cityName.getCityName() + "</option>";
    }
    html = html + "     </select>";
    html = html + "</div>";

    html = html + "</div>";

    html = html + "<h3>Choose a Start Year & Time Period</h3>";
    ArrayList<Climate> years = jdbc.getYearRange3A();
    html = html + "<div class='year-container'>";
    html = html + "<div id='startYearLayer'>";
    html = html + "<label for='StartYear1_drop'>Select a Start Year:</label>";
    html = html + "<select id='StarYear1_drop' name='StartYear1_drop' size='1'>";
    html = html + "<option value='' disabled selected hidden>--select year--</option>";
    for (Climate year : years) {
      html = html + "<option>" + year.getYear() + "</option>";
    }
    html = html + "</select>";
    html = html + "</div>";

    html = html + "<div id='timePeriodLayer'>";
    html = html + "<label for='TimePeriod_drop'>Select the Time Period (in years):</label>";
    html = html + "<select id='TimePeriod_drop' name='TimePeriod_drop' size='1'>";
    html = html + "<option value='' disabled selected hidden>--select period--</option>";
    for (int i = 1; i < years.size() + 1; ++i) {
      html = html + "<option>" + i + "</option>";
    }
    html = html + "</select>";
    html = html + "</div>";

    html = html + "</div>";

    html = html + "<h3>Add More Start Years</h3>";
    html = html + "<div class='year-container'>";
    html = html + "<div id='addYear1Layer'>";
    html = html + "<label for='StartYear2_drop'>Add a Start Year:</label>";
    html = html + "<select id='StarYear2_drop' name='StartYear2_drop' size='1'>";
    html = html + "<option value='' disabled selected hidden>--select year--</option>";
    for (Climate year : years) {
      html = html + "<option>" + year.getYear() + "</option>";
    }
    html = html + "</select>";
    html = html + "</div>";

    html = html + "<div id='addYear2Layer'>";
    html = html + "<label for='StartYear3_drop'>Add a Start Year:</label>";
    html = html + "<select id='StarYear3_drop' name='StartYear3_drop' size='1'>";
    html = html + "<option value='' disabled selected hidden>--select year--</option>";
    for (Climate year : years) {
      html = html + "<option>" + year.getYear() + "</option>";
    }
    html = html + "</select>";
    html = html + "</div>";

    html = html + "<div id='addYear3Layer'>";
    html = html + "<label for='StartYear4_drop'>Add a Start Year:</label>";
    html = html + "<select id='StarYear4_drop' name='StartYear4_drop' size='1'>";
    html = html + "<option value='' disabled selected hidden>--select year--</option>";
    for (Climate year : years) {
      html = html + "<option>" + year.getYear() + "</option>";
    }
    html = html + "</select>";
    html = html + "</div>";

    html = html + "<div id='addYear4Layer'>";
    html = html + "<label for='StartYear5_drop'>Add a Start Year:</label>";
    html = html + "<select id='StarYear5_drop' name='StartYear5_drop' size='1'>";
    html = html + "<option value='' disabled selected hidden>--select year--</option>";
    for (Climate year : years) {
      html = html + "<option>" + year.getYear() + "</option>";
    }
    html = html + "</select>";
    html = html + "</div>";

    html = html + "</div>";

    html = html + "<h3>Add More Locations to Compare</h3>";
    html = html + "<div class='location-container'>";
    html = html + "<div id='country1Layer' class='hide-element'>";
    html = html + "     <label for='Country2_drop'>Add a Country:</label>";
    html = html + "     <select id='Country2_drop' name='Country2_drop' size='1'>";
    html = html + "<option value='' disabled selected hidden>--select country--</option>";
    for (Climate countryName : countryNames) {
      html = html + "<option>" + countryName.getCountryName() + "</option>";
    }
    html = html + "</select>";
    html = html + "</div>";

    html = html + "<div id='country2Layer' class='hide-element'>";
    html = html + "     <label for='Country3_drop'>Add a Country:</label>";
    html = html + "     <select id='Country3_drop' name='Country3_drop' size='1'>";
    html = html + "<option value='' disabled selected hidden>--select country--</option>";
    for (Climate countryName : countryNames) {
      html = html + "<option>" + countryName.getCountryName() + "</option>";
    }
    html = html + "</select>";
    html = html + "</div>";

    html = html + "<div id='country3Layer' class='hide-element'>";
    html = html + "     <label for='Country4_drop'>Add a Country:</label>";
    html = html + "     <select id='Country4_drop' name='Country4_drop' size='1'>";
    html = html + "<option value='' disabled selected hidden>--select country--</option>";
    for (Climate countryName : countryNames) {
      html = html + "<option>" + countryName.getCountryName() + "</option>";
    }
    html = html + "</select>";
    html = html + "</div>";


    html = html + "<div id='state1Layer' class='hide-element'>";
    html = html + "     <label for='State2_drop'>Add a State:</label>";
    html = html + "     <select id='State2_drop' name='State2_drop' size='1'>";
    html = html + "<option value='' disabled selected hidden>--select state--</option>";
    for (Climate stateName : stateNames) {
      html = html + "<option>" + stateName.getStateName() + "</option>";
    }
    html = html + "</select>";
    html = html + "</div>";

    html = html + "<div id='state2Layer' class='hide-element'>";
    html = html + "     <label for='State3_drop'>Add a State:</label>";
    html = html + "     <select id='State3_drop' name='State3_drop' size='1'>";
    html = html + "<option value='' disabled selected hidden>--select state--</option>";
    for (Climate stateName : stateNames) {
      html = html + "<option>" + stateName.getStateName() + "</option>";
    }
    html = html + "</select>";
    html = html + "</div>";

    html = html + "<div id='state3Layer' class='hide-element'>";
    html = html + "     <label for='State4_drop'>Add a State:</label>";
    html = html + "     <select id='State4_drop' name='State4_drop' size='1'>";
    html = html + "<option value='' disabled selected hidden>--select state--</option>";
    for (Climate stateName : stateNames) {
      html = html + "<option>" + stateName.getStateName() + "</option>";
    }
    html = html + "</select>";
    html = html + "</div>";


    html = html + "<div id='city1Layer' class='hide-element'>";
    html = html + "     <label for='City2_drop'>Add a City:</label>";
    html = html + "     <select id='City2_drop' name='City2_drop' size='1'>";
    html = html + "<option value='' disabled selected hidden>--select city--</option>";
    for (Climate cityName : cityNames) {
      html = html + "<option>" + cityName.getCityName() + "</option>";
    }
    html = html + "</select>";
    html = html + "</div>";

    html = html + "<div id='city2Layer' class='hide-element'>";
    html = html + "     <label for='City3_drop'>Add a City:</label>";
    html = html + "     <select id='City3_drop' name='City3_drop' size='1'>";
    html = html + "<option value='' disabled selected hidden>--select city--</option>";
    for (Climate cityName : cityNames) {
      html = html + "<option>" + cityName.getCityName() + "</option>";
    }
    html = html + "</select>";
    html = html + "</div>";

    html = html + "<div id='city3Layer' class='hide-element'>";
    html = html + "     <label for='City4_drop'>Add a City:</label>";
    html = html + "     <select id='City4_drop' name='City4_drop' size='1'>";
    html = html + "<option value='' disabled selected hidden>--select city--</option>";
    for (Climate cityName : cityNames) {
      html = html + "<option>" + cityName.getCityName() + "</option>";
    }
    html = html + "</select>";
    html = html + "</div>";

    html = html + "</div>";

    // Sorting order
    html = html + """
        <div class='form-group'>
        <h3>Sort By Change in Average Temperature</h3>
        <input type='radio' id ='SortOrderNone' name = 'SortOrder' value='False' checked>
        <label class='radio-label' for='SortOrderNone'>Neither</label>
        <input type='radio' id='SortOrderAsc' name='SortOrder' value='Ascending'>
        <label class='radio-label' for='SortOrderAsc'>Low to High</label>
        <input type='radio' id='SortOrderDes' name='SortOrder' value='Descending'>
         <label class='radio-label' for='SortOrderDes'>High to Low</label>
         </div>
            """;

    html = html + "<button class='showTable' type='submit' class='btn btn-primary'>Show Table</button>";
    html = html + "<input class='reset' type='reset' value='Reset'>";
    html = html + "</form>";

    html = html + "<script>";
    html = html + "function showSelectedElements() {";
    html = html + "var GeoLocation_drop = document.getElementById('GeoLocation_drop');";
    html = html + "var selectedValue = GeoLocation_drop.value;";
    html = html + "var geoLayer1 = document.getElementById('geoLayer1');";
    html = html + "var geoLayer2 = document.getElementById('geoLayer2');";
    html = html + "var geoLayer3 = document.getElementById('geoLayer3');";
    html = html + "var country1Layer = document.getElementById('country1Layer');";
    html = html + "var country2Layer = document.getElementById('country2Layer');";
    html = html + "var country3Layer = document.getElementById('country3Layer');";
    html = html + "var state1Layer = document.getElementById('state1Layer');";
    html = html + "var state2Layer = document.getElementById('state2Layer');";
    html = html + "var state3Layer = document.getElementById('state3Layer');";
    html = html + "var city1Layer = document.getElementById('city1Layer');";
    html = html + "var city2Layer = document.getElementById('city2Layer');";
    html = html + "var city3Layer = document.getElementById('city3Layer');";

    html = html + "geoLayer1.style.display = 'none';";
    html = html + "geoLayer2.style.display = 'none';";
    html = html + "geoLayer3.style.display = 'none';";
    html = html + "country1Layer.style.display = 'none';";
    html = html + "country2Layer.style.display = 'none';";
    html = html + "country3Layer.style.display = 'none';";
    html = html + "state1Layer.style.display = 'none';";
    html = html + "state2Layer.style.display = 'none';";
    html = html + "state3Layer.style.display = 'none';";
    html = html + "city1Layer.style.display = 'none';";
    html = html + "city2Layer.style.display = 'none';";
    html = html + "city3Layer.style.display = 'none';";

    html = html + "if(selectedValue === 'Country') {";
    html = html + "geoLayer1.style.display = 'block';";
    html = html + "country1Layer.style.display = 'block';";
    html = html + "country2Layer.style.display = 'block';";
    html = html + "country3Layer.style.display = 'block';";
    html = html + "}";
    html = html + "else if (selectedValue === 'State') {";
    html = html + "geoLayer2.style.display = 'block';";
    html = html + "state1Layer.style.display = 'block';";
    html = html + "state2Layer.style.display = 'block';";
    html = html + "state3Layer.style.display = 'block';";
    html = html + "}";
    html = html + "else if (selectedValue === 'City') {";
    html = html + "geoLayer3.style.display = 'block';";
    html = html + "city1Layer.style.display = 'block';";
    html = html + "city2Layer.style.display = 'block';";
    html = html + "city3Layer.style.display = 'block';";
    html = html + "}";
    html = html + "}";
    html = html + "</script>";



    String geoLocation = context.formParam("GeoLocation_drop");
    String country1 = context.formParam("Country1_drop");
    String state1 = context.formParam("State1_drop");
    String city1 = context.formParam("City1_drop");
    String startYear1 = context.formParam("StartYear1_drop");
    String timePeriod = context.formParam("TimePeriod_drop");
    String startYear2 = context.formParam("StartYear2_drop");
    String startYear3 = context.formParam("StartYear3_drop");
    String startYear4 = context.formParam("StartYear4_drop");
    String startYear5 = context.formParam("StartYear5_drop");
    String country2 = context.formParam("Country2_drop");
    String country3 = context.formParam("Country3_drop");
    String country4 = context.formParam("Country4_drop");
    String state2 = context.formParam("State2_drop");
    String state3 = context.formParam("State3_drop");
    String state4 = context.formParam("State4_drop");
    String city2 = context.formParam("City2_drop");
    String city3 = context.formParam("City3_drop");
    String city4 = context.formParam("City4_drop");
    String sort = context.formParam("SortOrder");

    if(geoLocation == null) {
      html = html + "<h3>Please Fill Out The Form Above</h3>";
    }
    else if(geoLocation.equals("Global")) {
      if(timePeriod != null && startYear1 != null) {
      html = html + outputGlobalData(geoLocation, startYear1, timePeriod, startYear2, startYear3, startYear4, startYear5, sort);
      }
      else {
        html = html + "<h3>Please Select At Least One Start Year and Time Period</h3>";
      }
    }
    else if (geoLocation.equals("Country")) {
      if(country1 != null && timePeriod != null && startYear1 != null) {
        html = html + outputData(geoLocation, country1, startYear1, timePeriod, startYear2, startYear3, startYear4, startYear5, country2, country3, country4, sort);
      }
      else {
        html = html + "<h3>Please Select At Least One Country, Start Year and Time Period</h3>";
      }
    }
    else if (geoLocation.equals("State")) {
      if(state1 != null && timePeriod != null && startYear1 != null) {
        html = html + outputData(geoLocation, state1, startYear1, timePeriod, startYear2, startYear3, startYear4, startYear5, state2, state3, state4, sort);
      }
      else {
        html = html + "<h3>Please Select At least One State, Start Year and Time Period</h3>";
      }
    }
    else if (geoLocation.equals("City")) {
      if(city1 != null && timePeriod != null && startYear1 != null) {
        html = html + outputData(geoLocation, city1, startYear1, timePeriod, startYear2, startYear3, startYear4, startYear5, city2, city3, city4, sort);
      }
      else {
        html = html + "<h3>Please Select At least One City, Start Year and Time Period</h3>";
      }
    }

    // Close Content div
    html = html + "</div>";

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

  private String outputGlobalData(String geoType, String startYear1, String timePeriod, String startYear2, 
  String startYear3, String startYear4, String startYear5, String sort) {
    String html = "<div class='table-container'>";
    JDBCConnection jdbc = new JDBCConnection();
    ArrayList<Climate> globalTempData; 

    if (sort.equals("False")) {
      sort = ")) ORDER BY StartDate ASC; ";
      globalTempData = jdbc.get3AGlobalTableData(geoType, startYear1, timePeriod, startYear2, startYear3, startYear4, startYear5, sort);
      html = html + "<div>";
      html = html + "<h3>Global</h3>";
      html = html + "<table> <tr>";
      html = html + "<th>Year</th>";
      html = html + "<th>Average Temperature over " + timePeriod + " years</th>";
      html = html + "<th>Temperature Change from Previous Period</th> </tr>";
      for(int i = 0; i < globalTempData.size(); ++i) {
        html = html + "<tr> <td>" + globalTempData.get(i).getYear() + "</td> " + "<td>";
        html = html + globalTempData.get(i).getAverageTemperature() + "</td> " + "<td>";
        html = html + globalTempData.get(i).getAverageDifference() + "</td> " + "</tr>";
      }
      html = html + "</table>";
      html = html + "</div>";
    }
    else if (sort.equals("Ascending")) {
      sort = ")) ORDER BY AvgDiff ASC; ";
      globalTempData = jdbc.get3AGlobalTableData(geoType, startYear1, timePeriod, startYear2, startYear3, startYear4, startYear5, sort);
      html = html + "<div>";
      html = html + "<h3>Global</h3>";
      html = html + "<table> <tr>";
      html = html + "<th>Year</th>";
      html = html + "<th>Average Temperature over " + timePeriod + " years</th>";
      html = html + "<th>Average Difference </th> </tr>";
      for(int i = 0; i < globalTempData.size(); ++i) {
        html = html + "<tr> <td>" + globalTempData.get(i).getYear() + "</td> " + "<td>";
        html = html + globalTempData.get(i).getAverageTemperature() + "</td> " + "<td>";
        html = html + globalTempData.get(i).getAverageDifference() + "</td> " + "</tr>";
      }
      html = html + "</table>";
      html = html + "</div>";
    }
    else if (sort.equals("Descending")) {
      sort = ")) ORDER BY AvgDiff DESC; ";
      globalTempData = jdbc.get3AGlobalTableData(geoType, startYear1, timePeriod, startYear2, startYear3, startYear4, startYear5, sort);
      html = html + "<div>";
      html = html + "<h3>Global</h3>";
      html = html + "<table> <tr>";
      html = html + "<th>Year</th>";
      html = html + "<th>Average Temperature over " + timePeriod + " years</th>";
      html = html + "<th>Average Difference </th> </tr>";
      for(int i = 0; i < globalTempData.size(); ++i) {
        html = html + "<tr> <td>" + globalTempData.get(i).getYear() + "</td> " + "<td>";
        html = html + globalTempData.get(i).getAverageTemperature() + "</td> " + "<td>";
        html = html + globalTempData.get(i).getAverageDifference() + "</td> " + "</tr>";
      }
      html = html + "</table>";
      html = html + "</div>";
    }

    html = html + "</div>";

    return html;
  }

  private String outputData(String geoType, String geoName1, String startYear1, String timePeriod, String startYear2, 
  String startYear3, String startYear4, String startYear5, String geoName2, String geoName3, String geoName4, String sort) {
    
    String html = "<div class='table-container'>";

    if (geoType.equals("Country") && sort.equals("False")) {
      sort = ")) ORDER BY StartDate ASC; ";
      html = html + tableFormat(geoType, geoName1, startYear1, timePeriod, startYear2, startYear3, startYear4, startYear5, sort);
      if (geoName2 != null) {
        html = html + tableFormat(geoType, geoName2, startYear1, timePeriod, startYear2, startYear3, startYear4, startYear5, sort);
      }
      if (geoName3 != null) {
        html = html + tableFormat(geoType, geoName3, startYear1, timePeriod, startYear2, startYear3, startYear4, startYear5, sort);
      }
      if (geoName4 != null) {
        html = html + tableFormat(geoType, geoName4, startYear1, timePeriod, startYear2, startYear3, startYear4, startYear5, sort);
      }
    }
    else if (geoType.equals("Country") && sort.equals("Ascending")) {
      sort = ")) ORDER BY AvgDiff ASC; ";
      html = html + tableFormat(geoType, geoName1, startYear1, timePeriod, startYear2, startYear3, startYear4, startYear5, sort);
      if (geoName2 != null) {
        html = html + tableFormat(geoType, geoName2, startYear1, timePeriod, startYear2, startYear3, startYear4, startYear5, sort);
      }
      if (geoName3 != null) {
        html = html + tableFormat(geoType, geoName3, startYear1, timePeriod, startYear2, startYear3, startYear4, startYear5, sort);
      }
      if (geoName4 != null) {
        html = html + tableFormat(geoType, geoName4, startYear1, timePeriod, startYear2, startYear3, startYear4, startYear5, sort);
      }
    }
    else if (geoType.equals("Country") && sort.equals("Descending")) {
      sort = ")) ORDER BY AvgDiff DESC; ";
      html = html + tableFormat(geoType, geoName1, startYear1, timePeriod, startYear2, startYear3, startYear4, startYear5, sort);
      if (geoName2 != null) {
        html = html + tableFormat(geoType, geoName2, startYear1, timePeriod, startYear2, startYear3, startYear4, startYear5, sort);
      }
      if (geoName3 != null) {
        html = html + tableFormat(geoType, geoName3, startYear1, timePeriod, startYear2, startYear3, startYear4, startYear5, sort);
      }
      if (geoName4 != null) {
        html = html + tableFormat(geoType, geoName4, startYear1, timePeriod, startYear2, startYear3, startYear4, startYear5, sort);
      }
    }

    if (geoType.equals("State") && sort.equals("False")) {
      sort = ")) ORDER BY StartDate ASC; ";
      html = html + tableFormat(geoType, geoName1, startYear1, timePeriod, startYear2, startYear3, startYear4, startYear5, sort);
      if (geoName2 != null) {
        html = html + tableFormat(geoType, geoName2, startYear1, timePeriod, startYear2, startYear3, startYear4, startYear5, sort);
      }
      if (geoName3 != null) {
        html = html + tableFormat(geoType, geoName3, startYear1, timePeriod, startYear2, startYear3, startYear4, startYear5, sort);
      }
      if (geoName4 != null) {
        html = html + tableFormat(geoType, geoName4, startYear1, timePeriod, startYear2, startYear3, startYear4, startYear5, sort);
      }
    }
    else if (geoType.equals("State") && sort.equals("Ascending")) {
      sort = ")) ORDER BY AvgDiff ASC; ";
      html = html + tableFormat(geoType, geoName1, startYear1, timePeriod, startYear2, startYear3, startYear4, startYear5, sort);
      if (geoName2 != null) {
        html = html + tableFormat(geoType, geoName2, startYear1, timePeriod, startYear2, startYear3, startYear4, startYear5, sort);
      }
      if (geoName3 != null) {
        html = html + tableFormat(geoType, geoName3, startYear1, timePeriod, startYear2, startYear3, startYear4, startYear5, sort);
      }
      if (geoName4 != null) {
        html = html + tableFormat(geoType, geoName4, startYear1, timePeriod, startYear2, startYear3, startYear4, startYear5, sort);
      }
    }
    else if (geoType.equals("State") && sort.equals("Descending")) {
      sort = ")) ORDER BY AvgDiff DESC; ";
      html = html + tableFormat(geoType, geoName1, startYear1, timePeriod, startYear2, startYear3, startYear4, startYear5, sort);
      if (geoName2 != null) {
        html = html + tableFormat(geoType, geoName2, startYear1, timePeriod, startYear2, startYear3, startYear4, startYear5, sort);
      }
      if (geoName3 != null) {
        html = html + tableFormat(geoType, geoName3, startYear1, timePeriod, startYear2, startYear3, startYear4, startYear5, sort);
      }
      if (geoName4 != null) {
        html = html + tableFormat(geoType, geoName4, startYear1, timePeriod, startYear2, startYear3, startYear4, startYear5, sort);
      }
    }

    if (geoType.equals("City") && sort.equals("False")) {
      sort = ")) ORDER BY StartDate ASC; ";
      html = html + tableFormat(geoType, geoName1, startYear1, timePeriod, startYear2, startYear3, startYear4, startYear5, sort);
      if (geoName2 != null) {
        html = html + tableFormat(geoType, geoName2, startYear1, timePeriod, startYear2, startYear3, startYear4, startYear5, sort);
      }
      if (geoName3 != null) {
        html = html + tableFormat(geoType, geoName3, startYear1, timePeriod, startYear2, startYear3, startYear4, startYear5, sort);
      }
      if (geoName4 != null) {
        html = html + tableFormat(geoType, geoName4, startYear1, timePeriod, startYear2, startYear3, startYear4, startYear5, sort);
      }
    }
    else if (geoType.equals("City") && sort.equals("Ascending")) {
      sort = ")) ORDER BY AvgDiff ASC; ";
      html = html + tableFormat(geoType, geoName1, startYear1, timePeriod, startYear2, startYear3, startYear4, startYear5, sort);
      if (geoName2 != null) {
        html = html + tableFormat(geoType, geoName2, startYear1, timePeriod, startYear2, startYear3, startYear4, startYear5, sort);
      }
      if (geoName3 != null) {
        html = html + tableFormat(geoType, geoName3, startYear1, timePeriod, startYear2, startYear3, startYear4, startYear5, sort);
      }
      if (geoName4 != null) {
        html = html + tableFormat(geoType, geoName4, startYear1, timePeriod, startYear2, startYear3, startYear4, startYear5, sort);
      }
    }
    else if (geoType.equals("City") && sort.equals("Descending")) {
      sort = ")) ORDER BY AvgDiff DESC; ";
      html = html + tableFormat(geoType, geoName1, startYear1, timePeriod, startYear2, startYear3, startYear4, startYear5, sort);
      if (geoName2 != null) {
        html = html + tableFormat(geoType, geoName2, startYear1, timePeriod, startYear2, startYear3, startYear4, startYear5, sort);
      }
      if (geoName3 != null) {
        html = html + tableFormat(geoType, geoName3, startYear1, timePeriod, startYear2, startYear3, startYear4, startYear5, sort);
      }
      if (geoName4 != null) {
        html = html + tableFormat(geoType, geoName4, startYear1, timePeriod, startYear2, startYear3, startYear4, startYear5, sort);
      }
    }

    html = html + "</div>";
    
    return html;
  }

  private String tableFormat(String geoType, String geoName, String startYear1, String timePeriod, String startYear2, 
  String startYear3, String startYear4, String startYear5, String sort) {
    String html = "<div>";
    html = html + "<h3>" + geoName + "</h3>";

    html = html + "<table> <tr>";
    html = html + "<th>Year</th>";
    html = html + "<th>Average Temperature over " + timePeriod + " years</th>";
    html = html + "<th>Temperature Change from Previous Period</th> </tr>";
    
    JDBCConnection jdbc = new JDBCConnection();
    ArrayList<Climate> tempData = jdbc.get3ATableData(geoType, geoName, startYear1, timePeriod, startYear2, startYear3, startYear4, startYear5, sort);

    for(int i = 0; i < tempData.size(); ++i) {
      html = html + "<tr> <td>" + tempData.get(i).getYear() + "</td> " + "<td>";
      html = html + tempData.get(i).getAverageTemperature() + "</td> " + "<td>";
      html = html + tempData.get(i).getAverageDifference() + "</td> " + "</tr>";
    }

    html = html + "</table>";
    html = html + "</div>";

    return html;
  }

}
