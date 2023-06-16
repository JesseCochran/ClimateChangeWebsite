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
public class PageST3C implements Handler {

    // URL of this page relative to http://localhost:7001/
    public static final String URL = "/page3C.html";

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        String html = "<html>";

        // Add some Head information
        // makes it so certain characters are visible
        html = html + "<head> <meta charset='UTF-8'>" +
                "<title>Comparing Land and Land-Ocean Temperature Data</title>";

        // Add some CSS (external file)
        html = html + "<link rel='stylesheet' type='text/css' href='JesseTesting2c.css' />";
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

        // Add Div for page Content
        html = html + "<div class='content'>";

        // Add HTML for the page content
        // Explanation of land ocean temp
        html = html
                + """
                        <h2>A Look At Annual Global Land Ocean Temperature Records</h2>
                        <p>Analysing the relationship between global land-ocean temperatures and land temperatures can help determine long term temperature trends on a global scale.<br>
                        The reason for comparing global land-ocean with land temperature data is that water has a higher heat capacity than land and as such it takes longer to heat up and cool down thus resulting in a slower temperature change. <br>
                        This can than lead to much more useful results then otherwise just comparing a time period of land-ocean data to another time period, as would just comparing land temperature to land temperature. </p>

                        """;

        // All the Drop down menu stuff for the data to eventually be retrieved from
        // html = html + "<form action='/page2C.html' method='post'>";
        boolean getInfo = true;

        // A version of the same thing with a javascript function to stop values entered
        // being cleared on reload
        html = html + "<form id='page3CForm' action='/page3C.html' method='post' onsubmit='return ReenterData()'>";
        int numberOfDatasets = 0;
        String counterValue = "";
        if (context.formParam("counter") != null) {
            counterValue = context.formParam("counter");
            numberOfDatasets = Integer.parseInt(counterValue);
        }
        // This bit of javascript makes it so the page keeps the same values leading to
        // less user confusion when the page reloads
        html = html + "<script>";
        html = html + "   function ReenterData() {";
        html = html + "       var startYear = document.getElementById('StartYear_drop').value;";
        html = html + "       var timeYears = document.getElementById('timeYears_drop').value;";
        html = html + "       var sortOrder = document.querySelector('input[name=SortOrder]:checked').value;";
        html = html + "       var dataToShow = document.getElementById('TempSelection_drop').value;";
        html = html + "       var counterValue = document.getElementById('counterValue').value;";

        html = html + "       sessionStorage.setItem('startYear', startYear);";
        html = html + "       sessionStorage.setItem('timeYears', timeYears);";
        html = html + "       sessionStorage.setItem('sortOrder', sortOrder);";
        html = html + "       sessionStorage.setItem('dataToShow', dataToShow);";
        html = html + "       sessionStorage.setItem('counterValue', counterValue);";
        html = html + "       return true;";
        html = html + "   }";
        html = html + " window.onload = function() {";
        // html = html + " var startYear = sessionStorage.getItem('startYear');";
        // html = html + " var timeYears = sessionStorage.getItem('timeYears');";
        // html = html + " var sortOrder = sessionStorage.getItem('sortOrder');";
        // html = html + " var dataToShow = sessionStorage.getItem('dataToShow');";
        // html = html + " if (startYear)
        // document.getElementById('StartYear_drop').value = startYear;";
        // html = html + " if (timeYears)
        // document.getElementById('timeYears_drop').value = timeYears;";
        // html = html
        // + " if (sortOrder) document.querySelector('input[name=SortOrder][value=' +
        // sortOrder + ']').checked = true;";
        // html = html + " if (dataToShow)
        // document.getElementById('TempSelection_drop').value = dataToShow;";
        // html = html + " }";
        html = html + "       var startYear = sessionStorage.getItem('startYear');";
        html = html + "       var timeYears = sessionStorage.getItem('timeYears');";
        html = html + "       var sortOrder = sessionStorage.getItem('sortOrder');";
        html = html + "       var dataToShow = sessionStorage.getItem('dataToShow');";
        html = html + "       if (startYear) document.getElementById('StartYear_drop').value = startYear;";
        html = html + "       if (timeYears) document.getElementById('lengthDropdown').value = timeYears;";
        html = html
                + "       if (sortOrder) document.querySelector('input[name=SortOrder][value=' + sortOrder + ']').checked = true;";
        html = html + "       if (dataToShow) document.getElementById('dataType').value = dataToShow;";
        html = html + "   }";
        html = html + "</script>";

