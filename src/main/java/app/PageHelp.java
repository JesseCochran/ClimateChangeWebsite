package app;

import java.util.ArrayList;

import io.javalin.http.Context;
import io.javalin.http.Handler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PageHelp implements Handler {
    // URL of this page relative to http://localhost:7001/
    public static final String URL = "/PageHelp.html";

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        String html = "<html>";

        // Add some Head information
        html = html + "<head>" +
                "<title>Our Mission</title>";

        // Add some CSS (external file)
        html = html + "<link rel='stylesheet' type='text/css' href='common.css' />";
        html = html + "</head>";

        // Add the body
        html = html + "<body>";

        // Add header content block with logo on the right
        html = html
                + """

                            <div class='header'>
                                <h1>
                                <a href='/'><img src='logo.png' class='top-image' alt='RMIT logo' height='75' style='float: left;'></a>
                                    Climate Change Awareness
                                </h1>
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

        // Add header content block
        html = html + """
                    <div class='header'>
                        <h2>Help Page</h1>
                    </div>
                """;

        // Add Div for page Content
        html = html + "<div class='content'>";

        // Add HTML for the page content
        html = html + """
                <p>Mission page content</p>
                """;

        // This example uses JDBC to lookup the LGAs
        JDBCConnection jdbc = new JDBCConnection();

        // Next we will ask this *class* for the LGAs
        ArrayList<LGA> lgas = jdbc.getLGAs2016();

        // Add HTML for the LGA list
        html = html + "<h1>All 2016 LGAs in the CTG database (using JDBC Connection)</h1>" + "<ul>";

        // Finally we can print out all of the LGAs
        for (LGA lga : lgas) {
            html = html + "<li>" + lga.getCode()
                    + " - " + lga.getName() + "</li>";
        }

        // Finish the List HTML
        html = html + "</ul>";

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
}
