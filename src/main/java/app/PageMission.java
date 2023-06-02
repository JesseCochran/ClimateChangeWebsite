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
public class PageMission implements Handler {

    // URL of this page relative to http://localhost:7001/
    public static final String URL = "/mission.html";

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        String html = "<html>";

        // Add some Head information
        html += "<head>" +
                "<title>Our Mission</title>";

        // Add some CSS (external file)
        html += "<link rel='stylesheet' type='text/css' href='common.css' />";
        html += "</head>";


        // Add the body
        html += "<body>";


        // Add header content block
        html += """
                    <div class='header'>
                        <h1>Climate Change Awareness</h1>
                    </div>
                """;

        // Add the topnav
        // This uses a Java v15+ Text Block
        html += """
                    <div class='topnav'>
                    <a href='/'>Homepage</a>
                    <a href='mission.html'>Our Mission</a>
                    <a href='page2A.html'>Sub Task 2.A</a>
                    <a href='page2B.html'>Sub Task 2.B</a>
                    <a href='page2C.html'>Sub Task 2.C</a>
                    <a href='page3A.html'>Sub Task 3.A</a>
                    <a href='page3B.html'>Sub Task 3.B</a>
                    <a href='page3C.html'>Sub Task 3.C</a>
                    
                    </div>
                """;

        // Add Div for page Content
        html += "<div class='content'>";

        // Add HTML for the page content more to be added to the mission statement
        html += """
                <h1>Mission Statement</h1>
                """;

                    html += """
                      
    
                        <p> Our aim is to provide a platform in which we can raise awareness about climate change and provide an avenue to explore real world data. </p>

                            """;


        //

        // Finish the List HTML
        html += "</ul>";

        // Close Content div
        html += "</div>";

        // Footer
        html += """
                    <div class='footer'>
    
                        <a href='PageHelp.html'>Help Page</a>
                    </div>
                """;

        // Finish the HTML webpage
        html += "</body>" + "</html>";

        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(html);
    }

}

