package app;

import java.lang.Math;
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
public class PageST2C implements Handler {

    // URL of this page relative to http://localhost:7001/
    public static final String URL = "/page2C.html";

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        String html = "<html>";

        // Add some Head information
        // makes it so certain characters are visible
        html = html + "<head> <meta charset='UTF-8'>" +
                "<title>Focused View Of Land-Ocean Temperature</title>";

        // CSS style sheets for the page
        html = html + "<link rel='stylesheet' type='text/css' href='common.css' />";
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

        // Add the navigation bar that sits locked at the top of the screen
        html = html
                + """
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
        // Javascript for opening and closing burger nav
        html = html + "<script>";
        html = html + """

                                function openNav() {
                  document.getElementById('mySidenav').style.width = '250px';
                }

                function closeNav() {
                  document.getElementById('mySidenav').style.width = '0px';
                }
                                                """;
        // take user to help page when h is pressed
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
        // take to home page when esc is pressed
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
        // Code for the burger nav and the contents in it when its displayed
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
        // Explanation of land ocean temp
        html = html
                + """
                        <h2>A Look At Annual Global Land-Ocean Temperature Records</h2>
                        <p>Land-Ocean Temperature is an average of the temperatures of both the land and ocean surfaces over a period of time. </p>
                        <p>The Global Land-Ocean records are especially useful as a critical tool in assessing long term climate trends and the extent of global warming due to the inclusion of the surfaces temperature of ocean data,
                        this is because oceans have a higher heat capacity compared to land therefore meaning they absorb and release heat slower which can then help show greater discrepancies in temperatures therefore showing signs of climate change. </p>
                        """;

        // When the page reloads the data is placed back into the drop downs
        html = html + "<form action='/page2C.html' method='post' onsubmit='return ReenterData()'>";
        // This bit of javascript makes it so the page keeps the same values leading to
        // less user confusion when the page reloads as values are saved
        html = html + "<script>";
        html = html + "   function ReenterData() {";
        html = html + "       var startYear = document.getElementById('StartYear_drop').value;";
        html = html + "       var endYear = document.getElementById('EndYear_drop').value;";
        html = html + "       var sortOrder = document.querySelector('input[name=SortOrder]:checked').value;";
        html = html + "       var dataToShow = document.getElementById('TempSelection_drop').value;";
        html = html + "       sessionStorage.setItem('startYear', startYear);";
        html = html + "       sessionStorage.setItem('endYear', endYear);";
        html = html + "       sessionStorage.setItem('sortOrder', sortOrder);";
        html = html + "       sessionStorage.setItem('dataToShow', dataToShow);";
        html = html + "       return true;";
        html = html + "   }";
        html = html + " window.onload = function() {";
        html = html + " var startYear = sessionStorage.getItem('startYear');";
        html = html + " var endYear = sessionStorage.getItem('endYear');";
        html = html + " var sortOrder = sessionStorage.getItem('sortOrder');";
        html = html + " var dataToShow = sessionStorage.getItem('dataToShow');";
        html = html + " if (startYear) document.getElementById('StartYear_drop').value = startYear;";
        html = html + " if (endYear) document.getElementById('EndYear_drop').value = endYear;";
        html = html
                + " if (sortOrder) document.querySelector('input[name=SortOrder][value=' + sortOrder + ']').checked = true;";
        html = html + " if (dataToShow) document.getElementById('TempSelection_drop').value = dataToShow;";
        html = html + " }";
        html = html + "</script>";

        // reload/clear button
        html = html + "<button class='reset' type='button' onclick='reload()'>Reset</button>";
        // javascript for the reset button to clear all data entered
        //
        html = html + "<script>";
        html = html + "   function reload() {";
        html = html + "       document.getElementById('StartYear_drop').value = '';";
        html = html + "       document.getElementById('EndYear_drop').value = '';";
        html = html + "       var sortOrderRadios = document.querySelectorAll('input[name=SortOrder]');";
        html = html + "       sortOrderRadios.forEach(function(radio) { radio.checked = false; });";
        html = html + "       document.getElementById('TempSelection_drop').value = '';";

        html = html + "       document.getElementById('tableData').innerHTML = '';";
        html = html + "       return false;";
        html = html + "   }";
        html = html + "</script>";
        // start year drop down
        html = html + "   <div class='form-group'>";
        html = html + "      <label for='StartYear_drop'>Select the start year:</label>";
        html = html
                + "      <select id='StartYear_drop' name='StartYear_drop' onchange='updateEndYearOptions()' size='1'>";
        html = html + "<option value='' disabled selected hidden>--select year--</option>";
        // This onchange section ^ makes the website more dynamic by using a java script
        // Used java script taught in this video
        // https://www.youtube.com/watch?v=SBmSRK3feww&t=7s and my own knowledge

        // this connects the database to the start date drop down box.
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<Climate> years = jdbc.getLandOceanYears();
        for (Climate year : years) {
            html = html + "<option>" + year.getYear() + "</option>";
        }
        html = html + "      </select>";
        html = html + "   </div>";
        // Using some javascript to change the value of the end year drop down section
        // to be the same as the start year and vise versa
        // Stores selected values into javascript variables
        html = html + "<script>";
        html = html + "var selectedStartYear = null;";
        html = html + "var selectedEndYear = null;";
        html = html + "function updateEndYearOptions() {";
        html = html + "   var startYearDropdown = document.getElementById('StartYear_drop');";
        html = html + "   var EndYearDropdown = document.getElementById('EndYear_drop');";
        html = html + "   var startYear = parseInt(startYearDropdown.value);";
        html = html + "   var endYear = " + years.get(years.size() - 1).getYear() + ";";
        html = html + "   var selectedEndYear = parseInt(EndYearDropdown.value);";
        html = html + "   EndYearDropdown.innerHTML = '';";

        // This javascript code effectively makes the data in the end year section must
        // be in the range of the user selected date to the end of the avilable data in
        // the database.
        html = html + "   for (var year = startYear; year <= endYear; year++) {";
        html = html + "       var option = document.createElement('option');";
        html = html + "       option.text = year;";
        html = html + "       option.value = year;";
        html = html + "       EndYearDropdown.add(option);";
        html = html + "   }";
        html = html + "   if (selectedEndYear) EndYearDropdown.value = selectedEndYear;";
        html = html + "}";
        // this code does the same for the start year value
        html = html + "function updateStartYearOptions() {";
        html = html + "   var startYearDropdown = document.getElementById('StartYear_drop');";
        html = html + "   var EndYearDropdown = document.getElementById('EndYear_drop');";
        html = html + "   var EndYear = parseInt(EndYearDropdown.value);";
        html = html + "   var selectedStartYear = parseInt(startYearDropdown.value);";
        html = html + "   startYearDropdown.innerHTML = '';";
        html = html + "   for (var year = " + years.get(0).getYear() + "; year <= EndYear; year++) {";
        html = html + "       var option = document.createElement('option');";
        html = html + "       option.text = year;";
        html = html + "       option.value = year;";
        html = html + "       startYearDropdown.add(option);";
        html = html + "   }";
        html = html + "   if (selectedStartYear) startYearDropdown.value = selectedStartYear;";
        html = html + "}";
        html = html + "</script>";

        // end year drop down
        html = html + "   <div class='form-group'>";
        html = html + "      <label for='EndYear_drop'>Select the end year:</label>";
        html = html
                + "      <select id='EndYear_drop' name='EndYear_drop' onchange='updateStartYearOptions()' size='1'>";
        html = html + "<option value='' disabled selected hidden>--select year--</option>";
        // java code to help get the year data into the drop down options
        for (Climate year : years) {
            html = html + "<option>" + year.getYear() + "</option>";
        }
        html = html + "      </select>";
        html = html + "   </div>";

        // Sorting order
        html = html + """
                <p>Sort By</p>
                <input type='radio' id='SortOrderAsc' name='SortOrder' value='Ascending' checked>
                <label class='radio-label' for='SortOrderAsc'>Low to High</label><br>

