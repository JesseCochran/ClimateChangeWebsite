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
        html += "<head>" + "<meta charset='UTF-8'>" +
                "<title>Our Mission</title>";

        // Add some CSS (external file)
        html = html + "<link rel='stylesheet' type='text/css' href='JesseTesting2c.css' />";
        html += "</head>";

        // Add the body
        html += "<body>";

        // Add header content block
        html += """
                    <div class='header'>
                    <h1><a href='/'><img src='ClimateLogo.png' class='top-image' alt='Website Logo' height='120' width = '120' style='float: left;'></a>
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

        html += """
                <h2>Our Mission</h2>
                """;

        // Add HTML for the page content more to be added to the mission statement

        html += """


                <p> Climate change is a real and dangerous issue that our world is facing. While looking ahead to prevent further damage
                is fundemental, we believe that it is equally important to study our past. Over the last century, scientists across the
                world have studied and recorded the ever changing temperatures of our climate. Our goal is to provide a platform in which
                which everybody regardless of their background can learn something and potentially gain a deeper insight and intrest in our world's plight.
                The different pages of our website explore climate change at differing levels of depth. We provide data at a global, country, city and state level as
                well as information about changing temperatures and changing population levels.
                We truly hope you find the information you desire and walk away with a deeper understanding of the ever changing climate.
                </p>

                                """;

        // Personas Sections
        html = html + "<section id='persona-section'>";
        html += """
                 <h3>Who this site is built for</h3>
                """;

        ArrayList<PersonaData> data = JDBCConnection.getPersonaData();

        for (PersonaData p : data) {

            html += "<h3>" + p.getName() + "</h3>";
            html += String.format("<img style='width: 200px; height: 200px; float: right;' src='%s'/>",
                    p.getImagePath());
            html += "<p>" + p.getQuote() + "</p>";
            html += " <h4> Experience </h4>";
            html += "<p>" + p.getExperience() + "</p>";
            html += " <h4> Requirements </h4>";
            html += "<p>" + p.getRequirements() + "</p>";

        }
        html = html + "</section>";
        // Student info section
        html = html + "<section id='aboutUs-section'>";
        html += """
                <h3>Who are we</h3>
                """;

        ArrayList<StudentInfo> info = JDBCConnection.getStudentInfo();

        for (StudentInfo s : info) {

            html += "<h3>" + s.getFname() + " " + s.getLname() + "</h3>";
            html += " <h4> Student Number </h4>";
            html += "<p>" + s.getStudentNumber() + "</p>";
            html += " <h4>  Email </h4>";
            html += "<p>" + s.getEmail() + "</p>";

        }

        // Finish the List HTML
        html += "</ul>";
        html = html + "</section>";
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
