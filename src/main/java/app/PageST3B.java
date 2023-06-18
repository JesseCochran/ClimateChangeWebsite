package app;

import io.javalin.http.Context;
import io.javalin.http.Handler;

import java.util.ArrayList;

import static java.lang.Math.abs;

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
        html.append("""
                  <link rel='stylesheet' type='text/css' href='common.css' />
                  <link rel='stylesheet' type='text/css' href='ST3A.css' />
                  <link rel='stylesheet' type='text/css' href='burgerNav.css' />
                """);
        html.append(
                "<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css'>");
        html.append("</head>");

        // Add the body
        html.append("<body>");

        // Add header content block
        html.append(
                """
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
                """);

        // Function in js for opening and closing the burger menu
        html.append("<script>");
        html.append("""

                                function openNav() {
                  document.getElementById('mySidenav').style.width = '250px';
                }

                function closeNav() {
                  document.getElementById('mySidenav').style.width = '0px';
                }
                                                """);
        html.append("</script>");
        // elements inside the burger menu
        html.append("<div class='SideNavBar'>");
        html.append(
                """
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
                                """);
        html.append("</div>");
        // key press to reload/clear values
        html.append("""
                <script>
                                document.addEventListener('keydown', function(event) {
                      // Check if the C key (key code 67) is pressed
                      if (event.keyCode == 67) {
                        reload();
                      }
                      });
                                """);
        // take to help page
        html.append("""

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
                                        """);
        // take to home page
        html.append("""
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
                            """);
        html.append("</script>");
        // Add Div for page Content
        html.append("<div class='content'>");

        // Add HTML for the page content
        html.append("<h2> A Focused View Of Date On a Country, City or State Level </h2>");

        // page description
        html.append(
                "<p> On this page you have the option to select whether you wish to view data on a country, city, or state level. </p>");
        html.append(
                "<p> If you have chosen to view country data you may view population, temperature or both population and temperature. </p>");
        html.append("<p> If you have chosen to view city or state data only temperature data is available. </p>");
        html.append("<p> Additionally you may choose to compare data by relative change or absolute value </p>");
        html.append("<p> The table generator will provide you with information geographical locations with similar data to your chosen location. You may choose how many locations you wish to compare.  </p>");
        html.append("<p> Additionally you may choose to compare data by relative change or absolute value </p>");
        html.append("<p> Please note: Cities and States will be compared with respect to their parent country. </p>");

        // declaring variables
        int fromDate;
        int duration;
        int number;
        String countryParameterFromURL = context.req.getParameter("country");
        String cityParameterFromUrl = context.req.getParameter("city");
        String stateParameterFromUrl = context.req.getParameter("state");
        String similarityParameterFromUrl = context.req.getParameter("similarity");
        String dataParameterFromUrl = context.req.getParameter("data");

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
                        <select name="data" onchange='document.getElementById("form-id").submit();'>


                                  """);
                if (dataParameterFromUrl != null) {
                    if (dataParameterFromUrl.equals("temperature")) {
                        html.append("<option selected=selected value='temperature'>Temperature</option>");
                        html.append("<option value='population'>Population</option>");
                        html.append("<option value='tandp'>Temperature and Population</option>");

                    } else if (dataParameterFromUrl.equals("population")) {

                        html.append("<option selected=selected value='population'>Population</option>");
                        html.append("<option  value='temperature'>Temperature</option>");
                        html.append("<option value='tandp'>Temperature and Population</option>");

                    } else if (dataParameterFromUrl.equals("tandp")) {

                        html.append("<option value='population'>Population</option>");
                        html.append("<option value='temperature'>Temperature</option>");
                        html.append("<option selected=selected value='tandp'>Temperature and Population</option>");

                    } else {
                        html.append("<option value='population'>Population</option>");
                        html.append("<option value='temperature'>Temperature</option>");
                        html.append("<option value='tandp'>Temperature and Population</option>");

                    }

                } else {
                    html.append("""
                               <option value="temperature">Temperature</option>
                               <option value="population">Population</option>
                               <option value="tandp">Temperature and Population</option>
                            """);
                }
                html.append("</select>");
            }

            if (cityParameterFromUrl != null || stateParameterFromUrl != null) {
                html.append("<label>Select Desired Data:</label>");
                if (dataParameterFromUrl != null) {
                    if (dataParameterFromUrl.equals("temperature")) {
                        html.append("""
                                <select name="data" onchange='document.getElementById("form-id").submit();'>
                                   <option value="" selected disabled hidden>data</option>
                                   <option selected=selected value="temperature">Temperature</option>
                                      """);
                    }
                } else {
                    html.append("""
                            <select name="data" onchange='document.getElementById("form-id").submit();'>
                               <option value="" selected disabled hidden>data</option>
                               <option value="temperature">Temperature</option>
                                  """);
                }
            }

            html.append("</select>");

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

            for (int i = 1960; i < 2014; i++) {
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

                                  """);

            for (int i = 1; i < 53; i++) {
                if (duration == i) {
                    html.append("<option selected='selected' value='");
                    html.append(i).append("'>").append(i).append("</option>");
                } else {

                    html.append("<option value='");
                    html.append(i).append("'>").append(i).append("</option>");
                }
            }
            html.append("</select>");

            // determine Similarity in Terms Of
            if (similarityParameterFromUrl != null) {
                if (similarityParameterFromUrl.equals("absolute")) {
                    html.append("""
                            <p>Calculate similarity in terms of: </p>
                            <input type='radio' id='Similarity' name='similarity' checked value='absolute'>Absolute Value</input>
                            <input type='radio' id='Similarity' name='similarity' value='relative'>Relative Change in value</input>
                                """);

                } else if (similarityParameterFromUrl.equals("relative")) {
                    html.append(
                            """
                                    <p>Calculate similarity in terms of: </p>
                                    <input type='radio' id='Similarity' name='similarity'  value='absolute'>Absolute Value</input>
                                    <input type='radio' id='Similarity' name='similarity' checked value='relative'>Relative Change in value</input>
                                        """);

                } else {
                    html.append("""
                            <p>Calculate similarity in terms of: </p>
                            <input type='radio' id='Similarity' name='similarity' checked value='absolute'>Absolute Value</input>
                            <input type='radio' id='Similarity' name='similarity'  value='relative'>Relative Change in value</input>
                                """);
                }
            } else {
                html.append("""
                        <p>Calculate similarity in terms of: </p>
                        <input type='radio' id='Similarity' name='similarity' checked value='absolute'>Absolute Value</input>
                        <input type='radio' id='Similarity' name='similarity'  value='relative'>Relative Change in value</input>
                            """);
            }

            // number of comparisons
            html.append("""
                    </select>
                       <br> <label for>Select number of locations to compare:</label>
                          <select name='number'>

                                  """);

            for (int i = 1; i <= 200; i++) {
                if (number == i) {
                    html.append("<option selected='selected' value='");
                    html.append(i).append("'>").append(i).append("</option>");
                } else {

                    html.append("<option value='");
                    html.append(i).append("'>").append(i).append("</option>");
                }
            }

            // ********************** Average Temp ****************************

            html.append("</div>");
            html.append("</div>");
            html.append("</select>");
            html.append("<button class='showTable' type='submit' class='btn btn-primary'>Show Table</button>");
            html.append("<button class='reset' type='reset' >Reset</button>");
            html.append("</form>");

            if (fromDate != 0 && duration != 0) {

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
                if (fromDate != 0 && duration != 0 && countryParameterFromURL != null
                        && cityParameterFromUrl == null & stateParameterFromUrl == null) {
                    if (dataParameterFromUrl != null) {
                        if (dataParameterFromUrl.equals("temperature") || dataParameterFromUrl.equals("tandp")) {

                            if (similarityParameterFromUrl != null) {
                                if (similarityParameterFromUrl.equals("relative")) {
                                    int i = 1;
                                    html.append("<table> <tr>");
                                    html.append("<th>Rank By Similarity</th>");
                                    html.append("<th>Country</th>");
                                    html.append("<th>Start Year</th>");
                                    html.append("<th>End Year</th>");
                                    html.append("<th>Relative Change in Data</th>");
                                    html.append("<th>Start Year Temperature</th>");
                                    html.append("<th>End Year Temperature</th>");
                                    html.append("</tr>");

                                    for (Stat d : avgTempProportionalValues) {
                                        html.append("<tr>");
                                        html.append("<td>").append(i).append("</td>");
                                        html.append("<td>").append(d.getName()).append("</td>");
                                        html.append("<td>").append(fromDate).append("</td>");
                                        html.append("<td>").append(toDate).append("</td>");
                                        html.append("<td>").append(d.getProportion()).append("</td>");
                                        html.append("<td>").append(d.getStartData()).append("</td>");
                                        html.append("<td>").append(d.getEndData()).append("</td>");
                                        html.append("</tr>");
                                        i++;
                                    }
                                    html.append("</table>");
                                }
                            }
                        }
                    }
                }

                // ********CountryTemp Absolute Vale

                if (cityParameterFromUrl == null) {
                    if (stateParameterFromUrl == null) {
                        if (similarityParameterFromUrl != null) {
                            if (similarityParameterFromUrl.equals("absolute")) {
                                if (dataParameterFromUrl != null) {
                                    if (dataParameterFromUrl.equals("temperature") || dataParameterFromUrl.equals("tandp")) {

                                        html.append("<table> <tr>");
                                        html.append("<th>Rank By Similarity</th>");
                                        html.append("<th>Country</th>");
                                        html.append("<th>Start Year</th>");
                                        html.append("<th>End Year</th>");
                                        html.append("<th>Absolute Change in Data</th>");
                                        html.append("<th>Start Year Temperature</th>");
                                        html.append("<th>End Year Temperature</th>");
                                        html.append("</tr>");

                                        ArrayList<Stat> avgTempAbsoluteValuesforCountry = getAvgTempAbsoluteValue(minData, maxData);
                                        sort(avgTempAbsoluteValuesforCountry);
                                        avgTempAbsoluteValuesforCountry = getNearByCountries(avgTempAbsoluteValuesforCountry,
                                                countryParameterFromURL, number);
                                        if (fromDate != 0 && duration != 0 && countryParameterFromURL != null) {
                                            int i = 1;
                                            for (Stat d : avgTempAbsoluteValuesforCountry) {
                                                html.append("<tr>");
                                                html.append("<td>").append(i).append("</td>");
                                                html.append("<td>").append(d.getName()).append("</td>");
                                                html.append("<td>").append(fromDate).append("</td>");
                                                html.append("<td>").append(toDate).append("</td>");
                                                html.append("<td>").append(d.getProportion()).append("</td>");
                                                html.append("<td>").append(d.getStartData()).append("</td>");
                                                html.append("<td>").append(d.getEndData()).append("</td>");

                                                html.append("</tr>");
                                                i++;
                                            }
                                            html.append("</table>");
                                        }

                                    }
                                }
                            }
                        }
                    }
                }

                // ********************** Average Population ****************************
                if (cityParameterFromUrl == null) {
                    if (stateParameterFromUrl == null) {
                        if (similarityParameterFromUrl != null) {
                            if (similarityParameterFromUrl.equals("relative")) {
                                if (dataParameterFromUrl != null) {
                                    if (dataParameterFromUrl.equals("population") || dataParameterFromUrl.equals("tandp")) {

                                        html.append("<table> <tr>");
                                        html.append("<th>Rank By Similarity</th>");
                                        html.append("<th>Country</th>");
                                        html.append("<th>Start Year</th>");
                                        html.append("<th>End Year</th>");
                                        html.append("<th>Relative Change in Data</th>");
                                        html.append("<th>Start Year Population</th>");
                                        html.append("<th>End Year Population</th>");
                                        html.append("</tr>");

                                        ArrayList<Stat> avgPopProportionalValues = getPopulationProportionalValues(minData, maxData);
                                        sort(avgPopProportionalValues);
                                        avgPopProportionalValues = getNearByCountries(avgPopProportionalValues, countryParameterFromURL,
                                                number);
                                        if (fromDate != 0 && duration != 0 && countryParameterFromURL != null) {
                                            int i = 1;
                                            for (Stat d : avgPopProportionalValues) {
                                                html.append("<tr>");
                                                html.append("<td>").append(i).append("</td>");
                                                html.append("<td>").append(d.getName()).append("</td>");
                                                html.append("<td>").append(fromDate).append("</td>");
                                                html.append("<td>").append(toDate).append("</td>");
                                                html.append("<td>").append(d.getProportion()).append("</td>");
                                                html.append("<td>").append(d.getStartData()).append("</td>");
                                                html.append("<td>").append(d.getEndData()).append("</td>");

                                                html.append("</tr>");
                                                i++;
                                            }
                                            html.append("</table>");
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                // ********Absolute population data
                if (cityParameterFromUrl == null) {
                    if (stateParameterFromUrl == null) {
                        if (similarityParameterFromUrl != null) {
                            if (similarityParameterFromUrl.equals("absolute")) {
                                if (dataParameterFromUrl != null) {
                                    if (dataParameterFromUrl.equals("population") || dataParameterFromUrl.equals("tandp")) {
                                        html.append("<table> <tr>");
                                        html.append("<th>Rank By Similarity</th>");
                                        html.append("<th>Country</th>");
                                        html.append("<th>Start Year</th>");
                                        html.append("<th>End Year</th>");
                                        html.append("<th>Absolute Change in Data</th>");
                                        html.append("<th>Start Year Population</th>");
                                        html.append("<th>End Year Population</th>");
                                        html.append("</tr>");

                                        ArrayList<Stat> avgTempAbsoluteValuesforpopulation = getPopulationAbsoluteValues(minData, maxData);
                                        sort(avgTempAbsoluteValuesforpopulation);
                                        avgTempAbsoluteValuesforpopulation = getNearByCountries(avgTempAbsoluteValuesforpopulation,
                                                countryParameterFromURL, number);
                                        if (fromDate != 0 && duration != 0 && countryParameterFromURL != null) {
                                            int i = 1;
                                            for (Stat d : avgTempAbsoluteValuesforpopulation) {
                                                html.append("<tr>");
                                                html.append("<td>").append(i).append("</td>");
                                                html.append("<td>").append(d.getName()).append("</td>");
                                                html.append("<td>").append(fromDate).append("</td>");
                                                html.append("<td>").append(toDate).append("</td>");
                                                html.append("<td>").append(d.getProportion()).append("</td>");
                                                html.append("<td>").append(d.getStartData()).append("</td>");
                                                html.append("<td>").append(d.getEndData()).append("</td>");

                                                html.append("</tr>");
                                                i++;
                                            }
                                            html.append("</table>");
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                // ***********************Geting avgTemp for City***************

                if (cityParameterFromUrl != null && stateParameterFromUrl == null) {
                    if (similarityParameterFromUrl != null) {
                        if (similarityParameterFromUrl.equals("relative")) {
                            if (dataParameterFromUrl != null) {
                                if (dataParameterFromUrl.equals("temperature")) {

                                    html.append("<table> <tr>");
                                    html.append("<th>Rank By Similarity</th>");
                                    html.append("<th>City</th>");
                                    html.append("<th>Start Year</th>");
                                    html.append("<th>End Year</th>");
                                    html.append("<th>Relative Change in Data</th>");
                                    html.append("<th>Start Year Temperature</th>");
                                    html.append("<th>End Year Temperature</th>");
                                    html.append("</tr>");

                                    data = JDBCConnection.getAvgTempForCity(countryParameterFromURL, fromDate, toDate);
                                    minData = getDataByYear(data, fromDate);
                                    maxData = getDataByYear(data, toDate);
                                    avgTempProportionalValues = getAvgTempProportionalValues(minData, maxData);
                                    sort(avgTempProportionalValues);
                                    avgTempProportionalValues = getNearByCountries(avgTempProportionalValues, countryParameterFromURL,
                                            number);
                                    if (fromDate != 0 && duration != 0 && countryParameterFromURL != null) {
                                        int i = 1;
                                        for (Stat d : avgTempProportionalValues) {
                                            html.append("<tr>");
                                            html.append("<td>").append(i).append("</td>");
                                            html.append("<td>").append(d.getName()).append("</td>");
                                            html.append("<td>").append(fromDate).append("</td>");
                                            html.append("<td>").append(toDate).append("</td>");
                                            html.append("<td>").append(d.getProportion()).append("</td>");
                                            html.append("<td>").append(d.getStartData()).append("</td>");
                                            html.append("<td>").append(d.getEndData()).append("</td>");
                                            html.append("</tr>");
                                            i++;
                                        }
                                        html.append("</table>");
                                    }
                                }
                            }
                        }
                    }
                }

                // ********absvalue for city*******

                if (cityParameterFromUrl != null && stateParameterFromUrl == null) {
                    if (similarityParameterFromUrl != null) {
                        if (similarityParameterFromUrl.equals("absolute")) {
                            if (dataParameterFromUrl != null) {
                                if (dataParameterFromUrl.equals("temperature")) {

                                    html.append("<table> <tr>");
                                    html.append("<th>Rank By Similarity</th>");
                                    html.append("<th>City</th>");
                                    html.append("<th>Start Year</th>");
                                    html.append("<th>End Year</th>");
                                    html.append("<th>Change in Data</th>");
                                    html.append("<th>Start Year Temperature</th>");
                                    html.append("<th>End Year Temperature</th>");
                                    html.append("</tr>");

                                    data = JDBCConnection.getAvgTempForCity(countryParameterFromURL, fromDate, toDate);
                                    minData = getDataByYear(data, fromDate);
                                    maxData = getDataByYear(data, toDate);
                                    ArrayList<Stat> avgTempAbsoluteValuesforcities = getAvgTempAbsoluteValue(minData, maxData);
                                    sort(avgTempAbsoluteValuesforcities);
                                    avgTempAbsoluteValuesforcities = getNearByCountries(avgTempAbsoluteValuesforcities,
                                            countryParameterFromURL, number);
                                    if (fromDate != 0 && duration != 0 && countryParameterFromURL != null) {
                                        int i = 1;
                                        for (Stat d : avgTempAbsoluteValuesforcities) {
                                            html.append("<tr>");
                                            html.append("<td>").append(i).append("</td>");
                                            html.append("<td>").append(d.getName()).append("</td>");
                                            html.append("<td>").append(fromDate).append("</td>");
                                            html.append("<td>").append(toDate).append("</td>");
                                            html.append("<td>").append(d.getProportion()).append("</td>");
                                            html.append("<td>").append(d.getStartData()).append("</td>");
                                            html.append("<td>").append(d.getEndData()).append("</td>");

                                            html.append("</tr>");
                                            i++;
                                        }
                                        html.append("</table>");
                                    }
                                }
                            }
                        }
                    }
                }

                // ***********************Geting avgTemp for State***************
                if (cityParameterFromUrl == null && stateParameterFromUrl != null) {
                    if (similarityParameterFromUrl != null) {
                        if (similarityParameterFromUrl.equals("relative")) {
                            if (dataParameterFromUrl != null) {
                                if (dataParameterFromUrl.equals("temperature")) {

                                    html.append("<table> <tr>");
                                    html.append("<th>Rank By Similarity</th>");
                                    html.append("<th>State</th>");
                                    html.append("<th>Start Year</th>");
                                    html.append("<th>End Year</th>");
                                    html.append("<th>Relative Change in Data</th>");
                                    html.append("<th>Start Year Temperature</th>");
                                    html.append("<th>End Year Temperature</th>");
                                    html.append("</tr>");

                                    data = JDBCConnection.getAvgTempForState(countryParameterFromURL, fromDate, toDate);
                                    minData = getDataByYear(data, fromDate);
                                    maxData = getDataByYear(data, toDate);
                                    avgTempProportionalValues = getAvgTempProportionalValues(minData, maxData);
                                    sort(avgTempProportionalValues);
                                    avgTempProportionalValues = getNearByCountries(avgTempProportionalValues, countryParameterFromURL,
                                            number);
                                    if (fromDate != 0 && duration != 0 && countryParameterFromURL != null) {
                                        int i = 1;
                                        for (Stat d : avgTempProportionalValues) {
                                            html.append("<tr>");
                                            html.append("<td>").append(i).append("</td>");
                                            html.append("<td>").append(d.getName()).append("</td>");
                                            html.append("<td>").append(fromDate).append("</td>");
                                            html.append("<td>").append(toDate).append("</td>");
                                            html.append("<td>").append(d.getProportion()).append("</td>");
                                            html.append("<td>").append(d.getStartData()).append("</td>");
                                            html.append("<td>").append(d.getEndData()).append("</td>");
                                            html.append("</tr>");
                                            i++;
                                        }
                                        html.append("</table>");
                                    }
                                }
                            }
                        }
                    }
                }

                // ************** Absolute Value For State

                if (cityParameterFromUrl == null && stateParameterFromUrl != null) {
                    if (similarityParameterFromUrl != null) {
                        if (similarityParameterFromUrl.equals("absolute")) {
                            if (dataParameterFromUrl != null) {
                                if (dataParameterFromUrl.equals("temperature")) {

                                    html.append("<table> <tr>");
                                    html.append("<th>Rank By Similarity</th>");
                                    html.append("<th>State</th>");
                                    html.append("<th>Start Year</th>");
                                    html.append("<th>End Year</th>");
                                    html.append("<th>Change in Data</th>");
                                    html.append("<th>Start Year Temperature</th>");
                                    html.append("<th>End Year Temperature</th>");
                                    html.append("</tr>");

                                    ArrayList<Stat> avgTempAbsoluteValuesforstate = getAvgTempAbsoluteValue(minData, maxData);
                                    sort(avgTempAbsoluteValuesforstate);
                                    avgTempAbsoluteValuesforstate = getNearByCountries(avgTempAbsoluteValuesforstate,
                                            countryParameterFromURL, number);
                                    if (fromDate != 0 && duration != 0 && countryParameterFromURL != null) {
                                        int i = 1;
                                        for (Stat d : avgTempAbsoluteValuesforstate) {
                                            html.append("<tr>");
                                            html.append("<td>").append(i).append("</td>");
                                            html.append("<td>").append(d.getName()).append("</td>");
                                            html.append("<td>").append(fromDate).append("</td>");
                                            html.append("<td>").append(toDate).append("</td>");
                                            html.append("<td>").append(d.getProportion()).append("</td>");
                                            html.append("<td>").append(d.getStartData()).append("</td>");
                                            html.append("<td>").append(d.getEndData()).append("</td>");

                                            html.append("</tr>");
                                            i++;
                                        }
                                        html.append("</table>");
                                    }

                                }
                            }
                        }
                    }
                }
            }
        }

        // Close Content div
        html.append("</div>");

        // Footer
        html.append("""
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

    public ArrayList<Stat> getAvgTempProportionalValues(ArrayList<PopulationAndTemp> min,
                                                        ArrayList<PopulationAndTemp> max) {
        ArrayList<Stat> stats = new ArrayList<Stat>();
        for (PopulationAndTemp m : min) {
            for (PopulationAndTemp x : max) {
                if (m.getCountryId().equals(x.getCountryId())) {
                    stats.add(new Stat(x.getCountryId(), x.getName(), ((m.getAvgTemp() - x.getAvgTemp()) / m.getAvgTemp()) * 100,
                            m.getAvgTemp(), x.getAvgTemp()));
                }
            }
        }
        return stats;
    }

    public ArrayList<Stat> getAvgTempAbsoluteValue(ArrayList<PopulationAndTemp> min, ArrayList<PopulationAndTemp> max) {
        ArrayList<Stat> stats = new ArrayList<Stat>();
        for (PopulationAndTemp m : min) {
            for (PopulationAndTemp x : max) {
                if (m.getCountryId().equals(x.getCountryId())) {
                    stats.add(new Stat(x.getCountryId(), x.getName(), ((abs(m.getAvgTemp() - x.getAvgTemp()))), m.getAvgTemp(),
                            x.getAvgTemp()));
                }
            }
        }
        return stats;
    }

    public ArrayList<Stat> getPopulationProportionalValues(ArrayList<PopulationAndTemp> min,
                                                           ArrayList<PopulationAndTemp> max) {
        ArrayList<Stat> stats = new ArrayList<Stat>();
        for (PopulationAndTemp m : min) {
            for (PopulationAndTemp x : max) {
                if (m.getCountryId().equals(x.getCountryId())) {
                    stats.add(new Stat(x.getCountryId(), x.getName(),
                            ((m.getPopulation() - x.getPopulation()) / m.getPopulation()) * 100, m.getPopulation(),
                            x.getPopulation()));
                }
            }
        }
        return stats;
    }

    public ArrayList<Stat> getPopulationAbsoluteValues(ArrayList<PopulationAndTemp> min,
                                                       ArrayList<PopulationAndTemp> max) {
        ArrayList<Stat> stats = new ArrayList<Stat>();
        for (PopulationAndTemp m : min) {
            for (PopulationAndTemp x : max) {
                if (m.getCountryId().equals(x.getCountryId())) {
                    stats.add(new Stat(x.getCountryId(), x.getName(), (abs(m.getPopulation() - x.getPopulation())),
                            m.getPopulation(), x.getPopulation()));
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
        ArrayList<Stat> nearStats = new ArrayList<>();
        Stat country = stat.get(index);
        nearStats.add(country);
        float above;
        float below;
        for (int i = 1; i < limit + 1; i++) {
            if (index + i < stat.size() && index - i > 0) {
                above = country.getProportion() - stat.get(index + i).getProportion();
                below = country.getProportion() - stat.get(index - i).getProportion();
                if (abs(above) > abs(below)) {
                    nearStats.add(stat.get(index + i));
                } else {
                    nearStats.add(stat.get(index - i));
                }
            } else if (index + i < stat.size()) {
                nearStats.add(stat.get(index + i));
            } else if (index - i > 0) {
                nearStats.add(stat.get(index - i));
            }
        }
        return nearStats;
    }

}