        // reload/clear button
        html = html + "<button class='reset' type='button' onclick='reload()'>Reset</button>";
        // javascript for that button to clear all data entered
        //
        html = html + "<script>";
        html = html + "   function reload() {";
        html = html + "       document.getElementById('StartYear_drop').value = '';";
        html = html + "       document.getElementById('lengthDropdown').value = '';";
        html = html + " sessionStorage.removeItem('counter');";
        html = html + "    var datasetContainer = document.getElementById('datasetContainer');";
        html = html + "    datasetContainer.innerHTML = '';";
        html = html + "       var sortOrderRadios = document.querySelectorAll('input[name=SortOrder]');";
        html = html + "       sortOrderRadios.forEach(function(radio) { radio.checked = false; });";
        html = html + "       document.getElementById('TempSelection_drop').value = '';";

        html = html + "       document.getElementById('tableData').innerHTML = '';";
        html = html + "       return false;";
        html = html + "   }";
        html = html + "</script>";

        html = html + " <div class='container'>";
        html = html + "   <div class='form-group'>";
        html = html + "      <label for='StartYear_drop'>Select the start year:</label>";
        html = html + "      <select id='StartYear_drop' name='StartYear_drop'>";
        html = html + "<option value='' disabled selected hidden>--select date--</option>";

        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<Climate> years = jdbc.getLandOceanYears();
        for (Climate year : years) {
            html = html + "<option>" + year.getYear() + "</option>";
        }
        html = html + "      </select>";

        html = html + "      <label for='lengthDropdown'>Select the time period of years:</label>";
        html = html + "      <select id='lengthDropdown' name='lengthDropdown'>";
        html = html + "<option value='' disabled selected hidden>--select years--</option>";
        for (int i = 1; i < years.size(); i++) {
            if (i != 1) {
                html = html + " <option value='" + i + "'>" + i + " years</option>";
            } else {
                html = html + " <option value='" + i + "'>" + i + " year</option>";
            }
        }
        html = html + "      </select>";

        html = html + "      </div>";

        html = html + "   <div class='form-group'>";

        html = html + "      <label for='dataType'>Select data you wish to view:</label>";
        html = html + "      <select id='dataType' name='dataType' size='1'>";
        html = html + "<option value='' disabled selected hidden>--select data--</option>";
        html = html + "<option>Land Data</option>";
        html = html + "<option>Land-Ocean Data</option>";
        html = html + "      </select>";

        html = html
                + "   <button class='AnotherDataset' type='button' class='btn btn-NewDataset' onclick='incrementCounter()'>Add Another Set Of Data</button>";

        html = html + "      </div>";
        html = html + " </div>";

        // Enforces the rule that the duration must be possible with the data
        html = html + "<script>";
        html = html + "var startYearDropdowns = document.querySelectorAll('[name^=\"StartYear_drop\"]');";
        html = html + "var lengthDropdown = document.getElementById('lengthDropdown');";
        html = html + "var originalStartYearOptions = [];";

        // Populate start year options
        html = html + "function populateStartYearOptions() {";
        html = html + "    var selectedLength = parseInt(lengthDropdown.value);";
        html = html + "    var minStartYear = \"" + years.get(0).getYear() + "\";";
        html = html + "    var maxStartYear = \"" + years.get(years.size() - 1).getYear() + "\" - selectedLength;";

        html = html + "    originalStartYearOptions = [];";

        // Add options based on the selected length
        html = html + "    for (var year = maxStartYear; year >= minStartYear; year--) {";
        html = html + "        originalStartYearOptions.push(year);";
        html = html + "    }";

