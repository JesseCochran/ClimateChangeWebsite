package app;

import java.lang.Math;
import java.util.ArrayList;

import io.javalin.apibuilder.EndpointGroup;

//import javax.xml.crypto.Data;

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
public class PageST2A implements Handler {

    // URL of this page relative to http://localhost:7001/
    public static final String URL = "/page2A.html";

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        String html = "<html>";

        // Add some Head information
        html = html + "<head>" +
                "<title>Subtask 2.1</title>";

        // Add some CSS (external file)
        // adds a cool icon on the nav menu
        html = html
                + "<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css'>";
        html = html + "<link rel='stylesheet' type='text/css' href='common.css' />";
        html = html + "<link rel='stylesheet' type='text/css' href='burgerNav.css' />";
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
        // Add Div for page Content
        html = html + "<div class='content'>";

        // Add HTML for the page content
        html = html + """
                <h2>Focused view of temperature and population change by Country/World</h2>
                """;

        html = html
                + "<p>This page allows you to look at specific global temperature and population change over a time period"
                +
                " or compare changes in country population and temperature change. As a result you can find ways in which"
                +
                " population change has affected temperature change on a large scale.</p>";

        html = html
                + "<p><strong>Note:</strong> Only data where population and temperature is available has been used below.</p>";

        html = html + "<form action='/page2A.html' method='post'>";

        // Dropdown to select country or world data
        html = html + "<div>";
        html = html + "     <label for='CountryWorld_drop'>Select Global or Country data:</label>";
        html = html + "     <select id='CountryWorld_drop' name='CountryWorld_drop' size='1'>";
        html = html + "<option value='' disabled selected hidden>--select country/global--</option>";
        html = html + "     <option>Global</option>";
        html = html + "     <option>Country</option>";
        html = html + "     </select>";
        html = html + "</div>";

        // this connects the database to the start date drop down box.
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<Climate> years = jdbc.getPopulationYears();

        // Dropdown to select start year which updates end year based on result
        html = html + "<div>";
        html = html + "     <label for='StartYear_drop'>Select the start year:</label>";
        html = html
                + "     <select id='StartYear_drop' name='StartYear_drop' onchange='updateEndYearOptions()' size='1'>";
        html = html + "<option value='' disabled selected hidden>--select year--</option>";
        for (Climate year : years) {
            html = html + "<option>" + year.getYear() + "</option>";
        }
        html = html + "     </select>";
        html = html + " </div>";

        // Using some javascript to change the value of the end year drop down section
        // to be the same as the start year and vise versa
        html = html + "<script>";
        html = html + "var selectedStartYear = null;";
        html = html + "var selectedEndYear = null;";
        html = html + "function updateEndYearOptions() {";
        html = html + "   var startYearDropdown = document.getElementById('StartYear_drop');";
        html = html + "   var EndYearDropdown = document.getElementById('EndYear_drop');";
        html = html + "   var startYear = parseInt(startYearDropdown.value);";
        html = html + "   var endYear = " + years.get(years.size() - 1).getYear() + ";";
        html = html + "   var selectedEndYear = parseInt(EndYearDropdown.value);"; // Store selected value
        html = html + "   EndYearDropdown.innerHTML = '';"; // Clear existing options

        // This javascript code effectively makes the data in the end year section must
        // be in the range of the user selected date to the end of the avilable data in
        // the database.
        html = html + "   for (var year = startYear; year <= endYear; year++) {";
        html = html + "       var option = document.createElement('option');";
        html = html + "       option.text = year;";
        html = html + "       option.value = year;";
        html = html + "       EndYearDropdown.add(option);";
        html = html + "   }";
        html = html + "   if (selectedEndYear) EndYearDropdown.value = selectedEndYear;"; // Reapply selected value
        html = html + "}";
        // this code does the same for the start year value
        html = html + "function updateStartYearOptions() {";
        html = html + "   var startYearDropdown = document.getElementById('StartYear_drop');";
        html = html + "   var EndYearDropdown = document.getElementById('EndYear_drop');";
        html = html + "   var EndYear = parseInt(EndYearDropdown.value);";
        html = html + "   var selectedStartYear = parseInt(startYearDropdown.value);"; // Store selected value
        html = html + "   startYearDropdown.innerHTML = '';"; // Clear existing options
        html = html + "   for (var year = " + years.get(0).getYear() + "; year <= EndYear; year++) {";
        html = html + "       var option = document.createElement('option');";
        html = html + "       option.text = year;";
        html = html + "       option.value = year;";
        html = html + "       startYearDropdown.add(option);";
        html = html + "   }";
        html = html + "   if (selectedStartYear) startYearDropdown.value = selectedStartYear;"; // Reapply selected
                                                                                                // value
        html = html + "}";
        html = html + "</script>";

