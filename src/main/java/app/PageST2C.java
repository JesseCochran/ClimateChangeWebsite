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
public class PageST2C implements Handler {

    // URL of this page relative to http://localhost:7001/
    public static final String URL = "/page2C.html";

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        String html = "<html>";

        // Add some Head information
        html = html + "<head>" +
                "<title>Focused View Of Land Ocean Temperature</title>";

        // Add some CSS (external file)
        html = html + "<link rel='stylesheet' type='text/css' href='common.css' />";
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
                        <h2>A look at Annual Global Land Ocean Temperature records</h2>
                        <p>Land Ocean Temperature is an average of the temperatures of both the land and ocean surfaces over a period of time. </p>
                        <p>The Global Land Ocean records are especially useful as a critical tool in assessing long term climate trends and the extent of global warming due to the inclusion of the surfaces temperature of ocean data,
                        this is because oceans have a higher heat capacity compared to land therefore meaning they absorb and release heat slower which can then help show greater discrepancies in temperatures therefore showing signs of climate change. </p>
                        """;

        // All the Drop down menu stuff for the data to eventually be retrieved from
        html = html + "<form action='/page2C.html' method='post'>";

        html = html + "   <div class='form-group'>";
        html = html + "      <label for='StartYear_drop'>Select the start year (Dropdown):</label>";
        html = html
                + "      <select id='StartYear_drop' name='StartYear_drop' onchange='updateEndYearOptions(this.value)' size='1'>";
        // This onchange section ^ makes the website more dynamic by using a java script
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
        html = html + "      <label for='EndYear_drop'>Select the end year (Dropdown):</label>";
        html = html + "      <select id='EndYear_drop' name='EndYear_drop' size='1'>";
        html = html + "      </select>";
        html = html + "   </div>";
        // submit button
        html = html + "   <button type='submit' class='btn btn-primary'>Submit Year Range</button>";

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
            html = html + "<h2><i>No Results to show for dropbox</i></h2>";
        } else {
            // If NOT NULL, then lookup the movie by type!
            html = html + outputData(StartYear_drop);
        }

        String EndYear_drop = context.formParam("EndYear_drop");
        // String movietype_drop = context.queryParam("movietype_drop");
        if (EndYear_drop == null) {
            // If NULL, nothing to show, therefore we make some "no results" HTML
            html = html + "<h2><i>No Results to show for dropbox</i></h2>";
        } else {
            // If NOT NULL, then lookup the movie by type!
            html = html + outputData(EndYear_drop);
        }
        // Close Content div
        html = html + "</div>";

        // Footer
        html = html + """
                    <div class='footer'>
                        <p>COSC2803 - Studio Project Starter Code (Apr23)</p>
                    </div>
                """;

        // Finish the HTML webpage
        html = html + "</body>" + "</html>";

        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(html);
    }

    public String outputData(String Year) {
        String html = "";
        html = html + "<h2>" + Year + " Climate Data</h2>";

        return html;
    }

}