        // Update start year options for all dropdowns
        html = html + "    startYearDropdowns.forEach(function (dropdown) {";
        html = html + "        updateStartYearOptions(dropdown, originalStartYearOptions);";
        html = html + "    });";

        // Update selected value if out of range
        html = html + "    startYearDropdowns.forEach(function (dropdown) {";
        html = html + "        var selectedYear = dropdown.value;";
        html = html + "        if (!originalStartYearOptions.includes(parseInt(selectedYear))) {";
        html = html + "            var closestYear = originalStartYearOptions.find(function(year) {";
        html = html + "                return year >= parseInt(selectedYear);";
        html = html + "            });";
        html = html + "            dropdown.value = closestYear || originalStartYearOptions[0];";
        html = html + "        }";
        html = html + "    });";
        html = html + "}";

        // Event listener for length dropdown
        html = html + "lengthDropdown.addEventListener('change', function() {";
        html = html + "    populateStartYearOptions();";
        html = html + "});";

        html = html + "window.addEventListener('load', function() {";
        html = html + "    populateStartYearOptions();";
        html = html + "});";

        // Update start year options
        html = html + "function updateStartYearOptions(startYearDropdowns, startYearOptions) {";
        html = html + "    var selectedStartYear = startYearDropdowns.value;";
        html = html + "    var selectedIndex = startYearDropdowns.selectedIndex;";

        // Clear previous options
        html = html + "    startYearDropdowns.innerHTML = '';";

        // Add new options based on the original start year options
        html = html + "    for (var i = 0; i < startYearOptions.length; i++) {";
        html = html + "        var year = startYearOptions[i];";

        html = html + "        var option = document.createElement('option');";
        html = html + "        option.text = year;";
        html = html + "        option.value = year;";
        html = html + "        startYearDropdowns.appendChild(option);";
        html = html + "    }";

        // Set selected start year if within the available range
        html = html + "    if (startYearOptions.includes(parseInt(selectedStartYear))) {";
        html = html + "        startYearDropdowns.value = selectedStartYear;";
        html = html + "    } else if (selectedIndex >= 0) {";
        html = html + "        var closestYear = startYearOptions.find(function(year) {";
        html = html + "            return year >= parseInt(selectedStartYear);";
        html = html + "        });";
        html = html + "        startYearDropdowns.value = closestYear || startYearOptions[0];";
        html = html + "    }";
        html = html + "}";

        // Initial population of start year options
        html = html + "populateStartYearOptions();";

        // Function to update start year options in datasetContainer
        html = html + "function updateStartYearOptionsMultiple(selectElement) {";
        html = html + "    var selectedValue = selectElement.value;";
        html = html
                + "    var datasetStartYearDropdowns = document.querySelectorAll('#datasetContainer select[name^=\"StartYear_drop\"]');";

        // Update start year options for datasetContainer dropdowns
        html = html + "    datasetStartYearDropdowns.forEach(function(dropdown) {";
        html = html + "        updateStartYearOptions(dropdown, originalStartYearOptions);";
        html = html + "    });";
        html = html + "}";
        html = html + "</script>";

