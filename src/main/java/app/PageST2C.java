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
        html = html + "<head> <meta charset='UTF-8'>" +
                "<title>Focused View Of Land Ocean Temperature</title>";

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

        // Add HTML for the page content
        // Explanation of land ocean temp
        html = html
                + """
                        <h2>A Look At Annual Global Land Ocean Temperature Records</h2>
                        <p>Land Ocean Temperature is an average of the temperatures of both the land and ocean surfaces over a period of time. </p>
                        <p>The Global Land Ocean records are especially useful as a critical tool in assessing long term climate trends and the extent of global warming due to the inclusion of the surfaces temperature of ocean data,
                        this is because oceans have a higher heat capacity compared to land therefore meaning they absorb and release heat slower which can then help show greater discrepancies in temperatures therefore showing signs of climate change. </p>
                        """;

        // All the Drop down menu stuff for the data to eventually be retrieved from
        // html = html + "<form action='/page2C.html' method='post'>";

        // A version of the same thing with a javascript function to stop values entered
        // being cleared on reload
        html = html + "<form action='/page2C.html' method='post' onsubmit='return ReenterData()'>";

        // This bit of javascript makes it so the page keeps the same values leading to
        // less user confusion when the page reloads
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
        // javascript for that button to clear all data entered
        //
        html = html + "<script>";
        html = html + "   function reload() {";
        html = html + "       document.getElementById('StartYear_drop').value = '';";
        html = html + "       document.getElementById('EndYear_drop').options.length = 0;";
        html = html + "       var sortOrderRadios = document.querySelectorAll('input[name=SortOrder]');";
        html = html + "       sortOrderRadios.forEach(function(radio) { radio.checked = false; });";
        html = html + "       document.getElementById('TempSelection_drop').value = '';";

        html = html + "       document.getElementById('tableData').innerHTML = '';";
        html = html + "       return false;";
        html = html + "   }";
        html = html + "</script>";

        html = html + "   <div class='form-group'>";
        html = html + "      <label for='StartYear_drop'>Select the start year (Dropdown):</label>";
        html = html
                + "      <select id='StartYear_drop' name='StartYear_drop' onchange='updateEndYearOptions(this.value)' size='1'>";
        // This onchange section ^ makes the website more dynamic by using a java script
        // Used java script taught in this video
        // https://www.youtube.com/watch?v=SBmSRK3feww&t=7s and my own knowledge
        // function to take the value the user selects to then place it in the end date
        // drop down menu.

        // this connects the database to the start date drop down box.
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<Climate> years = jdbc.getLandOceanYears();
        for (Climate year : years) {
            html = html + "<option>" + year.getYear() + "</option>";
        }
        html = html + "      </select>";
        html = html + "   </div>";
        // Using some javascript to change the value of the end year drop down section
        // to be the same as the start year
        html = html + "<script>";
        html = html + "   function updateEndYearOptions(selectedItem) {";
        html = html + "       var endYearDropdown = document.getElementById('EndYear_drop');";
        html = html + "       endYearDropdown.innerHTML = '';"; // Clear existing options

        // This javascript code effectively makes the data in the end year section must
        // be in the range of the user selected date to the end of the avilable data in
        // the database.
        html = html + "       var selectedYear = parseInt(selectedItem);";
        html = html + "       var EndYear = " + years.get(years.size() - 1).getYear() + ";";
        html = html + "       for (var year = selectedYear; year <= EndYear; year++) {";
        html = html + "           var option = document.createElement('option');";
        html = html + "           option.text = year;";
        html = html + "           option.value = year;";
        html = html + "           endYearDropdown.add(option);";
        html = html + "       }";
        html = html + "   }";
        html = html + "</script>";

        // element of the end year drop down box
        html = html + "   <div class='form-group'>";
        html = html + "<p></p>";
        html = html + "      <label for='EndYear_drop'>Select the end year (Dropdown):</label>";
        html = html + "      <select id='EndYear_drop' name='EndYear_drop' size='1'>";
        html = html + "      </select>";
        html = html + "   </div>";

        // Sorting order
        html = html + """
                <p>Sort By</p>
                <input type='radio' id='SortOrder' name='SortOrder' value='Ascending'>
                <label class='radio-label' for='Ascending'>Ascending Order</label><br>
                <input type='radio' id='SortOrder' name='SortOrder' value='Descending'>
                 <label class='radio-label' for='Descending'>Descending Order</label>

                    """;

        html = html + "   <div class='form-group'>";
        html = html + "      <label for='TempSelection_drop'>Select Data You Wish To View (Dropdown):</label>";
        html = html + "      <select id='TempSelection_drop' name='TempSelection_drop' size='1'>";
        html = html + "<option>Only Average Land Ocean Temperature</option>";
        html = html + "<option>Only Minimum Land Ocean Temperature</option>";
        html = html + "<option>Only Maximum Land Ocean Temperature</option>";
        html = html + "<option>Show All Land Ocean Temperature Data</option>";
        html = html + "      </select>";
        html = html + "   </div>";
        // submit button
        html = html + "   <button class='showTable' type='submit' class='btn btn-primary'>Show Table</button>";

        html = html + "</form>";

        /*
         * Get the Form Data
         * from the drop down list
         * Need to be Careful!!
         * If the form is not filled in, then the form will return null!
         */
        String StartYear_drop = context.formParam("StartYear_drop");
        // String movietype_drop = context.queryParam("movietype_drop");
        if (StartYear_drop == null) {
            // If NULL, nothing to show, therefore we make some "no results" HTML

            // html = html + "<h2><i>No Results to show for dropbox</i></h2>";
        } else {
            // If NOT NULL, then lookup the movie by type!
            // html = html + outputData(StartYear_drop);

        }
        String DataToShow = context.formParam("TempSelection_drop");
        String EndYear_drop = context.formParam("EndYear_drop");
        String SortBy = context.formParam("SortOrder");
        // String movietype_drop = context.queryParam("movietype_drop");
        if (EndYear_drop == null) {
            // If NULL, nothing to show, therefore we make some "no results" HTML

            // html = html + "<h2><i>No Results to show for dropbox</i></h2>";
        } else {
            // If NOT NULL, then lookup the movie by type!
            // html = html + outputData(EndYear_drop);

            if (SortBy == null) {
                html = html + "<h2><i>Please select a sorting method</i></h2>";
            } else {
                html = html + outputData(StartYear_drop, EndYear_drop, DataToShow, SortBy);
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

    public String outputData(String startYear, String endYear, String DataToShow, String SortBy) {
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
            return html;

        } else {
            tempChange = "";
            roundDifferenceValue = "";
            percentageChange = "";
        }

        // html = html + "<h3>" + "Land Ocean Temperature (" + dataType + ")" + "</h3>";
        // html = html + "<p>" + "Start Year: " + startYear + ", End Year: " + endYear +
        // "</p>";
        // html = html + "<p>" + "Temperature Change: " + tempChange + "</p>";
        // html = html + "<p>" + "Round Difference Value: " + roundDifferenceValue +
        // "</p>";
        // html = html + "<p>" + "Percentage Change: " + percentageChange + "</p>";

        html = html + "<p> There was" + tempChange + " from " + startYear + " to " + endYear
                + " of " + roundDifferenceValue + "°C. This is a percentage change of  " + percentageChange
                + ". </p>";

        return html;
    }

    public String getTemperatureChange(float startValue, float endValue, String dataType) {
        String tempChange = "";
        float difference = endValue - startValue;
        if (difference > 0) {
            tempChange = " an increase in the " + dataType.toLowerCase() + " temperature";
        } else if (difference < 0) {
            tempChange = " a decrease in the " + dataType.toLowerCase() + " temperature";
        } else {
            tempChange = " no change in the " + dataType.toLowerCase() + " temperature";
        }
        // tempChange = tempChange + String.format("%.2f", difference) + " °C";
        return tempChange;
    }

    public String getRoundDifferenceValue(float startValue, float endValue) {
        float difference = endValue - startValue;
        String roundDifferenceValue = String.format("%.2f", difference);
        return roundDifferenceValue;
    }

    public String getPercentageChange(float startValue, float endValue) {
        float difference = endValue - startValue;
        float percentage = (difference / startValue) * 100;
        String percentageChange = String.format("%.2f", percentage) + "%";
        return percentageChange;
    }
}

