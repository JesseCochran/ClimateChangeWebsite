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
public class PageST3CTooMuchJS implements Handler {

    // URL of this page relative to http://localhost:7001/
    public static final String URL = "/page3CC.html";

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
        // html = html + "<link rel='stylesheet' type='text/css' href='common.css' />";
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

        // A version of the same thing with a javascript function to stop values entered
        // being cleared on reload
        html = html + "<form id='page3CForm' action='/page3C.html' method='post' onsubmit='return ReenterData()'>";

        // This bit of javascript makes it so the page keeps the same values leading to
        // less user confusion when the page reloads
        html = html + "<script>";
        html = html + "   function ReenterData() {";
        html = html + "       var startYear = document.getElementById('StartYear_drop').value;";
        html = html + "       var timeYears = document.getElementById('timeYears_drop').value;";
        html = html + "       var sortOrder = document.querySelector('input[name=SortOrder]:checked').value;";
        html = html + "       var dataToShow = document.getElementById('TempSelection_drop').value;";
        html = html + "       sessionStorage.setItem('startYear', startYear);";
        html = html + "       sessionStorage.setItem('timeYears', timeYears);";
        html = html + "       sessionStorage.setItem('sortOrder', sortOrder);";
        html = html + "       sessionStorage.setItem('dataToShow', dataToShow);";
        html = html + "       return true;";
        html = html + "   }";
        html = html + " window.onload = function() {";
        html = html + " var startYear = sessionStorage.getItem('startYear');";
        html = html + " var timeYears = sessionStorage.getItem('timeYears');";
        html = html + " var sortOrder = sessionStorage.getItem('sortOrder');";
        html = html + " var dataToShow = sessionStorage.getItem('dataToShow');";
        html = html + " if (startYear) document.getElementById('StartYear_drop').value = startYear;";
        html = html + " if (timeYears) document.getElementById('timeYears_drop').value = timeYears;";
        html = html
                + " if (sortOrder) document.querySelector('input[name=SortOrder][value=' + sortOrder + ']').checked = true;";
        html = html + " if (dataToShow) document.getElementById('TempSelection_drop').value = dataToShow;";
        html = html + " }";
        html = html + "</script>";

        // reload/clear button
        html = html + "<button class='reset' type='button' onclick='reload()'>Reset</button>";
        // javascript for that button to clear all data entered
        //
        html = html + "<script>";
        html = html + "   function reload() {";
        html = html + "       document.getElementById('StartYear_drop').value = '';";
        html = html + "       document.getElementById('lengthDropdown').value = '';";
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

        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<Climate> years = jdbc.getLandOceanYears();
        for (Climate year : years) {
            html = html + "<option>" + year.getYear() + "</option>";
        }
        html = html + "      </select>";

        html = html + "      <label for='lengthDropdown'>Select the time period of years:</label>";
        html = html + "      <select id='lengthDropdown' name='lengthDropdown'>";
        for (int i = 1; i < years.size(); i++) {
            if (i != 1) {
                html = html + " <option value= " + i + ">" + i + " years</option>";
            } else {
                html = html + " <option value= " + i + ">" + i + " year</option>";
            }
        }
        html = html + "      </select>";

        html = html + "      </div>";

        html = html + "   <div class='form-group'>";

        html = html + "      <label for='dataType'>Select data you wish to view:</label>";
        html = html + "      <select id='dataType' name='dataType' size='1'>";
        html = html + "<option>Land Data</option>";
        html = html + "<option>Land-Ocean Data</option>";
        html = html + "      </select>";

        html = html + "      <label for='Comparison_textbox'>Select how many time periods you wish to compare:</label>";
        html = html
                + "      <input class='form-control' id='Comparison_textbox' name='Comparison_textbox' placeholder='Eg. 1 or 10'>";
        html = html + "      </div>";
        html = html + " </div>";
        html = html + " <div class='container'>";
        // this comes up in the javascript code
        // html = html + "<p>Please Select Another Set Of Data To Compare:</p>";

