package app;

import io.javalin.http.Context;
import io.javalin.http.Handler;

import java.util.ArrayList;

import static java.lang.Math.abs;

/**
 * Example Index HTML class using Javalin
 * <p>
 * Generate a static HTML page using Javalin
 * by writing the raw HTML into a Java String object
 *
 * @author Timothy Wiley, 2023. email: timothy.wiley@rmit.edu.au
 * @author Santha Sumanasekara, 2021. email: santha.sumanasekara@rmit.edu.au
 */
public class PageST3B implements Handler {

    // URL of this page relative to http://localhost:7001/
    public static final String URL = "/page3B.html";

    public static void sort(ArrayList<Stat> list) {

        list.sort((o2, o1) -> Float.compare(o1.getProportion(), o2.getProportion()));
    }

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        StringBuilder html = new StringBuilder("<html>");

        // Add some Head information
        html.append("<head>").append("<title>Subtask 3.2</title>");

        // Add some CSS (external file)
        html.append("<link rel='stylesheet' type='text/css' href='JesseTesting2c.css' />");
        // adds a cool icon on the nav menu
        html.append("<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css'>");
        html.append("</head>");

        // Add the body
        html.append("<body>");

        // Add header content block
        html.append("""
                    <div class='header'>
                        <h1><a href='/'><img src='ClimateLogo.png' class='top-image' alt='Website Logo' height='120' width = '120' style='float: left;'></a>
                        Climate Change Awareness</h1>
                    </div>
                """);

        // Add the topnav
        // This uses a Java v15+ Text Block

        html.append("""
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
                """);

        // Add Div for page Content
        html.append("<div class='content'>");


        // Add HTML for the page content
        html.append("""
                     <h4> description of task from milsetone document <h4/>
                     <p>Enable the user to select a starting year, a time period (in years), and a geographic region
                          (country, state, or city), then find other time periods that are “the most similar” to the chosen
                          combination on a set of user selected properties, such that users may:
                          o Select similarity in terms of temperature alone
                          o Select similarity in terms of population alone (for country only)
                          o Select similarity in terms of both temperature and population (for country only)
                          o The user can then select to determine similarity in terms of:
                          the absolute values
                          the relative change in values
                          • From the user selection, your system must find the <x> most similar results, identifying the
                          region, the start year, end year, the values used to determine its similarity to the selected
                          region, as well as the similarity score, using the user's selection of constraints, where the user
                          chooses the number of regions <x> to locate:
                          o For example, the user selects the starting year 1900, a time period of 10 years, and the
                          region Australia. The user selects similarity by temperature alone based on the
                          absolute value, and to find the 5 most similar regions. Since the user has selected a
                          country (Australia), the system should then find countries (including Australia) and time
                          periods (of 10 years) that have similar start and end temperatures, then display the 5
                          most similar results.
                          • Present the selected region and most similar regions, along with the relevant data that was used
                          to determine the similarity.
                          • Sort the regions that are found by the most similar to the least similar.
                          • Retrieve the exact information from your database using as few queries as possible:
                          o Ideally, use only a single query (with column selections and JOIN operations)
                          o Unnecessary information should not be returned in the SQL queries.
                          o Performing the sorting through SQL queries if possible.
                          o Note that this is challenge in making suitable SQL queries </p>   
                """);


        // declaring variables
        int fromDate;
        int duration;
        int number;
        String countryParameterFromURL = context.req.getParameter("country");
        String cityParameterFromUrl = context.req.getParameter("city");
        String stateParameterFromUrl = context.req.getParameter("state");


        try {
            fromDate = Integer.parseInt(context.req.getParameter("from"));

        } catch (Exception e) {
            fromDate = 0;
        }
        try {
            duration = Integer.parseInt(context.req.getParameter("duration"));

        } catch (Exception e) {
            duration = 0;
        }
        try {
            number = Integer.parseInt(context.req.getParameter("number"));

        } catch (Exception e) {
            number = 0;
        }


        ArrayList<Country> mapOfCountries = JDBCConnection.getCountryNames();
        html.append("""
                            
                    <form id='form-id'>
                    <label for>Select Country:</label>
                    <select name="country" onchange='document.getElementById("form-id").submit();'>
                    <option value="" selected disabled hidden>Country</option>
                """);

        for (Country c : mapOfCountries) {

            String key = c.getId();
            String value = c.getName();
            if (countryParameterFromURL != null) {
                if (countryParameterFromURL.equals(key)) {

                    // part of code that makes the selected option stay after the page is refreshed

                    html.append("<option selected='selected' value='");
                    html.append(key).append("'>").append(value).append("</option>");
                } else {
                    html.append("<option value='");
                    html.append(key).append("'>").append(value).append("</option>");
                }
            } else {
                html.append("<option value='");
                html.append(key).append("'>").append(value).append("</option>");
            }
        }
        html.append("</select>");