// old
// public String outputData(String startYear, String endYear, String DataToShow,
// String SortBy) {
// String html = "<div id='tableData'>";
// html = html + "<h2>" + "Climate Data From " + startYear + " To " + endYear +
// "</h2>";
// JDBCConnection jdbc = new JDBCConnection();

// if (SortBy.equals("Ascending")) {
// ArrayList<Climate> oceantemps = jdbc.getAllLandOceanDataASC();
// int startIndex = 0;
// for (int i = 0; i < oceantemps.size(); i++) {
// if (oceantemps.get(i).getYear() == Integer.parseInt(startYear)) {
// startIndex = i;
// break;
// }
// }
// int endIndex = 0;
// for (int i = 0; i < oceantemps.size(); i++) {
// if (oceantemps.get(i).getYear() == Integer.parseInt(endYear)) {
// endIndex = i;
// break;
// }
// }

// if (DataToShow.equals("Only Average Land Ocean Temperature")) {

// // Comparison of data over the time period selected
// String percentageChange = String.format("%.2f",
// ((oceantemps.get(endIndex).getAverageTemperature()
// - oceantemps.get(startIndex).getAverageTemperature())
// / oceantemps.get(startIndex).getAverageTemperature()) * 100);
// String tempChange = "No Change";
// String roundDifferenceValue = "0";
// float differenceValue = oceantemps.get(endIndex).getAverageTemperature()
// - oceantemps.get(startIndex).getAverageTemperature();
// if (String.format("%.2f", differenceValue).equals("0.00")) {
// roundDifferenceValue = Float.toString(differenceValue);
// } else {
// roundDifferenceValue = String.format("%.2f", differenceValue);
// }
// if (oceantemps.get(startIndex).getAverageTemperature() <
// oceantemps.get(endIndex)
// .getAverageTemperature()) {
// tempChange = "There was an increase in average temperature";
// } else if (oceantemps.get(startIndex).getAverageTemperature() >
// oceantemps.get(endIndex)
// .getAverageTemperature()) {
// tempChange = "There was an decrease in average temperature";
// } else {
// tempChange = "There was no change in average temperature";
// }
// html = html + "<div id='tableData'>";
// html = html + "<h2> Change In Temperature Between " + startYear + " And " +
// endYear + "</h2>";
// html = html + "<p>" + tempChange + " from " + startYear + " to " + endYear
// + " of " + roundDifferenceValue + "°C. This is a percentage change of " +
// percentageChange
// + "%. </p>";
// html = html + "</div>";
// html = html
// + """
// <table>
// <tr>
// <th>Year</th>
// <th>Average Land Ocean Temperature</th>
// </tr>
// """;

