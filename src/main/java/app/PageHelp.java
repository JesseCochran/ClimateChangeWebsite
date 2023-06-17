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
    // Css files connected in order of use
    html = html + "<link rel='stylesheet' type='text/css' href='common.css' />";
    html = html + "<link rel='stylesheet' type='text/css' href='HelpPage.css' />";
    html = html + "<link rel='stylesheet' type='text/css' href='burgerNav.css' />";
    // adds a cool icon on the nav menu drop downs
    html = html
        + "<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css'>";
    html = html + "</head>";

    // Add the body
    html = html + "<body>";

    // Add header content block with logo
    html = html
        + """

                <div class='header'>
                    <h1>
                    <a href='/'><img src='ClimateLogo.png' class='top-image' alt='Website Logo' height='120' width = '120' style='float: left;'></a>
                        Climate Change Awareness
                    </h1>
                </div>
            """;

    // Navigation bar of the site
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
    // Javascript to operate showing and hiding burger menu contents
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
    // code for the burger menu
    // On click it uses javascript function above to hide side bar and when 3 lines
    // are pressed re opens it
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
    // take user to help page through pressing h
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
    // take user to home page when esc is pressed
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

    // Top of content section
    html = html + """
            <div class='content'>
            <h2>Help & Support</h2>
        """;

    // Page assistance section, shows many screen shots and tips to use elements of
    // webpage.
    html = html
        + """
                <section id='help-section'>
                    <h2 class='smaller'>Page Assistance</h2>
                    <p>Steps to help you around the website:  </p>

                    <h3>Drop down Buttons/Menus</h3>
                    <img src='dropdown.png' alt='dropdown image 1' aspect-ratio: auto; width='150' style='margin-right: 20px;'>
                    <img src='dropdown2.png' alt='dropdown image 2' aspect-ratio: auto; width='120' style='margin-right: 20px;'>
                    <img src='dropDown3.png' alt='dropdown image 3' aspect-ratio: auto; width='150' style='margin-right: 20px;'>
                    <p>Drop downs are the core of our website's design. In order to use them, click on the button and it should bring up a list of potential options that
                    you can then choose to select from. <br> To select one of the many options available all you have to do is click on the option you wish to view.
                    <br>
                    If viewing on a computer. <br>
                    Step 1: Left Click on the drop down. <br>
                    Step 2: Once you see the list of options Left Click the option you like. <br>
                    If viewing on a different device such as a phone or tablet. <br>
                    Tap the drop down instead of Left Clicking it.
                    </p>

                    <h3>Radio Buttons</h3>
                    <img src='radiobutton.png' alt='radio button example' aspect-ratio: auto; width = '120''>
                    <p>A radio button is a button that helps you choose one option from a list of options.<br>
                    On our website we use this type of button to allow you to choose how you want data to be sorted.<br>
                    Step 1: Think about which option you would prefer to have the data sorted. <br>
                    Step 2: Left Click/tap the circle next to the option you want, it should be highlighted if selected.
                    </p>

                    <h3>Show Table Button</h3>
                    <img src='showtablebutton.png' alt='show table example button' aspect-ratio: auto; width = '120''>
                    <p>By pressing this button it will take the inputs you have entered in the drop downs and radio buttons above it and get the data you asked for.<br>
                    Step 1: Ensure you have filled out all the required information before clicking, if you haven't no data will be displayed.<br>
                    Step 2: Left click/tap button to get results.</p>

                    <h3>Reset Button</h3>
                    <img src='resetbutton.png' alt='example reset button' aspect-ratio: auto; width = '120''>
                    <p>This button when pressed will clear all your previously entered values. <br>
                    This is necessary because on some pages when you reload the page it may not clear responses and as such you must press this button. <br>
                    Step 1: If you have data you want cleared, locate this button and left click/tap it. </p>

                    <h3>Heat Map</h3>
                    <img src='heatMap1.png' alt='example heat map' aspect-ratio: auto; width = '840''>
                    <p>The featured heat map on our website home page can be used by selecting the year from the drop down you wish to view and pressing the submit button. <br>
                    This heat map is colour coded from green being low/coldest temperatures to red being the warmest temperatures. <br>
                    If you wish to view a particular value/country on the map in a year, you can hover over it and it should tell you the average temperature of that country during the year.<br>
                    Please note some data may not be present especially in earlier years such as 1750 to 1850. We apologies if we don't have the particular country data you are looking for.
                    </p>



                </section>
            """;

    // FAQ section of the page
    html = html
        + """
            <section id='faq-section'>
                <h2 class='smaller'>Frequently Asked Questions (FAQ)</h2>
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
        <h2 class='smaller'>Advanced Features</h2>
        <h3>Keyboard Shortcuts</h3>
            <table>
              <tr>
                <th>Description</th>
                <th>Button Press</th>
              </tr>
              <tr>
                <td>Clear All/reset data on page</td>
                <td>Press C</td>
              </tr>
              <tr>
                <td>Send to Help & Support page</td>
                <td>Press h</td>
              </tr>
              <tr>
                <td>Send to home page</td>
                <td>Press esc</td>
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
    html = html + "</body>" + "</html>";

    // DO NOT MODIFY THIS
    // Makes Javalin render the webpage
    context.html(html);
  }
}