        if (countryParameterFromURL != null) {

            if (cityParameterFromUrl == null && stateParameterFromUrl == null) {

                html.append("<label>Select Desired Data:</label>");
                html.append("""
                        <select name='Data'>
                           <option value="" selected disabled hidden>Data</option>
                           <option value="Temperature">Temperature</option>
                           <option value="Population">Population</option>
                           <option value="Temperature-and-Population">Temperature and Temperature</option>
                              """);
                html.append("</select>");


            }

            if (cityParameterFromUrl != null || stateParameterFromUrl != null) {
                html.append("<label>Select Desired Data:</label>");
                html.append("""
                        <select name='Data'>
                           <option value="" selected disabled hidden>Data</option>
                           <option value="Temperature">Temperature</option>
                              """);
                html.append("</select>");
            }


            if (JDBCConnection.hasStates(countryParameterFromURL)) {
                ArrayList<CityState> mapOfStates = JDBCConnection.getStateNames(countryParameterFromURL);

                html.append("<br> <label for>(Optional) Select States:</label>");
                html.append("""
                        <select name='state' onchange='document.getElementById("form-id").submit();'>
                             <option value="" selected disabled hidden>State</option>
                                     """);

                for (CityState s : mapOfStates) {
                    if (stateParameterFromUrl != null) {
                        if (stateParameterFromUrl.equals(s.getName())) {
                            html.append("<option selected='selected' value='");
                            html.append(s.getName()).append("'>").append(s.getName()).append("</option>");
                        } else {
                            html.append("<option value='");
                            html.append(s.getName()).append("'>").append(s.getName()).append("</option>");
                        }
                    } else {
                        html.append("<option value='");
                        html.append(s.getName()).append("'>").append(s.getName()).append("</option>");
                    }
                }
                html.append("</select>");

            }
        }

        if (JDBCConnection.hasCities(countryParameterFromURL)) {
            ArrayList<CityState> mapOfCities = JDBCConnection.getCityNames(countryParameterFromURL);
            html.append("<br><label for>(Optional) Select City:</label>");
            html.append("""
                    <select name='city' onchange='document.getElementById("form-id").submit();'>
                         <option value="" selected disabled hidden>City</option>
                                 """);

            for (CityState x : mapOfCities) {
                if (cityParameterFromUrl != null) {
                    if (cityParameterFromUrl.equals(x.getName())) {
                        html.append("<option selected='selected' value='");
                        html.append(x.getName()).append("'>").append(x.getName()).append("</option>");
                    } else {
                        html.append("<option value='");
                        html.append(x.getName()).append("'>").append(x.getName()).append("</option>");
                    }
                } else {
                    html.append("<option value='");
                    html.append(x.getName()).append("'>").append(x.getName()).append("</option>");
                }
            }
            html.append("</select>");


        }

        // // dropdown for starting year
        html.append("""
                                    
                 <br></br> <label for>Select Start Year:</label>
                   <select name='from'>
                      <option value='' selected disabled hidden>Year</option>
                """);


        for (int i = 1750; i < 2014; i++) {
            if (fromDate == i) {
                html.append("<option selected='selected' value='");
                html.append(i).append("'>").append(i).append("</option>");
            } else {

                html.append("<option value='");
                html.append(i).append("'>").append(i).append("</option>");
            }
        }
        html.append("</select>");


        // duration dropdown
        html.append("""
                </select>
                   <br> <label for>Select Duration:</label>
                      <select name='duration'>
                          <option value='' selected disabled hidden> </option>

                              """);


        for (int i = 1; i < 250; i++) {
            if (duration == i) {
                html.append("<option selected='selected' value='");
                html.append(i).append("'>").append(i).append("</option>");
            } else {

                html.append("<option value='");
                html.append(i).append("'>").append(i).append("</option>");
            }
        }
        html.append("</select>");


        // determine Similarity in Terms  Of
        html.append("""
                <p>Calculate similarity in terms of: </p>
                <input type='radio' id='Similarity' name='Similarity' value='Absolute Value'>
                <label class='radio-label' for='Ascending'>Absolute Value</label><br>
                <input type='radio' id='Similarity' name='Similarity' value='Relative Change in value'>
                 <label class='radio-label' for='Descending'>Relative Change in value</label>
                    """);

        // number of comparisons
        html.append("""
                </select>
                   <br> <label for>Select number of locations to compare:</label>
                      <select name='number'>
                          <option value='' selected disabled hidden> </option>

                              """);


        for (int i = 1; i <= 10; i++) {
            if (number == i) {
                html.append("<option selected='selected' value='");
                html.append(i).append("'>").append(i).append("</option>");
            } else {

                html.append("<option value='");
                html.append(i).append("'>").append(i).append("</option>");
            }
        }

        html.append("</select>");
        html.append("</div>");
        html.append("<button class='showTable' type='submit' class='btn btn-primary'>Show Table</button>");
        html.append("</div>");
        html.append("<div>");
        html.append("<button class='reset' type='reset' >Reset</button>");
        html.append("</div>");
        html.append("</form>");

//        ********************** Average Temp ****************************