// for (int i = startIndex; i <= endIndex; i++) {
// html = html + " <tr> <td>" + oceantemps.get(i).getYear() + "</td> " + "<td>"
// + oceantemps.get(i).getAverageTemperature()
// + "</td> </tr>";
// }

// html = html + "</table>";

// } else if (DataToShow.equals("Only Minimum Land Ocean Temperature")) {

// // Comparison of data over the time period selected
// String percentageChange = String.format("%.2f",
// ((oceantemps.get(endIndex).getMinimumTemperature()
// - oceantemps.get(startIndex).getMinimumTemperature())
// / oceantemps.get(startIndex).getMinimumTemperature()) * 100);
// String tempChange = "No Change";
// String roundDifferenceValue = "0";
// float differenceValue = oceantemps.get(endIndex).getMinimumTemperature()
// - oceantemps.get(startIndex).getMinimumTemperature();
// if (String.format("%.2f", differenceValue).equals("0.00")) {
// roundDifferenceValue = Float.toString(differenceValue);
// } else {
// roundDifferenceValue = String.format("%.2f", differenceValue);
// }
// if (oceantemps.get(startIndex).getMinimumTemperature() <
// oceantemps.get(endIndex)
// .getMinimumTemperature()) {
// tempChange = "There was an increase in the minimum average temperature";
// } else if (oceantemps.get(startIndex).getMinimumTemperature() >
// oceantemps.get(endIndex)
// .getMinimumTemperature()) {
// tempChange = "There was an decrease in the minimum average temperature";
// } else {
// tempChange = "There was no change in the minimum average temperature";
// }
// html = html + "<div id='tableData'>";
// html = html + "<h2> Change In Temperature Between " + startYear + " And " +
// endYear + "</h2>";
// html = html + "<p>" + tempChange + " from " + startYear + " to " + endYear
// + " of " + roundDifferenceValue + "°C. This is a percentage change of " +
// percentageChange
// + "%. </p>";
// html = html + "</div>";

