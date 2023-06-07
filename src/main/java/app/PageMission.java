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


        //declaring variables

       


        // Add Div for page Content
        html += "<div class='content'>";


        // Add HTML for the page content more to be added to the mission statement
        html += """
                <h3>Mission Statement</h3>
                """;

         html += """
                      
    
            <p> Our aim is to provide a platform in which we can raise awareness about climate change and provide an avenue to explore real world data. </p>

                            """;

        // Personas Sections
                
            html += """
                     <h3>Who this site is built for</h3>
                    """;

            ArrayList<PersonaData> data = JDBCConnection.getPersonaData();

            for (PersonaData p : data) {
               
              html += "<h3>" + p.getName()+ "</h3>";
                html += String.format("<img style='width: 200px; height: 200px; float: right;' src='%s'/>",p.getImagePath());
                html += "<p>" + p.getQuote()+ "</p>";
                html += " <h4> Experience </h4>";
                html +=  "<p>" + p.getExperience()+ "</p>";
                html += " <h4> Requirements </h4>";
                html += "<p>" + p.getRequirements()+ "</p>";
                       
            }
            
        // Student info section
                
            html += """
                     <h3>Who are we</h3>
                     """;

            ArrayList<StudentInfo> info = JDBCConnection.getStudentInfo();

                for (StudentInfo s : info) {

                    html += "<h3>" + s.getFname()  + " " + s.getLname() + "</h3>";
                    html += " <h4> Student Number </h4>";
                    html += "<p>" + s.getStudentNumber() + "</p>";
                    html += " <h4>  Email </h4>";
                    html += "<p>" + s.getEmail()+ "</p>";

                }


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