                <input type='radio' id='SortOrderDes' name='SortOrder' value='Descending'>
                 <label class='radio-label' for='SortOrderDes'>High to Low</label><br>

                    """;
        // Type of data drop down
        html = html + "   <div class='form-group'>";
        html = html + "      <label for='TempSelection_drop'>Select Data You Wish To View:</label>";
        html = html + "      <select id='TempSelection_drop' name='TempSelection_drop' size='1'>";
        html = html + "<option value='' disabled selected hidden>--select data type--</option>";
        html = html + "<option>Only Average Land Ocean Temperature</option>";
        html = html + "<option>Only Minimum Land Ocean Temperature</option>";
        html = html + "<option>Only Maximum Land Ocean Temperature</option>";
        html = html + "<option>Show All Land Ocean Temperature Data</option>";
        html = html + "      </select>";
        html = html + "   </div>";
        // submit button
        html = html + "   <button class='showTable' type='submit' class='btn btn-primary'>Show Table</button>";
        html = html + "</form>";

        // gets data once form is posted
        String StartYear_drop = context.formParam("StartYear_drop");
        String DataToShow = context.formParam("TempSelection_drop");
        String EndYear_drop = context.formParam("EndYear_drop");
        String SortBy = context.formParam("SortOrder");

        // validation checks if stuff is null to prevent crashes
        if (StartYear_drop == null) {
            html = html + "<h3>Please select a start year</h3>";
        }
        if (EndYear_drop == null) {
            html = html + "<h3>Please select a end year</h3>";
        }
        if (DataToShow == null) {
            html = html + "<h3>Please select a type of data</h3>";
        }
        if (SortBy == null) {
            html = html + "<h3>Please select a sorting method</h3>";
        } else if (EndYear_drop != null && StartYear_drop != null && DataToShow != null && SortBy != null) {
            // function where data is calculated
            html = html + outputData(StartYear_drop, EndYear_drop, DataToShow, SortBy);
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

    private String outputData(String startYear, String endYear, String DataToShow, String SortBy) {
        // makes the table for the data and the box of important info
        String html = "<div id='tableData'>";
        html = html + "<h2>" + "Climate Data From " + startYear + " To " + endYear + "</h2>";
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<Climate> oceantemps;

        if (SortBy.equals("Ascending")) {
            oceantemps = jdbc.getAllLandOceanDataASC();
        } else if (SortBy.equals("Descending")) {
            oceantemps = jdbc.getAllLandOceanDataDESC();
        }
        // default option
        else {
            oceantemps = jdbc.getAllLandOceanDataASC();
        }

        int startIndex = getIndexByYear(oceantemps, Integer.parseInt(startYear));
        int endIndex = getIndexByYear(oceantemps, Integer.parseInt(endYear));

        if (DataToShow.equals("Only Average Land Ocean Temperature")) {
            String dataType = "Average";
            html = displayTable(html, startYear, endYear, oceantemps, startIndex, endIndex, dataType);
            html = html + "<table>";
            html = html
                    + "<tr><th>Year</th><th>Average Land Ocean Temperature</th></tr>";
            if (SortBy.equals("Ascending")) {
                for (int i = startIndex; i <= endIndex; i++) {
                    Climate climate = oceantemps.get(i);
                    html = html + "<tr><td>" + climate.getYear() + "</td><td>" + climate.getAverageTemperature()
                            + "</td></tr>";
                }
            } else if (SortBy.equals("Descending")) {
                for (int i = endIndex; i <= startIndex; i++) {
                    Climate climate = oceantemps.get(i);
                    html = html + "<tr><td>" + climate.getYear() + "</td><td>" + climate.getAverageTemperature()
                            + "</td></tr>";
                }
            }
            html = html + "</table>";

        } else if (DataToShow.equals("Only Minimum Land Ocean Temperature")) {
            String dataType = "Minimum";
            html = displayTable(html, startYear, endYear, oceantemps, startIndex, endIndex, dataType);
            html = html + "<table>";
            html = html
                    + "<tr><th>Year</th><th>Minimum Land Ocean Temperature</th></tr>";

            if (SortBy.equals("Ascending")) {
                for (int i = startIndex; i <= endIndex; i++) {
                    Climate climate = oceantemps.get(i);
                    html = html + "<tr><td>" + climate.getYear() + "</td><td>" + climate.getMinimumTemperature()
                            + "</td></tr>";
                }
            } else if (SortBy.equals("Descending")) {
                for (int i = endIndex; i <= startIndex; i++) {
                    Climate climate = oceantemps.get(i);
                    html = html + "<tr><td>" + climate.getYear() + "</td><td>" + climate.getMinimumTemperature()
                            + "</td></tr>";
                }
            }
            html = html + "</table>";

        } else if (DataToShow.equals("Only Maximum Land Ocean Temperature")) {
            String dataType = "Maximum";
            html = displayTable(html, startYear, endYear, oceantemps, startIndex, endIndex, dataType);
            html = html + "<table>";
            html = html
                    + "<tr><th>Year</th><th>Maximum Land Ocean Temperature</th></tr>";
            if (SortBy.equals("Ascending")) {
                for (int i = startIndex; i <= endIndex; i++) {
                    Climate climate = oceantemps.get(i);
                    html = html + "<tr><td>" + climate.getYear() + "</td><td>" + climate.getMaximumTemperature()
                            + "</td></tr>";
                }
            } else if (SortBy.equals("Descending")) {
                for (int i = endIndex; i <= startIndex; i++) {
                    Climate climate = oceantemps.get(i);
                    html = html + "<tr><td>" + climate.getYear() + "</td><td>" + climate.getMaximumTemperature()
                            + "</td></tr>";
                }
            }
            html = html + "</table>";

        } else if (DataToShow.equals("Show All Land Ocean Temperature Data")) {
            String dataType = "All";
            html = displayTable(html, startYear, endYear, oceantemps, startIndex, endIndex, dataType);
            html = html + "<table>";
            html = html
                    + "<tr><th>Year</th><th>Average Land Ocean Temperature</th><th>Minimum Land Ocean Temperature</th><th>Maximum Land Ocean Temperature</th></tr>";
            if (SortBy.equals("Ascending")) {
                for (int i = startIndex; i <= endIndex; i++) {
                    Climate climate = oceantemps.get(i);
                    html = html + "<tr><td>" + climate.getYear() + "</td><td>" + climate.getAverageTemperature()
                            + "</td><td>"
                            + climate.getMinimumTemperature() + "</td><td>" + climate.getMaximumTemperature()
                            + "</td></tr>";
                }
            } else if (SortBy.equals("Descending")) {
                for (int i = endIndex; i <= startIndex; i++) {
                    Climate climate = oceantemps.get(i);
                    html = html + "<tr><td>" + climate.getYear() + "</td><td>" + climate.getAverageTemperature()
                            + "</td><td>"
                            + climate.getMinimumTemperature() + "</td><td>" + climate.getMaximumTemperature()
                            + "</td></tr>";
                }

            }
            html = html + "</table>";
        }
        html = html + "</div>";

        return html;
    }

    private int getIndexByYear(ArrayList<Climate> oceantemps, int year) {
        // function is used to get all the years and check to see if its within the
        // dataset and where it is
        for (int i = 0; i < oceantemps.size(); i++) {
            if (oceantemps.get(i).getYear() == year) {
                return i;
            }
        }
        // Default index if year is not found otherwise there is an error
        return 0;
    }

    private String displayTable(String html, String startYear, String endYear, ArrayList<Climate> oceantemps,
            int startIndex, int endIndex, String dataType) {
        float startValue = 0;
        float endValue = 0;
        String tempChange = "";
        String roundDifferenceValue = "";
        String percentageChange = "";
        // creates the box for important information
        html = html
                + "<div style='width: 350px; margin-left: 630px; margin-top: -360px; position: absolute; border: 2px solid black; text-align: center;'>";
        html = html + "<h2> Change In Temperature Between " + startYear + " And " + endYear + "</h2>";

        if (dataType.equals("Average")) {

            startValue = oceantemps.get(startIndex).getAverageTemperature();
            endValue = oceantemps.get(endIndex).getAverageTemperature();
            tempChange = getTemperatureChange(startValue, endValue, dataType);
            roundDifferenceValue = getRoundDifferenceValue(startValue, endValue);
            percentageChange = getPercentageChange(startValue, endValue);
        } else if (dataType.equals("Minimum")) {

            startValue = oceantemps.get(startIndex).getMinimumTemperature();
            endValue = oceantemps.get(endIndex).getMinimumTemperature();
            tempChange = getTemperatureChange(startValue, endValue, dataType);
            roundDifferenceValue = getRoundDifferenceValue(startValue, endValue);
            percentageChange = getPercentageChange(startValue, endValue);
        } else if (dataType.equals("Maximum")) {

            startValue = oceantemps.get(startIndex).getMaximumTemperature();
            endValue = oceantemps.get(endIndex).getMaximumTemperature();
            tempChange = getTemperatureChange(startValue, endValue, dataType);
            roundDifferenceValue = getRoundDifferenceValue(startValue, endValue);
            percentageChange = getPercentageChange(startValue, endValue);
        } else if (dataType.equals("All")) {
            // average data
            dataType = "Average";
            startValue = oceantemps.get(startIndex).getAverageTemperature();
            endValue = oceantemps.get(endIndex).getAverageTemperature();
            tempChange = getTemperatureChange(startValue, endValue, dataType);
            roundDifferenceValue = getRoundDifferenceValue(startValue, endValue);
            percentageChange = getPercentageChange(startValue, endValue);
            html = html + "<p> There was" + tempChange + " from " + startYear + " to " + endYear
                    + " of " + roundDifferenceValue + "°C. This is a percentage change of  " + percentageChange
                    + ". </p>";
            // min data
            dataType = "Minimum";
            startValue = oceantemps.get(startIndex).getMinimumTemperature();
            endValue = oceantemps.get(endIndex).getMinimumTemperature();
            tempChange = getTemperatureChange(startValue, endValue, dataType);
            roundDifferenceValue = getRoundDifferenceValue(startValue, endValue);
            percentageChange = getPercentageChange(startValue, endValue);
            html = html + "<p> There was" + tempChange + " from " + startYear + " to " + endYear
                    + " of " + roundDifferenceValue + "°C. This is a percentage change of  " + percentageChange
                    + ". </p>";
            // max data
            dataType = "Maximum";
            startValue = oceantemps.get(startIndex).getMaximumTemperature();
            endValue = oceantemps.get(endIndex).getMaximumTemperature();
            tempChange = getTemperatureChange(startValue, endValue, dataType);
            roundDifferenceValue = getRoundDifferenceValue(startValue, endValue);
            percentageChange = getPercentageChange(startValue, endValue);
            html = html + "<p> There was" + tempChange + " from " + startYear + " to " + endYear
                    + " of " + roundDifferenceValue + "°C. This is a percentage change of  " + percentageChange
                    + ". </p>";
            html = html + "</div>";
            return html;

        } else {
            tempChange = "";
            roundDifferenceValue = "";
            percentageChange = "";
        }

        html = html + "<p> There was" + tempChange + " from " + startYear + " to " + endYear
                + " of " + roundDifferenceValue + "°C. This is a percentage change of  " + percentageChange
                + ". </p>";
        html = html + "</div>";
        return html;
    }

    private String getTemperatureChange(float startValue, float endValue, String dataType) {
        // calculates the change in temperature and formats into a string
        String tempChange = "";
        float difference = endValue - startValue;
        if (difference > 0) {
            tempChange = " an increase in the " + dataType.toLowerCase() + " temperature";
        } else if (difference < 0) {
            tempChange = " a decrease in the " + dataType.toLowerCase() + " temperature";
        } else {
            tempChange = " no change in the " + dataType.toLowerCase() + " temperature";
        }

        return tempChange;
    }

    private String getRoundDifferenceValue(float startValue, float endValue) {
        // calculates the rounded value from the end year value to start year value
        float difference = endValue - startValue;
        String roundDifferenceValue = String.format("%.2f", difference);
        return roundDifferenceValue;
    }

    private String getPercentageChange(float startValue, float endValue) {
        // calculates percentage change
        float difference = endValue - startValue;
        float percentage = (difference / startValue) * 100;
        percentage = Math.abs(percentage);
        String percentageChange = String.format("%.2f", percentage) + "%";
        return percentageChange;
    }
}