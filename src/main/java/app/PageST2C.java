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
                "<title>Subtask 2.2</title>";

        // Add some CSS (external file)
        html = html + "<link rel='stylesheet' type='text/css' href='common.css' />";
        html = html + "</head>";

        // Add the body
        html = html + "<body>";

         // Add header content block
         html = html + """
            <div class='header'>
                <h1>Subtask 3.C</h1>
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
                <p>Subtask 2.C page content</p>
                """;

        html = html + "<form action='/page2C.html' method='post'>";

        html = html + "   <div class='form-group'>";
        html = html + "      <label for='StartYear_drop'>Select the start year (Dropdown):</label>";
        html = html + "      <select id='StartYear_drop' name='StartYear_drop'>";
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<LGA> years = jdbc.getYears();
        for (LGA year : years) {
            html = html + "<option>" + year.getYear() + "</option>";
        }
        html = html + "         <option>2010</option>";
        html = html + "         <option>2011</option>";
        html = html + "         <option>2012</option>";
        html = html + "      </select>";
        html = html + "   </div>";

        html = html + "   <div class='form-group'>";
        html = html + "      <label for='EndYear_drop'>Select the end year (Dropdown):</label>";
        html = html + "      <select id='EndYear_drop' name='EndYear_drop'>";
        html = html + "         <option>2010</option>";
        html = html + "         <option>2011</option>";
        html = html + "         <option>2012</option>";
        html = html + "         <option>2013</option>";
        html = html + "      </select>";
        html = html + "   </div>";
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

        // 🚨 This block of code is for Pre-Req students.
        // Altneratively we can use JDBCConnection to add HTML for the movies list
        // Uncomment the code to use the JDBCConnection Objects example(s)

        // THIS IS FOR THE DATA BASE CONNECTION
        // JDBCConnection jdbc = new JDBCConnection();
        // ArrayList<LGA> Climates = jdbc.getDataByYearRange(Year);
        // html = html + "<h2>" +
        // Year +
        // " Movies with Years (from JDBCConnection)</h2>" +
        // "<ul>";
        // for (LGA Climate : Climates) {
        // html = html + "<li>" + Climate.name + " was made in " + Climate.year +
        // "</li>";
        // }
        // html = html + "</ul>";

        return html;
    }

}