        // the extras created by javascript depending on the comparision textbox
        html = html + "   <div id='startYearSection' class='form-group'></div>";
        html = html + "   <div id='lengthSection' class='form-group'></div>";
        html = html + "   <div id='dataTypeSection' class='form-group'></div>";
        html = html + " </div>";

        html = html + """
                <script>
                var startYearDropdown = document.getElementById('StartYear_drop');
                var lengthDropdown = document.getElementById('lengthDropdown');
                var startYearOptions = [];

                // Populate start year options
                function populateStartYearOptions() {
                  var selectedLength = parseInt(lengthDropdown.value);
                  """;
        html = html + "var minStartYear = " + years.get(0).getYear() + ";";
        html = html + "var maxStartYear = " + years.get(years.size() - 1).getYear() + "- selectedLength;";
        html = html + """
                    startYearOptions = [];

                    // Add options based on the selected length
                    for (var year = maxStartYear; year >= minStartYear; year--) {
                      startYearOptions.push(year);
                    }
                  }

                  // Event listener for length dropdown
                  lengthDropdown.addEventListener('change', function() {
                    populateStartYearOptions();
                    updateStartYearOptions();
                  });

                  // Update start year options
                  function updateStartYearOptions() {
                    var selectedStartYear = startYearDropdown.value;

                    // Clear previous options
                    startYearDropdown.innerHTML = '';

                    // Add new options based on the selected length
                    for (var i = 0; i < startYearOptions.length; i++) {
                      var year = startYearOptions[i];

                      var option = document.createElement('option');
                      option.text = year;
                      option.value = year;
                      startYearDropdown.appendChild(option);
                    }

                    // Set selected start year if within the available range
                    if (startYearOptions.includes(parseInt(selectedStartYear))) {
                      startYearDropdown.value = selectedStartYear;
                    }
                  }

                  // Initial population and update of start year options
                  populateStartYearOptions();
                  updateStartYearOptions();
                </script>
                      """;

        // Javascript to create multiple comparisons

        html = html + "<script>";
        html = html + "   function updateSections() {";
        html = html + "       var comparisonValue = parseInt(document.getElementById('Comparison_textbox').value);";
        html = html + "       var startYearSection = document.getElementById('startYearSection');";
        // html = html + " var lengthSection =
        // document.getElementById('lengthSection');";
        html = html + "       var dataTypeSection = document.getElementById('dataTypeSection');";
        html = html + "       startYearSection.innerHTML = '';";
        // html = html + " lengthSection.innerHTML = '';";
        html = html + "       dataTypeSection.innerHTML = '';";

        // gets data from existing 3 drop downs
        // start year drop down
        html = html + "       var startYearOptionsHTML = '';";
        html = html + "       var startYearDropdown = document.getElementById('StartYear_drop');";
        html = html + "       for (var i = 0; i < startYearDropdown.options.length; i++) {";
        html = html
                + "           startYearOptionsHTML += '<option value=\"' + startYearDropdown.options[i].value + '\">' + startYearDropdown.options[i].text + '</option>';";
        html = html + "       }";
        // figured we don't want them to compare different durations
        // duration drop down
        // html = html + " var lengthOptionsHTML = '';";
        // html = html + " var lengthDropdown =
        // document.getElementById('lengthDropdown');";
        // html = html + " for (var i = 0; i < lengthDropdown.options.length; i++) {";
        // html = html
        // + " lengthOptionsHTML += '<option value=\"' + lengthDropdown.options[i].value
        // + '\">' + lengthDropdown.options[i].text + '</option>';";
        // html = html + " }";
        // data options
        html = html + "       var dataTypeOptionsHTML = '';";
        html = html + "       var dataTypeDropdown = document.getElementById('dataType');";
        html = html + "       for (var i = 0; i < dataTypeDropdown.options.length; i++) {";
        html = html
                + "           dataTypeOptionsHTML += '<option value=\"' + dataTypeDropdown.options[i].value + '\">' + dataTypeDropdown.options[i].text + '</option>';";
        html = html + "       }";

