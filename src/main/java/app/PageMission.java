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
    html = html + "<link rel='stylesheet' type='text/css' href='common.css' />";
    html = html + "<link rel='stylesheet' type='text/css' href='burgerNav.css' />";
    // adds a cool icon on the nav menu
    html = html
        + "<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css'>";
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
    html = html + """
            <div class='topnav'>
                            <a href='/'>Home</a>
                            <div class='dropDown'>
                            <button class='dropbtn'>Climate Data and Analysis
                            <i class='fa fa-caret-down'></i>
                            </button>
                            <div class='dropdown-content'>
                            <a href='page2A.html'>Temperature & Population Change By Country/World</a>
                            <a href='page2B.html'>Temperature Change By State/City</a>
                            <a href='page2C.html'>Global Land-Ocean Temperature Change</a>
                            <a href='page3A.html'>Change In Temperature Over Extended Periods</a>
                            <a href='page3B.html'>Time Periods With Similar Temperature/Population</a>
                            <a href='page3C.html'>Comparison Of Global Temperature Data Over Extended Periods</a>
                            </div>
                            </div>
                            <div class='dropDown'>
                            <button class='dropbtn'>About Us
                            <i class='fa fa-caret-down'></i>
                            </button>
                            <div class='dropdown-content'>
                            <a href='mission.html'>Our Mission</a>
                            <a href='mission.html#persona-section'>Personas</a>
                            <a href='mission.html#aboutUs-section'>Contact Us</a>
                            </div>
                            </div>
                            <div class='dropDown'>
                            <button class='dropbtn'>Help & Support
                            <i class='fa fa-caret-down'></i>
                            </button>
                            <div class='dropdown-content'>
                            <a href='PageHelp.html'>Page Assistance</a>
                            <a href='PageHelp.html#faq-section'>FAQ</a>
                            <a href='PageHelp.html#advanced-section'>Advanced Features</a>
                            </div>
                            </div>
                            </div>
        """;

    html = html + "<script>";
    html = html + """

                        function openNav() {
          document.getElementById('mySidenav').style.width = '250px';
        }

        function closeNav() {
          document.getElementById('mySidenav').style.width = '0px';
        }
                                        """;
    html = html + "</script>";
    html = html + "<div class='SideNavBar'>";
    html = html
        + """
                    <div id='mySidenav' class='sidenav'>
                        <a href='javascript:void(0)' class='closebtn' onclick='closeNav()'>&times;</a>
                        <a href='/'>Home</a>
                        <p>Climate Data and Analysis</p>
                        <a href='page2A.html'>Temperature & Population Change By Country/World</a>
                        <a href='page2B.html'>Temperature Change By State/City</a>
                        <a href='page2C.html'>Global Land-Ocean Temperature Change</a>
                        <a href='page3A.html'>Change In Temperature Over Extended Periods</a>
                        <a href='page3B.html'>Time Periods With Similar Temperature/Population</a>
                        <a href='page3C.html'>Comparison Of Global Temperature Data Over Extended Periods</a>
                        <p>About Us</p>
                        <a href='mission.html'>Our Mission</a>
                <a href='mission.html#persona-section'>Personas</a>
                <a href='mission.html#aboutUs-section'>Contact Us</a>
                <p>Help & Support</p>
                <a href='PageHelp.html'>Page Assistance</a>
                <a href='PageHelp.html#faq-section'>FAQ</a>
                <a href='PageHelp.html#advanced-section'>Advanced Features</a>

                    </div>
            <span style='color: #f1f1f1; position: fixed; top:10px; right:20px; font-size:40px; cursor:pointer' onclick='openNav()'> &#9776;</span>
                    """;
    html = html + "</div>";
    html = html + "<script>";
    // take to help page
    html = html + """

             // Function to navigate to the help page
        function goToHelpPage() {
          window.location.href = 'PageHelp.html';
        }
                                document.addEventListener('keydown', function(event) {
                      // Check if the h key (key code 72) is pressed
                      if (event.keyCode === 72) {
                        goToHelpPage();
                      }
                    });
                                """;
    // take to home page
    html = html + """
             // Function to navigate to the home page
        function goToHomePage() {
          window.location.href = '/';
        }

        document.addEventListener('keydown', function(event) {
          // Check if the Esc key (key code 27) is pressed
          if (event.keyCode === 27) {
            goToHomePage();
          }
        });
                    """;
    html = html + "</script>";
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
         <h2>Who This Site Is Built For</h2>
        """;

    ArrayList<PersonaData> data = JDBCConnection.getPersonaData();

    for (PersonaData p : data) {

      html += "<h3>" + p.getName() + "</h3>";
      html += String.format("<img style='width: 200px; height: 200px; float: right;' src='%s'/>",
          p.getImagePath());
      html += "<p>" + p.getQuote() + "</p>";
      html += " <h4> Background </h4>";
      html += "<p>" + p.getBackground() + "</p>";
      html += " <h4> Experience </h4>";
      html += "<p>" + p.getExperience() + "</p>";
      html += " <h4> Requirements </h4>";
      html += "<p>" + p.getRequirements() + "</p>";

    }
    html = html + "</section>";
    // Student info section
    html = html + "<section id='aboutUs-section'>";
    html += """
        <h2>Who Are We</h2>
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
    html = html
        + """
            <div class='footer'>

                      <div class='footerBlock'>
                                  <div class='footerColumn'>
                                    <p style='margin-top: 0;'>Shallow View</p>
                                    <a href='page2A.html'>Temperature & Population Change By Country/World</a>
                                    <a href='page2B.html'>Temperature Change By State/City</a>
                                    <a href='page2C.html'>Global Land-Ocean Temperature Change</a>
                                  </div>
                                  <div class='footerColumn'>
                                    <p style='margin-top: 0;'>In-Depth View</p>
                                    <a href='page3A.html'>Change In Temperature Over Extended Periods</a>
                                    <a href='page3B.html'>Time Periods With Similar Temperature/Population</a>
                                    <a href='page3C.html'>Comparison Of Global Temperature Data Over Extended Periods</a>
                                  </div>
                                  <div class='footerColumn'>
                                    <p style='margin-top: 0;'>About</p>
                                    <a href='mission.html'>Our Mission</a>
                                    <a href='mission.html#persona-section'>Personas</a>
                                    <a href='mission.html#aboutUs-section'>Contact Us</a>
                                  </div>
                                  <div class='footerColumn'>
                                    <p style='margin-top: 0;'>Help & Support</p>
                                    <a href='PageHelp.html'>Page Assistance</a>
                                    <a href='PageHelp.html#faq-section'>FAQ</a>
                                    <a href='PageHelp.html#advanced-section'>Advanced Features</a>

                                  </div>
                                </div>
                              </div>
                  """;

    // Finish the HTML webpage
    html += "</body>" + "</html>";

    // DO NOT MODIFY THIS
    // Makes Javalin render the webpage
    context.html(html);
  }

}