        // Every time the button is pressed, it will reload the form and increase the
        // counter that is at the top of the document
        html = html + "<script>";
        html = html + "function incrementCounter() {";
        html = html + "    var counter = sessionStorage.getItem('counter');";
        html = html + "    if (counter) {";
        html = html + "        counter = parseInt(counter);";
        html = html + "        counter++;";
        html = html + "    } else {";
        html = html + "        counter = 1;";
        html = html + "    }";
        html = html + "    sessionStorage.setItem('counter', counter);";
        html = html + "    var hiddenInput = document.createElement('input');";
        html = html + "    hiddenInput.type = 'hidden';";
        html = html + "    hiddenInput.name = 'counter';";
        html = html + "    hiddenInput.value = counter;";
        html = html + "    var form = document.getElementById('page3CForm');";
        html = html + "    form.appendChild(hiddenInput);";
        html = html + "    form.submit();";
        html = html + "}";
        html = html + "</script>";
        // testing
        // html = html + "<p>" + numberOfDatasets + "</p>";
        if (numberOfDatasets > 0) {
            html = html + "<div id='datasetContainer'>";

            for (int i = 0; i < numberOfDatasets; i++) {
                html = html + "<p>Please Select Another Set Of Data To Compare:</p>";
                html = html + "      <label for='StartYear_drop" + i + "'>Select the start year:</label>";
                html = html + "      <select id='StartYear_drop" + i + "' name='StartYear_drop" + i
                        + "' onchange='updateStartYearOptionsMultiple(this)'>";
                html = html + "<option value='' disabled selected hidden>--select date--</option>";

                years = jdbc.getLandOceanYears();
                for (Climate year : years) {
                    html = html + "<option>" + year.getYear() + "</option>";
                }
                html = html + "      </select>";

                html = html + "      <label for='dataType" + i + "'>Select data you wish to view:</label>";
                html = html + "      <select id='dataType" + i + "' name='dataType" + i + "' size='1'>";
                html = html + "<option value='' disabled selected hidden>--select data--</option>";
                html = html + "<option>Land Data</option>";
                html = html + "<option>Land-Ocean Data</option>";
                html = html + "      </select><br>";
            }
            html = html + "</div>";
        }

        // Sorting order
        html = html + """
                <p>Sort By</p>
                <input type='radio' id='SortOrderAsc' name='SortOrder' value='Ascending'>
                <label class='radio-label' for='SortOrderAsc'>Least Change In Average Temperature</label><br>

                <input type='radio' id='SortOrderDes' name='SortOrder' value='Descending'>
                 <label class='radio-label' for='SortOrderDes'>Greatest Change In Average Temperature</label><br>

                    """;

        // View table
        html = html + "<input type='checkbox' id='dataTable' name='dataTable' value='seeTable'>";
        html = html + "<label for='dataTable'> Do you wish to see the data in a graph?</label><br>";
        // html = html + "<p>" + counterValue + "</p>";

        // hidden field to save number of dropdowns
        if (counterValue != null) {
            html = html + "<input type='hidden' name='counterValue' id='counterValue' value='" + numberOfDatasets
                    + "'>";
        }
        // submit button
        html = html
                + "   <button class='showTable' type='submit' class='btn btn-primary'>Get Information</button>";

        html = html + "</form>";
        // test
        String startYear = context.formParam("StartYear_drop");
        String timeYears = context.formParam("lengthDropdown");
        String sortOrder = context.formParam("SortOrder");
        String dataToShow = context.formParam("dataType");
        html = html + "<script>";
        html = html + "   sessionStorage.setItem('startYear', '" + startYear + "');";
        html = html + "   sessionStorage.setItem('timeYears', '" + timeYears + "');";
        html = html + "   sessionStorage.setItem('sortOrder', '" + sortOrder + "');";
        html = html + "   sessionStorage.setItem('dataToShow', '" + dataToShow + "');";
        html = html + "</script>";

        String viewTable = context.formParam("dataTable");
        String startYear1 = context.formParam("StartYear_drop");
        String duration = context.formParam("lengthDropdown");
        String dataType1 = context.formParam("dataType");
        String orderBy = context.formParam("SortOrder");

        // testing code
        // html = html + "<h2>" + context.formParam("counterValue") + "</h2>";
        int arrayLengthNum = 0;
        if (context.formParam("counterValue") != null) {
            String arrayLength = context.formParam("counterValue");
            arrayLengthNum = Integer.parseInt(arrayLength);
        }
        boolean getGraph = false;
        boolean error = false;
        ArrayList<String> startYears = new ArrayList<String>();
        ArrayList<String> dataTypes = new ArrayList<String>();
        startYears.add(context.formParam("StartYear_drop"));
        dataTypes.add(context.formParam("dataType"));
        for (int i = 0; i < arrayLengthNum; i++) {
            if (context.formParam("StartYear_drop" + i) != null) {
                startYears.add(context.formParam("StartYear_drop" + i));
            } else {
                error = true;
                // startYears.add("0");
            }
            if (context.formParam("StartYear_drop" + i) != null) {
                dataTypes.add(context.formParam("dataType" + i));
            } else {
                error = true;
                // dataTypes.add("0");
            }
            // for(int j = 0; j < startYears.size(); j++){
            // if(startYears.get(j));
            // }
        }

