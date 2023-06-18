package app;

import io.javalin.http.Context;
import io.javalin.http.Handler;

import java.util.ArrayList;

/**
 * Example Index HTML class using Javalin
 * <p>
 * Generate a static HTML page using Javalin
 * by writing the raw HTML into a Java String object
 *
 * @author Timothy Wiley, 2023. email: timothy.wiley@rmit.edu.au
 * @author Santha Sumanasekara, 2021. email: santha.sumanasekara@rmit.edu.au
 */
public class PageST2B implements Handler {

    // URL of this page relative to http://localhost:7001/
    public static final String URL = "/page2B.html";

    public static void sort(ArrayList<Stat> list) {

        list.sort((o2, o1) -> Float.compare(o1.getProportion(), o2.getProportion()));
    }

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        String html = "<html>";

        // Add some Head information
        html = html + "<head>" +
                "<title>Subtask 2.1</title>";

        // Add some CSS (external file)
        // html = html + "<link rel='stylesheet' type='text/css' href='common.css' />";
        // adds a cool icon on the nav menu
        html = html
                + "<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css'>";
        html = html + "<link rel='stylesheet' type='text/css' href='common.css' />";
        html = html + "<link rel='stylesheet' type='text/css' href='burgerNav.css' />";
        html = html + "</head>";

        // Add the body
        // Create a simple HTML webpage in a String

        // Add some Head information
        // html = html + "<head>" +
        // "<title>Subtask 2.1</title>";

        // // Add some CSS (external file)
        // html = html + "<link rel='stylesheet' type='text/css'
        // href='JesseTesting2c.css' />";
        // html = html + "</head>";

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
        html = html + "<div class='content'>";

        html = html + """
                <h2>Focused view of temperature by Cities or States</h2>
                """;

        // declaring variables

        int fromDate;
        int toDate;
        String type = context.req.getParameter("type");
        try {
            fromDate = Integer.parseInt(context.req.getParameter("from"));
            toDate = Integer.parseInt(context.req.getParameter("to"));
        } catch (Exception e) {
            fromDate = 0;
            toDate = 0;
        }
        String countryParameterFromURL = context.req.getParameter("country");

        // header content block

        html += """
                   <div class='header'>
                       <h3> Temperatures by States or Cities </h3>
                   </div>
                """;

        // brief description of info on page
        html += """
                   <p> While climate change is an issue that effects the world on a global scale,
                   it can often be beneficial to explore the changes on a smaller scale -
                   such as that of cities and states. The following table generator
                   provides a means to explore and compare this data. Additionally the respective cities and
                   states are ranked by the proportional change in temperature for the average, minimum and maximum
                   temperatures for the time period chosen to be explored. They are ranked from the largest to smallest change with respect to increasing
                   temperatures.
                     </p>
                     <p> Please note that data is not available for all countries. </p>

                """;

        // hashmap to get country names for dropdown menu

        ArrayList<Country> mapOfCountries = JDBCConnection.getCountryNames();
        html += """

                    <form id='form-id'>
                    <label for>Select Country:</label>
                    <select name="country" onchange='document.getElementById("form-id").submit();'>
                    <option value="" selected disabled hidden>Country</option>
                """;

        for (Country c : mapOfCountries) {

            String key = c.getId();
            String value = c.getName();
            if (countryParameterFromURL != null) {
                if (countryParameterFromURL.equals(key)) {

                    // part of code that makes the selected option stay after the page is refreshed

                    html += "<option selected='selected' value='";
                    html += key + "'>" + value + "</option>";
                } else {
                    html += "<option value='";
                    html += key + "'>" + value + "</option>";
                }
            } else {
                html += "<option value='";
                html += key + "'>" + value + "</option>";
            }
        }

        html += "</select>";

        // dropdown menu for cites or states

        html = html += "<br> <label for>Select Cities or States:</label>";
        html += """
                    <select name='type'>
                    <option value="" selected disabled hidden>City/State</option>
                """;

        if (countryParameterFromURL != null) {
            if (JDBCConnection.hasStates(countryParameterFromURL)) {
                if (type != null) {
                    if (type.equals("States")) {
                        html += "<option selected=selected value='States'>States</option> ";
                    } else {
                        html += "<option value='States'>States</option> ";
                    }
                } else {
                    html += "<option value='States'>States</option> ";
                }
            }

            if (JDBCConnection.hasCities(countryParameterFromURL)) {
                if (type != null) {

                    if (type.equals("Cities")) {
                        html += "<option selected=selected value='Cities'>Cities</option>";
                    } else {
                        html += "<option value='Cities'>Cities</option>";
                    }
                } else {
                    html += "<option value='Cities'>Cities</option> ";
                }

            }
        }
        // dropdown menues for start and end dates

        html += """
                    </select>
                    <br> <label for>Select Start Year:</label>
                    <select name='from'>
                    <option value='' selected disabled hidden>Year</option>
                """;

        for (int i = 1750; i < 2014; i++) {
            if (fromDate == i) {
                html += "<option selected='selected' value='";
                html += i + "'>" + i + "</option>";
            } else {

                html += "<option value='";
                html += i + "'>" + i + "</option>";
            }
        }

        html += "</select>";
        html += "<br> <label for>Select End Year:</label>";
        html += "<select name='to'>";
        html += """
                  <option value="" selected disabled hidden>Year</option>
                """;

        for (int i = 1749; i < 2014; i++) {
            if (toDate == i) {
                html += "<option selected ='selected' value='";
                html += i + "'>" + i + "</option>";
            } else {

                html += "<option value='";
                html += i + "'>" + i + "</option>";
            }
        }

        html += "</select>";