// html = html
// + """
// <table>
// <tr>
// <th>Year</th>
// <th>Minimum Land Ocean Temperature</th>
// </tr>
// """;

// for (int i = startIndex; i <= endIndex; i++) {
// html = html + " <tr> <td>" + oceantemps.get(i).getYear() + "</td> " + "<td>"
// + oceantemps.get(i).getMinimumTemperature()
// + "</td> </tr>";
// }

// html = html + "</table>";

// } else if (DataToShow.equals("Only Maximum Land Ocean Temperature")) {

// // Comparison of data over the time period selected
// String percentageChange = String.format("%.2f",
// ((oceantemps.get(endIndex).getMaximumTemperature()
// - oceantemps.get(startIndex).getMaximumTemperature())
// / oceantemps.get(startIndex).getMaximumTemperature()) * 100);
// String tempChange = "No Change";
// String roundDifferenceValue = "0";
// float differenceValue = oceantemps.get(endIndex).getMaximumTemperature()
// - oceantemps.get(startIndex).getMaximumTemperature();
// if (String.format("%.2f", differenceValue).equals("0.00")) {
// roundDifferenceValue = Float.toString(differenceValue);
// } else {
// roundDifferenceValue = String.format("%.2f", differenceValue);
// }
// if (oceantemps.get(startIndex).getMaximumTemperature() <
// oceantemps.get(endIndex)
// .getMaximumTemperature()) {
// tempChange = "There was an increase in the minimum average temperature";
// } else if (oceantemps.get(startIndex).getMaximumTemperature() >
// oceantemps.get(endIndex)
// .getMaximumTemperature()) {
// tempChange = "There was an decrease in the maximum average temperature";
// } else {
// tempChange = "There was no change in the maximum average temperature";
// }
// html = html + "<div id='tableData'>";
// html = html + "<h2> Change In Temperature Between " + startYear + " And " +
// endYear + "</h2>";
// html = html + "<p>" + tempChange + " from " + startYear + " to " + endYear
// + " of " + roundDifferenceValue + "°C. This is a percentage change of " +
// percentageChange
// + "%. </p>";
// html = html + "</div>";

// html = html
// + """
// <table>
// <tr>
// <th>Year</th>
// <th>Maximum Land Ocean Temperature</th>
// </tr>
// """;

// for (int i = startIndex; i <= endIndex; i++) {
// html = html + " <tr> <td>" + oceantemps.get(i).getYear() + "</td> " + "<td>"
// + oceantemps.get(i).getMaximumTemperature()
// + "</td> </tr>";
// }

// html = html +
// """
// </table>
// """;

// } else if (DataToShow.equals("Show All Land Ocean Temperature Data")) {
// html = html
// + """
// <table>
// <tr>
// <th>Year</th>
// <th>Average Land Ocean Temperature</th>
// <th>Minimum Land Ocean Temperature</th>
// <th>Maximum Land Ocean Temperature</th>
// </tr>
// """;

// for (int i = startIndex; i <= endIndex; i++) {
// html = html + " <tr> <td>" + oceantemps.get(i).getYear() + "</td> " + "<td>"
// + oceantemps.get(i).getAverageTemperature()
// + "</td>" + "<td>"
// + oceantemps.get(i).getMinimumTemperature() + "</td>" + "<td>"
// + oceantemps.get(i).getMaximumTemperature()
// + "</td> </tr>";
// }