        html = html + "   <div>";
        html = html + "      <label for='EndYear_drop'>Select the end year:</label>";
        html = html
                + "      <select id='EndYear_drop' name='EndYear_drop' onchange='updateStartYearOptions()' size='1'>";
        html = html + "<option value='' disabled selected hidden>--select year--</option>";
        for (Climate year : years) {
            html = html + "<option>" + year.getYear() + "</option>";
        }
        html = html + "      </select>";
        html = html + "   </div>";

        // Dropdown to select how to format the data
        html = html + "<div>";
        html = html + "      <label for='TypeOrder_drop'>Select how you want to order the data:</label>";
        html = html + "      <select id='TypeOrder_drop' name='TypeOrder_drop' size='1'>";
        html = html + "<option value='' disabled selected hidden>--select order type--</option>";
        html = html + "      <option>Population Change</option>";
        html = html + "      <option>Temperature Change</option>";
        html = html + "      </select>";
        html = html + "</div>";

        // Sorting order
        html = html + """
                <div>
                <p>Sort By</p>
                <input type='radio' id='SortOrderAsc' name='SortOrder' value='Ascending' checked>
                <label class='radio-label' for='SortOrderAsc'>Low to High</label><br>
                <input type='radio' id='SortOrderDes' name='SortOrder' value='Descending'>
                 <label class='radio-label' for='SortOrderDes'>High to Low</label>
                 </div>
                    """;

        // submit button
        html = html + "<button class='showTable' type='submit' class='btn btn-primary'>Show Table</button>";
        html = html + "<input class='reset' type='reset' value='Reset'>";
        html = html + "</form>";
        // javascript to allow 'c' to also reset the data
        html = html + """
                    <script>
                    document.addEventListener('DOMContentLoaded', function() {
                        var button = document.querySelector('.reset');
                        document.addEventListener('keydown', function(event) {
                            // Check if the C key (key code 67) is pressed
                            if (event.keyCode == 67) {
                                button.click();
                            }
                        });
                    });
                    </script>
                """;

        String DataToOutput = context.formParam("CountryWorld_drop");
        String StartYear_drop = context.formParam("StartYear_drop");
        String EndYear_drop = context.formParam("EndYear_drop");
        String TypeOrder = context.formParam("TypeOrder_drop");
        String SortOrder = context.formParam("SortOrder");