        html += "<div>";
        html += "<button class='showTable' type='submit' class='btn btn-primary'>Show Table</button>";
        html += "</div>";
        html += "<div>";
        html = html + "<br> <button class='reset' type='reset' >Reset</button>";
        html += "</div>";
        html += "</form>";

        // javascript to allow 'c' to also reset the data
        html = html + """
                    <script>
                    document.addEventListener('DOMContentLoaded', function() {
                        var button = document.querySelector('.reset');
                        document.addEventListener('keydown', function(event) {
                            // Check if the C key (key code 67) is pressed
                            if (event.keyCode == 67) {
                                button.click();
                            }
                        });
                    });
                    </script>
                """;
        // note from jesse had to disable code below as it was breaking the footer
        // html = html + "<div class='content' style='margin-top:10px;'>";

        if (countryParameterFromURL != null) {
            ArrayList<TempData> data;

            html += "<table><tr> <th> Year </th> ";

            if (type != null) {
                if (type.equals("States")) {
                    data = JDBCConnection.getTempByState(countryParameterFromURL, fromDate, toDate);
                    html += "<th> State</th>";

                } else {
                    data = JDBCConnection.getTempByCity(countryParameterFromURL, fromDate, toDate);

                    html += "<th> City</th>";

                }

                html += "<th> Average Temperature </th> ";
                html += "<th> Minimum Temperature </th> <th> Maximum Temperature </th> </tr>";

                for (TempData d : data) {
                    html += "<tr><td>" + d.getYear() + "</td>";
                    html += "<td>" + d.getName() + "</td>";
                    html += "<td>" + d.getAvgTemp() + "</td>";
                    html += "<td>" + d.getMinTemp() + "</td>";
                    html += "<td>" + d.getMaxTemp() + "</td></tr>";
                }
                html += "<table>";
                // " <td> " + Years + "</td>";

                ArrayList<TempData> minYearTemp = getMaximumTemperatures(data);
                ArrayList<TempData> maxYearTemp = getMinimumTemperatures(data);

                ArrayList<Stat> stats = getAvgTempProportionalValues(minYearTemp, maxYearTemp);
                ArrayList<Stat> statsMin = getMinTempProportionalValues(minYearTemp, maxYearTemp);
                ArrayList<Stat> statsMax = getMaxTempProportionalValues(minYearTemp, maxYearTemp);
                html += "<h3> The Proportional Change of Average Temperatures</h3>";
                html += printOutRanking(stats);
                html += "<h3> The Proportional Change of Minimum Temperatures</h3>";
                html += printOutRanking(statsMin);
                html += "<h3> The Proportional Change of Maximum Temperatures</h3>";
                html += printOutRanking(statsMax);
                html += "</table>";
            } else {
                html += "</tr></table>";

            }

        }

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

        html += "<script>$(\"#form-id\").html($(\"#form-id option\").sort(function (a, b) {\n" +
                "    return a.text == b.text ? 0 : a.text < b.text ? -1 : 1\n" +
                "}))</script>";
        // Finish the HTML webpage
        html = html + "</body>" + "</html>";

        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(html);

    }

    public ArrayList<TempData> getMinimumTemperatures(ArrayList<TempData> data) {
        ArrayList<TempData> tmp = new ArrayList<TempData>();
        for (TempData d : data) {
            boolean exists = false;
            for (TempData t : tmp) {
                if (d.getName().equals(t.getName())) {
                    exists = true;
                    break;
                }
            }
            if (exists) {
                return tmp;
            } else {
                tmp.add(d);
            }
        }
        return tmp;
    }

    public ArrayList<TempData> getMaximumTemperatures(ArrayList<TempData> data) {
        ArrayList<TempData> tmp = new ArrayList<TempData>();
        for (int i = data.size() - 1; i > 0; i--) {
            boolean exists = false;
            for (TempData t : tmp) {
                if (data.get(i).getName().equals(t.getName())) {
                    exists = true;
                    break;
                }
            }
            if (exists) {
                return tmp;
            } else {
                tmp.add(data.get(i));
            }
        }
        return tmp;
    }

    public ArrayList<Stat> getAvgTempProportionalValues(ArrayList<TempData> min, ArrayList<TempData> max) {
        ArrayList<Stat> stats = new ArrayList<Stat>();
        for (TempData m : min) {
            for (TempData x : max) {
                if (m.getName().equals(x.getName())) {
                    stats.add(new Stat(x.getName(), ((m.getAvgTemp() - x.getAvgTemp()) / m.getAvgTemp()) * 100));
                }
            }
        }
        return stats;
    }

    public ArrayList<Stat> getMinTempProportionalValues(ArrayList<TempData> min, ArrayList<TempData> max) {
        ArrayList<Stat> stats = new ArrayList<Stat>();
        for (TempData m : min) {
            for (TempData x : max) {
                if (m.getName().equals(x.getName())) {
                    stats.add(new Stat(x.getName(), ((m.getMinTemp() - x.getMinTemp()) / m.getMinTemp()) * 100));
                }
            }
        }
        return stats;
    }

    public ArrayList<Stat> getMaxTempProportionalValues(ArrayList<TempData> min, ArrayList<TempData> max) {
        ArrayList<Stat> stats = new ArrayList<Stat>();
        for (TempData m : min) {
            for (TempData x : max) {
                if (m.getName().equals(x.getName())) {
                    stats.add(new Stat(x.getName(), ((m.getMaxTemp() - x.getMaxTemp()) / m.getMaxTemp()) * 100));
                }
            }
        }
        return stats;
    }

    public String printOutRanking(ArrayList<Stat> stats) {
        sort(stats);
        String html = "";
        int i = 1;
        for (Stat s : stats) {
            html += "<li>Rank " + i + ". " + s.getId() + " has a change in proportion of: " + s.getProportion()
                    + "</li>";
            i++;
        }
        return html;
    }
}
