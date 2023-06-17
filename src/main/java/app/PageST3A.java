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
            <button class='dropbtn'>Shallow View Of Climate Change
            <i class='fa fa-caret-down'></i>
            </button>
            <div class='dropdown-content'>
            <a href='page2A.html'>Temperature & Population Change By Country/World</a>
            <a href='page2B.html'>Temperature Change By State/City</a>
            <a href='page2C.html'>Global Land-Ocean Temperature Change</a>
            </div>
            </div>
            <div class='dropDown'>
            <button class='dropbtn'>In-Depth View Of Climate Change
            <i class='fa fa-caret-down'></i>
            </button>
            <div class='dropdown-content'>
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

    html = html + "<form action='/page2A.html' method='post'>";

    // geo location drop down
    html = html + "<h4>Choose a Geographic Type & Location</h4>";
    html = html + "<div class='container'>";
    html = html + "<div id='top'>";
    html = html + "     <label for='GeoLocation_drop'>Select Global, Country, State or City:</label>";
    html = html + "     <select id='GeoLocation_drop' name='GeoLocation_drop' size='1'>";
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
    html = html + "<div class='layer1'>";
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
    html = html + "<div class='layer2'>";
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
    html = html + "<div class='layer3'>";
    html = html + "     <label for='City1_drop'>Select a City:</label>";
    html = html + "     <select id='City1_drop' name='City1_drop' size='1'>";
    html = html + "<option value='' disabled selected hidden>--select city--</option>";
    for (Climate cityName : cityNames) {
      html = html + "<option>" + cityName.getCityName() + "</option>";
    }
    html = html + "     </select>";
    html = html + "</div>";

    html = html + "</div>";

    html = html + "<div class='form-group'>";
    ArrayList<Climate> years = jdbc.getYearRange3A();
    html = html + "<h4>Choose a Start Year & Time Period</h4>";
    html = html + "<label for='StartYear1_drop'>Select a Start Year:</label>";
    html = html + "<select id='StarYear1_drop name='StartYear2_drop' size='1'>";
    html = html + "<option value='' disabled selected hidden>--select year--</option>";
    for (Climate year : years) {
      html = html + "<option>" + year.getYear() + "</option>";
    }
    html = html + "</select>";

    html = html + "<label for='TimePeriod_drop'>Select the Time Period:</label>";
    html = html + "<select id='TimePeriod_drop name='TimePeriod_drop' size='1'>";
    html = html + "<option value='' disabled selected hidden>--select period--</option>";
    html = html + "<option>1 year</option>";
    for (int i = 2; i < years.size() + 1; ++i) {
      html = html + "<option>" + i + " years</option>";
    }
    html = html + "</select>";
    html = html + "</div>";

    html = html + "<div class='form-group'>";
    html = html + "<h4>Add More Start Years</h4>";
    html = html + "<label for='StartYear2_drop'>Add a Start Year:</label>";
    html = html + "<select id='StarYear2_drop name='StartYear2_drop' size='1'>";
    html = html + "<option value='' disabled selected hidden>--select year--</option>";
    for (Climate year : years) {
      html = html + "<option>" + year.getYear() + "</option>";
    }
    html = html + "</select>";

    html = html + "<label for='StartYear3_drop'>Add a Start Year:</label>";
    html = html + "<select id='StarYear3_drop name='StartYear3_drop' size='1'>";
    html = html + "<option value='' disabled selected hidden>--select year--</option>";
    for (Climate year : years) {
      html = html + "<option>" + year.getYear() + "</option>";
    }
    html = html + "</select>";

    html = html + "<label for='StartYear4_drop'>Add a Start Year:</label>";
    html = html + "<select id='StarYear4_drop name='StartYear4_drop' size='1'>";
    html = html + "<option value='' disabled selected hidden>--select year--</option>";
    for (Climate year : years) {
      html = html + "<option>" + year.getYear() + "</option>";
    }
    html = html + "</select>";

    html = html + "<label for='StartYear5_drop'>Add a Start Year:</label>";
    html = html + "<select id='StarYear5_drop name='StartYear5_drop' size='1'>";
    html = html + "<option value='' disabled selected hidden>--select year--</option>";
    for (Climate year : years) {
      html = html + "<option>" + year.getYear() + "</option>";
    }
    html = html + "</select>";
    html = html + "</div>";

    html = html + "<div class='form-group'>";
    html = html + "<h4>Add More Locations to Compare</h4>";
    html = html + "     <label for='Country2_drop'>Add a Country:</label>";
    html = html + "     <select id='Country2_drop' name='Country2_drop' size='1'>";
    html = html + "<option value='' disabled selected hidden>--select country--</option>";
    for (Climate countryName : countryNames) {
      html = html + "<option>" + countryName.getCountryName() + "</option>";
    }
    html = html + "</select>";

    html = html + "     <label for='Country3_drop'>Add a Country:</label>";
    html = html + "     <select id='Country3_drop' name='Country3_drop' size='1'>";
    html = html + "<option value='' disabled selected hidden>--select country--</option>";
    for (Climate countryName : countryNames) {
      html = html + "<option>" + countryName.getCountryName() + "</option>";
    }
    html = html + "</select>";

    html = html + "     <label for='Country4_drop'>Add a Country:</label>";
    html = html + "     <select id='Country4_drop' name='Country4_drop' size='1'>";
    html = html + "<option value='' disabled selected hidden>--select country--</option>";
    for (Climate countryName : countryNames) {
      html = html + "<option>" + countryName.getCountryName() + "</option>";
    }
    html = html + "</select>";
    html = html + "</div>";

    // Sorting order
    html = html + """
        <div class='form-group'>
        <h4>Sort By </h4>
        <input type='radio' id ='SortOrder' name = 'SortOrder' value='False' checked>
        <label class='radio-label' for='False'>Neither</label>
        <input type='radio' id='SortOrder' name='SortOrder' value='Ascending'>
        <label class='radio-label' for='Ascending'>Low to High</label>
        <input type='radio' id='SortOrder' name='SortOrder' value='Descending'>
         <label class='radio-label' for='Descending'>High to Low</label>
         </div>
            """;

    html = html + "<button class='showTable' type='submit' class='btn btn-primary'>Show Table</button>";

    html = html + "</form>";

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
    String sort = context.formParam("SortOrder");

    /*
     * if(geoLocation.equals(" ")) {
     * html = html + "<h3>Please Select a Geographic Type</h3>";
     * }
     * else if(geoLocation.equals("Country")) {
     * 
     * }
     */

    // Close Content div
    html = html + "</div>";

    // Footer
    html = html
        + """
                <div class='footer'>
             <h3 style='text-align: center; margin-top: 0; text-decoration: underline;'>Index</h3>
            <div class='footerBlock'>
                        <div class='footerColumn'>
                          <p style='margin-bottom: 0; margin-top: 0;'>Shallow View</p>
                          <a href='page2A.html'>Temperature & Population Change By Country/World</a>
                          <a href='page2B.html'>Temperature Change By State/City</a>
                          <a href='page2C.html'>Global Land-Ocean Temperature Change</a>
                        </div>
                        <div class='footerColumn'>
                          <p style='margin-bottom: 0; margin-top: 0;'>In-Depth View</p>
                          <a href='page3A.html'>Change In Temperature Over Extended Periods</a>
                          <a href='page3B.html'>Time Periods With Similar Temperature/Population</a>
                          <a href='page3C.html'>Comparison Of Global Temperature Data Over Extended Periods</a>
                        </div>
                        <div class='footerColumn'>
                          <p style='margin-bottom: 0; margin-top: 0;'>About</p>
                          <a href='mission.html'>Our Mission</a>
                          <a href='mission.html#persona-section'>Personas</a>
                          <a href='mission.html#aboutUs-section'>Contact Us</a>
                        </div>
                        <div class='footerColumn'>
                          <p style='margin-bottom: 0; margin-top: 0;'>Help & Support</p>
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