        // validation for form
        if (DataToOutput == null) {
            html = html + "<h3>Please Select Country or Global Data</h3>";
        } else if (DataToOutput.equals("Global")) {
            if (StartYear_drop != null) {
                html = html + outputWorld(DataToOutput, StartYear_drop, EndYear_drop);
            } else {
                html = html + "<h3>Please Select a Start & End Date</h3>";
            }
        } else if (DataToOutput.equals("Country")) {
            if (TypeOrder != null) {
                html = html + outputCountry(DataToOutput, StartYear_drop, EndYear_drop, TypeOrder, SortOrder);
            } else {
                html = html + "<h3>Please Select an Order Type</h3>";
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

    // method to create World Table based off inputs
    private String outputWorld(String dataOutput, String startYear, String endYear) {
        String html = "<div id='tableData'>";
        html = html + "<h3>" + dataOutput + " data for " + startYear + " and " + endYear + "</h3>";
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<Climate> worldPopulationTemp = jdbc.getWorldPopulationTemp(startYear, endYear);

        html = html + "<table> <tr>";
        html = html + "<th>Country Name</th>";
        html = html + "<th>Population at " + startYear + "</th>";
        html = html + "<th>Population at " + endYear + "</th>";
        html = html + "<th>Change in Population</th>";
        html = html + "<th>Temperature at " + startYear + "</th>";
        html = html + "<th>Temperature at " + endYear + "</th>";
        html = html + "<th>Change in Temperature</th> </tr>";

        for (int i = 0; i < worldPopulationTemp.size(); ++i) {
            html = html + "<tr> <td>" + worldPopulationTemp.get(i).getCountryName() + "</td> " + "<td>";
            html = html + String.format("%,d", worldPopulationTemp.get(i).getStartPopulation()) + "</td>" + "<td>";
            html = html + String.format("%,d", worldPopulationTemp.get(i).getEndPopulation()) + "</td>" + "<td>";
            if (worldPopulationTemp.get(i).getPopulationPercent() > 0) {
                html = html + String.format("+%.2f%%", worldPopulationTemp.get(i).getPopulationPercent()) + "</td>"
                        + "<td>";
            } else {
                html = html + String.format("%.2f%%", worldPopulationTemp.get(i).getPopulationPercent()) + "</td>"
                        + "<td>";
            }
            html = html + worldPopulationTemp.get(i).getStartTemp() + "</td>" + "<td>";
            html = html + worldPopulationTemp.get(i).getEndTemp() + "</td>" + "<td>";
            if (worldPopulationTemp.get(i).getTempPercent() > 0) {
                html = html + String.format("+%.2f%%", worldPopulationTemp.get(i).getTempPercent()) + "</td>" + "<td>";
            } else {
                html = html + String.format("%.2f%%", worldPopulationTemp.get(i).getTempPercent()) + "</td>" + "<td>";
            }
        }

        html = html + "</table>";
        html = html + "</div>";

        return html;
    }

    // method to sort inputs into separate table formats
    private String outputCountry(String dataOutput, String startYear, String endYear, String type, String sort) {
        String html = "<div id='tableData'>";

        if (type.equals("Population Change")) {
            if (sort.equals("Ascending")) {
                type = "percentagep";
                sort = "ASC";
                html = html + countryTableFormat(html, dataOutput, startYear, endYear, type, sort);

            } else if (sort.equals("Descending")) {
                type = "percentagep";
                sort = "DESC";
                html = html + countryTableFormat(html, dataOutput, startYear, endYear, type, sort);

            }

        }

        else if (type.equals("Temperature Change")) {
            if (sort.equals("Ascending")) {
                type = "percentaget";
                sort = "ASC";
                html = html + countryTableFormat(html, dataOutput, startYear, endYear, type, sort);

            } else if (sort.equals("Descending")) {
                type = "percentaget";
                sort = "DESC";
                html = html + countryTableFormat(html, dataOutput, startYear, endYear, type, sort);

            }
        }

        return html;
    }

    // method to create country table based off inputs
    private String countryTableFormat(String html, String dataOutput, String startYear, String endYear, String type,
            String sort) {
        html = html + "<h3>" + dataOutput + " data for " + startYear + " and " + endYear + "</h3>";
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<Climate> countryPopulationTemp = jdbc.getCountryPopulationTemp(startYear, endYear, type, sort);

        html = html + "<table> <tr>";
        html = html + "<th>Country Name</th>";
        html = html + "<th>Population at " + startYear + "</th>";
        html = html + "<th>Population at " + endYear + "</th>";
        html = html + "<th>Change in Population</th>";
        html = html + "<th>Temperature at " + startYear + "</th>";
        html = html + "<th>Temperature at " + endYear + "</th>";
        html = html + "<th>Change in Temperature</th>";
        html = html + "<th>Correlation</th> </tr>";

        for (int i = 0; i < countryPopulationTemp.size(); ++i) {
            long startPop = countryPopulationTemp.get(i).getStartPopulation();
            long endPop = countryPopulationTemp.get(i).getEndPopulation();
            float startTemp = countryPopulationTemp.get(i).getStartTemp();
            float endTemp = countryPopulationTemp.get(i).getEndTemp();

            html = html + "<tr> <td>" + countryPopulationTemp.get(i).getCountryName() + "</td> " + "<td>";
            html = html + String.format("%,d", startPop) + "</td>" + "<td>";
            html = html + String.format("%,d", endPop) + "</td>" + "<td>";
            if (countryPopulationTemp.get(i).getPopulationPercent() > 0) {
                html = html + String.format("+%.2f%%", countryPopulationTemp.get(i).getPopulationPercent()) + "</td>"
                        + "<td>";
            } else {
                html = html + String.format("%.2f%%", countryPopulationTemp.get(i).getPopulationPercent()) + "</td>"
                        + "<td>";
            }
            html = html + startTemp + "</td>" + "<td>";
            html = html + endTemp + "</td>" + "<td>";
            if (countryPopulationTemp.get(i).getTempPercent() > 0) {
                html = html + String.format("+%.2f%%", countryPopulationTemp.get(i).getTempPercent()) + "</td>"
                        + "<td>";
            } else {
                html = html + String.format("%.2f%%", countryPopulationTemp.get(i).getTempPercent()) + "</td>" + "<td>";
            }
            html = html + String.format("%.3f", correlationCalc(startPop, endPop, startTemp, endTemp)) + "</td> </tr>";
        }

        html = html + "</table>";
        html = html + "</div>";

        return html;
    }

    // method to calculate correlation
    private float correlationCalc(long startPop, long endPop, float startTemp, float endTemp) {
        float corr;
        int n = 2;

        long sumX = startPop + endPop;
        float sumY = startTemp + endTemp;
        float sumXY = (float) ((startPop * startTemp) + (endPop * endTemp));
        long squareSumX = (startPop * startPop) + (endPop * endPop);
        float squareSumY = (startTemp * startTemp) + (endTemp * endTemp);

        float numerator = (n * sumXY) - (sumX * sumY);
        float denominator = (float) (Math
                .sqrt(((n * squareSumX) - (sumX * sumX)) * ((n * squareSumY) - (sumY * sumY))));
        corr = numerator / denominator;

        return corr;
    }

}