        html = html + "       for (var i = 1; i < comparisonValue; i++) {";
        html = html + "startYearSection.innerHTML += '<p>Please Select Another Set Of Data To Compare:</p>';";
        html = html
                + "startYearSection.innerHTML += 'Select another start year ' + i + ': <select id=\"startYear_' + i + '\" name=\"startYear_' + i + '\"><option></option>' + startYearOptionsHTML + '</select>';";
        // html = html
        // + "startYearSection.innerHTML += 'Select another or the same time period ' +
        // i + ': <select id=\"length_' + i + '\" name=\"length_' + i +
        // '\"><option></option>' + lengthOptionsHTML + '</select><br>';";

        html = html
                + "startYearSection.innerHTML += 'Select data you wish to view ' + i + ': <select id=\"dataType_' + i + '\" name=\"dataType_' + i +'\"><option></option>' + dataTypeOptionsHTML + '</select><br><br>';";

        html = html + "       }";
        html = html + "   }";

        html = html + "   document.getElementById('Comparison_textbox').addEventListener('input', function() {";
        html = html + "       updateSections();";
        html = html + "   });";

        html = html + "   window.addEventListener('load', function() {";
        html = html + "       updateSections();";
        html = html + "   });";
        html = html + "</script>";

        // Sorting order
        html = html + """
                <p>Sort By</p>
                <input type='radio' id='SortOrder' name='SortOrder' value='Ascending'>
                <label class='radio-label' for='Ascending'>Least Change In Average Temperature</label><br>
                <input type='radio' id='SortOrder' name='SortOrder' value='Descending'>
                 <label class='radio-label' for='Descending'>Greatest Change In Average Temperature</label><br>

                    """;

        // View table
        html = html + "<input type='checkbox' id='Table' name='dataTable' value='dataTable'>";
        html = html + "<label for='dataTable'> Do you wish to see table?</label><br>";
        // submit button
        html = html
                + "   <button class='showTable' type='submit' class='btn btn-primary' onclick='getDataInformation()'>Get Information</button>";

        html = html + "</form>";

        // retrieve javascript elements
        // html = html + """
        // document.getElementById('page3CForm').addEventListener('submit',
        // function(event) {
        // event.preventDefault();
        // """;

        html = html + "<script>";
        html = html + "   function getDataInformation() {";
        html = html + "       var comparisonValue = parseInt(document.getElementById('Comparison_textbox').value);";
        html = html + "       var selectedData = [];"; // Array to store selected options

        html = html + "       for (var i = 0; i < comparisonValue; i++) {";
        html = html + "           var startYearDropdown = document.getElementById('startYear_' + i);";
        html = html + "           var dataTypeDropdown = document.getElementById('dataType_' + i);";

        html = html + "           var startYear = startYearDropdown.options[startYearDropdown.selectedIndex].value;";
        html = html + "           var dataType = dataTypeDropdown.options[dataTypeDropdown.selectedIndex].value;";

        html = html + "           selectedData.push({";
        html = html + "               startYear: startYear,";
        html = html + "               dataType: dataType";
        html = html + "           });";
        html = html + "       }";

        html = html + "       // Do something with the selectedData array";
        html = html + "       console.log(selectedData);"; // Print the selected options to the console
        html = html + "   }";
        html = html + "</script>";

        /*
         * Get the Form Data
         * from the drop down list
         * Need to be Careful!!
         * If the form is not filled in, then the form will return null!
         */
        String StartYear_drop = context.formParam("StartYear_drop");

        if (StartYear_drop == null) {
            // If NULL, nothing to show, therefore we make some "no results" HTML

            // html = html + "<h2><i>No Results to show for dropbox</i></h2>";
        } else {
            // If NOT NULL, then lookup the movie by type!
            // html = html + outputData(StartYear_drop);

        }
        // String DataToShow = context.formParam("TempSelection_drop");
        String lengthDropdown = context.formParam("lengthDropdown");
        String SortBy = context.formParam("SortOrder");