// html = html +
// """
// </table>

// """;

// }
// } else if (SortBy.equals("Descending")) {
// ArrayList<Climate> oceantemps = jdbc.getAllLandOceanDataDESC();
// int startIndex = 0;
// for (int i = 0; i < oceantemps.size(); i++) {
// if (oceantemps.get(i).getYear() == Integer.parseInt(startYear)) {
// startIndex = i;
// // TESTING html = html + "<p>" + startIndex + "</p>";
// break;
// }
// }
// int endIndex = 0;
// for (int i = 0; i < oceantemps.size(); i++) {
// if (oceantemps.get(i).getYear() == Integer.parseInt(endYear)) {
// endIndex = i;
// // TESTING html = html + "<p>" + endIndex + "</p>";
// break;
// }
// }
// if (DataToShow.equals("Only Average Land Ocean Temperature")) {
// // Comparison of data over the time period selected
// String percentageChange = String.format("%.2f",
// ((oceantemps.get(endIndex).getAverageTemperature()
// - oceantemps.get(startIndex).getAverageTemperature())
// / oceantemps.get(startIndex).getAverageTemperature()) * 100);
// String tempChange = "No Change";
// String roundDifferenceValue = "0";
// float differenceValue = oceantemps.get(endIndex).getAverageTemperature()
// - oceantemps.get(startIndex).getAverageTemperature();
// if (String.format("%.2f", differenceValue).equals("0.00")) {
// roundDifferenceValue = Float.toString(differenceValue);
// } else {
// roundDifferenceValue = String.format("%.2f", differenceValue);
// }
// if (oceantemps.get(startIndex).getAverageTemperature() <
// oceantemps.get(endIndex)
// .getAverageTemperature()) {
// tempChange = "There was an increase in average temperature";
// } else if (oceantemps.get(startIndex).getAverageTemperature() >
// oceantemps.get(endIndex)
// .getAverageTemperature()) {
// tempChange = "There was an decrease in average temperature";
// } else {
// tempChange = "There was no change in average temperature";
// }
// html = html + "<div id='tableData'>";
// html = html + "<h2> Change In Temperature Between " + startYear + " And " +
// endYear + "</h2>";
// html = html + "<p>" + tempChange + " from " + startYear + " to " + endYear
// + " of " + roundDifferenceValue + "°C. This is a percentage change of " +
// percentageChange
// + "%. </p>";
// html = html + "</div>";

// html = html
// + """
// <table>
// <tr>
// <th>Year</th>
// <th>Average Land Ocean Temperature</th>
// </tr>
// """;

// for (int i = endIndex; i <= startIndex; i++) {
// html = html + " <tr> <td>" + oceantemps.get(i).getYear() + "</td> " + "<td>"
// + oceantemps.get(i).getAverageTemperature()
// + "</td> </tr>";
// }

// html = html +
// """
// </table>
// """;

// } else if (DataToShow.equals("Only Minimum Land Ocean Temperature")) {
// // Comparison of data over the time period selected
// String percentageChange = String.format("%.2f",
// ((oceantemps.get(endIndex).getMinimumTemperature()
// - oceantemps.get(startIndex).getMinimumTemperature())
// / oceantemps.get(startIndex).getMinimumTemperature()) * 100);
// String tempChange = "No Change";
// String roundDifferenceValue = "0";
// float differenceValue = oceantemps.get(endIndex).getMinimumTemperature()
// - oceantemps.get(startIndex).getMinimumTemperature();
// if (String.format("%.2f", differenceValue).equals("0.00")) {
// roundDifferenceValue = Float.toString(differenceValue);
// } else {
// roundDifferenceValue = String.format("%.2f", differenceValue);
// }
// if (oceantemps.get(startIndex).getMinimumTemperature() <
// oceantemps.get(endIndex)
// .getMinimumTemperature()) {
// tempChange = "There was an increase in the minimum average temperature";
// } else if (oceantemps.get(startIndex).getMinimumTemperature() >
// oceantemps.get(endIndex)
// .getMinimumTemperature()) {
// tempChange = "There was a decrease in the minimum average temperature";
// } else {
// tempChange = "There was no change in the minimum average temperature";
// }
// html = html + "<div id='tableData'>";
// html = html + "<h2> Change In Temperature Between " + startYear + " And " +
// endYear + "</h2>";
// html = html + "<p>" + tempChange + " from " + startYear + " to " + endYear
// + " of " + roundDifferenceValue + "°C. This is a percentage change of " +
// percentageChange
// + "%. </p>";
// html = html + "</div>";

