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
        html = html + "<link rel='stylesheet' type='text/css' href='JesseTesting2c.css' />";
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
        html = html + """
                <h2>Focused view of temperature and population change by Country/Global</h2>
                """;

        html = html + "<form action='/page2A.html' method='post' onsubmit='return ReenterData()'>";

        // Elements keep values on reload
        html = html + "<script>";
        html = html + "   function ReenterData() {";
        html = html + "       var dataToShow = document.getElementById('CountryWorld_drop').value;";
        html = html + "       var startYear = document.getElementById('StartYear_drop').value;";
        html = html + "       var endYear = document.getElementById('EndYear_drop').value;";
        html = html + "       var dataType = document.getElementById('TypeOrder_drop').value;";
        html = html + "       var sortOrder = document.querySelector('input[name=SortOrder]:checked').value;";
        html = html + "       sessionStorage.setItem('dataToShow', dataToShow);";
        html = html + "       sessionStorage.setItem('startYear', startYear);";
        html = html + "       sessionStorage.setItem('endYear', endYear);";
        html = html + "       sessionStorage.setItem('dataType', dataType);";
        html = html + "       sessionStorage.setItem('sortOrder', sortOrder);";
        html = html + "       return true;";
        html = html + "   }";
        html = html + " window.onload = function() {";
        html = html + " var dataToShow = sessionStorage.getItem('dataToShow');";
        html = html + " var startYear = sessionStorage.getItem('startYear');";
        html = html + " var endYear = sessionStorage.getItem('endYear');";
        html = html + " var dataType = sessionStorage.getItem('dataType');";
        html = html + " var sortOrder = sessionStorage.getItem('sortOrder');";
        html = html + " if (dataToShow) document.getElementById('TempSelection_drop').value = dataToShow;";
        html = html + " if (startYear) document.getElementById('StartYear_drop').value = startYear;";
        html = html + " if (endYear) document.getElementById('EndYear_drop').value = endYear;";
        html = html + " if (dataType) document.getElementById('TypeOrder_drop').value = dataType;";
        html = html
                + " if (sortOrder) document.querySelector('input[name=SortOrder][value=' + sortOrder + ']').checked = true;";
        html = html + " }";
        html = html + "</script>";

        // Dropdown to select country or world data
        html = html + "<div class='form-group'>";
        html = html + "     <label for='CountryWorld_drop'>Select World or Country data:</label>";
        html = html + "     <select id='CountryWorld_drop' name='CountryWorld_drop' size='1'>";
        html = html + "     <option>World</option>";
        html = html + "     <option>Country</option>";
        html = html + "     </select>";
        html = html + "</div>";

        // this connects the database to the start date drop down box.
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<Climate> years = jdbc.getPopulationYears();

        // Dropdown to select start year which updates end year based on result
        html = html + "<div class='form-group'>";
        html = html + "     <label for='StartYear_drop'>Select the start year:</label>";
        html = html
                + "     <select id='StartYear_drop' name='StartYear_drop' onchange='updateEndYearOptions()' size='1'>";
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

        html = html + "   <div class='form-group'>";
        html = html + "      <label for='EndYear_drop'>Select the end year:</label>";
        html = html
                + "      <select id='EndYear_drop' name='EndYear_drop' onchange='updateStartYearOptions()' size='1'>";

        for (Climate year : years) {
            html = html + "<option>" + year.getYear() + "</option>";
        }
        html = html + "      </select>";
        html = html + "   </div>";

        // Dropdown to select how to format the data
        html = html + "<div class='form-group'>";
        html = html + "      <label for='TypeOrder_drop'>Select how you want to order the data:</label>";
        html = html + "      <select id='TypeOrder_drop' name='TypeOrder_drop' size='1'>";
        html = html + "      <option>Population Change</option>";
        html = html + "      <option>Temperature Change</option>";
        html = html + "      </select>";
        html = html + "</div>";

        // Sorting order
        html = html + """
                <p>Sort By</p>
                <input type='radio' id='SortOrder' name='SortOrder' value='Ascending'>
                <label class='radio-label' for='Ascending'>Low to High</label><br>
                <input type='radio' id='SortOrder' name='SortOrder' value='Descending'>
                 <label class='radio-label' for='Descending'>High to Low</label>
                    """;

        // submit button
        html = html + "<div>";
        html = html + "<button class='showTable' type='submit' class='btn btn-primary'>Show Table</button>";
        html = html + "</div>";
        html = html + "</form>";

        String DataToOutput = context.formParam("CountryWorld_drop");
        String StartYear_drop = context.formParam("StartYear_drop");
        String EndYear_drop = context.formParam("EndYear_drop");
        String TypeOrder = context.formParam("TypeOrder_drop");
        String SortOrder = context.formParam("SortOrder");

        if (DataToOutput == null) {
            html = html + "<h3>Please Select Country or World Data</h3>";
        } else if (DataToOutput.equals("World")) {
            html = html + outputWorld(DataToOutput, StartYear_drop, EndYear_drop);
        } else if (DataToOutput.equals("Country")) {
            html = html + outputCountry(DataToOutput, StartYear_drop, EndYear_drop, TypeOrder, SortOrder);
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

    private float correlationCalc(long startPop, long endPop, float startTemp, float endTemp) {
        float corr;
        int n = 2;

        long sumX = startPop + endPop;
        float sumY = startTemp + endTemp;
        float sumXY = (float) ((startPop * startTemp) + (endPop * endTemp));
        long squareSumX = (startPop * startPop) + (endPop * endPop);
        float squareSumY = (startTemp * startTemp) + (endTemp * endTemp);

        float numerator = (n * sumXY) - (sumX * sumY);
        float denominator = (float) (Math.sqrt((n * squareSumX - sumX * sumX) * (n * squareSumY - sumY * sumY)));
        corr = numerator / denominator;

        return corr;
    }

}
