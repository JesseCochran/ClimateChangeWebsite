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
        html = html + "<link rel='stylesheet' type='text/css' href='JesseTesting2c.css' />";
        html = html + "<link rel='stylesheet' type='text/css' href='HelpPage.css' />";
        html = html + "</head>";

        // Add the body
        html = html + "<body>";

        // Add header content block with logo on the right
        html = html
                + """

                            <div class='header'>
                                <h1>
                                <a href='/'><img src='ClimateLogo.png' class='top-image' alt='Website Logo' height='120' width = '120' style='float: left;'></a>
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
                    <div class='content'>
                    <h2>Help & Support</h2>
                """;

        // Help section of the page
        html = html
                + """
                            <section id='help-section'>
                                <h2>Page Assistance</h2>
                                <p>Steps to help you around the website:  </p>

                                <h3>Drop down Buttons/Menus</h3>
                                <img src='dropdown.png' alt='dropdown image' height='120''>
                                <p>Drop downs are the core of our website's design. In order to use them, click on the button and it should bring up a list of potential options that
                                you can then choose to select from. <br> To select one of the many options available all you have to do is click on the option you wish to view.</p>

                                <h3>Radio Buttons</h3>
                                <img src='ClimateLogo.png' class='top-image' alt='Website Logo' height='120' width = '120''>
                                <p>Steps to help you around the website:  </p>

                                <h3>Show Table Button</h3>
                                <img src='ClimateLogo.png' class='top-image' alt='Website Logo' height='120' width = '120''>
                                <p>Steps to help you around the website:  </p>

                                <h3>Reset Button</h3>
                                <img src='ClimateLogo.png' class='top-image' alt='Website Logo' height='120' width = '120''>
                                <p>Steps to help you around the website:  </p>


                            </section>
                        """;

        // FAQ section of the page
        html = html
                + """
                        <section id='faq-section'>
                            <h2>Frequently Asked Questions (FAQ)</h2>
                            """;

        // Prints each question and answer from the database and formats the answer
        // properly by spliting up the answer after each fullstop and a space.
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<Climate> QnA = jdbc.getFAQ();
        String answerStructure = "";
        String[] lineByLine;

        for (int i = 0; i <= QnA.size() - 1; i++) {
            html = html + "<h3>" + QnA.get(i).getQuestion() + "</h3>";
            answerStructure = QnA.get(i).getAnswer();
            lineByLine = answerStructure.split("\\. ");
            for (String line : lineByLine) {
                html = html + "<p>" + line + "</p>";
            }

        }
        html = html + "<section>";

        // Advanced features section of the page
        html = html + """
                <section id='advanced-section'>
                <h2>Advanced Features</h2>
                <h3>Keyboard Shortcuts</h3>
                    <table>
                      <tr>
                        <th>Description</th>
                        <th>Button Press</th>
                      </tr>
                      <tr>
                        <td>Clear All</td>
                        <td>Press C</td>
                      </tr>
                      <tr>
                        <td>Scroll to a certain Page</td>
                        <td>Press 1, 2, 3... 5</td>
                      </tr>
                    </table>
                    </section>
                                    """;

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
