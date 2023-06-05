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
        int numberOfDatasets = 0;
        if (context.formParam("counter") != null) {
            String counterValue = context.formParam("counter");
            numberOfDatasets = Integer.parseInt(counterValue);
        }
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
        html = html + "    sessionStorage.removeItem('counter');";
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

        html = html
                + "   <button class='AnotherDataset' type='button' class='btn btn-NewDataset' onclick='incrementCounter()'' >Add Another Set Of Data</button>";

        html = html + "      </div>";
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
        // everytime button is pressed it will reload form and increase counter that is
        // at the top of document
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

        if (numberOfDatasets > 0) {
            html = html + "<div id='datasetContainer'>";

            for (int i = 0; i < numberOfDatasets; i++) {
                html = html + "<p>Please Select Another Set Of Data To Compare:</p>";
                html = html + "      <label for='StartYear_drop'>Select the start year:</label>";
                html = html + "      <select id='StartYear_drop" + i + "'name='StartYear_drop'>";

                years = jdbc.getLandOceanYears();
                for (Climate year : years) {
                    html = html + "<option>" + year.getYear() + "</option>";
                }
                html = html + "      </select>";

                html = html + "      <label for='dataType'>Select data you wish to view:</label>";
                html = html + "      <select id='dataType" + i + "'name='dataType' size='1'>";
                html = html + "<option>Land Data</option>";
                html = html + "<option>Land-Ocean Data</option>";
                html = html + "      </select><br>";
            }
            html = html + "</div>";
        }
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

}