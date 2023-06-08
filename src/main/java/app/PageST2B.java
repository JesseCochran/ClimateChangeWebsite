package app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import io.javalin.http.Context;
import io.javalin.http.Handler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static app.JDBCConnection.getTempByState;

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

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        String html = "<html>";

        // Add some Head information
        html = html + "<head>" +
                "<title>Subtask 2.1</title>";

        // Add some CSS (external file)
        // html = html + "<link rel='stylesheet' type='text/css' href='common.css' />";
        html = html + "<link rel='stylesheet' type='text/css' href='JesseTesting2c.css' />";
        html = html + "</head>";

        // Add the body
        // Create a simple HTML webpage in a String

        // Add some Head information
        html = html + "<head>" +
                "<title>Subtask 2.1</title>";

        // Add some CSS (external file)
        html = html + "<link rel='stylesheet' type='text/css' href='JesseTesting2c.css' />";
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

        // Add Div for page Content
        html = html + "<div class='content'>";

        html = html + """
            <h2>Focused view of temperature by Cities or States</h2>
            """;

        //declaring variables

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

        html +=
         """
                    <div class='header'>
                        <h3> Temperatures by States or Cities </h3>
                    </div>
         """;

        // brief description of info on page
        html +=
         """
            <p> As the yearly maximum, minimum and average temperatures for cities and states across the wold
            have been documented. The following table generator provides a means to explore and compare this data
            for a range of years at the users discretion.  </p>
                    
         """;

         // hashmap to get country names for dropdown menu 

        HashMap<String, String> mapOfCountries = JDBCConnection.getCountryNames();
        html += "<form id='form-id'>";
        
        html = html += "<label for>Select County:</label>";

                
        html +=  """       
             <select name="country" onchange='document.getElementById("form-id").submit();'>
        """;
        html +=  """
            <option value="" selected disabled hidden>Country</option>        
   """;
        html = html + "</script>";

        for (Map.Entry<String, String> entry : mapOfCountries.entrySet()) {
           
            String key = entry.getKey();
            String value = entry.getValue();
            if (countryParameterFromURL != null) {
            if (countryParameterFromURL.equals(key)) {
        
    // part of code that makes the selected option stay after the page is refereshed
       
        html += "<option selected='selected' value='";
        html += key + "'>" + value + "</option>";
        }
        
        else {
        html += 
        "<option value='";
        html += key + "'>" + value + "</option>";   
                }  
        }
        else {  html += "<option value='";
                html += key + "'>" + value + "</option>";
            }
        }

        // dropdown menu for cites or states

        html += 
        "</select>";
        html = html += "<br> <label for>Select Cities or States:</label>";
        html +="""
            <select name='type'>
            <option value="" selected disabled hidden>City/State</option>
        """;
        
        if (countryParameterFromURL != null){
            if (JDBCConnection.hasStates(countryParameterFromURL)){
                html += 
                """
                        <option value='States'>States</option> 
                """;}
                
                if (JDBCConnection.hasCities(countryParameterFromURL)){
                    html += 
                    """
                            <option value='Cities'>Cities</option> 
                    """;}
                }
                
                html += "</select>";
        html += 
        "</select>";
        html = html += "<br> <label for>Select Start Year:</label>";
        html += 
        "<select name='from'>";
        html +=  """
                 <option value="" selected disabled hidden>Year</option>        
        """;

        // dropdown menues for start and end dates

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
        html = html += "<br> <label for>Select End Year:</label>";
        html += "<select name='to'>";
        html += """
            <option value="" selected disabled hidden>Year</option>        
          """;

        for (int i = 1749; i < 2014; i++) {
            html += "<option>" + i + "</option>";
            if (toDate == i) {
                html += "<option selected ='selected' value='";
                html += i + "'>" + i + "</option>";
                 }
                 else {
        
                html += "<option value='";
                html += i + "'>" + i + "</option>";
                 }
        }
        
        html += "</select>";

        html +=  "<div>";
        html += "<button class='showTable' type='submit' class='btn btn-primary'>Show Table</button>";
        html += "</div>";
        html += "</form>";


        html +=  "<div>";
        html = html + "<br> <button class='reset' type='reset' >Reset</button>";
        html += "</form>";
        html += "</div>";


        html = html + "<div class='content'>";
        if (countryParameterFromURL != null) { 
            ArrayList<TempData> data;

            html +=
                    "<table><tr> <th> Year </th> ";

            if (type != null){
            if (type.equals("states")) {
            
             data = JDBCConnection.getTempByState(countryParameterFromURL, fromDate, toDate);
             

                html += "<th> State</th>";

                
            } else {
             data = JDBCConnection.getTempByCity(countryParameterFromURL, fromDate, toDate);

                html += "<th> City</th>";
                       
            
            }
         

            html +=
                    "<th> Average Temperature </th> ";
            html += "<th> Minimum Temperature </th> <th> Maximum Temperature </th> </tr>";

            for (TempData d : data) {
                html += "<tr><td>" + d.getYear() + "</td>";
                html += "<td>" + d.getName() + "</td>";
                html += "<td>" + d.getAvgTemp() + "</td>";
                html += "<td>" + d.getMinTemp() + "</td>";
                html += "<td>" + d.getMaxTemp() + "</td></tr>";
            }
            html += "<table>";
//                     " <td> " + Years + "</td>";
        }

        }

    
        // Close Content div
        html = html + "</div>";

        // Footer
        html = html

                + """
                    <div class='footer'>
                        <p style='display: flex; gap: 10px;'><a 
                  href='PageHelp.html#help-section'> Help </a><a   
                        href='PageHelp.html#faq-section'> FAQ </a><a 
                        href='PageHelp.html#advanced-section'> Advanced Features </a></p>
                    </div>
                """;

        // Finish the HTML webpage
        html = html + "</body>" + "</html>";


        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(html);

    }

    }