        html.append("<table> <tr>");
        html.append("<th>Rank By Similarity</th>");
        html.append("<th>Country</th>");
        html.append("<th>start year</th>");
        html.append("<th>end year</th>");
        html.append("<th>Proportion</th>");
        html.append("</tr>");

        int toDate = fromDate + duration;
        if (toDate > 2014) {
            toDate = 2013;
        }
        ArrayList<PopulationAndTemp> data = JDBCConnection.getCountryPopulationAndTemp(fromDate, toDate);
        ArrayList<PopulationAndTemp> minData = getDataByYear(data, fromDate);
        ArrayList<PopulationAndTemp> maxData = getDataByYear(data, toDate);
        ArrayList<Stat> avgTempProportionalValues = getAvgTempProportionalValues(minData, maxData);
        sort(avgTempProportionalValues);
        avgTempProportionalValues = getNearByCountries(avgTempProportionalValues, countryParameterFromURL, number);
        if (fromDate != 0 && duration != 0 && countryParameterFromURL != null) {
            int i = 1;
            for (Stat d : avgTempProportionalValues) {
                html.append("<tr>");
                html.append("<td>").append(i).append("</td>");
                html.append("<td>").append(d.getName()).append("</td>");
                html.append("<td>").append(fromDate).append("</td>");
                html.append("<td>").append(toDate).append("</td>");
                html.append("<td>").append(d.getProportion()).append("</td>");
                html.append("</tr>");
                i++;
            }
            html.append("</table>");
        }

//        ********************** Average Population ****************************

        html.append("<table> <tr>");
        html.append("<th>Rank By Similarity</th>");
        html.append("<th>Country</th>");
        html.append("<th>start year</th>");
        html.append("<th>end year</th>");
        html.append("<th>Proportion</th>");
        html.append("</tr>");


        ArrayList<Stat> avgPopProportionalValues = getPopulationProportionalValues(minData, maxData);
        sort(avgPopProportionalValues);
        avgPopProportionalValues = getNearByCountries(avgPopProportionalValues, countryParameterFromURL, number);
        if (fromDate != 0 && duration != 0 && countryParameterFromURL != null) {
            int i = 1;
            for (Stat d : avgPopProportionalValues) {
                html.append("<tr>");
                html.append("<td>").append(i).append("</td>");
                html.append("<td>").append(d.getName()).append("</td>");
                html.append("<td>").append(fromDate).append("</td>");
                html.append("<td>").append(toDate).append("</td>");
                html.append("<td>").append(d.getProportion()).append("</td>");
                html.append("</tr>");
                i++;
            }
            html.append("</table>");
        }


        // Close Content div
        html.append("</div>");

        // Footer
        html.append("""
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
                        """);

        // Finish the HTML webpage
        html.append("</body>").append("</html>");

        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(html.toString());
    }

    public ArrayList<PopulationAndTemp> getDataByYear(ArrayList<PopulationAndTemp> data, int year) {
        ArrayList<PopulationAndTemp> tmp = new ArrayList<PopulationAndTemp>();
        for (PopulationAndTemp d : data) {
            if (d.getYear() == year) {
                tmp.add(d);
            }
        }
        return tmp;
    }

    public ArrayList<Stat> getAvgTempProportionalValues(ArrayList<PopulationAndTemp> min, ArrayList<PopulationAndTemp> max) {
        ArrayList<Stat> stats = new ArrayList<Stat>();
        for (PopulationAndTemp m : min) {
            for (PopulationAndTemp x : max) {
                if (m.getCountryId().equals(x.getCountryId())) {
                    stats.add(new Stat(x.getCountryId(), x.getName(), ((m.getAvgTemp() - x.getAvgTemp()) / m.getAvgTemp()) * 100));
                }
            }
        }
        return stats;
    }

    public ArrayList<Stat> getPopulationProportionalValues(ArrayList<PopulationAndTemp> min, ArrayList<PopulationAndTemp> max) {
        ArrayList<Stat> stats = new ArrayList<Stat>();
        for (PopulationAndTemp m : min) {
            for (PopulationAndTemp x : max) {
                if (m.getCountryId().equals(x.getCountryId())) {
                    stats.add(new Stat(x.getCountryId(), x.getName(), ((m.getPopulation() - x.getPopulation()) / m.getPopulation()) * 100));
                }
            }
        }
        return stats;
    }


    public ArrayList<Stat> getNearByCountries(ArrayList<Stat> stat, String countryName, int limit) {
        int x = 0;
        int index = 0;
        for (Stat s : stat) {
            if (s.getId().equals(countryName)) {
                index = x;
                break;
            }
            x++;
        }
        Stat country = stat.get(index);
        ArrayList<Stat> nearStats = new ArrayList<>();
        nearStats.add(country);
        for (int i = 1; i < limit; i++) {
            float above = country.getProportion() - stat.get(index + i).getProportion();
            float below = country.getProportion() - stat.get(index - i).getProportion();
            if (abs(above) > abs(below)) {
                nearStats.add(stat.get(index - i));
            } else {
                nearStats.add(stat.get(index + i));
            }

        }
        return nearStats;
    }

}