        for (int i = 0; i < startYears.size(); i++) {

            if (startYears.get(i) == null || startYears.get(i).isEmpty() || duration == null
                    || dataTypes.get(i) == null || dataTypes.get(i).isEmpty()) {
                html = html + "<h3>Please select all start periods and the data you wish to view</h3>";
                getInfo = false;
                error = true;
            }
            if (error == true) {
                getGraph = false;
            } else {
                getGraph = true;
            }
        }

        if (startYear1 == null || duration == null) {
            html = html + "<h3>Please select a start period and a time period</h3>";
            getInfo = false;
        }
        if (dataType1 == null) {
            html = html + "<h3>Please select the data you wish to view out of land data and land-ocean data</h3>";
            getInfo = false;
        }
        if (orderBy == null) {
            html = html + "<h3>Please select how you would like the data sorted</h3>";
            getInfo = false;
        }

        if (viewTable != null && viewTable.equals("seeTable") && getInfo == true && startYears != null
                && dataTypes != null
                && orderBy != null && duration != null && getGraph == true) {

            html = html + outputTable(startYears, dataTypes, duration, orderBy, "Graph");
            // html = html + showGraph(startYears, dataTypes, duration, orderBy);
        } else if (getInfo && startYears != null && dataTypes != null && orderBy != null && duration != null) {
            html = html + outputTable(startYears, dataTypes, duration, orderBy, "TableOnly");
            // html = html + showGraph(startYears, dataTypes, duration, orderBy);
        }

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

    private String outputTable(ArrayList<String> startYears, ArrayList<String> dataTypes, String duration,
            String orderBy, String tableGraph) {
        String html = "<div id='tableData'>";
        // testing
        // html = html + "<p>" + startYears.size() + "</p>";
        // html = html + "<p>" + startYears.get(0) + "</p>";
        // html = html + "<p>" + startYears.get(1) + "</p>";
        html = html + "<h3> Temperature data over " + duration + " years</h3>";
        JDBCConnection jdbc = new JDBCConnection();
        // ArrayList<Climate> WorldData =
        // jdbc.getWorldLandOceanAverageTempOverPeriod(startYear,
        // Integer.toString(endYear),
        // dataType);

        html = html + "<table> <tr>";
        html = html + "<th>Type of Data</th>";
        html = html + "<th>start year</th>";
        html = html + "<th>end year</th>";
        html = html + "<th>Average temperature at start year</th>";
        html = html + "<th>Average temperature at end year</th>";
        html = html + "<th>Average temperature over selected time period</th>";
        html = html + "<th>Change in average temperature</th> </tr>";
        ArrayList<String> endYears = new ArrayList<String>();
        for (int i = 0; i < startYears.size(); ++i) {

            String selectedStartYear = startYears.get(i);
            int endYear = Integer.parseInt(selectedStartYear) + Integer.parseInt(duration) - 1;
            endYears.add(Integer.toString(endYear));
        }
        ArrayList<Climate> WorldData = jdbc.getWorldLandOceanAverageTempOverPeriod(startYears,
                endYears, dataTypes, orderBy);
        if (tableGraph.equals("Graph")) {
            html = html + showGraph(WorldData, duration);
        }

        for (int j = 0; j < WorldData.size(); j++) {
            html = html + "<tr> <td>" + WorldData.get(j).getDataType() + "</td> " + "<td>";
            html = html + WorldData.get(j).getStartYear() + "</td>" + "<td>";
            html = html + WorldData.get(j).getEndYear() + "</td>" + "<td>";
            html = html + String.format("%.2f", WorldData.get(j).getStartTemp()) + "</td>" + "<td>";
            html = html + String.format("%.2f", WorldData.get(j).getEndTemp()) + "</td>" + "<td>";
            html = html + String.format("%.2f", WorldData.get(j).getAverageTemperature()) + "</td>" + "<td>";
            html = html + String.format("%.2f%%", WorldData.get(j).getTempPercent()) + "</td></tr>";
        }

        html = html + "</table></div>";

        return html;
    }