        String javascriptvar1 = context.queryParam("startYear_2");
        html = html + "<p>" + javascriptvar1 + "</p>";
        if (lengthDropdown == null) {
            // If NULL, nothing to show, therefore we make some "no results" HTML

            // html = html + "<h2><i>No Results to show for dropbox</i></h2>";
        } else {
            // If NOT NULL, then lookup the movie by type!
            // html = html + outputData(timeYears_drop);

            if (SortBy == null) {
                html = html + "<h2><i>Please select a sorting method</i></h2>";
            } else {
                // html = html + outputData(StartYear_drop, lengthDropdown, DataToShow, SortBy);
            }
        }

        // Close Content div
        html = html + "</div>";

        // Footer
        html = html
                + """
                            <div class='footer'>
                                <p>COSC2803 - Studio Project Starter Code (Apr23)</p>
                                <a href='PageHelp.html'>Help Page</a>
                                <p style='display: flex; gap: 10px;'><a href='PageHelp.html#help-section'>Help</a><a href='PageHelp.html#faq-section'>FAQ</a><a href='PageHelp.html#advanced-section'>Advanced Features</a></p>
                            </div>
                        """;

        // Finish the HTML webpage
        html = html + "</body>" + "</html>";

        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(html);
    }

    // public String outputData(String startYear, String timeYears, String
    // DataToShow, String SortBy) {
    // String endYear = Integer.toString(Integer.parseInt(startYear) +
    // Integer.parseInt(timeYears) - 1);
    // String html = "<div id='tableData'>";
    // html = html + "<h2>" + "Climate Data From " + startYear + " To " + endYear +
    // "</h2>";
    // JDBCConnection jdbc = new JDBCConnection();
    // ArrayList<Climate> oceantemps;

    // if (SortBy.equals("Ascending")) {
    // oceantemps = jdbc.getAllLandOceanDataASC();
    // } else if (SortBy.equals("Descending")) {
    // oceantemps = jdbc.getAllLandOceanDataDESC();
    // }
    // // default option
    // else {
    // oceantemps = jdbc.getAllLandOceanDataASC();
    // }

    // int startIndex = getIndexByYear(oceantemps, Integer.parseInt(startYear));
    // int endIndex = getIndexByYear(oceantemps, Integer.parseInt(endYear));

    // if (DataToShow.equals("Only Average Land Ocean Temperature")) {
    // String dataType = "Average";
    // html = displayTable(html, startYear, timeYears, oceantemps, startIndex,
    // endIndex, dataType);
    // html = html + "<table>";
    // html = html
    // + "<tr><th>Year</th><th>Average Land Ocean Temperature</th></tr>";
    // if (SortBy.equals("Ascending")) {
    // for (int i = startIndex; i <= endIndex; i++) {
    // Climate climate = oceantemps.get(i);
    // html = html + "<tr><td>" + climate.getYear() + "</td><td>" +
    // climate.getAverageTemperature()
    // + "</td></tr>";
    // }
    // } else if (SortBy.equals("Descending")) {
    // for (int i = endIndex; i <= startIndex; i++) {
    // Climate climate = oceantemps.get(i);
    // html = html + "<tr><td>" + climate.getYear() + "</td><td>" +
    // climate.getAverageTemperature()
    // + "</td></tr>";
    // }
    // }
    // html = html + "</table>";

    // } else if (DataToShow.equals("Only Minimum Land Ocean Temperature")) {
    // String dataType = "Minimum";
    // html = displayTable(html, startYear, timeYears, oceantemps, startIndex,
    // endIndex, dataType);
    // html = html + "<table>";
    // html = html
    // + "<tr><th>Year</th><th>Minimum Land Ocean Temperature</th></tr>";

    // if (SortBy.equals("Ascending")) {
    // for (int i = startIndex; i <= endIndex; i++) {
    // Climate climate = oceantemps.get(i);
    // html = html + "<tr><td>" + climate.getYear() + "</td><td>" +
    // climate.getMinimumTemperature()
    // + "</td></tr>";
    // }
    // } else if (SortBy.equals("Descending")) {
    // for (int i = endIndex; i <= startIndex; i++) {
    // Climate climate = oceantemps.get(i);
    // html = html + "<tr><td>" + climate.getYear() + "</td><td>" +
    // climate.getMinimumTemperature()
    // + "</td></tr>";
    // }
    // }
    // html = html + "</table>";

    // } else if (DataToShow.equals("Only Maximum Land Ocean Temperature")) {
    // String dataType = "Maximum";
    // html = displayTable(html, startYear, timeYears, oceantemps, startIndex,
    // endIndex, dataType);
    // html = html + "<table>";
    // html = html
    // + "<tr><th>Year</th><th>Maximum Land Ocean Temperature</th></tr>";
    // if (SortBy.equals("Ascending")) {
    // for (int i = startIndex; i <= endIndex; i++) {
    // Climate climate = oceantemps.get(i);
    // html = html + "<tr><td>" + climate.getYear() + "</td><td>" +
    // climate.getMaximumTemperature()
    // + "</td></tr>";
    // }
    // } else if (SortBy.equals("Descending")) {
    // for (int i = endIndex; i <= startIndex; i++) {
    // Climate climate = oceantemps.get(i);
    // html = html + "<tr><td>" + climate.getYear() + "</td><td>" +
    // climate.getMaximumTemperature()
    // + "</td></tr>";
    // }
    // }
    // html = html + "</table>";

    // } else if (DataToShow.equals("Show All Land Ocean Temperature Data")) {
    // String dataType = "All";
    // html = displayTable(html, startYear, timeYears, oceantemps, startIndex,
    // endIndex, dataType);
    // html = html + "<table>";
    // html = html
    // + "<tr><th>Year</th><th>Average Land Ocean Temperature</th><th>Minimum Land
    // Ocean Temperature</th><th>Maximum Land Ocean Temperature</th></tr>";
    // if (SortBy.equals("Ascending")) {
    // for (int i = startIndex; i <= endIndex; i++) {
    // Climate climate = oceantemps.get(i);
    // html = html + "<tr><td>" + climate.getYear() + "</td><td>" +
    // climate.getAverageTemperature()
    // + "</td><td>"
    // + climate.getMinimumTemperature() + "</td><td>" +
    // climate.getMaximumTemperature()
    // + "</td></tr>";
    // }
    // } else if (SortBy.equals("Descending")) {
    // for (int i = endIndex; i <= startIndex; i++) {
    // Climate climate = oceantemps.get(i);
    // html = html + "<tr><td>" + climate.getYear() + "</td><td>" +
    // climate.getAverageTemperature()
    // + "</td><td>"
    // + climate.getMinimumTemperature() + "</td><td>" +
    // climate.getMaximumTemperature()
    // + "</td></tr>";
    // }

    // }
    // html = html + "</table>";
    // }
    // html = html + "</div>";

    // return html;
    // }

    // private int getIndexByYear(ArrayList<Climate> oceantemps, int year) {
    // for (int i = 0; i < oceantemps.size(); i++) {
    // if (oceantemps.get(i).getYear() == year) {
    // return i;
    // }
    // }
    // // Default index if year is not found otherwise there is an error
    // return 0;
    // }

    // private String displayTable(String html, String startYear, String timeYears,
    // ArrayList<Climate> oceantemps,
    // int startIndex, int endIndex, String dataType) {
    // float startValue = 0;
    // float endValue = 0;
    // String tempChange = "";
    // String roundDifferenceValue = "";
    // String percentageChange = "";
    // html = html + "<h2> Change In Temperature Between " + startYear + " And " +
    // timeYears + "</h2>";

    // if (dataType.equals("Average")) {

    // startValue = oceantemps.get(startIndex).getAverageTemperature();
    // html = html + "<p>" + startIndex + "</p>";
    // endValue = oceantemps.get(endIndex).getAverageTemperature();
    // tempChange = getTemperatureChange(startValue, endValue, dataType);
    // roundDifferenceValue = getRoundDifferenceValue(startValue, endValue);
    // percentageChange = getPercentageChange(startValue, endValue);
    // } else if (dataType.equals("Minimum")) {

    // startValue = oceantemps.get(startIndex).getMinimumTemperature();
    // endValue = oceantemps.get(endIndex).getMinimumTemperature();
    // tempChange = getTemperatureChange(startValue, endValue, dataType);
    // roundDifferenceValue = getRoundDifferenceValue(startValue, endValue);
    // percentageChange = getPercentageChange(startValue, endValue);
    // } else if (dataType.equals("Maximum")) {

    // startValue = oceantemps.get(startIndex).getMaximumTemperature();
    // endValue = oceantemps.get(endIndex).getMaximumTemperature();
    // tempChange = getTemperatureChange(startValue, endValue, dataType);
    // roundDifferenceValue = getRoundDifferenceValue(startValue, endValue);
    // percentageChange = getPercentageChange(startValue, endValue);
    // } else if (dataType.equals("All")) {
    // // average data
    // dataType = "Average";
    // startValue = oceantemps.get(startIndex).getAverageTemperature();
    // endValue = oceantemps.get(endIndex).getAverageTemperature();
    // tempChange = getTemperatureChange(startValue, endValue, dataType);
    // roundDifferenceValue = getRoundDifferenceValue(startValue, endValue);
    // percentageChange = getPercentageChange(startValue, endValue);
    // html = html + "<p> There was" + tempChange + " from " + startYear + " to " +
    // timeYears
    // + " of " + roundDifferenceValue + "°C. This is a percentage change of " +
    // percentageChange
    // + ". </p>";
    // // min data
    // dataType = "Minimum";
    // startValue = oceantemps.get(startIndex).getMinimumTemperature();
    // endValue = oceantemps.get(endIndex).getMinimumTemperature();
    // tempChange = getTemperatureChange(startValue, endValue, dataType);
    // roundDifferenceValue = getRoundDifferenceValue(startValue, endValue);
    // percentageChange = getPercentageChange(startValue, endValue);
    // html = html + "<p> There was" + tempChange + " from " + startYear + " to " +
    // timeYears
    // + " of " + roundDifferenceValue + "°C. This is a percentage change of " +
    // percentageChange
    // + ". </p>";
    // // max data
    // dataType = "Maximum";
    // startValue = oceantemps.get(startIndex).getMaximumTemperature();
    // endValue = oceantemps.get(endIndex).getMaximumTemperature();
    // tempChange = getTemperatureChange(startValue, endValue, dataType);
    // roundDifferenceValue = getRoundDifferenceValue(startValue, endValue);
    // percentageChange = getPercentageChange(startValue, endValue);
    // html = html + "<p> There was" + tempChange + " from " + startYear + " to " +
    // timeYears
    // + " of " + roundDifferenceValue + "°C. This is a percentage change of " +
    // percentageChange
    // + ". </p>";
    // return html;

    // } else {
    // tempChange = "";
    // roundDifferenceValue = "";
    // percentageChange = "";
    // }

    // html = html + "<p> There was" + tempChange + " from " + startYear + " to " +
    // timeYears
    // + " of " + roundDifferenceValue + "°C. This is a percentage change of " +
    // percentageChange
    // + ". </p>";

    // return html;
    // }

    // public String getTemperatureChange(float startValue, float endValue, String
    // dataType) {
    // String tempChange = "";
    // float difference = endValue - startValue;
    // if (difference > 0) {
    // tempChange = " an increase in the " + dataType.toLowerCase() + "
    // temperature";
    // } else if (difference < 0) {
    // tempChange = " a decrease in the " + dataType.toLowerCase() + " temperature";
    // } else {
    // tempChange = " no change in the " + dataType.toLowerCase() + " temperature";
    // }

    // return tempChange;
    // }

    // public String getRoundDifferenceValue(float startValue, float endValue) {
    // float difference = endValue - startValue;
    // String roundDifferenceValue = String.format("%.2f", difference);
    // return roundDifferenceValue;
    // }

    // public String getPercentageChange(float startValue, float endValue) {
    // float difference = endValue - startValue;
    // float percentage = (difference / startValue) * 100;
    // String percentageChange = String.format("%.2f", percentage) + "%";
    // return percentageChange;
    // }

}