// html = html
// + """
// <table>
// <tr>
// <th>Year</th>
// <th>Minimum Land Ocean Temperature</th>
// </tr>
// """;

// for (int i = endIndex; i <= startIndex; i++) {
// html = html + " <tr> <td>" + oceantemps.get(i).getYear() + "</td> " + "<td>"
// + oceantemps.get(i).getMinimumTemperature()
// + "</td> </tr>";
// }

// html = html +
// """
// </table>
// """;

// } else if (DataToShow.equals("Only Maximum Land Ocean Temperature")) {
// // Comparison of data over the time period selected
// String percentageChange = String.format("%.2f",
// ((oceantemps.get(endIndex).getMaximumTemperature()
// - oceantemps.get(startIndex).getMaximumTemperature())
// / oceantemps.get(startIndex).getMaximumTemperature()) * 100);
// String tempChange = "No Change";
// String roundDifferenceValue = "0";
// float differenceValue = oceantemps.get(endIndex).getMaximumTemperature()
// - oceantemps.get(startIndex).getMaximumTemperature();
// if (String.format("%.2f", differenceValue).equals("0.00")) {
// roundDifferenceValue = Float.toString(differenceValue);
// } else {
// roundDifferenceValue = String.format("%.2f", differenceValue);
// }
// if (oceantemps.get(startIndex).getMaximumTemperature() <
// oceantemps.get(endIndex)
// .getMaximumTemperature()) {
// tempChange = "There was an increase in the minimum average temperature";
// } else if (oceantemps.get(startIndex).getMaximumTemperature() >
// oceantemps.get(endIndex)
// .getMaximumTemperature()) {
// tempChange = "There was an decrease in the maximum average temperature";
// } else {
// tempChange = "There was no change in the maximum average temperature";
// }
// html = html + "<div id='tableData'>";
// html = html + "<h2> Change In Temperature Between " + startYear + " And " +
// endYear + "</h2>";
// html = html + "<p>" + tempChange + " from " + startYear + " to " + endYear
// + " of " + roundDifferenceValue + "°C. This is a percentage change of " +
// percentageChange
// + "%. </p>";
// html = html + "</div>";

// html = html
// + """
// <table>
// <tr>
// <th>Year</th>
// <th>Maximum Land Ocean Temperature</th>
// </tr>
// """;

// for (int i = endIndex; i <= startIndex; i++) {
// html = html + " <tr> <td>" + oceantemps.get(i).getYear() + "</td> " + "<td>"
// + oceantemps.get(i).getMaximumTemperature()
// + "</td> </tr>";
// }

// html = html +
// """
// </table>
// """;

// } else if (DataToShow.equals("Show All Land Ocean Temperature Data")) {
// html = html
// + """
// <table>
// <tr>
// <th>Year</th>
// <th>Average Land Ocean Temperature</th>
// <th>Minimum Land Ocean Temperature</th>
// <th>Maximum Land Ocean Temperature</th>
// </tr>
// """;

// for (int i = endIndex; i <= startIndex; i++) {
// html = html + " <tr> <td>" + oceantemps.get(i).getYear() + "</td> " + "<td>"
// + oceantemps.get(i).getAverageTemperature()
// + "</td>" + "<td>"
// + oceantemps.get(i).getMinimumTemperature() + "</td>" + "<td>"
// + oceantemps.get(i).getMaximumTemperature()
// + "</td> </tr>";
// }

// html = html +
// """
// </table>
// """;

// }
// }

// html = html + "</div>";

// return html;
// }
// }