    private String showGraph(ArrayList<Climate> WorldData, String duration) {
        String html = "";
        // html = html + "<div id='columnchart_values' style=\"width: 900px; height:
        // 300px;\"></div>";
        // graph code from google charts
        html = html + "<script type='text/javascript' src='https://www.gstatic.com/charts/loader.js'></script>";
        html = html + "  <script type='text/javascript'>";
        html = html + "    google.charts.load('current', {packages:['corechart']});";
        html = html + "    google.charts.setOnLoadCallback(drawChart);";
        html = html + "    function drawChart() {";
        html = html + "      var data = google.visualization.arrayToDataTable([";
        html = html + "        ['YearRange', 'Change In Average'],";
        for (int i = 0; i < WorldData.size(); i++) {
            html = html + "        ['" + WorldData.get(i).getDataType() + "\\n" + WorldData.get(i).getStartYear()
                    + " - "
                    + WorldData.get(i).getEndYear()
                    + "', " + WorldData.get(i).getTempPercent() + "],";
        }
        html = html + "      ]);";
        html = html + "";
        html = html + "      var view = new google.visualization.DataView(data);";
        html = html + "      view.setColumns([0, 1, {";
        html = html + "        calc: function(data, row) {";
        html = html + "          var value = data.getValue(row, 1) + '.00%';"; // Add the % symbol here
        html = html + "          if (!Number.isInteger(data.getValue(row, 1))) {";
        html = html + "            value = data.getValue(row, 1).toFixed(2) + '%';";
        html = html + "          }";
        html = html + "          return value;";
        html = html + "        },";
        html = html + "        type: 'string',";
        html = html + "        role: 'annotation'";
        html = html + "      }]);";
        html = html + "      var options = {";
        html = html + "        title: 'Change In Average Temperature Over " + duration + " Years',";
        html = html + "        width: 600,";
        html = html + "        height: 300,";
        html = html + "        bar: {groupWidth: '95%'},";
        html = html + "        legend: { position: 'none' },";
        html = html + "        colors: ['#292d6a'],";
        html = html + "        annotations: {";
        html = html + "          textStyle: {";
        html = html + "            fontName: 'Roboto, Arial, sans-serif'";
        html = html + "          },";
        html = html + "          format: '##.##%'";
        html = html + "        },";
        html = html + "        titleTextStyle: {";
        html = html + "          fontSize: 18,";
        html = html + "          fontName: 'Roboto, Arial, sans-serif'";
        html = html + "        },";
        html = html + "        hAxis: {";
        html = html + "          textStyle: {";
        html = html + "            fontSize: 14,";
        html = html + "            fontName: 'Roboto, Arial, sans-serif'";
        html = html + "          }";
        html = html + "        },";
        html = html + "        vAxis: {";
        html = html + "          textStyle: {";
        html = html + "            fontSize: 14,";
        html = html + "            fontName: 'Roboto, Arial, sans-serif'";
        html = html + "          }";
        html = html + "        },";
        html = html + "      chartArea: {";
        html = html + "        left: 35,";
        html = html + "        top: 30,";
        html = html + "        right: 0,";
        html = html + "        width: '70%',";
        html = html + "        height: '70%'";
        html = html + "      }";
        html = html + "      };";
        html = html
                + "      var chart = new google.visualization.ColumnChart(document.getElementById('columnchart_values'));";
        html = html + "      chart.draw(view, options);";
        html = html + "  }";
        html = html + "  </script>";
        html = html
                + "<div id='columnchart_values' style='padding: 0px; margin-left: 650px; margin-top: -350px; position: absolute; border: 2px solid black; text-align: center;''></div>";

        return html;
    }
}