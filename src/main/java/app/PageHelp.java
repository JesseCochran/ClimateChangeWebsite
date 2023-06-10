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
        // nice scroll affect i found **This offsets the scroll a bit as the top stuff
        // is fixed to the screen
        html = html
                + """
                            <script>
                                document.addEventListener('DOMContentLoaded', function() {
                                    // Get all anchor links in the document
                                    var anchorLinks = document.querySelectorAll('a[href^="#"]');

                                    // Attach click event listener to each anchor link
                                    anchorLinks.forEach(function(link) {
                                        link.addEventListener('click', function(e) {
                                            e.preventDefault(); // Prevent default link behavior

                                            var targetId = this.getAttribute('href'); // Get the target section id
                                            var targetSection = document.querySelector(targetId); // Get the target section

                                            if (targetSection) {
                                                var topOffset = targetSection.offsetTop -65; // Calculate the top offset of the target section
                                                window.scrollTo({
                                                    top: topOffset,
                                                    behavior: 'smooth' // Smooth scroll to the target section
                                                });
                                            }
                                        });
                                    });
                                });
                            </script>
                        """;

        // Add header content block
        html = html + """
                    <div class='content'>
                    <div class='sections'>
                        <h2>Help And Support Page</h2>
                        <h3>Contents</h3>
                        <h3><a href='#help-section'>Help</a></h3>
                        <h3><a href='#faq-section'>FAQ</a></h3>
                        <h3><a href='#advanced-section'>Advanced Features</a></h3>
                    </div>
                """;

        // Help section of the page
        html = html + """
                    <section id='help-section'>
                        <h2>Help</h2>
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
        // html = html
        // + """

        // <h3> Question 1 </h3>
        // <p> Answer: Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent
        // vulputate dolor ut euismod cursus. Sed elementum odio nec nulla scelerisque,
        // a pretium urna dapibus. Ut ac efficitur elit, a rutrum enim. Duis eu turpis
        // vestibulum, placerat lectus et, fermentum nisl. Pellentesque felis nisi,
        // pulvinar sed magna eu, fermentum malesuada ex. Pellentesque iaculis pretium
        // orci eget bibendum. Nullam a leo nunc.

        // Duis venenatis justo ligula, ut convallis odio lacinia at. Vestibulum
        // tristique elementum urna a feugiat. Nunc iaculis erat efficitur, tempor
        // mauris at, lacinia nibh. Sed sed hendrerit orci. Etiam vehicula malesuada
        // aliquet. Aliquam blandit tellus eu dui gravida, quis pretium purus posuere.
        // Proin arcu sem, imperdiet ac cursus at, posuere nec massa. Nunc ullamcorper
        // tellus vitae turpis mattis, dapibus placerat nibh tincidunt. Mauris eu
        // rhoncus augue, non malesuada erat.

        // Mauris felis nibh, molestie vitae commodo sed, faucibus hendrerit sapien.
        // Mauris malesuada rutrum semper. Vivamus ut lobortis justo, at condimentum
        // eros. Aenean in mi malesuada, facilisis ipsum sit amet, placerat risus.
        // Suspendisse sed libero eu dolor pharetra porta. Mauris consequat dolor vitae
        // diam scelerisque dictum. Nunc et ex ipsum. Pellentesque facilisis velit
        // lacus, ac porttitor nibh ultrices ac. Aliquam interdum efficitur tellus, eu
        // aliquet libero tincidunt at. Suspendisse pellentesque molestie urna a
        // porttitor. Sed vitae sem ut dui finibus ornare. Ut blandit, nisl eu pretium
        // dignissim, nibh lorem ultrices tellus, ut eleifend ex leo ac nunc. Fusce
        // pellentesque ex in ipsum consequat consequat.
        // </p>
        // <p>
        // </p>
        // <h3> Question 2 </h3>
        // <p> Answer: Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent
        // vulputate dolor ut euismod cursus. Sed elementum odio nec nulla scelerisque,
        // a pretium urna dapibus. Ut ac efficitur elit, a rutrum enim. Duis eu turpis
        // vestibulum, placerat lectus et, fermentum nisl. Pellentesque felis nisi,
        // pulvinar sed magna eu, fermentum malesuada ex. Pellentesque iaculis pretium
        // orci eget bibendum. Nullam a leo nunc.

        // Duis venenatis justo ligula, ut convallis odio lacinia at. Vestibulum
        // tristique elementum urna a feugiat. Nunc iaculis erat efficitur, tempor
        // mauris at, lacinia nibh. Sed sed hendrerit orci. Etiam vehicula malesuada
        // aliquet. Aliquam blandit tellus eu dui gravida, quis pretium purus posuere.
        // Proin arcu sem, imperdiet ac cursus at, posuere nec massa. Nunc ullamcorper
        // tellus vitae turpis mattis, dapibus placerat nibh tincidunt. Mauris eu
        // rhoncus augue, non malesuada erat.

        // Mauris felis nibh, molestie vitae commodo sed, faucibus hendrerit sapien.
        // Mauris malesuada rutrum semper. Vivamus ut lobortis justo, at condimentum
        // eros. Aenean in mi malesuada, facilisis ipsum sit amet, placerat risus.
        // Suspendisse sed libero eu dolor pharetra porta. Mauris consequat dolor vitae
        // diam scelerisque dictum. Nunc et ex ipsum. Pellentesque facilisis velit
        // lacus, ac porttitor nibh ultrices ac. Aliquam interdum efficitur tellus, eu
        // aliquet libero tincidunt at. Suspendisse pellentesque molestie urna a
        // porttitor. Sed vitae sem ut dui finibus ornare. Ut blandit, nisl eu pretium
        // dignissim, nibh lorem ultrices tellus, ut eleifend ex leo ac nunc. Fusce
        // pellentesque ex in ipsum consequat consequat.
        // Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent vulputate
        // dolor ut euismod cursus. Sed elementum odio nec nulla scelerisque, a pretium
        // urna dapibus. Ut ac efficitur elit, a rutrum enim. Duis eu turpis vestibulum,
        // placerat lectus et, fermentum nisl. Pellentesque felis nisi, pulvinar sed
        // magna eu, fermentum malesuada ex. Pellentesque iaculis pretium orci eget
        // bibendum. Nullam a leo nunc.

        // Duis venenatis justo ligula, ut convallis odio lacinia at. Vestibulum
        // tristique elementum urna a feugiat. Nunc iaculis erat efficitur, tempor
        // mauris at, lacinia nibh. Sed sed hendrerit orci. Etiam vehicula malesuada
        // aliquet. Aliquam blandit tellus eu dui gravida, quis pretium purus posuere.
        // Proin arcu sem, imperdiet ac cursus at, posuere nec massa. Nunc ullamcorper
        // tellus vitae turpis mattis, dapibus placerat nibh tincidunt. Mauris eu
        // rhoncus augue, non malesuada erat.

        // Mauris felis nibh, molestie vitae commodo sed, faucibus hendrerit sapien.
        // Mauris malesuada rutrum semper. Vivamus ut lobortis justo, at condimentum
        // eros. Aenean in mi malesuada, facilisis ipsum sit amet, placerat risus.
        // Suspendisse sed libero eu dolor pharetra porta. Mauris consequat dolor vitae
        // diam scelerisque dictum. Nunc et ex ipsum. Pellentesque facilisis velit
        // lacus, ac porttitor nibh ultrices ac. Aliquam interdum efficitur tellus, eu
        // aliquet libero tincidunt at. Suspendisse pellentesque molestie urna a
        // porttitor. Sed vitae sem ut dui finibus ornare. Ut blandit, nisl eu pretium
        // dignissim, nibh lorem ultrices tellus, ut eleifend ex leo ac nunc. Fusce
        // pellentesque ex in ipsum consequat consequat.
        // </p>
        // </section>
        // """;

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
                                <p>COSC2803 - Studio Project Starter Code (Apr23)</p>
                                <a href='PageHelp.html'>Help Page</a>
                                <p style='display: flex; gap: 10px;'><a href='PageHelp.html#help-section'>Help</a><a href='PageHelp.html#faq-section'>FAQ</a><a href='PageHelp.html#advanced-section'>Advanced Features</a></p>
                            </div>
                        """;

        // Finish the HTML webpage
        html = html + "</body>" + "</html>";

        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(html);
    }
}
