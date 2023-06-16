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
public class PageST3A implements Handler {

    // URL of this page relative to http://localhost:7001/
    public static final String URL = "/page3A.html";

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        String html = "<html>";

        // Add some Head information
        html = html + "<head>" +
                "<title>Subtask 3.1</title>";

        // Add some CSS (external file)
        html = html + "<link rel='stylesheet' type='text/css' href='JesseTesting2c.css' />";
        // adds a cool icon on the nav menu
        html = html
                + "<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css'>";
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
        // Add the topnav
        html = html + """
                    <div class='topnav'>
                    <a href='/'>Home</a>
                    <div class='dropDown'>
                    <button class='dropbtn'>Shallow View Of Climate Change
                    <i class='fa fa-caret-down'></i>
                    </button>
                    <div class='dropdown-content'>
                    <a href='page2A.html'>Temperature & Population Change By Country/World</a>
                    <a href='page2B.html'>Temperature Change By State/City</a>
                    <a href='page2C.html'>Global Land-Ocean Temperature Change</a>
                    </div>
                    </div>
                    <div class='dropDown'>
                    <button class='dropbtn'>In-Depth View Of Climate Change
                    <i class='fa fa-caret-down'></i>
                    </button>
                    <div class='dropdown-content'>
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

        // Add Div for page Content
        html = html + "<div class='content'>";

        // Add HTML for the page content
        html = html + """
                <h2>Identify changed in temperature over extended periods</h2>
                """;

        html = html + "<form action='/page2A.html' method='post'>";
        
        //geo location drop down
        html = html + "<div class='form-group'>";
        html = html + "     <label for='GeoLocation_drop'>Select Global, Country, State or City:</label>";
        html = html + "     <select id='GeoLocation_drop' name='GeoLocation_drop' size='1'>";
        html = html + "     <option>Global</option>";
        html = html + "     <option>Country</option>";
        html = html + "     <option>State</option>";
        html = html + "     <option>City</option>";
        html = html + "     </select>";

        //country select dropdown 
        html = html + "<p class='hide-element'>";
        html = html + "     <label for='Country_drop'>Select Country:</label>";
        html = html + "     <select id='Country_drop' name='Country_drop' size='1'>";
        //insert loop that enters CountryName's
        html = html + "     </select>";
        html = html + "</p>";

        //state select dropdown
        html = html + "<p class='hide-element'>";
        html = html + "     <label for='State_drop'>Select State:</label>";
        html = html + "     <select id='State_drop' name='State_drop' size='1'>";
        //insert loop that enters StateName's
        html = html + "     </select>";
        html = html + "</p>";

        //city select dropdown
        html = html + "<p class='hide-element'>";
        html = html + "     <label for='Cty_drop'>Select State:</label>";
        html = html + "     <select id='City_drop' name='City_drop' size='1'>";
        //insert loop that enters CityName's
        html = html + "     </select>";
        html = html + "</p>";
        html = html + "</div>";
        


        html = html + "</form>";

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

}
